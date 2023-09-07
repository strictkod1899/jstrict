package ru.strict.view.notification;

import javax.swing.*;
import java.awt.*;

public class MultilineNotificationWindow extends BaseNotification {

    public MultilineNotificationWindow(String message, Params params, NotificationListener listener) {
        super(message, params, listener);
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

        var scrollWidth = (int) params.getSize().getWidth() - 60;
        var scrollHeight = (int) params.getSize().getHeight() - 60;
        messageScroll.setPreferredSize(new Dimension(scrollWidth, scrollHeight));

        return messageScroll;
    }
}
