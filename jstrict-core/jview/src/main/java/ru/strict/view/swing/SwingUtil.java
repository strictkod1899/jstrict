package ru.strict.view.swing;

import lombok.experimental.UtilityClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

@UtilityClass
public class SwingUtil {

    public void refresh(Component component) {
        component.validate();
        component.invalidate();
        component.repaint();
    }

    public static int calcSizeByRatio(int frameWidth, int frameHeight, double userRatio) {
        var screenSizeRatio = Math.abs(1.0D - Math.abs(1.6D - (double)frameWidth / (double)frameHeight));
        return (int)(screenSizeRatio * (double)frameWidth / 100.0D * userRatio);
    }

    public JPanel createImageButton(
            int hgap,
            int vgap,
            Color baseColor,
            Color selectColor,
            ImageIcon image,
            MouseListener... mouseListeners) {
        var layout = new FlowLayout(FlowLayout.CENTER, hgap, vgap);
        var panel = new JPanel();
        panel.setLayout(layout);
        panel.setBackground(baseColor);
        if (mouseListeners != null) {
            for (var mouseListener : mouseListeners) {
                panel.addMouseListener(mouseListener);
            }
        }

        panel.addMouseListener(new ChangeBackgroundMouseAction(panel, selectColor, baseColor));
        var label = new JLabel();
        label.addMouseListener(new ChangeBackgroundMouseAction(panel, selectColor, baseColor));
        if (image != null) {
            label.setIcon(image);
        }

        panel.add(label);
        return panel;
    }
}
