package com.qiusm.eju.crawler.utils.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class WriteExcel {
    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    public static void writeExcel(String[] title, List<Map<String, Object>> dataList, String finalXlsxPath) {
        OutputStream out = null;
        try {
            // 读取Excel文档
            File finalXlsxFile = new File(finalXlsxPath);
            Workbook workBook = getWorkbook(finalXlsxFile);
            // sheet 对应一个工作页
            Sheet sheet = workBook.createSheet();
            // 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out = new FileOutputStream(finalXlsxPath);
            workBook.write(out);

            // 写入表头
            Row rowTitle = sheet.createRow(0);
            int cellIndexTitle = 0;
            for (String s : title) {
                Cell cell = rowTitle.createCell(cellIndexTitle);
                cell.setCellValue(s);
                cellIndexTitle++;
            }

            for (int j = 0; j < dataList.size(); j++) {
                // 创建一行：从第二行开始，跳过属性列
                Row row = sheet.createRow(j + 1);
                // 得到要插入的每一条记录
                Map dataMap = dataList.get(j);
                int cellIndex = 0;
                for (String s : title) {
                    Object value = dataMap.get(s);
                    Cell cell = row.createCell(cellIndex);
                    if (value == null) {
                        cell.setCellValue("");
                    } else {
                        cell.setCellValue(value.toString());
                    }
                    cellIndex++;

                }
            }
            // 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out = new FileOutputStream(finalXlsxPath);
            workBook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("数据导出成功");
    }

    /**
     * 判断Excel的版本,获取Workbook
     *
     * @return
     */
    public static Workbook getWorkbook(File file) {
        Workbook wb = null;
        if (file.getName().endsWith(EXCEL_XLS)) {
            wb = new HSSFWorkbook();
        } else if (file.getName().endsWith(EXCEL_XLSX)) {
            wb = new XSSFWorkbook();
        }
        return wb;
    }
}