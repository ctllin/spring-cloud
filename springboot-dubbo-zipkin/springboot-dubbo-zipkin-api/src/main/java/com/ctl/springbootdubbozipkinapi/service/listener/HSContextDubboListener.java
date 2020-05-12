package com.ctl.springbootdubbozipkinapi.service.listener;

import com.alibaba.dubbo.registry.Registry;
import com.alibaba.dubbo.registry.dubbo.DubboRegistryFactory;
import com.alibaba.dubbo.remoting.exchange.ExchangeServer;
import com.alibaba.dubbo.rpc.Exporter;
import com.alibaba.dubbo.rpc.protocol.dubbo.DubboProtocol;
import com.alibaba.fastjson.JSON;
import com.ctl.springbootdubbozipkinapi.service.util.DubboUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Collection;

/**
 * <p>Title: DubboUtil</p>
 * <p>Description: deploy获取dubbo服务信息,undeploy释放dubbo占用端口</p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-04-25 18:05
 */
@WebListener
public class HSContextDubboListener implements ServletContextListener {
    Logger logger = LoggerFactory.getLogger(HSContextDubboListener.class);
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Collection<ExchangeServer> servers = DubboProtocol.getDubboProtocol().getServers();
            Collection<Exporter<?>> exporters = DubboProtocol.getDubboProtocol().getExporters();
            Collection<Registry> registries = DubboRegistryFactory.getRegistries();
            servers.parallelStream().forEach(exchangeServer -> {
                logger.info("dubbo service wise_base_sensor_impl start ,servers.getLocalAddress={}", JSON.toJSONString(exchangeServer.getLocalAddress()));
            });
            exporters.stream().forEach(exporter -> {
                logger.info("dubbo service wise_base_sensor_impl,interface info：servers.getUrl={}",JSON.toJSONString(exporter.getInvoker().getUrl()));
            });
            logger.info("dubbo service wise_base_sensor_impl start,registries={}", registries);
        } catch (Exception ex) {
            logger.error("dubbo service start fail,or not dubbo service", ex);
        }
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            System.out.println("destory dubbo starting....");
            DubboUtil.destory();
            System.out.println("destory dubbo servcie done！");
        } catch (Exception ex) {
            System.out.println("destory dubbo fail,or not dubbo service");
        }
    }
}
