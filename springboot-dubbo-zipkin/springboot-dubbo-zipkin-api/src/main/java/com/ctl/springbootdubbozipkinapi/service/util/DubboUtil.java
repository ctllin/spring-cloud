package com.ctl.springbootdubbozipkinapi.service.util;

import com.alibaba.dubbo.registry.dubbo.DubboRegistryFactory;
import com.alibaba.dubbo.rpc.protocol.dubbo.DubboProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Title: DubboUtil</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-05-08 13:35
 */
public class DubboUtil {
    private static Logger logger = LoggerFactory.getLogger(DubboUtil.class);
    public static void destory() {
       try{
           logger.info("销毁dubbo实例中....");
           DubboRegistryFactory.destroyAll();
           DubboProtocol.getDubboProtocol().destroy();
           logger.info("销毁dubbo服务完成！");
       }catch (Exception e){
           logger.error("掉用destory失败",e);
       }
    }
}
