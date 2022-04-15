package com.quan.netty.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author Force-oneself
 * @description HttpDemo Http协议演示
 * @date 2022-04-15
 */
public class HttpDemo {

    public static void main(String[] args) {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        NioEventLoopGroup boss = new NioEventLoopGroup();
        try {
            new ServerBootstrap()
                    .group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    // work负责读写
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        // 连接建立触发
                        @Override
                        protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                            // 添加具体的事件
                            nioSocketChannel.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                            nioSocketChannel.pipeline().addLast(new HttpServerCodec());
                            // 针对特定类型消息进行处理
                            nioSocketChannel.pipeline().addLast(new SimpleChannelInboundHandler<HttpRequest>() {
                                @Override
                                protected void channelRead0(ChannelHandlerContext ctx, HttpRequest msg) throws Exception {
                                    System.out.println(msg.uri());
                                    DefaultFullHttpResponse response = new DefaultFullHttpResponse(msg.protocolVersion(), HttpResponseStatus.OK);
                                    byte[] bytes = "<h1>Hello, world!</h1>".getBytes();
                                    response.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, bytes.length);
                                    response.content().writeBytes(bytes);
                                    ctx.writeAndFlush(response);
                                }
                            });
//                            nioSocketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
//                                @Override
//                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//
//                                    // 请求行
//                                    if (msg instanceof HttpRequest) {
//
//                                        // 请求体
//                                    } else if (msg instanceof HttpContent) {
//
//                                    }
//
//                                }
//                            });
                        }
                    })
                    .bind(8080)
                    .sync()
                    .channel()
                    .closeFuture()
                    .sync();
        } catch (InterruptedException ignore) {

        } finally {
            worker.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }
}
