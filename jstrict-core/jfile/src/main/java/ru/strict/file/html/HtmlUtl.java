package ru.strict.file.html;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import ru.strict.validate.ValidateBaseValue;

import java.io.*;

public class HtmlUtl {

    public static Elements selectByFileUTF8(String filePath, String selector) throws IOException, NullPointerException {
        return selectByFile(filePath, selector, "UTF-8");
    }

    public static Elements selectByFile(String filePath, String selector, String encoding) throws IOException, NullPointerException {
        if (ValidateBaseValue.isEmptyOrNull(filePath)) {
            throw new IllegalArgumentException("filePath is NULL");
        }

        Document page = Jsoup.parse(new File(filePath), encoding);
        if (page == null) {
            throw new NullPointerException(String.format("Page by content [%s] not found", filePath));
        }

        return getTag(page, selector);
    }

    public static Elements selectByContent(String content, String selector) throws NullPointerException {
        if (ValidateBaseValue.isEmptyOrNull(content)) {
            throw new IllegalArgumentException("content is NULL");
        }

        Document page = Jsoup.parseBodyFragment(content);
        if(page == null){
            throw new NullPointerException(String.format("Page by content [%s] not found", content));
        }

        return getTag(page, selector);
    }

    public static Elements selectByGet(String url, String selector) throws IOException, NullPointerException {
        if (ValidateBaseValue.isEmptyOrNull(url)) {
            throw new IllegalArgumentException("url is NULL");
        }

        Document page = Jsoup.connect(url).get();
        if (page == null) {
            throw new NullPointerException(String.format("Page by url [%s] [GET] not found", url));
        }

        return getTag(page, selector);
    }

    public static Elements selectByPost(String url, String selector) throws IOException, NullPointerException {
        if (ValidateBaseValue.isEmptyOrNull(url)) {
            throw new IllegalArgumentException("url is NULL");
        }

        Document page = Jsoup.connect(url).post();
        if(page == null){
            throw new NullPointerException(String.format("Page by url [%s] [POST] not found", url));
        }

        return getTag(page, selector);
    }

    /**
     * Конвертировать html в pdf
     */
    public static void convertToPdf(String sourceHtmlFilePath, String targetPdfFilePath) throws IOException, DocumentException {
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(targetPdfFilePath));
        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(sourceHtmlFilePath));
        document.close();
    }

    /**
     * Получить строки html-таблицы
     */
    public static Elements getTableRows(String content) {
        return selectByContent(content, "tbody > tr");
    }

    private static Elements getTag(Document page, String selector){
        Elements tags = null;
        if (ValidateBaseValue.isEmptyOrNull(selector)) {
            tags = page.getAllElements();
        } else {
            tags = page.select(selector);
        }
        if (tags.size() == 0) {
            tags = null;
        }

        return tags;
    }
}
