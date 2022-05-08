package com.quan.demo.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author Force-oneself
 * @description BioServer
 * @date 2022-03-25
 */
public class BioServer extends Thread {

    private ServerSocket serverSocket;

    public int getPort() {
        return serverSocket.getLocalPort();
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(8080);
            //使用多线程实现
            Executor executor = Executors.newFixedThreadPool(8);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("连接建立");
                RequestHandler requestHandler = new RequestHandler(socket);
                executor.execute(requestHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    /**
     * 1、服务端ServerSocket，启动绑定端口
     * 2、调用accept，阻塞等待客户端连接
     * 3、客户端连接后，启动一个单独线程负责处理客户端请求
     * <p>
     * 也可以使用多线程来处理连接不是很多的情况
     */
    static class RequestHandler extends Thread {
        private final Socket socket;

        public RequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {

                PrintWriter out = new PrintWriter(socket.getOutputStream());
                out.println("Hello World");
                out.flush();
                byte[] bytes = new byte[1024];
                InputStream inputStream = socket.getInputStream();
                while (true){
                    int read = inputStream.read(bytes);
                    if (read != -1){
                        System.out.println("读取客户端数据"+new String(bytes,0,read));
                    }else {
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}