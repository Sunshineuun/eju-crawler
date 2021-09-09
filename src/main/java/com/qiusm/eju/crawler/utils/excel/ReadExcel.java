package com.qiusm.eju.crawler.utils.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ReadExcel {

    public static void main(String[] args) {
        String filePath = "source/file/门店数据.xlsx";
        String sheetName = "Sheet1";
        String[] title = new String[]{"1", "2", "3", "4", "5", "6", "7"};
        List<Map<String, Object>> list = readExcel(filePath, sheetName, title);
    }

    public static List<Map<String, Object>> readExcel(String filePath, String sheetName, String[] title) {
        List<Map<String, Object>> list = new ArrayList<>();
        Workbook wb = createWorkbook(filePath);
        Sheet sheet;
        Row row;
        String cellData;
        if (wb != null) {
            sheet = wb.getSheet(sheetName);
            int rowTotal = sheet.getPhysicalNumberOfRows();
            row = sheet.getRow(0);
            //获取最大列数
            int colNum = row.getPhysicalNumberOfCells();
            for (int i = 1; i < rowTotal; i++) {
                Map<String, Object> map = new LinkedHashMap<>();
                row = sheet.getRow(i);
                if (row != null) {
                    for (int j = 0; j < colNum; j++) {
                        cellData = (String) getCellFormatValue(row.getCell(j));
                        map.put(title[j], cellData);
                    }
                } else {
                    break;
                }
                list.add(map);
            }
        }
        return list;
    }

    public static Workbook createWorkbook(String filePath) {
        if (filePath == null) {
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is;
        try {
            is = new FileInputStream(filePath);
            if (".xls".equals(extString)) {
                return new HSSFWorkbook(is);
            } else if (".xlsx".equals(extString)) {
                return new XSSFWorkbook(is);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getCellFormatValue(Cell cell) {
        Object cellValue;
        if (cell != null) {
            //判断cell类型
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC: {
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case Cell.CELL_TYPE_FORMULA: {
                    //判断cell是否为日期格式
                    if (DateUtil.isCellDateFormatted(cell)) {
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    } else {
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING: {
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        } else {
            cellValue = "";
        }
        return cellValue;
    }
}