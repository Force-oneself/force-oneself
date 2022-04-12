package com.quan.netty.protocol;

import io.netty.buffer.ByteBuf;

/**
 * @author Force-oneself
 * @description MarshallingEncoder
 * @date 2022-04-11
 */
public class MarshallingEncoder {

    private static final byte[] LENGTH_PLACEHOLDER = new byte[4];

    protected void encode(Object msg, ByteBuf out) throws Exception {

    }
}
