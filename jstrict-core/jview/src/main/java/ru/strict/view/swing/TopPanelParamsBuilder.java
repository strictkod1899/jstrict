package ru.strict.view.swing;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import ru.strict.exception.Errors;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class TopPanelParamsBuilder {
    Container parentWindow;
    Boolean enableWindowMoving;
    Boolean visibleTurnButton;
    Boolean visibleChangeSizeButton;
    Boolean visibleExitButton;
    Integer hGap;
    Integer vGap;
    String title;
    Integer buttonSize;
    Integer iconSize;
    String iconPath;
    Color background;
    List<JMenu> menuList;
    TopPanel.Action exitButtonAction;

    Errors errors;

    public TopPanelParamsBuilder() {
        errors = new Errors();
        menuList = new ArrayList<>();
    }

    public TopPanelParamsBuilder parentWindow(Container parentWindow) {
        this.parentWindow = parentWindow;
        return this;
    }

    public TopPanelParamsBuilder visibleTurnButton(boolean visibleTurnButton) {
        this.visibleTurnButton = visibleTurnButton;
        return this;
    }

    public TopPanelParamsBuilder visibleChangeSizeButton(boolean visibleChangeSizeButton) {
        this.visibleChangeSizeButton = visibleChangeSizeButton;
        return this;
    }

    public TopPanelParamsBuilder visibleExitButton(boolean visibleExitButton) {
        this.visibleExitButton = visibleExitButton;
        return this;
    }

    public TopPanelParamsBuilder title(String title) {
        this.title = title;
        return this;
    }

    public TopPanelParamsBuilder iconPath(String iconPath) {
        this.iconPath = iconPath;
        return this;
    }

    public TopPanelParamsBuilder addMenu(JMenu menu) {
        this.menuList.add(menu);
        return this;
    }

    public TopPanelParamsBuilder exitButtonAction(TopPanel.Action exitButtonAction) {
        this.exitButtonAction = exitButtonAction;
        return this;
    }

    public TopPanelParams build() {
        checkRequiredFields();
        if (errors.isPresent()) {
            throw errors.toException();
        }

        fillDefaultFields();

        checkVisibleParams();
        if (errors.isPresent()) {
            throw errors.toException();
        }

        return createFromBuilder();
    }

    private void checkRequiredFields() {
        if (parentWindow == null) {
            errors.addError(TopPanelError.errParentWindowForTopPanelIsRequired());
        }
    }

    private void fillDefaultFields() {
        if (title == null) {
            title = "";
        }
        if (buttonSize == null) {
            buttonSize = 11;
        }
        if (iconSize == null) {
            iconSize = (int) ((double) this.buttonSize * 1.5D);
        }
        if (vGap == null) {
            vGap = 10;
        }
        if (hGap == null) {
            hGap = this.vGap * 2;
        }
        if (visibleTurnButton == null) {
            visibleTurnButton = true;
        }
        if (visibleChangeSizeButton == null) {
            visibleChangeSizeButton = true;
        }
        if (visibleExitButton == null) {
            visibleExitButton = true;
        }
        if (enableWindowMoving == null) {
            enableWindowMoving = true;
        }
        if (background == null) {
            background = ViewColor.FRAME_BACKGROUND.getColor();
        }
    }

    private void checkVisibleParams() {
        if (visibleExitButton && exitButtonAction == null) {
            errors.addError(TopPanelError.errExitButtonActionIsRequired());
        }
    }

    private TopPanelParams createFromBuilder() {
        var params = new TopPanelParams();
        params.parentWindow = parentWindow;
        params.enableWindowMoving = enableWindowMoving;
        params.visibleTurnButton = visibleTurnButton;
        params.visibleChangeSizeButton = visibleChangeSizeButton;
        params.visibleExitButton = visibleExitButton;
        params.hGap = hGap;
        params.vGap = vGap;
        params.title = title;
        params.buttonSize = buttonSize;
        params.iconSize = iconSize;
        params.iconPath = iconPath;
        params.background = background;
        params.menuList = menuList;
        params.exitButtonAction = exitButtonAction;

        return params;
    }
}
