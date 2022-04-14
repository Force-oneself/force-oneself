package com.quan.netty.marshalling;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author Force-oneself
 * @description MarshallingServerHandler
 * @date 2022-04-14
 */
public class MarshallingServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        MarshallingReq req = (MarshallingReq) msg;
        System.out.println("服务端收到消息体："+req.toString());
        if("gholly".equalsIgnoreCase(req.getUserName())){
            ctx.writeAndFlush(subResp(req.getSubReqID()));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        ctx.close();
    }


    private MarshallingResp subResp(int i){
        MarshallingResp resp =new MarshallingResp();
        resp.setSubReqID(i);
        resp.setRespCode(0);
        resp.setDesc("I love China");
        return resp;
    }


}
