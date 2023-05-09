package ru.strict.env;

import ru.strict.util.StringUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class EnvRegistry {
    private final Map<String, String> environments;

    public EnvRegistry() {
        environments = new HashMap<>();
    }

    public void setEnv(String key, String value) {
        if (StringUtil.isNullOrEmpty(key)) {
            throw EnvRegistryError.errEnvKeyIsRequired();
        }
        if (StringUtil.isNullOrEmpty(value)) {
            throw EnvRegistryError.errEnvValueIsRequired();
        }

        environments.put(key, value);
    }

    public String getEnv(String key) {
        var value = environments.get(key);
        if (value == null) {
            throw EnvRegistryError.errEnvByKeyNotFound(key);
        }

        return value;
    }

    public Optional<String> tryGetEnv(String key) {
        return Optional.ofNullable(environments.get(key));
    }
}
