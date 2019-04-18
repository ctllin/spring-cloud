package com.ctl.springboottest.filter;

import com.alibaba.dubbo.rpc.*;
import com.ctl.springboottest.constants.DubboLogConstants;
import org.slf4j.MDC;

import java.util.Map;

/**
 * com.ctl.springboottest.filter
 * d
 * ctl 2019/4/18 21:44
 */
public class TraceFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        //从MDC中获取
        String logId = MDC.get(DubboLogConstants.TRACE_LOG_ID);
        Map<String, String> attachments = invocation.getAttachments();
        attachments.put(DubboLogConstants.TRACE_LOG_ID, logId);
        return invoker.invoke(invocation);
    }

}