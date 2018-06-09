package ru.strict.swing.views;

import ru.strict.utils.StrictUtilLogger;
import ru.strict.swing.models.StrictModelFormBase;
import ru.strict.swing.views.dialogs.StrictDialogDefault;
import ru.strict.swing.views.frames.StrictFrameDefault;

import java.awt.*;

/**
 * Класс предоставляет методы используемые в окнах фрейма, диалога и панели.
 * Он используется, чтобы предотвратить дублирование кода кастомной работе с компонентами класса Window.
 * Например, т.к. диалоговое окно и окно фрейма представляют разные классы
 */
public class StrictGeneralView {

    private Window window;

    public StrictGeneralView(Window window) {
        this.window = window;
    }

    /**
     * Обновление формы
     */
    public void updateView() {
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
    public <M extends StrictModelFormBase> void build(M model) {
        ((StrictFormBase) window).setModel(model);
        window.setBackground(model.getBackground());

        window.setSize(model.getWidth(), model.getHeight());
        window.setPreferredSize(new Dimension(model.getWidth(), model.getHeight()));
    }

    /**
     * Установить размер формы по содержимому
     */
    public void pack() {
        StrictUtilLogger.info(StrictGeneralView.class, "pack - started");
        Component[] components = window.getComponents();
        int width = 0;
        int height = 0;
        for (Component component : components) {
            width += component.getPreferredSize().width;
            height += component.getPreferredSize().height;
        }
        window.setSize(width, height);
        window.setPreferredSize(new Dimension(width, height));
        StrictUtilLogger.info(StrictGeneralView.class, "pack - finished");
    }

    /**
     * Установить высоту формы по содержимому
     */
    public void packHeight() {
        StrictUtilLogger.info(StrictGeneralView.class, "packHeight - started");
        Component[] components = window.getComponents();
        int height = 0;
        for (Component component : components)
            height += component.getPreferredSize().height;
        window.setSize(window.getWidth(), height);
        window.setPreferredSize(new Dimension(window.getWidth(), height));
        StrictUtilLogger.info(StrictGeneralView.class, "packHeight - finished");
    }

    /**
     * Установить ширину формы по содержимому
     */
    public void packWidth() {
        StrictUtilLogger.info(StrictGeneralView.class, "packWidth - started");
        Component[] components = window.getComponents();
        int width = 0;
        for (Component component : components)
            width += component.getPreferredSize().width;

        window.setSize(width, window.getHeight());
        window.setPreferredSize(new Dimension(width, window.getHeight()));
        StrictUtilLogger.info(StrictGeneralView.class, "packWidth - finished");
    }

    /**
     * Установить менеджера компоновки
     * @param manager
     */
    public void setLayout(LayoutManager manager) {
        if (window instanceof StrictFrameDefault)
            ((StrictFrameDefault) window).getPanelContent().setLayout(manager);
        else if(window instanceof StrictDialogDefault)
            ((StrictDialogDefault)window).getPanelContent().setLayout(manager);
    }

    /**
     * Добавить компонент
     * @param comp
     * @return
     */
    public Component add(Component comp) {
        if (window instanceof StrictFrameDefault)
            ((StrictFrameDefault) window).getPanelContent().add(comp);
        else if(window instanceof StrictDialogDefault)
            ((StrictDialogDefault)window).getPanelContent().add(comp);
        return comp;
    }

    /**
     * Установить цвет
     * @param bgColor
     */
    public void setBackground(Color bgColor) {
        if (window instanceof StrictFrameDefault)
            ((StrictFrameDefault) window).getPanelContent().setBackground(bgColor);
        else if(window instanceof StrictDialogDefault)
            ((StrictDialogDefault)window).getPanelContent().setBackground(bgColor);
    }
}
