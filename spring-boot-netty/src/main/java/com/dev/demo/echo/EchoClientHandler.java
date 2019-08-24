package com.dev.demo.echo;

import com.sun.org.apache.xpath.internal.operations.String;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.Random;

/**
 * <p>Title: EchoClientHandler</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-08-24 15:50
 */
@ChannelHandler.Sharable                                //1
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
      while (true){
          try {
              Thread.sleep(100);
              ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", //2
                      CharsetUtil.UTF_8));
          } catch (Exception e) {
              e.printStackTrace();
          }
      }
    }

//    @Override
//    public void channelRead0(ChannelHandlerContext ctx,
//                             ByteBuf in) {
//        System.out.println("Client received: " + in.toString(CharsetUtil.UTF_8));    //3
//    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {                    //4
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * Is called for each message of type {@link I}.
     *
     * @param ctx the {@link ChannelHandlerContext} which this {@link SimpleChannelInboundHandler}
     *            belongs to
     * @param msg the message to handle
     * @throws Exception is thrown if an error occurred
     */
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println(msg);
    }
}