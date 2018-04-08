package ru.strict.models;

import javax.swing.*;
import java.awt.*;

/**
 * Модель базового фрейма
 */
public abstract class StrictModelFormBase extends StrictModelViewBase{

    private String title;

    private String pathIcon;

    private void initDefault(){
        setWidth((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth());
        setHeight((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        this.title = "";
        pathIcon = null;
    }

    public StrictModelFormBase() {
        super();
        initDefault();
    }

    /**
     * Заголовок окна
     */
    public String getTitle() {
        return title;
    }

    /**
     * Заголовок окна
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public String getPathIcon() {
        return pathIcon;
    }

    public void setPathIcon(String pathIcon) {
        this.pathIcon = pathIcon;
    }
}
