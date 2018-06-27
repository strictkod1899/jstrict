package ru.strict.swing.views.components;

import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class TextFieldPlaceholder extends JTextField {

    private String placeholder;

    private void init(){
        placeholder = "";
    }

    public TextFieldPlaceholder() {
        init();
    }

    public TextFieldPlaceholder(final int countColumns) {
        super(countColumns);
        init();
    }

    public TextFieldPlaceholder(final String text) {
        super(text);
        init();
    }

    public TextFieldPlaceholder(final String text, final int countColumns) {
        super(text, countColumns);
        init();
    }

    public void setPlaceholder(final String placeholder) {
        this.placeholder = placeholder;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    @Override
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        if (placeholder.length() == 0 || getText().length() > 0) {
            return;
        }

        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(getDisabledTextColor());
        g.drawString(placeholder, getInsets().left, pG.getFontMetrics()
                .getMaxAscent() + getInsets().top);
    }
}
