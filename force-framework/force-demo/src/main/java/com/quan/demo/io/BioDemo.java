package com.quan.demo.io;

/**
 * @author Force-oneself
 * @description BioDemo
 * @date 2022-03-25
 */
public class BioDemo {

    public static void main(String[] args) {
        BioServer server = new BioServer();
        server.start();
//        try {
//            Socket client = new Socket(InetAddress.getLocalHost(), server.getPort());
//            BufferedReader bufferReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
//            System.out.println(bufferReader.readLine());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
