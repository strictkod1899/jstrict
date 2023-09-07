package ru.strict.view.notification;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

class NotificationWindowTest {

    @Test
    void testView_WithCustomButtons_NoError() {
        var notificationController = Mockito.mock(NotificationListener.class);

        var customButtonParams = BaseNotification.CustomButtonParams.builder().
                text("custom button").
                actions(List.of()).
                build();

        var params = BaseNotification.Params.createDefault(false);
        params.setButtonsAlignment(BaseNotification.ButtonsAlignment.VERTICAL);
        params.addCustomButton(customButtonParams);

        var notification = new NotificationWindow("Test", params, notificationController);

        notification.show();
        notification.hide();
    }

    @Test
    void testView_longText_success() {
        var notificationController = Mockito.mock(NotificationListener.class);
        var params = BaseNotification.Params.createDefault(false);

        var notification =
                new NotificationWindow("TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest", params, notificationController);
        notification.show();
        notification.hide();
    }
}
