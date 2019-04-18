package com.ctl.springboottest.filter;

import com.alibaba.dubbo.rpc.*;
import com.ctl.springboottest.constants.DubboLogConstants;
import org.slf4j.MDC;

/**
 * com.ctl.springboottest.filter
 * TraceFilter
 * ctl 2019/4/18 21:47
 */
public class TraceFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String logId = invocation.getAttachment(DubboLogConstants.TRACE_LOG_ID);
        MDC.put(DubboLogConstants.TRACE_LOG_ID, logId);
        return invoker.invoke(invocation);
    }
}
