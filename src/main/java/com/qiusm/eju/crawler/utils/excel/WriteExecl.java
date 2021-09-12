package com.qiusm.eju.crawler.utils.excel;

import com.qiusm.eju.crawler.utils.lang.DateUtils;
import com.qiusm.eju.crawler.utils.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Clob;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 1.向Excel写入数据的时候，需要通过两部分内容，第一部分首行的及标题行的数据，第二部分提供写入数据；<br>
 * 1.1map写入的数据会根据标题行的key值进行依次写入；<br>
 * 1.2未得到第一部分内容讲抛出异常；<br>
 */
public class WriteExecl {

    private Logger logger = Logger.getLogger(getClass());

    /* 当没有设置文件路径的时候默认用这个路径 */
    private String filePath =
            "D://Temp//WriteExcel//temp_" + System.currentTimeMillis() + ".xlsx";

    /* sheet页 */
    private SXSSFSheet sheetSXSSF;

    /* 文档空间 */
    private SXSSFWorkbook workbookSXSSF;

    private String[] arrayTitle;

    /**
     * <p>
     * 已默认路径创建文档空间
     * </p>
     * qiushengming 2017年4月9日
     */
    public WriteExecl() {
        workbookSXSSF = new SXSSFWorkbook();
        sheetSXSSF = workbookSXSSF.createSheet();
    }


    /**
     * <p>
     * 已filePath创建文档空间
     * </p>
     * qiushengming 2017年4月9日
     *
     * @param filePath 文件路径
     */
    public WriteExecl(String filePath) {
        this.filePath = filePath;
        workbookSXSSF = new SXSSFWorkbook();
        sheetSXSSF = workbookSXSSF.createSheet();
    }


    /**
     * <p>
     * 指定rowaccess的sheet页面，好像是这样还是不太确定
     * </p>
     * qiushengming 2017年4月9日
     *
     * @param rowaccess 未知
     */
    public WriteExecl(int rowaccess) {
        workbookSXSSF = new SXSSFWorkbook(rowaccess);
        sheetSXSSF = workbookSXSSF.createSheet();
    }


    public Sheet getSheetSXSSF() {
        return sheetSXSSF;
    }


    public Workbook getWorkbookSXSSF() {
        return workbookSXSSF;
    }

    public String[] getArrayTitle() {
        return arrayTitle;
    }

    public void setArrayTitle(String[] arrayTitle) {
        this.arrayTitle = arrayTitle;
    }

    /**
     * 将List集合中的数据写入filePath指定的excel文件中</br>
     * 2017年4月9日
     *
     * @param records 写入的数据
     * @return 无
     * @throws IOException IO异常
     * @author qiushengming
     */
    public void writeData(List<Map<String, Object>> records)
            throws IOException {

        /* 创建流 */
        FileOutputStream outStream = null;
        try {
            outStream = new FileOutputStream(new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }

        /* 在首行写入数据 */
        writeTitles(getArrayTitle());

        /* 从第2行开始写入真实数据 */
        writeData(records, 1, null);

        workbookSXSSF.write(outStream);

        /* 刷新流和关闭流 */
        if (outStream != null) {
            outStream.flush();
            outStream.close();
        }
    }

    /**
     * 写入首行 或称之为标题行
     * 2017年4月9日 qiushengming
     *
     * @param titles 标题数据
     * @return 无
     */
    private void writeTitles(String[] titles) {

        if (ArrayUtils.isEmpty(titles)) {
            throw new NullPointerException("标题为空请提供标题数据");
        }

        for (int i = 0; i < titles.length; i++) {
            // 写表头
            String title = titles[i];
            ExcelUtil
                    .setContent(sheetSXSSF, 0, i, HSSFCell.CELL_TYPE_STRING, title,
                            getCellStyle());
        }
    }


    @SuppressWarnings("unused")
    private void writeChildData(String value,
                                int startRowNum, XSSFCellStyle style) {
        ExcelUtil
                .setContent(sheetSXSSF, startRowNum, 0, HSSFCell.CELL_TYPE_STRING,
                        value, style);

        CellRangeAddress region =
                new CellRangeAddress(startRowNum, startRowNum, 0, 11); // 参数都是从O开始
        sheetSXSSF.addMergedRegion(region);
    }


    /**
     * 2017年4月9日 qiushengming
     *
     * @param records     写入数据
     * @param startRowNum 起始行索引
     * @throws IOException void
     */
    public void writeData(List<String[]> records, int startRowNum)
            throws IOException {
        /* 创建流 */
        FileOutputStream outStream = null;
        try {
            outStream = new FileOutputStream(new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* 起始行索引 */
        int indexRow = startRowNum;

        /* 遍历写入数据 */
        for (String[] strs : records) {
            for (int indexColumn = 0;
                 indexColumn < strs.length; indexColumn++) {

                if (indexColumn == 0) {
                    strs[indexColumn] = String.valueOf(indexRow);
                }

                ExcelUtil.setContent(sheetSXSSF, indexRow, indexColumn,
                        HSSFCell.CELL_TYPE_STRING, strs[indexColumn], null);
            }

            // 每当行数达到设置的值就刷新数据到硬盘,以清理内存
            if (indexRow % workbookSXSSF.getRandomAccessWindowSize() == 0) {
                sheetSXSSF.flushRows();
            }
            indexRow++;
        }

        workbookSXSSF.write(outStream);

        /* 刷新流和关闭流 */
        outStream.flush();
        outStream.close();
    }


    /**
     * 2017年4月9日 qiushengming
     *
     * @param records     写入数据
     * @param startRowNum 起始行索引
     * @throws IOException void
     *                     <p>
     *                     </p>
     */
    private void writeData(List<Map<String, Object>> records, int startRowNum,
                           XSSFCellStyle style) throws IOException {

        /* 起始行索引 */
        int indexRow = startRowNum;

        Object obj; // 需要写入的对象
        String objClazzName; // 需要写入的对象className
        String value; // 存储转换obj的值

        /* 遍历写入数据 */
        for (Map<String, Object> map : records) {
            int indexColumn = 0;
            for (String key : getArrayTitle()) {

                /*需要写入的对象*/
                obj = map.get(key);

                if (obj instanceof Integer) {
                    value = String.valueOf(obj);
                } else if (obj instanceof Date) {
                    value = DateUtils
                            .formatDate((Date) obj, "yyyy-mm-dd hh:mm:ss");
                } else if (obj instanceof Clob) {
                    value = StringUtils.clobToString((Clob) obj);
                } else if (obj instanceof BigDecimal) {
                    value = obj.toString();
                } else {
                    value = (String) obj;
                }


                ExcelUtil.setContent(sheetSXSSF, indexRow, indexColumn,
                        HSSFCell.CELL_TYPE_STRING, value, style);
                indexColumn++;
            }

            // 每当行数达到设置的值就刷新数据到硬盘,以清理内存
            if (indexRow % workbookSXSSF.getRandomAccessWindowSize() == 0) {
                sheetSXSSF.flushRows();
            }
            indexRow++;
        }
    }


    /**
     * 单元个样式创建
     * 2017年4月9日 qiushengming
     *
     * @return XSSFCellStyle
     */
    private XSSFCellStyle getCellStyle() {
        XSSFCellStyle style = (XSSFCellStyle) workbookSXSSF.createCellStyle();
        style.setFont(getFont());

        /* 设置单元格边框 */
        // CellStyle.BORDER_DOUBLE 双边线
        // CellStyle.BORDER_THIN 细边线
        // CellStyle.BORDER_MEDIUM 中等边线
        // CellStyle.BORDER_DASHED 虚线边线
        // CellStyle.BORDER_HAIR 小圆点虚线边线
        // CellStyle.BORDER_THICK 粗边线
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);

        /**
         * 说明：setFillPattern是设置单元格填充样式，SOLID_FOREGROUND纯色使用前景颜色填充，
         * 接着设置前景颜色(setFillForegroundColor)就可以给单元格着色了。
         */
        /* 单元格颜色填充 */
        /* 设置前景填充样式 */
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        /* 前景填充颜色 */
        style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
        /* 背景填充颜色 */
        style.setFillBackgroundColor(HSSFColor.WHITE.index);

        /* 设置字体 */
        style.setFont(getFont());

        return style;
    }


    /**
     * 2017年4月9日 qiushengming
     *
     * @return Font
     * <p>
     * 单元格格式创建
     * </p>
     */
    private Font getFont() {
        Font font = workbookSXSSF.createFont();

        /* 字体加粗 */
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        return font;
    }

}
