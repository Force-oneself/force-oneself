package com.quan.demo.io.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

/**
 * @author Force-oneself
 * @description HelloClient
 * @date 2022-03-29
 */
public class EventLoopClient {

    public static void main(String[] args) throws InterruptedException {
        ChannelFuture channelFuture = new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new StringEncoder());
                    }
                })
                .connect(new InetSocketAddress("localhost", 8080))
                // 同步连接完成
//                .sync()
                .channel()
                .writeAndFlush("Hello, World");

        // 处理回调
        channelFuture.addListener((ChannelFutureListener) channelFuture1 -> {
            Channel channel = channelFuture.channel();
            channel.writeAndFlush("hello world");
        });
    }
}
