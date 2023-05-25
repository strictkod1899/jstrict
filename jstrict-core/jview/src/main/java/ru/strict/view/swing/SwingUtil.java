package ru.strict.view.swing;

import lombok.experimental.UtilityClass;

import java.awt.*;

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
}
