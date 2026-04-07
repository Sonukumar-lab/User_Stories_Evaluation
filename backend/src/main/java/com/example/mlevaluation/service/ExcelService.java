package com.example.mlevaluation.service;

import com.example.mlevaluation.model.UserStoryData;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ExcelService {

    public List<UserStoryData> readExcel(MultipartFile file) throws Exception {

        List<UserStoryData> stories = new ArrayList<>();

        InputStream is = file.getInputStream();
        Workbook workbook = WorkbookFactory.create(is);

        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);

            String text = getCellValue(row.getCell(0));

            String unique = getCellValue(row.getCell(1));
            String conflict = getCellValue(row.getCell(2));
            String uniform = getCellValue(row.getCell(3));
            String independent = getCellValue(row.getCell(4));
            String complete = getCellValue(row.getCell(5));

            List<String> values = Arrays.asList(
                    unique,
                    conflict,
                    uniform,
                    independent,
                    complete
            );

            stories.add(new UserStoryData(text, values));
        }

        workbook.close();

        return stories;
    }

    private String getCellValue(Cell cell) {

        if (cell == null) return "";

        switch (cell.getCellType()) {

            case STRING:
                return cell.getStringCellValue();

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue()).toUpperCase();

            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());

            default:
                return "";
        }
    }
}