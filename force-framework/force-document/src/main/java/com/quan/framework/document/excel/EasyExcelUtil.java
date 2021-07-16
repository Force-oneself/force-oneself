package com.quan.framework.document.excel;

import com.alibaba.excel.EasyExcel;
import com.quan.framework.document.excel.constant.DefaultExcelCell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 导出excel数据工具
 *
 * @author hyq
 * @date 2020/9/23
 */
public class EasyExcelUtil {

    private final static Logger log = LoggerFactory.getLogger(EasyExcelUtil.class);

    /**
     * 兼容原有替换掉老版本中PoiUtil中的方法
     *
     * @param response web端应答请求
     * @param dataList 被转换成map的List集合
     * @param heads    表格头部名为：List<List> 类型的集合
     * @param fields   数据内容字段集合
     * @param filename 文件名
     * @throws IOException
     */
    public static void download(HttpServletResponse response, List<? extends Map<String, Object>> dataList,
                                List<List<String>> heads, List<String> fields, String filename) throws IOException {
        doGarbled(response, filename);
        //字段和表头对应问题
        List<List<Object>> resultData = restructureData(fields, dataList);

        EasyExcel.write(response.getOutputStream())
                .head(heads)
                .registerWriteHandler(DefaultExcelCell.DEFAULT_CELL_STYLE_STRATEGY)
                .registerWriteHandler(DefaultExcelCell.DEFAULT_CUSTOM_STRATEGY)
                .sheet("数据")
                .doWrite(resultData);
    }

    /**
     * 将原来PoiUtil中的Map类型的集合与实体字段相对应装换成EasyExcel中的List类型的集合
     *
     * @param fields
     * @param dataList
     * @return
     */
    private static List<List<Object>> restructureData(List<String> fields, List<? extends Map<String, Object>> dataList) {
        // 所有行的集合
        List<List<Object>> list = new ArrayList<>();

        for (int i = 0; i < dataList.size(); i++) {
            // 第 n 行的数据
            List<Object> row = new ArrayList<>();
            for (String field : fields) {
                Map<String, Object> mapData = dataList.get(i);
                row.add(mapData.get(field));
            }
            list.add(row);
        }
        return list;
    }

    /**
     * 使用实体类注解的方式写入
     * 默认样式-单个sheet
     *
     * @param response  返回web的端应答请求
     * @param clazz     带Excel注解的实体类
     * @param fileName  生成的文件名称
     * @param sheetName sheet表的名称
     * @param data      需要写入的数据
     */
    public static void write(@NotNull HttpServletResponse response, @NotNull Class clazz, @NotNull String fileName,
                             @NotNull String sheetName, List data) {
        doGarbled(response, fileName);
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            EasyExcel.write(outputStream, clazz)
                    .sheet(sheetName)
                    .registerWriteHandler(DefaultExcelCell.DEFAULT_CELL_STYLE_STRATEGY)
                    .registerWriteHandler(DefaultExcelCell.DEFAULT_CUSTOM_STRATEGY)
                    .doWrite(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 本地写入数据
     *
     * @param fileName 文件路劲名（路劲+文件名）
     * @param clazz 带注解的实体
     * @param sheetName sheet名称
     * @param data 写入数据
     */
    public static void write(@NotNull String fileName, @NotNull Class clazz, @NotNull String sheetName, List data) {
        EasyExcel.write(fileName, clazz)
                .sheet(sheetName)
                .registerWriteHandler(DefaultExcelCell.DEFAULT_CELL_STYLE_STRATEGY)
                .registerWriteHandler(DefaultExcelCell.DEFAULT_CUSTOM_STRATEGY)
                .doWrite(data);
    }

    /**
     * 防止乱码处理
     *
     * @param response 返回web端应答请求
     * @param fileName 文件名称
     */
    public static void doGarbled(HttpServletResponse response, String fileName) {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        try {
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            log.error("编码异常!", e);
        }
    }
}
