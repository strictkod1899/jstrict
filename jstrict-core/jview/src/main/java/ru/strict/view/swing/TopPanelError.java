package ru.strict.view.swing;

import lombok.experimental.UtilityClass;
import ru.strict.exception.CodeableException;

@UtilityClass
public class TopPanelError {
    public static String parentWindowForTopPanelIsRequiredErrorCode = "57e5d609-001";
    public static String exitButtonActionIsRequiredErrorCode = "57e5d609-002";

    public CodeableException errParentWindowForTopPanelIsRequired() {
        var errMsg = "parent window for TopPanel is required";
        return new CodeableException(parentWindowForTopPanelIsRequiredErrorCode, errMsg);
    }

    public CodeableException errExitButtonActionIsRequired() {
        var errMsg = "action for exitButton is required";
        return new CodeableException(exitButtonActionIsRequiredErrorCode, errMsg);
    }
}
