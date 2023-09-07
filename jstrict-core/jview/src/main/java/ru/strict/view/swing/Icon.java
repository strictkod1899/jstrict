package ru.strict.view.swing;

import lombok.Getter;
import ru.strict.util.ResourcesUtil;

import javax.swing.*;

@Getter
public enum Icon {
    TURN("turn.png"),
    CHANGE_SIZE("changeSize.png"),
    CHANGE_SIZE_FULL("sizefull.png"),
    CLOSE("close.png"),
    MENU("menu.png"),
    ADD("add.png"),
    ADD_USER("adduser.png"),
    REMOVE("remove.png"),
    REMOVE_USER("removeuser.png"),
    SELECT("select.png"),
    SETTINGS("settings.png"),
    EXIT("exit.png"),
    BACK("back.png"),
    HELP("help.png"),
    FILE("file.png"),
    FILES("files.png"),
    VIEW("view.png"),
    REFRESH("refresh.png"),
    STAR("star.png"),
    SEARCH("search.png"),
    NODE("node.png");

    private String path;
    public final String DIRECT_ICON = "images/icons/";

    Icon(String path) {
        this.path = "images/icons/" + path;
    }

    public ImageIcon getImageFromResources(int size) {
        var imageInputStream = ResourcesUtil.getResourceStream(path);
        return ImageUtil.resizeImage(
                imageInputStream,
                size,
                size);
    }
}
