package ru.strict.view.swing;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.awt.*;
import java.io.File;
import java.util.Optional;

@Getter
@FieldDefaults(level = AccessLevel.PACKAGE)
public class BaseWindowParams {
    String title;
    int width;
    int height;
    File logoFile;
    Color background;

    public Optional<File> getLogoFile() {
        return Optional.ofNullable(logoFile);
    }

    public static BaseWindowParamsBuilder builder() {
        return new BaseWindowParamsBuilder();
    }
}
