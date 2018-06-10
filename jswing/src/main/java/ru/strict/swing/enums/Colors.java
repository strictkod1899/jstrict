package ru.strict.swing.enums;

import java.awt.Color;

/**
 * Доступные для использования цвета
 */
public enum Colors {

    /**
     * Базовый цвет для фона окна
     */
    BACKGROUND_FORM(255, 255, 255),

    /**
     * Базовый цвет компонентов
     */
    BACKGROUND_COMP(77, 126, 190),

    /**
     * Базовый цвет компонентов (вариант 2)
     */
    BACKGROUND_COMP_2(150, 182, 223),

    /**
     * Цвет наведения курсора мыши на элементы
     */
    BACKGROUND_SELECT(250, 250, 250),

    /**
     * Цвет наведения курсора мыши на кнопку выхода
     */
    BACKGROUND_SELECT_FIRE(230, 50, 50);

    private Color color;

    Colors(int r, int g, int b){
        color = new Color(r, g, b);
    }

    public Color getColor() {
        return color;
    }
}
