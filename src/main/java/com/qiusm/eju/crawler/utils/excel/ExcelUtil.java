package com.qiusm.eju.crawler.utils.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

/**
 * @author qiushengming
 */
public class ExcelUtil {

    /**
     * 2017年4月9日
     * qiushengming
     *
     * @param sheet  sheet页
     * @param rownum 第几行
     * @param colnum 第几列
     * @param cType  value的类型
     * @param value  写入值
     * @return void
     * <p></p>
     */
    public static void setContent(Sheet sheet, int rownum, int colnum,
        int cType, String value, CellStyle cellStyle) {
        Row row = null;
        Cell cell = null;

        row = sheet.getRow(rownum);
        if (row == null) {
            row = sheet.createRow(rownum);
        }

        cell = row.getCell((short) colnum);
        if (cell == null) {
            cell = row.createCell((short) colnum);
        }

        /*设置单元格文本类型*/
        cell.setCellType(cType);
        /*设置单元格样式*/
        cell.setCellStyle(cellStyle);

        switch (cType) {
            case Cell.CELL_TYPE_NUMERIC:
                cell.setCellValue(Double.parseDouble(value));
                break;
            case Cell.CELL_TYPE_STRING:
                cell.setCellValue(new XSSFRichTextString(value));
                break;
            case Cell.CELL_TYPE_FORMULA:
                cell.setCellFormula(value);
                break;
        }
    }
}
