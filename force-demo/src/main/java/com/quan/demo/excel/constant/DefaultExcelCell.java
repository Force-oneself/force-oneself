package com.quan.demo.excel.constant;

import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.quan.demo.excel.strategy.AutoColumnWidthStrategy;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;

/**
 * @Description: EasyExcel 默认样式对象
 * @Author heyq
 * @Date 2020-09-24
 **/
public final class DefaultExcelCell {

    /**
     * 默认的Excel中头部样式
     */
    public static final WriteCellStyle DEFAULT_HEAD_STYLE;
    /**
     * 默认的Excel中内容样式
     */
    public static final WriteCellStyle DEFAULT_CONTENT_STYLE;
    /**
     * 默认的Excel执行的样式策略
     */
    public static final HorizontalCellStyleStrategy DEFAULT_CELL_STYLE_STRATEGY;
    /**
     * 默认实现自动列宽策略
     */
    public static final AutoColumnWidthStrategy DEFAULT_CUSTOM_STRATEGY = new AutoColumnWidthStrategy();

    static {
        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景
        headWriteCellStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
        headWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        // 边框
        headWriteCellStyle.setBorderBottom(BorderStyle.THIN);
        headWriteCellStyle.setBorderLeft(BorderStyle.THIN);
        headWriteCellStyle.setBorderRight(BorderStyle.THIN);
        headWriteCellStyle.setBorderTop(BorderStyle.THIN);
        // 上下/水平区中
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        headWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 字体
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short) 10);
        headWriteFont.setColor(IndexedColors.VIOLET.getIndex());
        headWriteFont.setBold(true);
        // 将字体应用到头部样式中
        headWriteCellStyle.setWriteFont(headWriteFont);
        DEFAULT_HEAD_STYLE = headWriteCellStyle;

        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 这里必须指定背景色为白色，Word版会出现黑色背景
        contentWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        // 设置边框
        contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);
        contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);
        contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
        contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
        // 边框颜色
        contentWriteCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        contentWriteCellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        contentWriteCellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        contentWriteCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
        contentWriteCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        // 设置水平垂直区中
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 字体
        WriteFont contentWriteFont = new WriteFont();
        contentWriteFont.setFontName("Courier New");
        // 将字体样式应用至内容区域
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        DEFAULT_CONTENT_STYLE = contentWriteCellStyle;

        DEFAULT_CELL_STYLE_STRATEGY = new HorizontalCellStyleStrategy(DEFAULT_HEAD_STYLE, DEFAULT_CONTENT_STYLE);
    }
}
