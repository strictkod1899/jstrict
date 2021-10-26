package ru.strict.view.swing;

import java.awt.*;

public class BigNotificationView implements Notification {

    private final NotificationView notificationView;

    public BigNotificationView(String message) {
        this(message, false);
    }

    public BigNotificationView(String message, boolean dialog) {
        var wrapperText = LabelUtil.wrapText(message, 80);
        this.notificationView = new NotificationView(wrapperText, dialog);

        notificationView.getSettings().setSize(new Dimension(500, 240));
        notificationView.repaint();
    }

    @Override
    public void show() {
        notificationView.show();
    }

    @Override
    public void hide() {
        notificationView.hide();
    }
}
