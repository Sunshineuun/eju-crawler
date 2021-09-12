/**
 * ==============================================
 * (C)2016 Shanghai KingstarWinning Corporation. All rights reserved.
 * 项目名称：DataCrawl
 * 系统名称： @TODO
 * 文件名称： ReaderExcel.java
 * 注意事项：
 * <p>
 * <p>
 * $Id: ReaderExcel.java,v 1.6 2015/08/04 04:56:16 yuan Exp $
 * ==============================================
 */
package com.qiusm.eju.crawler.utils.excel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Class类功能定义的说明性内容。（请以句号“。”结尾、段中换行请使用“<br/>
 * ”符号）
 * </p>
 * <p>
 * 说明:
 * </p>
 * <p>
 * 备注:
 * </p>
 *
 * @version 1.0
 * @author 公司名 : 上海金仕达卫宁软件科技有限公司（Shanghai KingStar WinningSoft LTD.） <br />
 *         变更履历 <br />
 *
 */
public class ReaderExcel {

    private static Workbook workbook;
    private static Sheet sheet;
    private List<String> columnHeaderList;
    private List<Map<String, String>> mapData;
    private boolean flag;
    private Map<String, List<Double>> kindMap;
    private List<String> keyWord = new ArrayList<String>();
    private static FileInputStream inStreams = null;

    public ReaderExcel(String path, String sheetName) {

    }

    public static Sheet getSheet(String path, String sheetName) {
        inStreams = load(path, sheetName);
        return sheet;
    }

    private static FileInputStream load(String filePath, String sheetName) {
        FileInputStream inStream = null;
        File file = new File(filePath);
        try {
            inStream = new FileInputStream(file);
            workbook = WorkbookFactory.create(inStream);
            sheet = workbook.getSheet(sheetName);
        } catch (Exception e) {
            e.printStackTrace();

            if (inStream != null)
                try {
                    inStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        }
        return inStream;
    }

    public static String getCellValue(Cell cell) {
        String cellValue = "";
        DataFormatter formatter = new DataFormatter();
        if (cell != null) {
            switch (cell.getCellType()) {
                case 0:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        cellValue = formatter.formatCellValue(cell);
                    } else {
                        double value = cell.getNumericCellValue();
                        int intValue = (int) value;
                        cellValue = value - intValue == 0.0D ?
                            String.valueOf(intValue) :
                            String.valueOf(value);
                    }
                    break;
                case 1:
                    cellValue = cell.getStringCellValue();
                    break;
                case 4:
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                case 2:
                    //cellValue = String.valueOf(cell.getCellFormula());
                    cellValue = String.valueOf(cell.getRichStringCellValue());
                    break;
                case 3:
                    cellValue = "";
                    break;
                case 5:
                    cellValue = "";
                    break;
                default:
                    cellValue = cell.toString().trim();
            }

        }

        return cellValue.trim();
    }

    private void getSheeData() {
        kindMap = new HashMap<String, List<Double>>();
        mapData = new ArrayList<Map<String, String>>();
        columnHeaderList = new ArrayList<String>();
        int numOfRows = sheet.getLastRowNum() + 1;

        for (int i = 0; i < numOfRows; i++) {
            Row row = sheet.getRow(i);
            Map<String, String> map = new HashMap<String, String>();

            if (row != null) {
                String kindKey = getCellValue(row.getCell(1));
                if ((kindMap.get(kindKey) == null) && (i > 0)) {
                    List<Double> kindlist = new ArrayList<Double>();
                    kindlist.add(Double.valueOf(0.0D));
                    kindlist.add(Double.valueOf(0.0D));
                    kindMap.put(kindKey, kindlist);
                    keyWord.add(kindKey);
                }

                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);

                    if (i != 0)
                        map.put(this.columnHeaderList.get(j),
                            getCellValue(cell));
                    else {
                        this.columnHeaderList.add(getCellValue(cell));
                    }

                }

                if (i > 0) {
                    List<Double> kind = this.kindMap.get(kindKey);
                    Double num =
                        Double.valueOf(kind.get(0).doubleValue() + 1.0D);
                    kind.set(0, num);
                    Double sum = Double.valueOf(
                        kind.get(1).doubleValue() + Double
                            .valueOf(getCellValue(row.getCell(2)))
                            .doubleValue());
                    kind.set(1, sum);
                }

                if (i > 0) {
                    this.mapData.add(map);
                }

            }

            this.flag = true;
        }
    }

    /**
     * 读取execle文件
     * @author:qiushengming
     * @return:List<Map<String,String>>
     * @date:2016-6-23 上午10:08:03
     */
    public static List<Map<String, String>> readExcel(String filePath,
        String sheetName) {
        load(filePath, sheetName);
        List<Map<String, String>> mapData =
            new ArrayList<Map<String, String>>();
        List<String> keyWord = new ArrayList<String>();

        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            Map<String, String> map = new HashMap<String, String>();
            Row row = sheet.getRow(i);
            Cell cell = null;
            if (i != 0) {
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    try {
                        cell = row.getCell(j);
                        map.put(keyWord.get(j), getCellValue(cell) == null ?
                            "" :
                            getCellValue(cell));
                    } catch (Exception e) {
                    }
                }
            } else if (i == 0) {
                /**
                 * i==0的时候为第一行，第一行为关键字，另外存储起来。
                 */
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    cell = row.getCell(j);
                    keyWord.add(getCellValue(cell));
                }
            }

            if (map != null) {
                mapData.add(map);
            }
        }
        return mapData;

    }

    public static void writeExecl(List<Map<String, String>> list,
        String filePath, String sheetName) {
        FileOutputStream inStream = null;
        File file = new File(filePath);
        Workbook workbookThis = null;
        try {
            inStream = new FileOutputStream(file);
            workbookThis = WorkbookFactory.create(file);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheetResult = workbookThis.getSheet(sheetName);

        for (int i = 0; i < list.size(); i++) {
            Map<String, String> map = list.get(i);
            Row row = sheetResult.createRow(i);
            Integer index = 1;
            for (String key : map.keySet()) {
                createCell(workbookThis, row, index++, map.get(key), null);
            }

        }

        //workbookThis.w
    }

    /**
     * 创建单元格并设置样式,值
     *
     * @param wb
     * @param row
     * @param column
     * @param
     * @param
     * @param value
     */
    private static void createCell(Workbook wb, Row row, int column,
        String value, Font font) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value);
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_BOTTOM);
        cellStyle.setFont(font);
        //        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("@"));
        cell.setCellStyle(cellStyle);
    }

    public List<Map<String, String>> getMapData() {
        return this.mapData;
    }

    public Map<String, List<Double>> getKindMap() {
        return this.kindMap;
    }

    public List<String> getKeyWord() {
        return this.keyWord;
    }

    public String getCellData(int row, String headerName) {
        if (row <= 0) {
            return null;
        }
        if (!this.flag) {
            getSheeData();
        }
        if ((this.mapData.size() >= row) && (this.mapData.get(row - 1)
            .containsKey(headerName))) {
            return (String) this.mapData.get(row - 1).get(headerName);
        }
        return null;
    }

    public static CellStyle createCellStyle() {
        return workbook.createCellStyle();
    }

    public static Font getFont() {
        Font ztFont = workbook.createFont();
        ztFont.setItalic(true);                     // 设置字体为斜体字
        ztFont.setColor(Font.COLOR_RED);            // 将字体设置为“红色”
        ztFont.setFontHeightInPoints((short) 22);    // 将字体大小设置为18px
        ztFont.setFontName("华文行楷");             // 将“华文行楷”字体应用到当前单元格上
        ztFont.setUnderline(
            Font.U_DOUBLE);         // 添加（Font.U_SINGLE单条下划线/Font.U_DOUBLE双条下划线）
        ztFont.setBoldweight(Font.BOLDWEIGHT_BOLD); //字体加粗
        //      ztFont.setStrikeout(true);
        return ztFont;
    }

    public static Workbook getWorkBook() {
        return workbook;
    }

    public static void setWorkBook(String filePath) {
        FileInputStream inStream = null;
        File file = new File(filePath);
        try {
            inStream = new FileInputStream(file);
            workbook = WorkbookFactory.create(inStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void colse(String fileName) {
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(fileName);
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        // 把相应的Excel 工作簿存盘
        try {
            workbook.write(fOut);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            fOut.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 操作结束，关闭文件
        try {
            fOut.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
