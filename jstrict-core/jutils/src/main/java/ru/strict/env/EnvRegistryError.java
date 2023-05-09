package ru.strict.env;

import lombok.experimental.UtilityClass;
import ru.strict.exception.CodeableException;

@UtilityClass
public class EnvRegistryError {

    public final String envKeyIsRequiredErrorCode = "68de6228-001";
    public final String envValueIsRequiredErrorCode = "68de6228-002";
    public final String envNotFoundErrorCode = "68de6228-003";

    public CodeableException errEnvKeyIsRequired() {
        return new CodeableException(envKeyIsRequiredErrorCode, "Key for env is required");
    }

    public CodeableException errEnvValueIsRequired() {
        return new CodeableException(envValueIsRequiredErrorCode, "Value for env is required");
    }

    public CodeableException errEnvByKeyNotFound(String key) {
        var errMsg = String.format("Env by key = '%s' not found", key);
        return new CodeableException(envNotFoundErrorCode, errMsg);
    }
}
