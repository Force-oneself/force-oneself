package com.quan.application.net.upload;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

    /**
     * 实现步骤:
     * 1.创建一个本地字节输入流FileInputStream对象,构造方法中绑定要读取的数据源
     * 2.创建一个客户端Socket对象,构造方法中绑定服务器的IP地址和端口号
     * 3.使用Socket中的方法getOutputStream,获取网络字节输出流OutputStream对象
     * 4.使用本地字节输入流FileInputStream对象中的方法read,读取本地文件
     * 5.使用网络字节输出流OutputStream对象中的方法write,把读取到的文件上传到服务器
     * 6.使用Socket中的方法getInputStream,获取网络字节输入流InputStream对象
     * 7.使用网络字节输入流InputStream对象中的方法read读取服务回写的数据
     * 8.释放资源(FileInputStream,Socket)
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("D:\\Picture\\Desktop\\girl.jpg");

        Socket socket = new Socket("127.0.0.1", 8888);

        OutputStream outputStream = socket.getOutputStream();
        int len = 0;
        byte[] bytes = new byte[8192];
        while ((len = fileInputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, len);
        }

        socket.shutdownOutput();

        InputStream inputStream = socket.getInputStream();
        while ((len = inputStream.read(bytes)) != -1) {
            System.out.println(new String(bytes, 0, len));
        }
        fileInputStream.close();
        socket.close();
    }
}
