package com.quan.application.net.upload;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    /**
     * 实现步骤:
     * 1.创建一个服务器ServerSocket对象,和系统要指定的端口号
     * 2.使用ServerSocket对象中的方法accept,获取到请求的客户端Socket对象
     * 3.使用Socket对象中的方法getInputStream,获取到网络字节输入流InputStream对象
     * 4.判断d:\\upload文件夹是否存在,不存在则创建
     * 5.创建一个本地字节输出流FileOutputStream对象,构造方法中绑定要输出的目的地
     * 6.使用网络字节输入流InputStream对象中的方法read,读取客户端上传的文件
     * 7.使用本地字节输出流FileOutputStream对象中的方法write,把读取到的文件保存到服务器的硬盘上
     * 8.使用Socket对象中的方法getOutputStream,获取到网络字节输出流OutputStream对象
     * 9.使用网络字节输出流OutputStream对象中的方法write,给客户端回写"上传成功"
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8888);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();

        File file = new File("D:\\EventBusTest");
        if (!file.exists()) {
            file.mkdir();
        }

        FileOutputStream fileOutputStream = new FileOutputStream(file + "\\girl-cp.jpg");
        int len = 0;
        byte[] bytes = new byte[8192];
        while ((len = inputStream.read(bytes)) != -1) {
            fileOutputStream.write(len);
        }
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("上传成功".getBytes());
        fileOutputStream.close();
        socket.close();
        serverSocket.close();
    }
}
