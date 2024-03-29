package com.quan.demo.remote;

import java.lang.reflect.Method;

/**
 * @Description: JavaClass执行工具
 * @Author heyq
 * @Date 2021-01-30
 **/
public class JavaClassExecutor {
    /**
     * 执行外部传过来的代表一个Java类的byte数组
     * 将输入类的byte数组中代表java.lang.System的CONSTANT_Utf8_info常量修改为劫持后个HackSystem
     * 执行方法为该类的static main(String[] args)方法,输出结果为该类向System.out/err输出的信息
     *
     * @param classByte 代表一个Java类的byte数组
     * @return 执行结果
     */
    public static String execute(byte[] classByte) {
        HackSystem.clearBuffer();
        ClassModifier cm = new ClassModifier(classByte);
        byte[] modiBytes = cm.modifyUTF8Constant("java/lang/System", "org/fenixsoft/classloading/execute/HackSystem");
        HotSwapClassLoader loader = new HotSwapClassLoader();
        Class<?> clazz = loader.loadByte(modiBytes);
        try {
            Method method = clazz.getMethod("main", String[].class);
            method.invoke(null, (Object) new String[]{});
        } catch (Throwable e) {
            e.printStackTrace(HackSystem.out);
        }
        return HackSystem.getBufferString();
    }
}
