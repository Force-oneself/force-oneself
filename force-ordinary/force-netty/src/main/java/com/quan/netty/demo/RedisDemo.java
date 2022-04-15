package com.quan.netty.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author Force-oneself
 * @description RedisDemo Redis协议演示
 * @date 2022-04-15
 */
public class RedisDemo {

    public static final byte[] LINE = {13, 10};

    public static void main(String[] args) {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            new Bootstrap()
                    .group(worker)
                    .channel(NioSocketChannel.class)
                    // work负责读写
                    .handler(new ChannelInitializer<NioSocketChannel>() {
                        // 连接建立触发
                        @Override
                        protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                            // 添加具体的事件
                            nioSocketChannel.pipeline().addLast(new LoggingHandler());
                            nioSocketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    ByteBuf buffer = ctx.alloc().buffer();
                                    buffer.writeBytes("*3".getBytes(StandardCharsets.UTF_8));
                                    buffer.writeBytes(LINE);
                                    buffer.writeBytes("$3".getBytes(StandardCharsets.UTF_8));
                                    buffer.writeBytes(LINE);
                                    buffer.writeBytes("set".getBytes(StandardCharsets.UTF_8));
                                    buffer.writeBytes(LINE);
                                    buffer.writeBytes("$4".getBytes(StandardCharsets.UTF_8));
                                    buffer.writeBytes(LINE);
                                    buffer.writeBytes("name".getBytes(StandardCharsets.UTF_8));
                                    buffer.writeBytes(LINE);
                                    buffer.writeBytes("$8".getBytes(StandardCharsets.UTF_8));
                                    buffer.writeBytes(LINE);
                                    buffer.writeBytes("zhangsan".getBytes(StandardCharsets.UTF_8));
                                    buffer.writeBytes(LINE);
                                    ctx.writeAndFlush(buffer);
                                }

                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    ByteBuf buf = (ByteBuf) msg;
                                    System.out.println(buf.toString(Charset.defaultCharset()));
                                }
                            });

                        }
                    })
                    .connect("localhost", 6379)
                    .sync()
                    .channel()
                    .closeFuture()
                    .sync();
        } catch (InterruptedException ignore) {

        } finally {
            worker.shutdownGracefully();
        }
    }
}
