package com.quan.netty.demo;

import com.quan.netty.util.NettyUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;

/**
 * @author Force-oneself
 * @description SliceDemo
 * @date 2022-04-01
 */
public class ByteBufDemo {

    public static void main(String[] args) {
        composite();
    }

    private static void composite() {
        ByteBuf buf1 = ByteBufAllocator.DEFAULT.buffer();
        buf1.writeBytes(new byte[]{1, 2, 3, 4, 5});
        ByteBuf buf2 = ByteBufAllocator.DEFAULT.buffer();
        buf2.writeBytes(new byte[]{10, 11, 12, 13, 14});

        CompositeByteBuf compositeBuffer = ByteBufAllocator.DEFAULT.compositeBuffer();
        compositeBuffer.addComponents(true, buf1, buf2);
        NettyUtils.log(compositeBuffer);
    }

    /**
     * 零拷贝
     */
    private static void slice() {
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(10);
        buf.writeBytes(new byte[]{'a', 'b', 'd', 'e', 'f', 'g', 'h', 'i', 'j'});
        NettyUtils.log(buf);

        ByteBuf s1 = buf.slice(0, 5);
        ByteBuf s2 = buf.slice(5, 5);

        NettyUtils.log(s1);
        NettyUtils.log(s2);
    }

}
