package ru.strict.fx;

/**
 * Доступные для использования цвета
 */
public enum Color {

    /**
     * Базовый цвет для фона окна
     */
    BACKGROUND_FORM("#FFFFFF"),

    /**
     * Базовый цвет компонентов
     */
    BACKGROUND_COMP("#4D7EBE"),

    /**
     * Базовый цвет компонентов (вариант 2)
     */
    BACKGROUND_COMP_2("#96B6DF"),

    /**
     * Цвет наведения курсора мыши на элементы
     */
    BACKGROUND_SELECT("#FAFAFA"),

    /**
     * Цвет наведения курсора мыши на кнопку выхода
     */
    BACKGROUND_SELECT_FIRE("#E63232");

    private String code;

    Color(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}