package ru.strict.view.notification;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MultilineNotificationWindowTest {

    @Test
    void testView_success() {
        var notificationController = Mockito.mock(NotificationListener.class);
        var params = BaseNotification.Params.createDefault(false);

        var notification = new MultilineNotificationWindow("Test", params, notificationController);
        notification.show();
        notification.hide();
    }

    @Test
    void testView_longText_success() {
        var notificationController = Mockito.mock(NotificationListener.class);
        var params = BaseNotification.Params.createDefault(false);

        var notification = new MultilineNotificationWindow("TestTestTestTestTest TestTestTestTestTest TestTestTestTestTestTest" +
                "TestTestTestTestTestTestTest TestTestTestTestTestTest TestTestTestTestTestTestTestTestTestTestTestTest" +
                "TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest" +
                "TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest" +
                "TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest" +
                "TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest" +
                "TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest" +
                "TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest" +
                "TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest" +
                "TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest" +
                "TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest" +
                "TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest" +
                "TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest" +
                "TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest" +
                "TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest" +
                "TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest" +
                "TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest" +
                "TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest", params, notificationController);
        notification.show();
        notification.hide();
    }
}
