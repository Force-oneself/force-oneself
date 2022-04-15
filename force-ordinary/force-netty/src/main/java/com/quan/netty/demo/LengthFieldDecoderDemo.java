package com.quan.netty.demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author Force-oneself
 * @description LengthFieldDecoderDemo
 * @date 2022-04-15
 */
public class LengthFieldDecoderDemo {

    public static void main(String[] args) {
        EmbeddedChannel channel = new EmbeddedChannel(
                new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 0),
                new LoggingHandler(LogLevel.DEBUG)
        );
        // 4个字节内容长度
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        send(buffer, "Hello, world");
        send(buffer, "He!");
        channel.writeInbound(buffer);
    }

    public static void send(ByteBuf buf, String msg) {
        // 实际内容
        byte[] bytes = msg.getBytes();
        // 内容长度
        int length = msg.length();
        buf.writeInt(length);
        buf.writeBytes(bytes);
    }
}
