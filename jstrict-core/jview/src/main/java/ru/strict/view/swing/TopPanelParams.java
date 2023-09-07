package ru.strict.view.swing;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Optional;

@Getter
@FieldDefaults(level = AccessLevel.PACKAGE)
public class TopPanelParams {
    Container parentWindow;
    boolean enableWindowMoving;
    boolean visibleTurnButton;
    boolean visibleChangeSizeButton;
    boolean visibleExitButton;
    int hGap;
    int vGap;
    String title;
    int buttonSize;
    int iconSize;
    String iconPath;
    Color background;
    List<JMenu> menuList;
    TopPanel.Action exitButtonAction;

    public Optional<String> getIconPath() {
        return Optional.ofNullable(iconPath);
    }

    public static TopPanelParamsBuilder builder() {
        return new TopPanelParamsBuilder();
    }
}
