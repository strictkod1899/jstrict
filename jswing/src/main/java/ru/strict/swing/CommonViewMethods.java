package ru.strict.swing;

import ru.strict.utils.UtilLogger;

import java.awt.*;
import java.awt.Color;

/**
 * Класс предоставляет методы используемые в окнах фрейма, диалога и панели.
 * Он используется, чтобы предотвратить дублирование кода при кастомной работе с компонентами класса Window.
 * Например, т.к. диалоговое окно и окно фрейма представляют разные классы
 */
class CommonViewMethods {

    /**
     * Обновление окна
     */
    public static void refresh(Window window) {
        window.validate();
        window.invalidate();
        window.repaint();
    }

    /**
     * Создание формы
     *
     */
    public static void build(Window window) {
        window.setBackground(new Color(255, 255, 255));

        int width = window.getPreferredSize().width;
        int height = window.getPreferredSize().height;

        if(width == 0){
            width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        }
        if(height == 0){
            height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        }

        window.setSize(width, height);
        window.setPreferredSize(new Dimension(width, height));
        refreshSize(window);
    }

    public static void refreshSize(Container container) {
        int maxWidth = container.getMaximumSize().width;
        int maxHeight = container.getMaximumSize().height;
        int minWidth = container.getMinimumSize().width;
        int minHeight = container.getMinimumSize().height;

        int width = container.getPreferredSize().width;
        int height = container.getPreferredSize().height;

        if (maxWidth > 0 && width > maxWidth) {
            container.setSize(maxWidth, container.getPreferredSize().height);
            container.setPreferredSize(new Dimension(maxWidth, container.getPreferredSize().height));
        }
        if (maxHeight > 0 && height > maxHeight) {
            container.setSize(container.getPreferredSize().width, maxHeight);
            container.setPreferredSize(new Dimension(container.getPreferredSize().width, maxHeight));
        }

        if (minWidth > 0 && width < minWidth) {
            container.setSize(minWidth, container.getPreferredSize().height);
            container.setPreferredSize(new Dimension(minWidth, container.getPreferredSize().height));
        }

        if (minHeight > 0 && height < minHeight) {
            container.setSize(container.getPreferredSize().width, minHeight);
            container.setPreferredSize(new Dimension(container.getPreferredSize().width, minHeight));
        }
    }

    /**
     * Установить размер формы по содержимому
     */
    public static void pack(Container container) {
        UtilLogger.info(CommonViewMethods.class, "pack  started");
        Component[] components = container.getComponents();
        int width = 0;
        int height = 0;
        for (Component component : components) {
            width += component.getPreferredSize().width;
            height += component.getPreferredSize().height;
        }

        container.setSize(width, height);
        container.setPreferredSize(new Dimension(width, height));
        UtilLogger.info(CommonViewMethods.class, "pack  finished");
    }

    /**
     * Установить высоту формы по содержимому
     */
    public static void packHeight(Container container) {
        UtilLogger.info(CommonViewMethods.class, "packHeight  started");
        Component[] components = container.getComponents();
        int height = 0;
        for (Component component : components) {
            height += component.getPreferredSize().height;
        }
        container.setSize(container.getPreferredSize().width, height);
        container.setPreferredSize(new Dimension(container.getWidth(), height));
        UtilLogger.info(CommonViewMethods.class, "packHeight  finished");
    }

    /**
     * Установить ширину формы по содержимому
     */
    public static void packWidth(Container container) {
        UtilLogger.info(CommonViewMethods.class, "packWidth  started");
        Component[] components = container.getComponents();
        int width = 0;
        for (Component component : components) {
            width += component.getPreferredSize().width;
        }
        container.setSize(width, container.getPreferredSize().height);
        container.setPreferredSize(new Dimension(width, container.getHeight()));
        UtilLogger.info(CommonViewMethods.class, "packWidth  finished");
    }
}
