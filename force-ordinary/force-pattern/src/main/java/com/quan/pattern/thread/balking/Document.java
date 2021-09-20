package com.quan.pattern.thread.balking;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.currentThread;

/**
 * 在Document中有两个主要方法save和edit分别用于保存文档和编辑文档
 *
 * @author Force-Oneself
 * @date 2020-05-27
 */
public class Document {

    //如果文档发生改变，changed会被设置为true
    private boolean changed = false;

    //一次需要保存的内容，可以将其理解为内容缓存
    private List<String> content = new ArrayList<>();

    private final FileWriter writer;

    //自动保存文档的线程
    private static AutoSaveThread autoSaveThread;

    //构造函数需要传入文档保存的路径和文档名称
    private Document(String documentPath, String documentName) throws IOException {
        this.writer = new FileWriter(new File(documentPath, documentName), true);
    }

    //静态方法，主要用于创建文档，顺便启动自动保存文档的线程
    public static Document create(String documentPath, String documentName) throws IOException {
        Document document = new Document(documentPath, documentName);
        autoSaveThread = new AutoSaveThread(document);
        autoSaveThread.start();
        return document;
    }

    //文档的编辑，其实就是往content队列中提交字符串
    public void edit(String content) {
        synchronized (this) {
            this.content.add(content);
            //文档改变，changed会变为true
            this.changed = true;
        }
    }

    //文档关闭的时候首先中断自动保存线程，然后关闭writer释放资源
    public void close() throws IOException {
        autoSaveThread.interrupt();
        writer.close();
    }

    //save方法用于为外部显式进行文档保存
    public void save() throws IOException {
        synchronized (this) {
            //balking,如果文档已经被保存了，则直接返回
            if (!changed) {
                return;
            }
            System.out.println(currentThread() + " execute the save action");
            //将内容写入文档中
            for (String cacheLine : content) {
                this.writer.write(cacheLine);
                this.writer.write("\r\n");
            }

            this.writer.flush();
            //将changed修改为false,表明此刻再没有新的内容编辑
            this.changed = false;
            this.content.clear();
        }
    }

}
