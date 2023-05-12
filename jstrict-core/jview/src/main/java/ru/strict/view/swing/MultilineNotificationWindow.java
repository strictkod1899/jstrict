package ru.strict.view.swing;

import javax.swing.*;
import java.awt.*;

public class MultilineNotificationWindow extends BaseNotification {

    public MultilineNotificationWindow(String message) {
        this(message, false);
    }

    public MultilineNotificationWindow(String message, boolean dialog) {
        super(message, dialog);
        getParams().setSize(new Dimension(500, 240));
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

        var scrollWidth = (int) getParams().getSize().getWidth() - 60;
        var scrollHeight = (int) getParams().getSize().getHeight() - 60;
        messageScroll.setPreferredSize(new Dimension(scrollWidth, scrollHeight));

        return messageScroll;
    }
}
