package com.quan.common.util.excel.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @Description:
 * @Author heyq
 * @Date 2020-11-14
 **/
public class Poi {

    public static void read(InputStream in) {

        Workbook wb = new HSSFWorkbook();
        try {
            wb = new HSSFWorkbook(in);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Sheet sheet = wb.getSheetAt(0);
        if (sheet.getLastRowNum() == sheet.getFirstRowNum()) {
            throw new RuntimeException("无数据!");
        }
        for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {
            // doReadRowBefore
            Row row = sheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum() + 1; j++) {
                Cell cell = row.getCell(j);
                // 处理cell
                if (Objects.nonNull(cell)) {
                    CellType cellTypeEnum = cell.getCellTypeEnum();
                    switch (cellTypeEnum) {
                        case NUMERIC:
                            // NUMERIC
                            break;
                        case STRING:
                            // String
                            break;
                        case FORMULA:
                            // formula
                            break;
                        case BLANK:
                            // blank
                            break;
                        case BOOLEAN:
                            // boolean
                            break;
                        case ERROR:
                            // error
                            break;
                        default:
                            break;
                    }
                }
            }
            // doReadRowAfter
        }
        // doReadAllAfter
    }
}
