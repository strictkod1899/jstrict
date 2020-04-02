package ru.strict.office.doc;

import ru.strict.office.IOfficeFormat;

import java.util.Arrays;

public enum WordFormat implements IOfficeFormat {
    DOC("doc"),
    DOCX("docx");

    private String caption;

    WordFormat(String caption) {
        this.caption = caption;
    }

    @Override
    public String getCaption() {
        return caption;
    }

    @Override
    public String toString() {
        return caption;
    }


    public static WordFormat getByCaption(String caption){
        if(caption == null){
            return null;
        }

        return Arrays.stream(WordFormat.values())
                .filter(object -> object.caption.equals(caption))
                .findFirst()
                .orElse(null);
    }
}
