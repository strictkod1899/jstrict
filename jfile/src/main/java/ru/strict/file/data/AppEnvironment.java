package ru.strict.file.data;

public enum AppEnvironment {
    DEVELOPMENT("development"),
    PRODUCTION("production");

    private String value;

    AppEnvironment(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
