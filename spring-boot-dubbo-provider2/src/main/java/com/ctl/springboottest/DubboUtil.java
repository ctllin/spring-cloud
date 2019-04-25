package com.ctl.springboottest;

import com.alibaba.dubbo.remoting.exchange.ExchangeServer;
import com.alibaba.dubbo.rpc.Exporter;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.protocol.dubbo.DubboProtocol;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * <p>Title: DubboUtil</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-04-25 18:05
 */
public class DubboUtil {
    Logger logger = LoggerFactory.getLogger(DubboUtil.class);
    public void init(){
        try {
            int defaultPort = DubboProtocol.getDubboProtocol().getDefaultPort();
            Collection<ExchangeServer> servers = DubboProtocol.getDubboProtocol().getServers();
            Collection<Exporter<?>> exporters = DubboProtocol.getDubboProtocol().getExporters();
            Collection<Invoker<?>> invokers = DubboProtocol.getDubboProtocol().getInvokers();
            logger.info("dubbo服务启动端口：{},servers={},exporters={},invokers={}", defaultPort, JSONObject.toJSONString(servers),
                    JSONObject.toJSONString(exporters), JSONObject.toJSONString(invokers));
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
