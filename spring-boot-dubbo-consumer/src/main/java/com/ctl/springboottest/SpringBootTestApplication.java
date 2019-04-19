package com.ctl.springboottest;

import com.alibaba.dubbo.rpc.*;
import com.ctl.springboottest.constants.DubboLogConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
@RestController
public class SpringBootTestApplication {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@RequestMapping("/")
	public void  index(){
		logger.trace("日志输出 trace");
		logger.debug("日志输出 debug");
		logger.info("日志输出 info");
		logger.warn("日志输出 warn");
		logger.error("日志输出 error");
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTestApplication.class, args);
	}
}
//curl http://localhost:20001/log/get5/1
//curl http://localhost:20001/log/get4/1
//curl http://localhost:20001/log/get3/1
//curl http://localhost:20001/log/get2/1
//curl http://localhost:20001/log/get/1
//curl http://localhost:20001/log/get/1
//curl http://localhost:20001/log/get2/1
//curl http://localhost:20001/log/get3/1
//curl http://localhost:20001/log/get4/1
//curl http://localhost:20001/log/get5/1



//dubbo链路追踪
//1.在controller调用处生成一个uuid然后放放进去
//		com.ctl.springboottest.controller.DubboLogController{
//			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
//			org.slf4j.MDC.put(DubboLogConstants.TRACE_LOG_ID, uuid);
//		}
//-------------------dubbo 客户端----------------
//2.在resource文件夹下建立文件夹并创建文件META-INF/dubbo/com.alibaba.dubbo.rpc.Filter
//   文件内容为consumerFilter=com.ctl.springboottest.filter.TraceFilter
//		import com.alibaba.dubbo.rpc.*;
//		import com.ctl.springboottest.constants.DubboLogConstants;
//		import org.slf4j.MDC;
//		import java.util.Map;
//		class TraceFilter implements Filter {
//			@Override
//			public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
//				//从MDC中获取
//				String logId = MDC.get(DubboLogConstants.TRACE_LOG_ID);
//				Map<String, String> attachments = invocation.getAttachments();
//				attachments.put(DubboLogConstants.TRACE_LOG_ID, logId);
//				return invoker.invoke(invocation);
//			}
//
//		}
//3.在dubbo-consumer.xml配置该filter
//	<dubbo:consumer filter="consumerFilter" />
//
//-------------------dubbo 服务端----------------
//4.在resource文件夹下建立文件夹并创建文件META-INF/dubbo/com.alibaba.dubbo.rpc.Filter
//	文件内容为consumerFilter=com.ctl.springboottest.filter.TraceFilter
//		import com.alibaba.dubbo.rpc.*;
//		import com.ctl.springboottest.constants.DubboLogConstants;
//		import org.slf4j.MDC;
//		public class TraceFilter implements Filter {
//			@Override
//			public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
//				String logId = invocation.getAttachment(DubboLogConstants.TRACE_LOG_ID);
//				MDC.put(DubboLogConstants.TRACE_LOG_ID, logId);
//				return invoker.invoke(invocation);
//			}
//		}
//
//5.在dubbo-consumer.xml配置该filter
//	<dubbo:provider filter="providerFilter" />
//
//6.创建DubboLogConstants(dubbo服务端,客户端可以共用)
//	public class DubboLogConstants {
//		public final static String TRACE_LOG_ID="TRACE_LOG_ID";
//	}
//7.logback.xml配置
//  <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} %contextName [%thread] %-5level [%X{TRACE_LOG_ID}] %logger{36} - %msg%n</pattern>
//  配置中的TRACE_LOG_ID即为DubboLogConstants配置的常量TRACE_LOG_ID的值


//http://localhost:10001/log/get/1
//看到客户端日志   1234e60804b041cda17585a688981e35
//2019-04-18 23:20:31.742 logback [http-nio-10001-exec-1] INFO  [1234e60804b041cda17585a688981e35] c.c.s.controller.DubboLogController - controller->param:1
//2019-04-18 23:20:31.909 logback [http-nio-10001-exec-1] DEBUG [1234e60804b041cda17585a688981e35] o.s.w.s.m.m.a.RequestResponseBodyMethodProcessor - Using 'application/json;q=0.8', given [text/html, application/xhtml+xml, image/webp, application/xml;q=0.9, */*;q=0.8] and supported [application/json, application/*+json, application/json, application/*+json]
//2019-04-18 23:20:31.909 logback [http-nio-10001-exec-1] DEBUG [1234e60804b041cda17585a688981e35] o.s.w.s.m.m.a.RequestResponseBodyMethodProcessor - Writing [{}]
//2019-04-18 23:20:31.928 logback [http-nio-10001-exec-1] DEBUG [1234e60804b041cda17585a688981e35] o.s.web.servlet.DispatcherServlet - Completed 200 OK
//看到服务端1日志  1234e60804b041cda17585a688981e35
//2019-04-18 23:20:31.814 logback [DubboServerHandler-192.168.42.1:20881-thread-2] INFO  c.c.s.s.i.DubboLogTraceIdServiceImpl 25 -- [1234e60804b041cda17585a688981e35] - 日志链路追踪测试1
//看到服务端2日志  1234e60804b041cda17585a688981e35
//2019-04-18 23:20:31.870 logback [DubboServerHandler-192.168.42.1:20880-thread-7] INFO  c.c.s.s.i.DubboLogTraceId2ServiceImpl 22 -- [1234e60804b041cda17585a688981e35] - 日志链路追踪2测试1
