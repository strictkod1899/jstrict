package ru.strict.view.notification;

import javax.swing.*;

public class NotificationWindow extends BaseNotification {

    public NotificationWindow(String message, Params params, NotificationListener listener) {
        super(message, params, listener);
    }

    @Override
    JComponent createMessageComponent(String message) {
        return new JLabel(message);
    }
}
