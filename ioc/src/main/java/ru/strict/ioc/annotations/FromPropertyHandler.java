package ru.strict.ioc.annotations;

import lombok.experimental.UtilityClass;
import ru.strict.ioc.exceptions.ComponentNotFoundException;
import ru.strict.ioc.exceptions.IoCException;
import ru.strict.utils.PropertiesUtil;
import ru.strict.utils.ReflectionUtil;
import ru.strict.utils.ResourcesUtil;
import ru.strict.utils.StringUtil;
import ru.strict.validate.CommonValidate;

import java.lang.reflect.Field;
import java.util.Properties;

@UtilityClass
public class FromPropertyHandler {

    private static final String APP_FILE_NAME = "app.properties";

    public static void fillFromProperty(Object instance) {
        var fromPropertyAnnotation = instance.getClass().getAnnotation(FromProperty.class);
        if (fromPropertyAnnotation == null) {
            return;
        }

        var prefix = fromPropertyAnnotation.prefix();
        var configValues = getConfigValues(fromPropertyAnnotation);
        fillInstance(instance, prefix, configValues);
    }

    private static Properties getConfigValues(FromProperty fromPropertyAnnotation) {
        var fileName = fromPropertyAnnotation.file();
        fileName = CommonValidate.isEmptyOrNull(fileName) ? APP_FILE_NAME : fileName;

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

                var croppedPropertyName = propertyName.substring(prefix.length() + 1);

                var targetFieldName = getFieldName(croppedPropertyName);
                if (targetFieldName == null) {
                    continue;
                }

                var field = ReflectionUtil.getField(instance.getClass(), targetFieldName);
                if (field == null) {
                    throw new IoCException(String.format(
                            "Not found field '%s' in class '%s' for fill component from properties",
                            targetFieldName,
                            instance.getClass()));
                }

                var fieldSetterName = getFieldSetter(field);
                var fieldSetter = instance.getClass().getMethod(fieldSetterName, field.getType());
                if (fieldSetter == null) {
                    throw new IoCException(String.format("Not found setter '%s' for fill component from properties",
                            fieldSetterName));
                }

                Object targetValue = convertValueToTargetType(value, field);
                fieldSetter.invoke(instance, targetValue);
            }
        } catch (Exception ex) {
            var errorMessage = String.format("An error occurred at fill component from properties. ComponentClass = %s",
                    instance.getClass());
            throw new IoCException(errorMessage, ex);
        }
    }

    private String getFieldName(String propertyName) {
        if (propertyName.contains(".")) {
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

    private String getFieldSetter(Field field) {
        var fieldName = field.getName();
        var formattedFieldName = StringUtil.toUpperFirstSymbol(fieldName);
        return String.format("set%s", formattedFieldName);
    }

    private Object convertValueToTargetType(String value, Field field) {
        Class<?> fieldType = field.getType();

        if (ReflectionUtil.isInstanceOf(Boolean.class, fieldType)) {
            return value.equals("true");
        } else if (ReflectionUtil.isInstanceOf(Integer.class, fieldType)) {
            return Integer.valueOf(value);
        } else if (ReflectionUtil.isInstanceOf(Long.class, fieldType)) {
            return Long.valueOf(value);
        } else if (ReflectionUtil.isInstanceOf(Float.class, fieldType)) {
            return Float.valueOf(value);
        } else if (ReflectionUtil.isInstanceOf(Double.class, fieldType)) {
            return Double.valueOf(value);
        } else {
            return value;
        }
    }
}
