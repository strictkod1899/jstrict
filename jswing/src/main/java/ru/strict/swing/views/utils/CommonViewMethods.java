package ru.strict.swing.views.utils;

import ru.strict.utils.UtilLogger;

import java.awt.*;

/**
 * Класс предоставляет методы используемые в окнах фрейма, диалога и панели.
 * Он используется, чтобы предотвратить дублирование кода при кастомной работе с компонентами класса Window.
 * Например, т.к. диалоговое окно и окно фрейма представляют разные классы
 */
public class CommonViewMethods {

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
        window.setMaximumSize(new Dimension(0, 0));
        window.setMinimumSize(new Dimension(0, 0));

        int width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

        window.setSize(width, height);
        window.setPreferredSize(new Dimension(width, height));
        window.setMaximumSize(new Dimension(width, height));
    }

    /**
     * Установить размер формы по содержимому
     */
    public static void pack(Container container) {
        UtilLogger.info(CommonViewMethods.class, "pack - started");
        Component[] components = container.getComponents();
        int width = 0;
        int height = 0;
        for (Component component : components) {
            width += component.getPreferredSize().width;
            height += component.getPreferredSize().height;
        }

        container.setSize(width, height);
        container.setPreferredSize(new Dimension(width, height));
        UtilLogger.info(CommonViewMethods.class, "pack - finished");
    }

    /**
     * Установить высоту формы по содержимому
     */
    public static void packHeight(Container container) {
        UtilLogger.info(CommonViewMethods.class, "packHeight - started");
        Component[] components = container.getComponents();
        int height = 0;
        for (Component component : components) {
            height += component.getPreferredSize().height;
        }
        container.setSize(container.getPreferredSize().width, height);
        container.setPreferredSize(new Dimension(container.getWidth(), height));
        UtilLogger.info(CommonViewMethods.class, "packHeight - finished");
    }

    /**
     * Установить ширину формы по содержимому
     */
    public static void packWidth(Container container) {
        UtilLogger.info(CommonViewMethods.class, "packWidth - started");
        Component[] components = container.getComponents();
        int width = 0;
        for (Component component : components) {
            width += component.getPreferredSize().width;
        }
        container.setSize(width, container.getPreferredSize().height);
        container.setPreferredSize(new Dimension(width, container.getHeight()));
        UtilLogger.info(CommonViewMethods.class, "packWidth - finished");
    }
}
