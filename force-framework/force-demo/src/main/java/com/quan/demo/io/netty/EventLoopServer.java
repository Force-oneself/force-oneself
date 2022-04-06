package com.quan.demo.io.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.DefaultEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.charset.Charset;

/**
 * @author Force-oneself
 * @description EventLoopServer
 * @date 2022-03-30
 */
public class EventLoopServer {

    public static void main(String[] args) {
        // 创建独立的DefaultEventLoop处理耗时任务
        DefaultEventLoop eventLoop = new DefaultEventLoop();

        new ServerBootstrap()
                // boss负责accept事件，worker负责socketChannel上的读写
                .group(new NioEventLoopGroup(), new NioEventLoopGroup(2))
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline()
                                .addLast("handler-1", new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        ByteBuf buffer = (ByteBuf) msg;
                                        System.out.println(buffer.toString(Charset.defaultCharset()));
                                        // 将消息传递到下一个handler
                                        ctx.fireChannelRead(msg);
                                    }
                                })
                                .addLast(eventLoop, "handler-2", new ChannelInboundHandlerAdapter() {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        ByteBuf buffer = (ByteBuf) msg;
                                        System.out.println(buffer.toString(Charset.defaultCharset()));
                                    }
                                });


                    }
                })
                .bind(8080);
    }
}
