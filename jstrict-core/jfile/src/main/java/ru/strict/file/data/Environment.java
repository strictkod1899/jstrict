package ru.strict.file.data;

import java.util.Arrays;

public enum Environment {
    TEST("test"),
    DEVELOPMENT("development"),
    PRODUCTION("production");

    private String value;

    Environment(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Environment getByString(String environment){
        if(environment == null){
            return null;
        }

        return Arrays.stream(Environment.values())
                .filter(env -> env.getValue().equals(environment))
                .findFirst()
                .orElse(null);
    }
}
