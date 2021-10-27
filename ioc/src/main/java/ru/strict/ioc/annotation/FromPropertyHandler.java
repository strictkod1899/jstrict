package ru.strict.ioc.annotation;

import lombok.experimental.UtilityClass;
import ru.strict.ioc.exception.ComponentNotFoundException;
import ru.strict.ioc.exception.IoCException;
import ru.strict.util.PropertiesUtil;
import ru.strict.util.ReflectionUtil;
import ru.strict.util.ResourcesUtil;
import ru.strict.util.StringUtil;
import ru.strict.validate.CommonValidate;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Properties;

@UtilityClass
public class FromPropertyHandler {

    private static final String APP_FILE_NAME = "app.properties";

    public static void fillFromProperties(Object instance) {
        var fromPropertyAnnotation = instance.getClass().getAnnotation(FromProperties.class);
        if (fromPropertyAnnotation == null) {
            return;
        }

        fillFromProperties(instance, fromPropertyAnnotation);
    }

    public static void fillFromProperties(Object instance, FromProperties fromPropertiesAnnotation) {
        var prefix = fromPropertiesAnnotation.prefix();
        var configValues = getConfigValues(fromPropertiesAnnotation);

        fillInstance(instance, prefix, configValues);
    }

    private static Properties getConfigValues(FromProperties fromPropertiesAnnotation) {
        var fileName = fromPropertiesAnnotation.file();
        fileName = CommonValidate.isNullOrEmpty(fileName) ? APP_FILE_NAME : fileName;

        var propertiesInputStream = ResourcesUtil.getResourceStream(fileName);
        if (propertiesInputStream == null) {
            throw new ComponentNotFoundException(fileName);
        }

        return PropertiesUtil.getValues(propertiesInputStream);
    }

    private static void fillInstance(Object instance, String prefix, Properties configValues) {
        try {
            for (var entry : configValues.entrySet()) {
                String propertyName = String.valueOf(entry.getKey());
                String value = String.valueOf(entry.getValue());

                if (!propertyName.startsWith(prefix)) {
                    continue;
                }

                var croppedPropertyName = cropPrefix(propertyName, prefix);

                var targetFieldName = getFieldName(croppedPropertyName);
                if (targetFieldName == null) {
                    continue;
                }

                var fieldSetterName = getFieldSetter(targetFieldName);
                var fieldSetter = getFirstSetter(instance.getClass(), fieldSetterName);
                if (fieldSetter == null) {
                    continue;
                }

                var fieldType = fieldSetter.getParameterTypes()[0];
                Object targetValue = convertValueToTargetType(value, fieldType);
                fieldSetter.invoke(instance, targetValue);
            }
        } catch (Exception ex) {
            var errorMessage = String.format("An error occurred at fill component from properties. ComponentClass = %s",
                    instance.getClass());
            throw new IoCException(errorMessage, ex);
        }
    }

    private Method getFirstSetter(Class<?> instanceClass, String setterName) {
        return Arrays.stream(instanceClass.getMethods())
                .filter(method -> method.getName().equals(setterName) && method.getParameterCount() == 1)
                .findFirst()
                .orElse(null);
    }

    private String getFieldName(String propertyName) {
        if (isInnerProperty(propertyName)) {
            return null;
        }

        String fieldName = propertyName;

        while (fieldName.contains("-")) {
            var hyphenIndexOf = fieldName.indexOf("-");
            var nextChar = fieldName.substring(hyphenIndexOf + 1, hyphenIndexOf + 2);
            var replaceText = String.format("-%s", nextChar);

            fieldName = fieldName.replaceAll(replaceText, nextChar.toUpperCase());
        }

        return fieldName;
    }

    private boolean isInnerProperty(String propertyName) {
        return propertyName.contains(".");
    }

    private String getFieldSetter(String fieldName) {
        var formattedFieldName = StringUtil.toUpperFirstSymbol(fieldName);
        return String.format("set%s", formattedFieldName);
    }

    private String cropPrefix(String propertyName, String prefix) {
        return CommonValidate.isNullOrEmpty(prefix) ? propertyName : propertyName.substring(prefix.length() + 1);
    }

    private Object convertValueToTargetType(String value, Class<?> type) {
        if (ReflectionUtil.isInstanceOf(Boolean.class, type)) {
            return value.equals("true");
        } else if (ReflectionUtil.isInstanceOf(Integer.class, type)) {
            return Integer.valueOf(value);
        } else if (ReflectionUtil.isInstanceOf(Long.class, type)) {
            return Long.valueOf(value);
        } else if (ReflectionUtil.isInstanceOf(Float.class, type)) {
            return Float.valueOf(value);
        } else if (ReflectionUtil.isInstanceOf(Double.class, type)) {
            return Double.valueOf(value);
        } else {
            return value;
        }
    }
}
