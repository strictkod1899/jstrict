package ru.strict.view.swing;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

class NotificationViewTest {

    @Test
    void testView_WithCustomButtons_NoError() {
        var notification = new NotificationWindow("Test");

        var customButtonParams = BaseNotification.CustomButtonParams.builder().
                text("custom button").
                actions(List.of()).
                build();

        notification.getParams().setButtonsAlignment(BaseNotification.ButtonsAlignment.VERTICAL);
        notification.getParams().addCustomButton(customButtonParams);
        notification.show();
        notification.hide();
    }

    @Test
    void testView_longText_success() {
        var notification =
                new NotificationWindow("TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest");
        notification.show();
        notification.hide();
    }
}
