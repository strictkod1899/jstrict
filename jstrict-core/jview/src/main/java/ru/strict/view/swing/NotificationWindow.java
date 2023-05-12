package ru.strict.view.swing;

import javax.swing.*;

public class NotificationWindow extends BaseNotification {

    public NotificationWindow(String message) {
        super(message);
    }

    public NotificationWindow(String message, boolean dialog) {
        super(message, dialog);
    }

    @Override
    JComponent createMessageComponent(String message) {
        return new JLabel(message);
    }
}
