package ru.strict.view.swing;

import org.junit.jupiter.api.Test;

class BigNotificationViewTest {

    @Test
    void testView_success() {
        var notification = new BigNotificationView("Test");
        notification.show();
        notification.hide();
    }

    @Test
    void testView_longText_success() {
        var notification = new BigNotificationView("TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest" +
                "TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest" +
                "TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest");
        notification.show();

        notification.hide();
    }
}
