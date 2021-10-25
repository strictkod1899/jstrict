package ru.strict.file.html;

import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import lombok.experimental.UtilityClass;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import ru.strict.file.FileProcessingException;
import ru.strict.validate.CommonValidate;
import ru.strict.validate.Validator;

import java.io.*;

@UtilityClass
public class HtmlUtil {

    public Elements select(String source, String selector) {
        if (source.startsWith("http") || source.startsWith("https")) {
            return HtmlUtil.selectByGet(source, selector);
        } else {
            return HtmlUtil.selectByUTF8File(source, selector);
        }
    }

    public static Elements selectByUTF8File(String filePath, String selector) {
        return selectByFile(filePath, selector, "UTF-8");
    }

    public static Elements selectByFile(String filePath, String selector, String encoding) {
        Validator.isEmptyOrNull(filePath, "filePath");

        try {
            var page = Jsoup.parse(new File(filePath), encoding);
            if (page == null) {
                throw new NullPointerException(String.format("Page by content [%s] not found", filePath));
            }

            return getTag(page, selector);
        } catch (IOException ex) {
            throw new FileProcessingException(filePath, ex);
        }
    }

    public static Elements selectByContent(String content, String selector) {
        Validator.isEmptyOrNull(content, "content");

        var page = Jsoup.parseBodyFragment(content);
        return getTag(page, selector);
    }

    public static Elements selectByGet(String url, String selector) {
        Validator.isEmptyOrNull(url, "url");

        try {
            var page = Jsoup.connect(url).get();
            if (page == null) {
                throw new NullPointerException(String.format("Page by url [%s] [GET] not found", url));
            }

            return getTag(page, selector);
        } catch (IOException ex) {
            throw new FileProcessingException(url, ex);
        }
    }

    public static Elements selectByPost(String url, String selector) {
        Validator.isEmptyOrNull(url, "url");

        try {
            var page = Jsoup.connect(url).post();
            if (page == null) {
                throw new NullPointerException(String.format("Page by url [%s] [POST] not found", url));
            }

            return getTag(page, selector);
        } catch (IOException ex) {
            throw new FileProcessingException(url, ex);
        }
    }

    /**
     * Конвертировать html в pdf
     */
    public static void convertToPdf(String sourceHtmlFilePath, String targetPdfFilePath) {
        var document = new com.itextpdf.text.Document();

        try {
            var writer = PdfWriter.getInstance(document, new FileOutputStream(targetPdfFilePath));
            document.open();
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(sourceHtmlFilePath));
            document.close();
        } catch (Exception ex) {
            throw new FileProcessingException(sourceHtmlFilePath, ex);
        }
    }

    /**
     * Получить строки html-таблицы
     */
    public static Elements getTableRows(String content) {
        return selectByContent(content, "tbody > tr");
    }

    private static Elements getTag(Document page, String selector) {
        Elements tags;
        if (CommonValidate.isEmptyOrNull(selector)) {
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
