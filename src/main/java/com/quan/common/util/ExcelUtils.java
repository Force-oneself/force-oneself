package com.quan.common.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.read.listener.ReadListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author heyq
 * @Date 2020-09-23
 **/
public class ExcelUtils {

    public static void readToListener(String excelPath, Class<?> clazz, ReadListener readListener) {
        EasyExcel.read(excelPath, clazz, readListener).sheet().doRead();
    }

    public static void write(String excelPath, Class<?> clazz, List<?> data, String sheetName) {
        EasyExcel.write(excelPath, clazz).sheet(sheetName).doWrite(data);
    }

    public static void writeDynamicHead(String excelPath, List<List<String>> head, String sheetName, List<List<String>> data) {
        EasyExcel.write(excelPath).head(head).sheet(sheetName).doWrite(data);
    }

    public static void writeByTemplate(String fileName, String templateFileName, String sheetName, Class<?> clazz, List<?> data) {
        EasyExcel.write(fileName, clazz).withTemplate(templateFileName).sheet(sheetName).doWrite(data);
    }

    @Data
    @AllArgsConstructor
    static class Demo {
        @ExcelProperty("姓名")
        private String name;

        @ExcelIgnore
        @ExcelProperty("忽略字段")
        private String ignore;

        @ExcelProperty("创建时间")
        @DateTimeFormat("yyyy-MM-dd")
        private Date createTime;

        @ExcelProperty("状态")
        private boolean alive;

        @ExcelProperty("年龄")
        private Integer age;
    }

    public static void main(String[] args) {
        Demo demo = new Demo("zhangsan", "忽略字段", new Date(), true, 23);
        Demo demo1 = new Demo("lisi", "忽略字段", new Date(), false, 26);
        Demo demo2 = new Demo("zha", "忽略字段", new Date(), true, 14);
        List<Demo> list = Arrays.asList(new Demo[]{demo, demo1, demo2});
        ExcelUtils.write("E:\\Sendi\\Doc\\test.xlsx", Demo.class, list, "数据");
    }
}
