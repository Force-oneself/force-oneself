package com.quan.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(18888);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int read = inputStream.read(bytes);

        String s = new String(bytes, 0, read);
        System.out.println(s);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("Thanks!".getBytes());
        socket.close();
        serverSocket.close();

    }
}
