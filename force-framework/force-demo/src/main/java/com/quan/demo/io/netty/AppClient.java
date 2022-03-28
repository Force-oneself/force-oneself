package com.quan.demo.io.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * @author Force-oneself
 * @description AppClient
 * @date 2022-03-27
 */
public class AppClient {

    private final String host;
    private final int port;

    // 右键选择generater 创建构造函数
    //或者 alt + insert建
    //实例化这个类的时候，需要传这两个参数进来

    public AppClient(String host, int port) {
        this.host = host;
        this.port = port;
    }


    public void run() throws Exception {

        // IO的线程池
        EventLoopGroup group = new EpollEventLoopGroup();
        // CTRL +ALT +T 快速输入
        try {
            // 客户端辅助启动类
            Bootstrap bs = new Bootstrap();
            bs.group(group)
                    // 实例化一个channel
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    // 进行通道初始化配置
                    .handler((new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // 内部配置handler
                            // pipeline 管道，可以看作是handler的容器
                            // 添加自定义的handler
                            socketChannel.pipeline().addLast(new ClientHandler());
                        }
                    }));


            // 配置完成后，链接到远端
            // 连接到远程节点，等待连接完成
            ChannelFuture future = bs.connect().sync();

            // 发送消息到服务器，格式是utf8
            future.channel().writeAndFlush(Unpooled.copiedBuffer("hello world", CharsetUtil.UTF_8));

            // 阻塞操作,closeFuture()开启了一个channel的监听器（这期间channel在进行各项工作，知道连接断开）
            future.channel().closeFuture().sync();


        } finally {
            // 最后要释放资源，释放线程池后，彻底关闭，防止内存泄漏
            // 优雅的关闭线程池
            group.shutdownGracefully().sync();
        }

    }

    public static void main(String[] args) throws Exception {
        new AppClient("127.0.0.1", 1357).run();
    }
}
