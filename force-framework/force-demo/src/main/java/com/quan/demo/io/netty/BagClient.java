package com.quan.demo.io.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author Force-oneself
 * @description HelloClient
 * @date 2022-03-29
 */
public class BagClient {

    public void send() {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            new Bootstrap()
                    .group(worker)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                            nioSocketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    ByteBuf buffer = ctx.alloc().buffer(16);
                                    buffer.writeBytes(new byte[]{0,1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,16});
                                    ctx.writeAndFlush(buffer);
                                    ctx.channel().close();
                                }
                            });
                        }
                    })
                    .connect("127.0.0.1", 8080)
                    .sync()
                    .channel()
                    .closeFuture()
                    .sync();
        } catch (InterruptedException e) {
//            worker.shutdownGracefully()
        } finally {
            worker.shutdownGracefully();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        BagClient bagClient = new BagClient();
//        for (int i = 0; i < 10; i++) {
            bagClient.send();
//        }
        System.out.println("finish");
    }
}
