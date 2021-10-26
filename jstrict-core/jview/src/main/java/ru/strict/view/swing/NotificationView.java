package ru.strict.view.swing;

import javax.swing.*;

public class NotificationView extends BaseNotification {

    public NotificationView(String message) {
        super(message);
    }

    public NotificationView(String message, boolean dialog) {
        super(message, dialog);
    }

    @Override
    JComponent createMessageComponent(String message) {
        return new JLabel(message);
    }
}
