package ru.strict.view.swing;

import javax.swing.*;
import java.awt.*;

public class TextFieldWithPlaceholder extends JTextField {
    private String placeholder;

    public TextFieldWithPlaceholder() {
        init();
    }

    public TextFieldWithPlaceholder(int countColumns) {
        super(countColumns);
        init();
    }

    public TextFieldWithPlaceholder(String text) {
        super(text);
        init();
    }

    public TextFieldWithPlaceholder(String text, int countColumns) {
        super(text, countColumns);
        init();
    }

    private void init() {
        placeholder = "";
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    protected void paintComponent(Graphics pG) {
        super.paintComponent(pG);

        if (placeholder.isEmpty() || !getText().isEmpty()) {
            return;
        }

        var g = (Graphics2D)pG;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(getDisabledTextColor());
        g.drawString(placeholder, getInsets().left, pG.getFontMetrics().getMaxAscent() + getInsets().top);
    }
}
