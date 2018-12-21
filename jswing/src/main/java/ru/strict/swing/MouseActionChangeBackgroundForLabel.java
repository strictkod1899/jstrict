package ru.strict.swing;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Событие реагирующее на наведение мыши на JLabel для смены цвета
 */
class MouseActionChangeBackgroundForLabel extends MouseAdapter {

    // Проверяем была ли нажата какая-нибудь кнопка или нет
    private boolean isButPressed = false;

    private JLabel lab;

    public MouseActionChangeBackgroundForLabel(JLabel lab) {
        this.lab = lab;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(!isButPressed) {
            lab.setOpaque(true);
            lab.repaint();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(!isButPressed) {
            lab.setOpaque(false);
            lab.repaint();
        }
    }

    public void setButPressed(boolean butPressed) {
        isButPressed = butPressed;
    }

    public boolean isButPressed() {
        return isButPressed;
    }
}
