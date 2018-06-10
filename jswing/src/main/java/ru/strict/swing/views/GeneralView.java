package ru.strict.swing.views;

import ru.strict.swing.views.dialogs.DialogDefault;
import ru.strict.swing.views.frames.FrameDefault;
import ru.strict.utils.UtilLogger;
import ru.strict.swing.models.ModelFormBase;

import java.awt.*;

/**
 * Класс предоставляет методы используемые в окнах фрейма, диалога и панели.
 * Он используется, чтобы предотвратить дублирование кода кастомной работе с компонентами класса Window.
 * Например, т.к. диалоговое окно и окно фрейма представляют разные классы
 */
public class GeneralView {

    private Window window;

    public GeneralView(Window window) {
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
    public <M extends ModelFormBase> void build(M model) {
        ((FormBase) window).setModel(model);
        window.setBackground(model.getBackground());

        window.setSize(model.getWidth(), model.getHeight());
        window.setPreferredSize(new Dimension(model.getWidth(), model.getHeight()));
    }

    /**
     * Установить размер формы по содержимому
     */
    public void pack() {
        UtilLogger.info(GeneralView.class, "pack - started");
        Component[] components = window.getComponents();
        int width = 0;
        int height = 0;
        for (Component component : components) {
            width += component.getPreferredSize().width;
            height += component.getPreferredSize().height;
        }
        window.setSize(width, height);
        window.setPreferredSize(new Dimension(width, height));
        UtilLogger.info(GeneralView.class, "pack - finished");
    }

    /**
     * Установить высоту формы по содержимому
     */
    public void packHeight() {
        UtilLogger.info(GeneralView.class, "packHeight - started");
        Component[] components = window.getComponents();
        int height = 0;
        for (Component component : components)
            height += component.getPreferredSize().height;
        window.setSize(window.getWidth(), height);
        window.setPreferredSize(new Dimension(window.getWidth(), height));
        UtilLogger.info(GeneralView.class, "packHeight - finished");
    }

    /**
     * Установить ширину формы по содержимому
     */
    public void packWidth() {
        UtilLogger.info(GeneralView.class, "packWidth - started");
        Component[] components = window.getComponents();
        int width = 0;
        for (Component component : components)
            width += component.getPreferredSize().width;

        window.setSize(width, window.getHeight());
        window.setPreferredSize(new Dimension(width, window.getHeight()));
        UtilLogger.info(GeneralView.class, "packWidth - finished");
    }

    /**
     * Установить менеджера компоновки
     * @param manager
     */
    public void setLayout(LayoutManager manager) {
        if (window instanceof FrameDefault)
            ((FrameDefault) window).getPanelContent().setLayout(manager);
        else if(window instanceof DialogDefault)
            ((DialogDefault)window).getPanelContent().setLayout(manager);
    }

    /**
     * Добавить компонент
     * @param comp
     * @return
     */
    public Component add(Component comp) {
        if (window instanceof FrameDefault)
            ((FrameDefault) window).getPanelContent().add(comp);
        else if(window instanceof DialogDefault)
            ((DialogDefault)window).getPanelContent().add(comp);
        return comp;
    }

    /**
     * Установить цвет
     * @param bgColor
     */
    public void setBackground(Color bgColor) {
        if (window instanceof FrameDefault)
            ((FrameDefault) window).getPanelContent().setBackground(bgColor);
        else if(window instanceof DialogDefault)
            ((DialogDefault)window).getPanelContent().setBackground(bgColor);
    }
}
