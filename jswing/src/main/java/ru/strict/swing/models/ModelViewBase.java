package ru.strict.swing.models;

import ru.strict.swing.enums.Colors;
import ru.strict.swing.enums.Fonts;
import ru.strict.utils.UtilFrame;

import java.awt.*;

/**
 * Модель базового графического элемента
 */
public abstract class ModelViewBase extends ModelBase {

    protected static final double RATIO_FONT_TEXT = 1.3;

    private Color background;

    private Font fontText;

    private int gap;

    private int width, height;

    private int maxWidth, maxHeight;

    private int minWidth, minHeight;

    private void initDefault(){
        background = Colors.BACKGROUND_FORM.getColor();
        fontText = new Font(Fonts.UBUNTU.getFontName(), Font.PLAIN,
                UtilFrame.calcSizeByRatio(Toolkit.getDefaultToolkit().getScreenSize().width,
                        Toolkit.getDefaultToolkit().getScreenSize().height,
                        RATIO_FONT_TEXT));
        gap = 1;
        width = 0;
        height = 0;
        maxWidth = 0;
        maxHeight = 0;
        minWidth = 0;
        minHeight = 0;
    }

    public ModelViewBase(){
        super();
        initDefault();
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
     * Шрифт основного текста
     */
    public Font getFontText() {
        return fontText;
    }

    /**
     * Шрифт основного текста
     */
    public void setFontText(Font fontText) {
        this.fontText = fontText;
    }

    /**
     * Интервал между компонентами
     */
    public int getGap() {
        return gap;
    }

    /**
     * Интервал между компонентами
     */
    public void setGap(int gap) {
        this.gap = gap;
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
