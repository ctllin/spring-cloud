package com.ctl.springbootdubbozipkinapi.config;

import brave.Tracing;
import brave.propagation.B3Propagation;
import brave.propagation.ExtraFieldPropagation;
import brave.sampler.Sampler;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.ErrorPageFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zipkin2.Span;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Sender;
import zipkin2.reporter.okhttp3.OkHttpSender;
import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.EmptySpanCollectorMetricsHandler;
import com.github.kristofa.brave.SpanCollector;
import com.github.kristofa.brave.http.DefaultSpanNameProvider;
import static com.github.kristofa.brave.Brave.Builder;
import static com.github.kristofa.brave.http.HttpSpanCollector.create;

import com.github.kristofa.brave.http.HttpSpanCollector;
import com.github.kristofa.brave.okhttp.BraveOkHttpRequestResponseInterceptor;
import com.github.kristofa.brave.servlet.BraveServletFilter;
import okhttp3.OkHttpClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.ErrorPageFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class ZipkinConfiguration {

    @Autowired
    private ZipkinProperties properties;

    @Bean
    public Tracing tracing(){

        Sender sender = OkHttpSender.create(properties.getUrl());

        AsyncReporter<Span> reporter = AsyncReporter.builder(sender)
                .closeTimeout(properties.getConnectTimeout(), TimeUnit.MILLISECONDS)
                .messageTimeout(properties.getReadTimeout(), TimeUnit.MILLISECONDS)
                .build();

        Tracing tracing = Tracing.newBuilder()
                .localServiceName(properties.getServiceName())
                .propagationFactory(ExtraFieldPropagation.newFactory(B3Propagation.FACTORY, "shiliew"))
                .sampler(Sampler.ALWAYS_SAMPLE)
                .spanReporter(reporter)
                .build();
        return tracing;
    }




    /**
     * @Description: span（一次请求信息或者一次链路调用）信息收集器
     * @Param:
     * @return: SpanCollector 控制器
     * @Author: zhihua
     * @Date: 2019-07-11 11:41
     */
    @Bean
    public SpanCollector spanCollector() {
//        zipkin.serviceName=dubbo-hystrix
//        zipkin.url=http://192.168.2.110:9411
//        zipkin.connectTimeout=6000
//        zipkin.readTimeout=6000
//        zipkin.flushInterval=1
//        zipkin.compressionEnabled=true


        HttpSpanCollector.Config config = HttpSpanCollector.Config.builder()
                // 默认false，span在transport之前是否会被gzipped
                .compressionEnabled(true)
                .connectTimeout(6000)
                .flushInterval(1)
                .readTimeout(6000)
                .build();
        return create(properties.getUrl(), config, new EmptySpanCollectorMetricsHandler());
    }

    /**
     * @Description: 作为各调用链路，只需要负责将指定格式的数据发送给zipkin
     * @Param:
     * @return:
     * @Author: r
     * @Date: 2019-07-11 11:41
     */
    @Bean
    public Brave brave(SpanCollector spanCollector) {
        //调用服务的名称
        Builder builder = new Builder("ctl_serviceName");
        builder.spanCollector(spanCollector);
        //采集率
        builder.traceSampler(com.github.kristofa.brave.Sampler.ALWAYS_SAMPLE);
        return builder.build();
    }


    /**
     * @Description: 设置server的（服务端收到请求和服务端完成处理，并将结果发送给客户端）过滤器
     * @Param:
     * @return: 过滤器
     * @Author: zhihua
     * @Date: 2019-07-11 11:41
     */
    @Bean
    public BraveServletFilter braveServletFilter(Brave brave) {
        BraveServletFilter filter = new BraveServletFilter(brave.serverRequestInterceptor(),
                brave.serverResponseInterceptor(), new DefaultSpanNameProvider());
        return filter;
    }

    /**
     * @Description: 设置client的（发起请求和获取到服务端返回信息）拦截器
     * @Param:
     * @return: OkHttpClient 返回请求实例
     * @Author: zhihua
     * @Date: 2019-07-11 11:41
     */
    @Bean
    public OkHttpClient okHttpClient(Brave brave) {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new BraveOkHttpRequestResponseInterceptor(
                        brave.clientRequestInterceptor(),
                        brave.clientResponseInterceptor(),
                        new DefaultSpanNameProvider())).build();
        return httpClient;
    }
    @Bean
    public ErrorPageFilter errorPageFilter() {
        return new ErrorPageFilter();
    }
    @Bean
    public FilterRegistrationBean disableSpringBootErrorFilter(ErrorPageFilter filter) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.setEnabled(false);
        return filterRegistrationBean;
    }

}
