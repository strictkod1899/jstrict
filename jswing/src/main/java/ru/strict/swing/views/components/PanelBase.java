package ru.strict.swing.views.components;

import ru.strict.patterns.mvc.views.IView;

import javax.swing.*;
import java.awt.*;

/**
 * Графическая панель
 */
public class PanelBase extends JPanel implements IView {

    public PanelBase() {
        build();
    }

    private void build() {
        setBackground(new Color(255, 255, 255));
    }

    @Override
    public void refresh() {
        validate();
        invalidate();
        repaint();
    }

    @Override
    public void launch() {
        setVisible(true);
    }

    @Override
    public void destroy() {
        setVisible(false);
    }
}
