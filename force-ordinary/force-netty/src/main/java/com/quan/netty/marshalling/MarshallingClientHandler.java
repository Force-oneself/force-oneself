package com.quan.netty.marshalling;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author Force-oneself
 * @description MarshallingClientHandler
 * @date 2022-04-14
 */
public class MarshallingClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println(msg);
        MarshallingResp resp = (MarshallingResp) msg;
        System.out.println("客户端收到消息体为：" + resp.toString());
    }


    private MarshallingReq subReq(int i) {
        MarshallingReq req = new MarshallingReq();
        req.setAddress("Shenzhen");
        req.setPhoneNumber("110");
        req.setProductName("netty");
        req.setSubReqID(i);
        req.setUserName("gholly");
        return req;
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        for (int i = 0; i < 10; i++) {
            ctx.write(subReq(i));
        }
        ctx.flush();
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
        cause.printStackTrace();
    }
}
