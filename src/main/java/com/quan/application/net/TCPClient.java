package com.quan.application.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 18888);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("HelloWorld!".getBytes());
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int read = inputStream.read(bytes);

        String s = new String(bytes, 0, read);
        System.out.println(s);
        socket.close();

    }
}
