package ru.strict.swing.enums;


/**
 * Набор доступных шрифтов
 */
public enum Fonts {

    BASE("Serif"),

    UBUNTU("Ubuntu"),

    ROBOTO("Roboto");

    private String font;

    Fonts(String font){
        this.font = font;
    }

    public String getFontName() {
        return font;
    }
}
