package com.quan.demo.rpc.protocol.http;

import com.quan.demo.rpc.framework.Invocation;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Yuk on 2018/12/31.
 */
public class HttpClient {

    /**
     * 远程方法调用
     * @param hostname
     * @param port
     * @param invocation
     * @return
     */
    public String post(String hostname, Integer port, Invocation invocation){
        try {
            // 进行http连接
            URL url = new URL("http",hostname,port,"/client/");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // 将对象写入输出流
            OutputStream os = connection.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(invocation);
            oos.flush();
            oos.close();

            // 将输入流转为字符串（此处可是java对象）
            InputStream is = connection.getInputStream();
            return IOUtils.toString(is);

        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
