package com.ctl.springboottest;

import com.alibaba.dubbo.registry.Registry;
import com.alibaba.dubbo.registry.dubbo.DubboRegistryFactory;
import com.alibaba.dubbo.remoting.exchange.ExchangeServer;
import com.alibaba.dubbo.rpc.Exporter;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.protocol.dubbo.DubboProtocol;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Collection;

/**
 * <p>Title: DubboUtil</p>
 * <p>Description: 释放dubbo占用端口</p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-04-25 18:05
 */
public class DubboUtil implements ServletContextListener {
    Logger logger = LoggerFactory.getLogger(DubboUtil.class);

    public void contextInitialized(ServletContextEvent sce) {
        logger.info("初始化");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("销毁dubbo实例中....");
        DubboRegistryFactory.destroyAll();
        DubboProtocol.getDubboProtocol().destroy();
        logger.info("销毁dubbo服务完成！");
    }

    public void init(){
        try {
            int defaultPort = DubboProtocol.getDubboProtocol().getDefaultPort();
            Collection<ExchangeServer> servers = DubboProtocol.getDubboProtocol().getServers();
            Collection<Exporter<?>> exporters = DubboProtocol.getDubboProtocol().getExporters();
            Collection<Invoker<?>> invokers = DubboProtocol.getDubboProtocol().getInvokers();
            Collection<Registry> registries = DubboRegistryFactory.getRegistries();

            logger.info("dubbo服务启动端口：{},servers={},exporters={},invokers={}", defaultPort, JSONObject.toJSONString(servers),
                    JSONObject.toJSONString(exporters), JSONObject.toJSONString(invokers), JSONObject.toJSONString(registries));
        } catch (Exception ex) {
            logger.error("销毁dubbo失败", ex);
        }

    }
    public void destroy() {
        try {
            DubboProtocol.getDubboProtocol().destroy();
            logger.info("销毁dubbo成功");
        } catch (Exception ex) {
            logger.error("销毁dubbo失败", ex);
        }
    }
}
