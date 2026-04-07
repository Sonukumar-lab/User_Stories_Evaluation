package com.example.mlevaluation.util;

import org.apache.poi.ss.usermodel.*;

public class ExcelParser {

    public static String getCellValue(Row row, int index) {

        Cell cell = row.getCell(index);

        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {

            case STRING:
                return cell.getStringCellValue();

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());

            default:
                return "";
        }
    }
}