package ru.strict.view.swing;

import org.junit.jupiter.api.Test;

class NotificationViewTest {

    @Test
    void testView_success() {
        var notification = new NotificationView("Test");
        notification.show();
        notification.hide();
    }

    @Test
    void testView_longText_success() {
        var notification = new NotificationView("TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest");
        notification.show();
        notification.hide();
    }
}
