package ru.strict.swing.views.components;

import ru.strict.patterns.mvc.views.IView;

import javax.swing.*;
import java.awt.*;

/**
 * Графическая панель
 */
public class PanelBase extends JPanel implements IView {

    private boolean isBuilt;

    @Override
    public PanelBase build() {
        setBackground(new Color(255, 255, 255));
        isBuilt = true;
        return this;
    }

    @Override
    public void launch() {
        if(!isBuilt) {
            build();
        }
        setVisible(true);
    }

    @Override
    public void refresh() {
        validate();
        invalidate();
        repaint();
    }

    @Override
    public void destroy() {
        setVisible(false);
    }

    protected boolean isBuilt() {
        return isBuilt;
    }

    protected void setBuilt(boolean built) {
        isBuilt = built;
    }
}
