package ru.strict.view.swing;

import lombok.Getter;

import java.awt.*;

@Getter
public enum ViewColor {
    FRAME_BACKGROUND(255, 255, 255),
    BACKGROUND_COMP(77, 126, 190),
    BACKGROUND_COMP_2(150, 182, 223),
    FOCUS_BACKGROUND(240, 240, 240),
    PRESS_BACKGROUND(230, 50, 50);

    private Color color;

    ViewColor(int r, int g, int b) {
        this.color = new Color(r, g, b);
    }
}
