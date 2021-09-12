package com.qiusm.eju.crawler.utils.excel;

import org.apache.log4j.Logger;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;


/**
 * @Description: 利用poi将word简单的转换成html文件
 * @author qiushengming
 * @version v1.0
 */
public class Word2Html {

    private static Logger logger = Logger.getLogger(Word2Html.class);

    /*word中图片存储路径*/
    private static final String IMAGE_PATH = "D://Temp//";

    public static void main(String[] args) {
        String filePath = "D://1.docx";
        String htmlPath = "D://";
        File file = new File(filePath);
        try {
            Word2007ToHtml(file, htmlPath);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
    }

    /**
     * 1.校验文件是否存在 && 文件是否.docx结尾的；</br>
     * 2017年7月4日</br>
     *
     * @author qiushengming</br>
     * @param file 文件存储路径_D:\\
     * @param htmlPath 文件名称
     * @throws IOException
     * @return 无
     */
    public static void Word2007ToHtml(final File file, final String htmlPath)
        throws IOException {

        /* html文件名称定义 */
        String htmlName = file.getName().replace("docx", "html");
        /*图片文件存储路劲*/
        String imagePath = htmlPath + "image//";

        /*文件是否存在监测*/
        if (!file.exists()) {
            logger.error(file.getName() + " >> 文件不存在！！！！");
            return;
        }

        /*是否为2007版本WORD进行监测*/
        if (file.getName().endsWith(".docx")
                || file.getName().endsWith(".DOCX")) {
            /* 加载word文档生成 XWPFDocument对象 */
            InputStream in = new FileInputStream(file);
            XWPFDocument document = new XWPFDocument(in);

            /* 解析 XHTML配置 (这里设置IURIResolver来设置图片存放的目录)*/
            File imageFolderFile = new File(imagePath);

            XHTMLOptions options = XHTMLOptions.create()
                    .URIResolver(new FileURIResolver(imageFolderFile));

            options.setExtractor(new FileImageExtractor(imageFolderFile));
            options.setIgnoreStylesIfUnused(false);
            options.setFragment(true);

            /* 将 XWPFDocument转换成XHTML */
            OutputStream out =
                    new FileOutputStream(new File(htmlPath + htmlName));

            try{
                XHTMLConverter.getInstance().convert(document, out, options);
            }catch(NullPointerException e){
                logger.error(file.getName() + " >> ");
                logger.error(e);
            }

             /*也可以使用字符数组流获取解析的内容*/
             /*ByteArrayOutputStream baos = new ByteArrayOutputStream();
             XHTMLConverter.getInstance().convert(document, baos,
             options);
             String content = baos.toString();
             logger.info(content);
             baos.close();*/
        }else{
            logger.error(file.getName() + " >> Enter only MS Office 2007+ files");
        }

    }


    public static void Word2003ToHtml(final File file, final String htmlPath)
        throws ParserConfigurationException, IOException, TransformerException {

        /* html文件 */
        String htmlName = file.getName().replace("doc", "html");

        /* 图片文件存储路劲 */
        final String imagePath = htmlPath + "image//";

        /* 文件是否存在监测 */
        if (!file.exists()) {
            logger.error(file.getName() + " >> 文件不存在！！！！");
            return;
        }

        if (file.getName().endsWith(".doc")
                || file.getName().endsWith(".DOC")) {

            /* 加载word文档 */
            InputStream in = new FileInputStream(file);
            HWPFDocument wordDocument = new HWPFDocument(in);

            WordToHtmlConverter wordToHtmlConverter =
                    new WordToHtmlConverter(DocumentBuilderFactory.newInstance()
                            .newDocumentBuilder().newDocument());

            wordToHtmlConverter.setPicturesManager(new PicturesManager() {
                public String savePicture(byte[] content,
                    PictureType pictureType, String suggestedName,
                    float widthInches, float heightInches) {

                    File imageFile = new File(imagePath);

                    if (!imageFile.exists()) {
                        /* 图片目录不存在则创建 */
                        imageFile.mkdirs();
                    }

                    logger.info(imagePath + " >> imagePath >> " + suggestedName);
                    File file = new File(imagePath +suggestedName);

                    try {
                        OutputStream os = new FileOutputStream(file);
                        os.write(content);
                        os.close();
                    }
                    catch (Exception e) {
                        logger.error(e);
                    }

                    return file.getPath();
                }
            });

            /* 解析word文档 */
            wordToHtmlConverter.processDocument(wordDocument);
            Document htmlDocument = wordToHtmlConverter.getDocument();

            File htmlFile = new File(htmlPath + htmlName);
            OutputStream outStream = new FileOutputStream(htmlFile);

            DOMSource domSource = new DOMSource(htmlDocument);
            StreamResult streamResult = new StreamResult(outStream);

            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer serializer = factory.newTransformer();
            serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            serializer.setOutputProperty(OutputKeys.METHOD, "html");

            serializer.transform(domSource, streamResult);

            /* 也可以使用字符数组流获取解析的内容 */
            /*ByteArrayOutputStream baos = new ByteArrayOutputStream();
            OutputStream outStream = new BufferedOutputStream(baos);*/
            /* 也可以使用字符数组流获取解析的内容 */
            /*String content = baos.toString();
            System.out.println(content);
            baos.close();*/

            outStream.close();
        }
    }
}
