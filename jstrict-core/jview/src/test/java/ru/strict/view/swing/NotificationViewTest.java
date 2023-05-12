package ru.strict.view.swing;

import org.junit.jupiter.api.Test;

class NotificationViewTest {

    @Test
    void testView_success() {
        var notification = new NotificationWindow("Test");
        notification.show();
        notification.hide();
    }

    @Test
    void testView_longText_success() {
        var notification = new NotificationWindow("TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest");
        notification.show();
        notification.hide();
    }
}
