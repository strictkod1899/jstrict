package ru.strict.file.html;

import org.jsoup.select.Elements;

public class HtmlClient {

    public Elements select(String source, String selector) {
        if (source.startsWith("http") || source.startsWith("https")) {
            return selectByGet(source, selector);
        } else {
            return selectByFileUTF8(source, selector);
        }
    }

    public Elements selectByGet(String source, String selector) {
        return HtmlUtil.selectByGet(source, selector);
    }

    public Elements selectByFileUTF8(String source, String selector) {
        return HtmlUtil.selectByFileUTF8(source, selector);
    }
}
