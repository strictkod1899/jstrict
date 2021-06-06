package ru.strict.ioc;

import ru.strict.utils.ReflectionUtil;

import java.util.Map;

public class ReflectionTestUtil {

    public static void setField(IoC ioc, Object component, String fieldName, Object field) {
        Map<IoCKeys, IoCData> components = ReflectionUtil.getFieldValue(ioc, "components");

        Object sourceComponent = components.values().stream()
                .filter(data -> data.getComponentInstance() == component)
                .findFirst()
                .map(IoCData::getSourceInstance)
                .orElse(null);

        if (sourceComponent != null) {
            ReflectionUtil.setField(sourceComponent, fieldName, field);
        }
        ReflectionUtil.setField(component, fieldName, field);
    }
}
