package ru.strict.swing.enums;


/**
 * Набор доступных шрифтов
 */
public enum StrictEnumFonts {

    BASE("Serif"),

    UBUNTU("Ubuntu"),

    ROBOTO("Roboto");

    private String font;

    StrictEnumFonts(String font){
        this.font = font;
    }

    public String getFontName() {
        return font;
    }
}
