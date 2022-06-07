package com.quan.framework.document.excel.strategy;

import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.util.CollectionUtils;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.style.column.AbstractColumnWidthStyleStrategy;
import org.apache.poi.ss.usermodel.Cell;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义列宽自适应实现策略
 *
 * @author Force-oneself
 * @date 2022-06-07
 */
public class AutoColumnWidthStrategy extends AbstractColumnWidthStyleStrategy {

    private final Map<Integer, Map<Integer, Integer>> widthCache = new HashMap<>(16);

    @Override
    protected void setColumnWidth(WriteSheetHolder writeSheetHolder, List<CellData> cellDataList, Cell cell, Head head,
                                  Integer relativeRowIndex, Boolean isHead) {
        boolean needSetWidth = (isHead != null && isHead) || !CollectionUtils.isEmpty(cellDataList);
        if (!needSetWidth) {
            return;
        }
        Map<Integer, Integer> maxWidthMap = widthCache.computeIfAbsent(writeSheetHolder.getSheetNo(), k -> new HashMap<>(16));
        Integer columnWidth = this.dataLength(cellDataList, cell, isHead);
        if (columnWidth < 0) {
            return;
        }
        columnWidth = Math.min(columnWidth, 255);
        Integer maxColumnWidth = maxWidthMap.get(cell.getColumnIndex());
        if (maxColumnWidth != null && columnWidth <= maxColumnWidth) {
            return;
        }
        maxWidthMap.put(cell.getColumnIndex(), columnWidth);
        writeSheetHolder.getSheet().setColumnWidth(cell.getColumnIndex(), columnWidth * 256);
    }


    private Integer dataLength(List<CellData> cellDataList, Cell cell, Boolean isHead) {
        if (isHead != null && isHead) {
            return autoLength(cell.getStringCellValue());
        }
        CellData cellData = cellDataList.get(0);
        CellDataTypeEnum type = cellData.getType();
        if (type == null) {
            return -1;
        }
        switch (type) {
            case STRING:
                return this.autoLength(cellData.getStringValue());
            case BOOLEAN:
                return this.autoLength(cellData.getBooleanValue().toString());
            case NUMBER:
                return this.autoLength(cellData.getNumberValue().toString());
            default:
                return -1;
        }
    }

    private int autoLength(String cellValue) {
        return Math.max(cellValue.getBytes().length, cellValue.length() * 2);
    }
}
