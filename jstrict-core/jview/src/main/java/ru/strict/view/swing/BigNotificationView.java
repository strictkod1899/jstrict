package ru.strict.view.swing;

import javax.swing.*;
import java.awt.*;

public class BigNotificationView extends BaseNotification {

    public BigNotificationView(String message) {
        this(message, false);
    }

    public BigNotificationView(String message, boolean dialog) {
        super(message, dialog);
    }

    @Override
    JComponent createMessageComponent(String message) {
        var textArea = new JTextArea(message);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        return textArea;
    }

    @Override
    JComponent wrapMessageComponent(JComponent messageComponent) {
        var messageScroll = new JScrollPane(messageComponent);
        messageScroll.setOpaque(true);
        messageScroll.setBorder(BorderFactory.createEmptyBorder());

        var scrollWidth = (int) getSettings().getSize().getWidth() - 60;
        var scrollHeight = (int) getSettings().getSize().getHeight() - 60;
        messageScroll.setPreferredSize(new Dimension(scrollWidth, scrollHeight));

        return messageScroll;
    }

    @Override
    Settings createSettings() {
        var settings = super.createSettings();
        settings.setSize(new Dimension(500, 240));

        return settings;
    }
}