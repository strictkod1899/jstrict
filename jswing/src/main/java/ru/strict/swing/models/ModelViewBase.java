package ru.strict.swing.models;

import ru.strict.swing.enums.Colors;
import ru.strict.swing.utils.UtilSwing;

import java.awt.*;

/**
 * Модель базового графического элемента
 */
public abstract class ModelViewBase extends ModelBase {

    protected static final double RATIO_FONT_TEXT = 1.3;

    private Color background;

    private Color foreground;

    private int width, height;

    private int maxWidth, maxHeight;

    private int minWidth, minHeight;

    private void initialize(){
        background = Colors.BACKGROUND_FORM.getColor();
        foreground = new Color(42, 45, 52);
        width = 0;
        height = 0;
        maxWidth = 0;
        maxHeight = 0;
        minWidth = 0;
        minHeight = 0;
    }

    public ModelViewBase(){
        super();
        initialize();
    }

    public Color getForeground() {
        return foreground;
    }

    public void setForeground(Color foreground) {
        this.foreground = foreground;
    }

    /**
     * Цвет фона
     */
    public Color getBackground() {
        return background;
    }

    /**
     * Цвет фона
     */
    public void setBackground(Color background) {
        this.background = background;
    }

    /**
     * Ширина окна
     */
    public int getWidth() {
        return width;
    }

    /**
     * Ширина окна
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Высота окна
     */
    public int getHeight() {
        return height;
    }

    /**
     * Высота окна
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Максимальная ширина
     */
    public int getMaxWidth() {
        return maxWidth;
    }

    /**
     * Максимальная ширина
     */
    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    /**
     * Максимальная высота
     */
    public int getMaxHeight() {
        return maxHeight;
    }

    /**
     * Максимальная высота
     */
    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public int getMinWidth() {
        return minWidth;
    }

    public void setMinWidth(int minWidth) {
        this.minWidth = minWidth;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(int minHeight) {
        this.minHeight = minHeight;
    }
}
