package ru.strict.view.swing;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChangeBackgroundMouseAction extends MouseAdapter {
    final JPanel panel;
    final Color selectColor;
    final Color baseColor;

    boolean isButPressed;

    public ChangeBackgroundMouseAction(
            JPanel panel,
            Color selectColor,
            Color baseColor) {
        this.panel = panel;
        this.selectColor = selectColor;
        this.baseColor = baseColor;

        this.isButPressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        if (isButPressed) {
            return;
        }

        panel.setBackground(this.selectColor);
        panel.repaint();
    }

    @Override
    public void mouseExited(MouseEvent event) {
        if (isButPressed) {
            return;
        }

        panel.setBackground(this.baseColor);
        panel.repaint();
    }

    public void setButPressed(boolean butPressed) {
        isButPressed = butPressed;
    }

    public boolean isButPressed() {
        return isButPressed;
    }
}
