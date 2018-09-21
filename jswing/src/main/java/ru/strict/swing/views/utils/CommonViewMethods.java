package ru.strict.swing.views.utils;

import ru.strict.utils.UtilLogger;
import ru.strict.swing.models.ModelFormBase;

import javax.swing.*;
import java.awt.*;

/**
 * Класс предоставляет методы используемые в окнах фрейма, диалога и панели.
 * Он используется, чтобы предотвратить дублирование кода кастомной работе с компонентами класса Window.
 * Например, т.к. диалоговое окно и окно фрейма представляют разные классы
 */
public class CommonViewMethods {

    /**
     * Обновление окна
     */
    public static void updateView(Window window) {
        window.validate();
        window.invalidate();
        window.repaint();
    }

    /**
     * Создание формы
     *
     * @param model
     * @param <M>
     */
    public static <M extends ModelFormBase> void build(Window window, M model) {
        window.setBackground(model.getBackground());

        window.setSize(model.getWidth(), model.getHeight());
        window.setPreferredSize(new Dimension(model.getWidth(), model.getHeight()));
        window.setMaximumSize(new Dimension(model.getWidth(), model.getHeight()));
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

    /**
     * Добавить компонент к текущему окну
     * @param comp
     * @return
     */
    public static Component add(Container container, Component comp) {
        container.add(comp);
        return comp;
    }

    /**
     * Установить менеджер компоновки
     */
    public static void setLayout(Container container, LayoutManager layout) {
        if(container != null && layout != null){
            container.setLayout(layout);
        }
    }

    /**
     * Установить цвет фона
     */
    public static void setBackground(Container container, Color color) {
        if(container != null && color != null) {
            container.setBackground(color);
        }
    }

    /**
     * Добавить компонент к текущему окну
     * @param comp
     * @return
     */
    public static Component add(Window window, Component comp) {
        if(window != null && comp != null) {
            window.add(comp);
        }
        return comp;
    }
}
