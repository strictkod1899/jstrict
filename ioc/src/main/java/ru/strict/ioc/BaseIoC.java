package ru.strict.ioc;

import lombok.RequiredArgsConstructor;
import ru.strict.exception.ValidateException;
import ru.strict.ioc.annotation.*;
import ru.strict.ioc.box.ComponentFactoryProcessor;
import ru.strict.ioc.box.ComponentSupplier;
import ru.strict.ioc.exception.*;
import ru.strict.util.ReflectionUtil;
import ru.strict.util.StringUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static ru.strict.ioc.IoCUtils.*;

/**
 * Чтобы при использовании метода addComponent передать string-переменную как значение параметра конструктору,
 * надо использовать в начале строки символ @, иначе этот параметр будет учитываться как название ioc-компонента.
 * <p><b>Пример использования:</b></p>
 * <code><pre style="background-color: white; font-family: consolas">
 * public class IoC extends ru.strict.ioc.IoC {
 *
 *     private static IoC instance;
 *
 *     public static IoC instance() {
 *         if (instance == null) {
 *             instance = new IoC();
 *         }
 *
 *         return instance;
 *     }
 *
 *     protected void configure() {
 *         addComponent(A.class, A.class, InstanceType.REQUEST);
 *         addComponent(B.class, B.class, InstanceType.REQUEST, "comp1", "@param2", new Param3());
 *         addSingleton(C.class, new C());
 *         addComponent(D.class, D.class, InstanceType.REQUEST, A.class, B.class);
 *         ...
 *     }
 * }
 * </pre></code>
 */
public abstract class BaseIoC implements IoC {

    private Map<IoCKey, IoCData> components;
    private List<IoCKey> componentKeysForConfigure;
    //private Collection<Class<? extends LoggerBase>> defaultLoggingClasses;
    //private Class<? extends LoggerBase> defaultLoggerClass;

    public BaseIoC() {
        components = new HashMap<>();
        componentKeysForConfigure = new ArrayList<>();
        //defaultLoggingClasses = new HashSet<>();
        init();
    }

    @Override
    public void addComponent(Class<?> componentClass, Object... constructorArguments) {
        addComponent(componentClass, InstanceType.SINGLETON, constructorArguments);
    }

    @Override
    public void addComponent(Class<?> componentClass, InstanceType type, Object... constructorArguments) {
        addComponent(getComponentNameByClass(componentClass), componentClass, type, constructorArguments);
    }

    @Override
    public void addComponent(String name, Class<?> componentClass, Object... constructorArguments) {
        addComponent(name, componentClass, InstanceType.SINGLETON, constructorArguments);
    }

    @Override
    public void addComponent(String name,
            Class<?> componentClass,
            InstanceType type,
            Object... constructorArguments) {
        if (name == null || componentClass == null) {
            throw new ValidateException(
                    "IoC exception. Fail add component to IoC because any is null: caption = %s," +
                            "componentClass = %s",
                    name,
                    componentClass);
        }

        if (isExistsComponentByName(name)) {
            throw new ManyMatchComponentsException(name);
        }

        components.put(new IoCKey(name, componentClass), new IoCData(componentClass, constructorArguments, type));
    }

    @Override
    public void addSingleton(Class<?> componentClass, ComponentSupplier<?> componentSupplier) {
        addSingleton(getComponentNameByClass(componentClass), componentClass, componentSupplier);
    }

    @Override
    public void addSingleton(String name, Class<?> componentClass, ComponentSupplier<?> componentSupplier) {
        addSingleton(name, componentClass, new IoCData(componentSupplier));
    }

    @Override
    public void addSingleton(Object component) {
        addSingleton(getComponentNameByClass(component.getClass()), component);
    }

    @Override
    public void addSingleton(String name, Object component) {
        addSingleton(name, component.getClass(), new IoCData(component));
    }

    @Override
    public <T> T getComponent(String name) {
        T component = null;
        if (name != null) {
            var key = getKey(new FilterKeyByName(name), () -> new ManyMatchComponentsException(name));
            if (key == null) {
                throw new KeyNotFoundException(name);
            }

            component = getInstance(key);
        }

        return component;
    }

    @Override
    public <T> T getComponent(Class<T> componentClass) {
        T component = null;
        if (componentClass != null) {
            var key = getKey(new FilterKeyByClass(componentClass),
                    () -> new ManyMatchComponentsException(componentClass));
            if (key == null) {
                throw new KeyNotFoundException(componentClass);
            }

            component = getInstance(key);
        }

        return component;
    }

    @Override
    public void closeSessionInstance(String name) {
        if (name != null) {
            IoCKey key = getKey(new FilterKeyByName(name), () -> new ManyMatchComponentsException(name));
            closeSessionInstanceProcess(key);
        }
    }

    @Override
    public void closeSessionInstance(Class<?> componentClass) {
        if (componentClass != null) {
            IoCKey key = getKey(new FilterKeyByClass(componentClass),
                    () -> new ManyMatchComponentsException(componentClass));
            closeSessionInstanceProcess(key);
        }
    }

    private IoCKey getKey(Predicate<IoCKey> keyFilter, Supplier<RuntimeException> orThrow) {
        var foundKeys = components.keySet().
                stream().
                filter(keyFilter).
                toList();
        if (foundKeys.size() > 1) {
            throw orThrow.get();
        }

        return foundKeys.isEmpty() ? null : foundKeys.get(0);
    }

    private void init() {
        configure();
        fillFromConfigurations();

        initSingletons();
        initConfigurations();
        invokeConfigurationMethods();
    }

    private void fillFromConfigurations() {
        components.keySet().stream()
                .filter(key -> components.get(key).getType() == InstanceType.CONFIGURATION)
                .toList()
                .forEach(key -> ComponentHandler.fillIoCFromConfiguration(key.getClazz(), this));
    }

    private void initConfigurations() {
        components.entrySet().stream()
                .filter((entry) -> entry.getValue().getType() == InstanceType.CONFIGURATION)
                .map(Map.Entry::getKey)
                .toList()
                .forEach(this::getInstance);
    }

    private void initSingletons() {
        components.keySet().stream()
                .filter(key -> {
                    var data = components.get(key);
                    return data.getType() == InstanceType.SINGLETON && !data.isExistsComponentInstance();
                })
                .toList()
                .forEach(this::getInstance);
    }

    private void invokeConfigurationMethods() {
        componentKeysForConfigure.forEach(key -> {
            var componentInstance = getInstance(key);
            ConfigurationHandler.invokeVoidConfigurationMethods(componentInstance);
        });
    }

    private void addSingleton(String componentName, Class<?> keyClass, IoCData ioCData) {
        if (componentName == null || keyClass == null) {
            throw new ValidateException(
                    "IoC exception. Fail add component to IoC because any is null: caption = %s," +
                            "keyClass = %s",
                    componentName,
                    keyClass);
        }

        if (isExistsComponentByName(componentName)) {
            throw new ManyMatchComponentsException(componentName);
        }

        components.put(new IoCKey(componentName, keyClass), ioCData);
    }

    /**
     * Установить класс логирования, который будет использоваться по-умолчнию с аннотацией @Logger
     */
    /*public void setDefaultLogger(Class<? extends LoggerBase> loggerClass) {
        this.defaultLoggerClass = loggerClass;
    }*/

    /**
     * Добавить класс логирования, который будет использоваться по-умолчнию с аннотацией @Loggin
     */
    /*public void addDefaultLogging(Class<? extends LoggerBase> loggerClass) {
        this.defaultLoggingClasses.add(loggerClass);
    }*/
    private void closeSessionInstanceProcess(IoCKey key) {
        if (key != null) {
            IoCData instanceData = components.get(key);
            if (instanceData.getType() == InstanceType.SESSION) {
                instanceData.closeSessionInstance();
            } else {
                throw new MatchInstanceTypeException(InstanceType.SESSION, instanceData.getType());
            }
        }
    }

    private <T> T getInstance(IoCKey key) {
        T componentInstance = null;

        var componentData = components.get(key);

        try {
            switch (componentData.getType()) {
                case REQUEST:
                    componentInstance =
                            createInstance(componentData.getInstanceClass(), componentData.getConstructorArguments());
                    componentInstance = postCreateProcess(componentInstance);
                    ConfigurationHandler.invokeVoidConfigurationMethods(componentInstance);
                    break;
                case SESSION:
                case SINGLETON:
                case CONFIGURATION:
                    if (componentData.isExistsComponentInstance()) {
                        componentInstance = componentData.getComponentInstance();
                    } else {
                        if (componentData.withSupplier()) {
                            componentInstance = componentData.getFromSupplier();
                        } else {
                            componentInstance = createInstance(componentData.getInstanceClass(),
                                    componentData.getConstructorArguments());
                        }
                        componentData.setSourceInstance(componentInstance);

                        componentInstance = postCreateProcess(componentInstance);
                        componentData.setComponentInstance(componentInstance);
                        componentKeysForConfigure.add(key);
                    }
                    break;
            }
        } catch (ComponentNotFoundException | ConstructorNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CreateComponentException(key.getClazz(), key.getName(), ex);
        }

        return componentInstance;
    }

    private <T> T createInstance(Class<?> instanceClass, Object[] constructorArguments) {
        return createInstance(instanceClass, constructorArguments, false);
    }

    private <T> T createInstance(Class<?> instanceClass,
            Object[] constructorArguments,
            boolean skipComponentHandler) {
        if (instanceClass == null || constructorArguments == null) {
            throw new ValidateException(
                    "IoC exception. Fail add component to IoC because any is null:" +
                            "clazzInstance = [%s], constructorArguments = [%s]",
                    instanceClass,
                    constructorArguments);
        }

        if (!skipComponentHandler) {
            Constructor<?> mainConstructor = findConstructor(instanceClass);
            if (constructorArguments.length == 0 && mainConstructor != null) {
                Object[] argumentsInstances = ComponentHandler.getConstructorArguments(mainConstructor, instanceClass);
                return createInstance(instanceClass, argumentsInstances, true);
            } else {
                return createInstanceByArguments(instanceClass, constructorArguments);
            }
        } else {
            return createInstanceByArguments(instanceClass, constructorArguments);
        }
    }

    private <T> T postCreateProcess(T instance) {
        //LoggerHandler.injectLogger(instance, this, defaultLoggerClass);
        FromPropertyHandler.fillFromProperties(instance);
        PostConstructHandler.invokePostConstructMethod(instance);
        //instance = (RESULT)
        //        LoggingHandler.wrapLoggingProxy(instance, this, defaultLoggingClasses.toArray(new Class[0]));
        return instance;
    }

    private <T> T createInstanceByArguments(Class<?> instanceClass, Object[] constructorArguments) {
        Object[] argumentsInstances = createArgumentsInstances(constructorArguments);
        return (T) ReflectionUtil.createDeclaredInstance(instanceClass, false, argumentsInstances);
    }

    private Object[] createArgumentsInstances(Object[] originalArguments) {
        int argumentsCount = originalArguments.length;
        Object[] argumentsInstances = new Object[argumentsCount];

        for (int i = 0; i < argumentsCount; i++) {
            Object argumentInstance;
            Object originalArgument = originalArguments[i];
            if (originalArgument instanceof String && !((String) originalArgument).startsWith("@")) {
                argumentInstance = getComponentOrThrow((String) originalArgument);
            } else if (originalArgument instanceof ParameterizedType) {
                var parameterizedType = (ParameterizedType) originalArgument;

                argumentInstance = getBoxComponent(parameterizedType);
                if (argumentInstance == null) {
                    argumentInstance = getComponentOrThrow((Class<?>) parameterizedType.getRawType());
                }
            } else if (originalArgument instanceof Class) {
                argumentInstance = getComponentOrThrow((Class<?>) originalArgument);
            } else {
                if (originalArgument instanceof String && ((String) originalArgument).startsWith("@")) {
                    argumentInstance = ((String) originalArgument).substring(1, ((String) originalArgument).length());
                } else {
                    argumentInstance = originalArgument;
                }
            }

            argumentsInstances[i] = argumentInstance;
        }

        return argumentsInstances;
    }

    private <T> T getBoxComponent(ParameterizedType componentType) {
        return (T) ComponentFactoryProcessor.getComponent(componentType, this);
    }

    private <T> T getComponentOrThrow(Class<T> componentClass) {
        T component = getComponent(componentClass);
        if (component == null) {
            throw new ComponentNotFoundException(componentClass);
        }

        return component;
    }

    private <T> T getComponentOrThrow(String componentName) {
        T component = getComponent(componentName);
        if (component == null) {
            throw new ComponentNotFoundException(componentName);
        }

        return component;
    }

    private boolean isExistsComponentByName(String componentName) {
        return components.keySet().
                stream().
                anyMatch(key -> componentName.equals(key.getName()));
    }

    private String getComponentNameByClass(Class<?> clazz) {
        if (clazz.getDeclaringClass() != null) {
            var superClass = clazz.getDeclaringClass();
            return String.format("%s.%s",
                    StringUtil.toLowerFirstSymbol(superClass.getSimpleName()),
                    StringUtil.toLowerFirstSymbol(clazz.getSimpleName()));
        }

        return StringUtil.toLowerFirstSymbol(clazz.getSimpleName());
    }

    protected abstract void configure();

    @RequiredArgsConstructor
    private static class FilterKeyByClass implements Predicate<IoCKey> {
        private final Class<?> componentClass;

        @Override
        public boolean test(IoCKey ioCKey) {
            return ioCKey.getClazz() != null && ReflectionUtil.isInstanceOf(componentClass, ioCKey.getClazz());
        }
    }

    @RequiredArgsConstructor
    private static class FilterKeyByName implements Predicate<IoCKey> {
        private final String name;

        @Override
        public boolean test(IoCKey ioCKey) {
            return name.equals(ioCKey.getName());
        }
    }
}
