package ru.strict.ioc;

import lombok.RequiredArgsConstructor;
import ru.strict.exceptions.ValidateException;
import ru.strict.ioc.annotations.*;
import ru.strict.ioc.exceptions.*;
import ru.strict.utils.ReflectionUtil;
import ru.strict.utils.StringUtil;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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
    //private Collection<Class<? extends LoggerBase>> defaultLoggingClasses;
    //private Class<? extends LoggerBase> defaultLoggerClass;

    public BaseIoC() {
        components = new HashMap<>();
        //defaultLoggingClasses = new HashSet<>();
        init();
    }

    @Override
    public void addComponent(Class<?> componentClass, Object... constructorArguments) {
        addComponent(componentClass, InstanceType.SINGLETON, constructorArguments);
    }

    @Override
    public void addComponent(Class<?> componentClass, InstanceType type, Object... constructorArguments) {
        addComponent(getComponentName(componentClass), componentClass, type, constructorArguments);
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
            throw ValidateException.byDetails(
                    "IoC exception. Fail add component to IoC because any is null: caption = %s," +
                            "componentClass = %s",
                    name,
                    componentClass);
        }

        if (isExistsComponentCaption(name)) {
            throw new ManyMatchComponentsException(name);
        }

        Class<?> keyClass = type == InstanceType.FACTORY ? ComponentFactory.class : componentClass;

        components.put(new IoCKey(name, keyClass), new IoCData(componentClass, constructorArguments, type));
    }

    @Override
    public void addSingleton(Class<?> componentClass, ComponentSupplier<?> componentSupplier) {
        addSingleton(getComponentName(componentClass), componentClass, componentSupplier);
    }

    @Override
    public void addSingleton(String name, Class<?> componentClass, ComponentSupplier<?> componentSupplier) {
        addSingleton(name, componentClass, new IoCData(componentSupplier));
    }

    @Override
    public void addSingleton(Object component) {
        addSingleton(getComponentName(component.getClass()), component);
    }

    @Override
    public void addSingleton(String name, Object component) {
        addSingleton(name, component.getClass(), new IoCData(component));
    }

    @Override
    public <T> T getComponent(String name) {
        T component = null;
        if (name != null) {
            IoCKey key = getKey(new FilterKeyByName(name), () -> new ManyMatchComponentsException(name));
            component = getInstance(key);
        }

        return component;
    }

    @Override
    public <T> T getComponent(Class<T> componentClass) {
        T component = null;
        if (componentClass != null) {
            IoCKey key = getKey(new FilterKeyByClass(componentClass),
                    () -> new ManyMatchComponentsException(componentClass));
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

    /**
     * @throws ManyMatchComponentsException
     */
    private IoCKey getKey(Predicate<IoCKey> keyFilter, Supplier<RuntimeException> orThrow) {
        List<IoCKey> foundKeys = components.keySet().stream()
                .filter(keyFilter)
                .collect(Collectors.toList());
        if (foundKeys.size() > 1) {
            throw orThrow.get();
        }

        return foundKeys.isEmpty() ? null : foundKeys.get(0);
    }

    private void init() {
        configure();

        initConfigurations();
        initSingletons();
    }

    private void initConfigurations() {
        Set<IoCKey> keys = components.keySet();

        for (IoCKey key : keys) {
            IoCData data = components.get(key);
            if (data.getType() == InstanceType.CONFIGURATION) {
                getInstance(key);
            }
        }
    }

    private void initSingletons() {
        Set<IoCKey> keys = components.keySet();

        for (IoCKey key : keys) {
            IoCData data = components.get(key);
            if ((data.getType() == InstanceType.SINGLETON || data.getType() == InstanceType.FACTORY)
                    && data.getComponentInstance() == null) {
                getInstance(key);
            }
        }
    }

    private void addSingleton(String caption, Class<?> keyClass, IoCData ioCData) {
        if (caption == null || keyClass == null) {
            throw ValidateException.byDetails(
                    "IoC exception. Fail add component to IoC because any is null: caption = %s," +
                            "keyClass = %s",
                    caption,
                    keyClass);
        }

        if (isExistsComponentCaption(caption)) {
            throw new ManyMatchComponentsException(caption);
        }

        components.put(new IoCKey(caption, keyClass), ioCData);
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

        IoCData instanceData = components.get(key);

        try {
            switch (instanceData.getType()) {
                case REQUEST:
                    componentInstance =
                            createInstance(instanceData.getInstanceClass(), instanceData.getConstructorArguments());
                    componentInstance = postCreateProcess(componentInstance);
                    break;
                case SESSION:
                case SINGLETON:
                case CONFIGURATION:
                    if (instanceData.getComponentInstance() != null) {
                        componentInstance = instanceData.getComponentInstance();
                    } else {
                        componentInstance =
                                createInstance(instanceData.getInstanceClass(), instanceData.getConstructorArguments());
                        instanceData.setSourceInstance(componentInstance);

                        componentInstance = postCreateProcess(componentInstance);
                        instanceData.setComponentInstance(componentInstance);
                    }
                    break;
                case FACTORY:
                    if (instanceData.getComponentInstance() != null) {
                        componentInstance = instanceData.getComponentInstance();
                    } else {
                        componentInstance = (T) new ComponentFactory<>(instanceData.getInstanceClass(), this);
                        instanceData.setSourceInstance(componentInstance);
                        instanceData.setComponentInstance(componentInstance);
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
            throw ValidateException.byDetails(
                    "IoC exception. Fail add component to IoC because any is null:" +
                            "clazzInstance = [%s], constructorArguments = [%s]",
                    instanceClass,
                    constructorArguments);
        }

        if (!skipComponentHandler) {
            Constructor<?> mainConstructor = findConstructor(instanceClass);
            if (constructorArguments.length == 0 && mainConstructor != null) {
                Object[] argumentsInstances = ComponentHandler.getConstructorArguments(mainConstructor);
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
        PostConstructHandler.invokePostConstructMethod(instance);
        ConfigurationHandler.invokeConfigurationMethods(instance);
        //instance = (RESULT)
        //        LoggingHandler.wrapLoggingProxy(instance, this, defaultLoggingClasses.toArray(new Class[0]));
        return instance;
    }

    private <T> T createInstanceByArguments(Class<?> instanceClass, Object[] constructorArguments) {
        Object[] instanceArguments = createInstanceArguments(constructorArguments);
        return (T) ReflectionUtil.createDeclaredInstance(instanceClass, false, instanceArguments);
    }

    private Object[] createInstanceArguments(Object[] createArguments) {
        int countArguments = createArguments.length;
        Object[] instanceArguments = new Object[countArguments];

        for (int i = 0; i < countArguments; i++) {
            Object instanceArgument;
            Object createArgument = createArguments[i];
            if (createArgument instanceof String && !((String) createArgument).startsWith("@")) {
                instanceArgument = getComponentOrThrow((String) createArgument);
            } else if (createArgument instanceof Class) {
                instanceArgument = getComponentOrThrow((Class) createArgument);
            } else {
                if (createArgument instanceof String && ((String) createArgument).startsWith("@")) {
                    instanceArgument = ((String) createArgument).substring(1, ((String) createArgument).length());
                } else {
                    instanceArgument = createArgument;
                }
            }

            instanceArguments[i] = instanceArgument;
        }

        return instanceArguments;
    }

    private <T> T getComponentOrThrow(Class<T> clazz) {
        T component = getComponent(clazz);
        if (component == null) {
            throw new ComponentNotFoundException(clazz);
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

    private boolean isExistsComponentCaption(String componentCaption) {
        return components.keySet().stream().anyMatch((k) -> componentCaption.equals(k.getName()));
    }

    private String getComponentName(Class<?> clazz) {
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
