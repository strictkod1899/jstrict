package ru.strict.swing.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Событие реагирующее на наведение мыши на кнопку для смены цвета
 */
public class MouseActionChangeBackground extends MouseAdapter {

    // Проверяем была ли нажата какая-нибудь кнопка или нет
    private boolean isButPressed = false;

    private JPanel panel;
    private Color selectColor, baseColor;

    public MouseActionChangeBackground(JPanel panel, Color selectColor, Color baseColor) {
        this.panel = panel;
        this.selectColor = selectColor;
        this.baseColor = baseColor;
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        if(!isButPressed) {
            panel.setBackground(selectColor);
            panel.repaint();
        }
    }

    @Override
    public void mouseExited(MouseEvent event) {
        if(!isButPressed) {
            panel.setBackground(baseColor);
            panel.repaint();
        }
    }

    public void setButPressed(boolean butPressed) {
        isButPressed = butPressed;
    }

    public boolean isButPressed() {
        return isButPressed;
    }
}
