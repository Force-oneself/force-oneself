package com.quan.netty.http;

import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Pattern;

/**
 * @author Force-oneself
 * @description HttpFileServerHandler
 * @date 2022-04-10
 */
public class HttpFileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {


    public static final Pattern ALLOWED_FILE_NAME = Pattern.compile("[A-Za-z0-9][-_A-Za-z0-9\\.]*");

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        if (!request.getDecoderResult().isSuccess()) {
            sendError(ctx, "BAD_REQUEST");
            return;
        }
        if (request.method() != HttpMethod.GET) {
            sendError(ctx, "METHOD_NOT_ALLOWED");
            return;
        }
        String uri = request.uri();
        String path = sanitizeUri(uri);
        if (path == null) {
            sendError(ctx, "FORBIDDEN");
            return;
        }
        File file = new File(path);
        if (file.isHidden() || !file.exists()) {
            sendError(ctx, "NOT_FOUND");
            return;
        }

        if (!file.isFile()) {
            sendError(ctx, "NOT_FOUND");
            return;
        }
        RandomAccessFile randomAccessFile = null;
        try {
            // 只读方式打开文件
            randomAccessFile = new RandomAccessFile(file, "r");
        } catch (FileNotFoundException e) {
            sendError(ctx, "NOT_FOUND");
            return;
        }
        long fileLength = randomAccessFile.length();
        DefaultHttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        this.setContentLength(response, fileLength);
        this.setContentHeader(response, fileLength);
        if (isKeepAlive(request)) {
            response.headers().set("CONNECTION", HttpHeaders.Values.KEEP_ALIVE);
        }
        ctx.write(response);
        ChannelFuture channelFuture;
        channelFuture = ctx.write(new ChunkedFile(randomAccessFile, 0, fileLength, 8192), ctx.newProgressivePromise());
        channelFuture.addListener(new ChannelProgressiveFutureListener() {
            @Override
            public void operationProgressed(ChannelProgressiveFuture future, long progress, long total) throws Exception {

            }

            @Override
            public void operationComplete(ChannelProgressiveFuture future) throws Exception {

            }
        });


    }

    private boolean isKeepAlive(FullHttpRequest request) {
        return false;
    }

    private void setContentHeader(DefaultHttpResponse response, long fileLength) {

    }

    private void setContentLength(DefaultHttpResponse response, long fileLength) {

    }

    private String sanitizeUri(String uri) {
        try {
            uri = URLDecoder.decode(uri, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException();
        }
        uri = uri.replace('/', File.separatorChar);
        if (uri.contains(File.separator + '.')
                || uri.contains('.' + File.separator)
                || uri.startsWith(".")
                || uri.endsWith(".")) {
            return null;
        }
        return System.getProperty("user.dor") + File.separator + uri;
    }

    private void sendError(ChannelHandlerContext ctx, String msg) {

    }


}
