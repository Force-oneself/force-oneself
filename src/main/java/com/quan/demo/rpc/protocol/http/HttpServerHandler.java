package com.quan.demo.rpc.protocol.http;

import com.quan.demo.rpc.framework.Invocation;
import com.quan.demo.rpc.framework.URL;
import com.quan.demo.rpc.register.Register;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;

/**
 * Created by Yuk on 2018/12/31.
 */
@SuppressWarnings("unchecked")
public class HttpServerHandler {

    public void handler(HttpServletRequest req, HttpServletResponse resp){

        try{
            // Http请求流转为对象
            InputStream is = req.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            Invocation invocation = (Invocation)ois.readObject();

            // 寻找实现类，通过反射执行
            Class implClass = Register.get(new URL("localhost",8080),invocation.getInterfaceName());
            Method method = implClass.getMethod(invocation.getMethodName(),invocation.getParamTypes());
            // 执行结果
            String result = (String) method.invoke(implClass.newInstance(),invocation.getParams());
            System.out.println(result);

            // 将结果返回
            IOUtils.write(result,resp.getOutputStream());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
