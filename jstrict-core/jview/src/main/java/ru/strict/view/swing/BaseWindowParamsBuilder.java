package ru.strict.view.swing;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.awt.*;
import java.io.File;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseWindowParamsBuilder {
    String title;
    int width;
    int height;
    File logoFile;
    Color background;

    public BaseWindowParamsBuilder title(String title) {
        this.title = title;
        return this;
    }

    public BaseWindowParamsBuilder width(int width) {
        this.width = width;
        return this;
    }

    public BaseWindowParamsBuilder height(int height) {
        this.height = height;
        return this;
    }

    public BaseWindowParamsBuilder logoFile(File logoFile) {
        this.logoFile = logoFile;
        return this;
    }

    public BaseWindowParamsBuilder background(Color background) {
        this.background = background;
        return this;
    }

    public BaseWindowParams build() {
        fillDefaultFields();

        return createFromBuilder();
    }

    private void fillDefaultFields() {
        if (title == null) {
            title = "";
        }
        if (width == 0) {
            width = 400;
        }
        if (height == 0) {
            height = 300;
        }
        if (background == null) {
            background = Color.WHITE;
        }
    }

    private BaseWindowParams createFromBuilder() {
        var params = new BaseWindowParams();
        params.title = title;
        params.width = width;
        params.height = height;
        params.logoFile = logoFile;
        params.background = background;

        return params;
    }
}
