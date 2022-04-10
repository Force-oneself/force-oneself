package com.quan.netty.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.util.internal.StringUtil;

/**
 * @author Force-oneself
 * @description NettyUtils
 * @date 2022-04-10
 */
public class NettyUtils {

    public static void log(ByteBuf buf) {
        int len = buf.readableBytes();
        int rows = len / 16 + (len % 15 == 0 ? 0 : 1) + 4;
        StringBuilder builder = new StringBuilder(rows * 80 * 2)
                .append(" read index: ").append(buf.readerIndex())
                .append(" write index: ").append(buf.writerIndex())
                .append(" capacity: ").append(buf.capacity())
                .append(StringUtil.NEWLINE);
        ByteBufUtil.appendPrettyHexDump(builder, buf);
        System.out.println(builder);
    }

}
