package ru.strict.file.data;

import java.util.Arrays;

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

    public static AppEnvironment getEnvironmentByString(String environment){
        return Arrays.stream(AppEnvironment.values())
                .filter(env -> env.getValue().equals(environment))
                .findFirst()
                .orElse(null);
    }
}
