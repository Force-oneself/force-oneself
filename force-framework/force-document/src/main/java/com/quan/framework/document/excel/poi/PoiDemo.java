package com.quan.framework.document.excel.poi;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Force-oneself
 * @date 2023-06-02
 */
public class PoiDemo {

    public static void main(String[] args) throws IOException {

//        Workbook workbook = new XSSFWorkbook(Files.newInputStream(Paths.get("/Users/force-oneself/Desktop/img (1).xlsx")));
        Workbook workbook = new XSSFWorkbook(Files.newInputStream(Paths.get("/Users/force-oneself/Desktop/img.xlsx")));
        Sheet sheet = workbook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= lastRowNum; i++) {
            // 行
            Row row = sheet.getRow(i);
            if (row == null) {
                sb.append("空行").append(i).append("\n");
                continue;
            }
            // 列从1开始
            int columns = row.getLastCellNum();
            if (columns == 0) {
                sb.append("空行");
            }
            for (int j = 0; j < columns; j++) {
                Cell cell = row.getCell(j);
                if (cell == null) {
                    sb.append(" + |");
                    continue;
                }
                CellType cellTypeEnum = cell.getCellTypeEnum();
                switch (cellTypeEnum) {
                    case BLANK:
                        sb.append("_");
                        break;
                    case STRING:
                        sb.append(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        sb.append(cell.getNumericCellValue());
                        break;
                    case BOOLEAN:
                        sb.append(cell.getBooleanCellValue());
                        break;
                    case FORMULA:
                        sb.append(cell.getCellFormula());
                        break;
                    case ERROR:
                        sb.append(cell.getErrorCellValue());
                        break;
                    case _NONE:
                        sb.append("-");
                        break;
                    default:
                        break;
                }
                sb.append(" | ");
            }
            sb.append(" end \n");
        }
        System.out.print(sb);
    }
}
