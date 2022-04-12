package com.quan.netty.protocol;

import io.netty.buffer.ByteBuf;

/**
 * @author Force-oneself
 * @description MarshallingDecoder
 * @date 2022-04-11
 */
public class MarshallingDecoder {

    protected Object decode(ByteBuf in) throws  Exception {
        int objectSize = in.readInt();
        ByteBuf buf = in.slice(in.readerIndex(), objectSize);
        return null;
    }
}
