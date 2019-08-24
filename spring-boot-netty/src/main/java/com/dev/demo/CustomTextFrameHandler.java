package com.dev.demo;

/**
 * <p>Title: CustomTextFrameHandler</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-08-23 15:38
 */

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomTextFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private static Logger logger = LoggerFactory.getLogger(CustomTextFrameHandler.class);

    private static ChannelGroup recipients = new DefaultChannelGroup("ChannelGroups", GlobalEventExecutor.INSTANCE);
    public CustomTextFrameHandler() {

    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx,TextWebSocketFrame frame) throws Exception {
        String request = frame.text();
        logger.info("size:{},ctx={},request={}",recipients.size(),ctx.name(),request);
        recipients.writeAndFlush(new TextWebSocketFrame(request.toUpperCase()));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        recipients.add(ctx.channel());
        logger.info("connect:{},ctx={}",recipients.size(),ctx.name());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        try {
            recipients.remove(ctx.channel());
            logger.info("删除channel成功:{},ctx={}",recipients.size(),ctx.name());
        } catch (Exception ex) {
            logger.info("删除channel失败:{}",recipients.size(),ex);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
