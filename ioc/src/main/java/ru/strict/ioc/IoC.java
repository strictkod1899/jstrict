package ru.strict.ioc;

import ru.strict.exceptions.ValidateException;
import ru.strict.ioc.annotations.ComponentHandler;
import ru.strict.ioc.annotations.ConfigurationHandler;
import ru.strict.ioc.annotations.LoggingHandler;
import ru.strict.ioc.annotations.PostConstructHandler;
import ru.strict.ioc.exceptions.ComponentNotFoundException;
import ru.strict.ioc.exceptions.ConstructorNotFoundException;
import ru.strict.ioc.exceptions.CreateComponentException;
import ru.strict.ioc.exceptions.ManyMatchComponentsException;
import ru.strict.ioc.exceptions.MatchInstanceTypeException;
import ru.strict.utils.ReflectionUtil;
import ru.strict.utils.StringUtil;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
public abstract class IoC implements IIoC {

    private Map<IoCKeys, IoCData> components;
    //private Collection<Class<? extends LoggerBase>> defaultLoggingClasses;
    //private Class<? extends LoggerBase> defaultLoggerClass;

    public IoC(IoC... joins) {
        this();
        for (IoC join : joins) {
            joinIoC(join);
        }
    }

    public IoC() {
        components = new HashMap<>();
        //defaultLoggingClasses = new HashSet<>();
        init();
    }

    private void init() {
        configure();

        initConfigurations();
        initSingletons();
    }

    private void initConfigurations() {
        Set<IoCKeys> keys = components.keySet();

        for (IoCKeys key : keys) {
            IoCData data = components.get(key);
            if (data.getType() == InstanceType.CONFIGURATION) {
                getInstance(key);
            }
        }
    }

    private void initSingletons() {
        Set<IoCKeys> keys = components.keySet();

        for (IoCKeys key : keys) {
            IoCData data = components.get(key);
            if (data.getType() == InstanceType.SINGLETON && data.getComponentInstance() == null) {
                getInstance(key);
            }
        }
    }

    protected abstract void configure();

    /**
     * Объединить несколько IoC-контейнеров в один
     *
     * @param join объединяемый IoC-контейнер
     */
    public void joinIoC(IoC join) {
        join.components
                .keySet()
                .forEach(key -> {
                    IoCData joinData = join.components.get(key);
                    addComponent(key.getClazz(),
                            key.getCaption(),
                            joinData.getInstanceClass(),
                            joinData.getType(),
                            joinData.getConstructorArguments()
                    );
                });
    }

    @Override
    public <RESULT> void addComponent(Class<RESULT> clazz, Object... constructorArguments) {
        addComponent(clazz, clazz, InstanceType.SINGLETON, constructorArguments);
    }

    @Override
    public <RESULT> void addComponent(Class<RESULT> clazz, InstanceType type, Object... constructorArguments) {
        addComponent(clazz, clazz, type, constructorArguments);
    }

    @Override
    public <RESULT> void addComponent(Class<RESULT> clazz,
            Class component,
            InstanceType type,
            Object... constructorArguments) {
        if (clazz == null || component == null) {
            throw ValidateException.byDetails(
                    "IoC exception. Fail add component to IoC because any is null: clazz = [%s], component = [%s]",
                    clazz,
                    component);
        }

        if (isExistsComponentClass(clazz)) {
            throw new ManyMatchComponentsException(clazz);
        }

        components.put(new IoCKeys(getComponentName(clazz), clazz), new IoCData(component, constructorArguments, type));
    }

    @Override
    public void addComponent(String caption, Class component, Object... constructorArguments) {
        addComponent(caption, component, InstanceType.SINGLETON, constructorArguments);
    }

    @Override
    public void addComponent(String caption, Class component, InstanceType type, Object... constructorArguments) {
        if (caption == null || component == null) {
            throw ValidateException.byDetails(
                    "IoC exception. Fail add component to IoC because any is null: caption = [%s], component = [%s]",
                    caption,
                    component);
        }

        if (isExistsComponentCaption(caption)) {
            throw new ManyMatchComponentsException(caption);
        }

        components.put(new IoCKeys(caption, component), new IoCData(component, constructorArguments, type));
    }

    @Override
    public <RESULT> void addComponent(Class<RESULT> clazz,
            String caption,
            Class component,
            InstanceType type,
            Object... constructorArguments) {
        if (caption == null || component == null || clazz == null) {
            throw ValidateException.byDetails(
                    "IoC exception. Fail add component to IoC because any is null: caption = [%s]," +
                            "clazz = [%s], component = [%s]",
                    caption,
                    clazz,
                    component);
        }

        if (isExistsComponentClass(clazz)) {
            throw new ManyMatchComponentsException(clazz);
        }

        if (isExistsComponentCaption(caption)) {
            throw new ManyMatchComponentsException(caption);
        }

        components.put(new IoCKeys(caption, clazz), new IoCData(component, constructorArguments, type));
    }

    @Override
    public void addSingleton(String caption, Object component) {
        if (caption == null || component == null) {
            throw ValidateException.byDetails(
                    "IoC exception. Fail add component to IoC because any is null: caption = [%s], component = [%s]",
                    caption,
                    component);
        }

        if (isExistsComponentCaption(caption)) {
            throw new ManyMatchComponentsException(caption);
        }

        components.put(new IoCKeys(caption, component.getClass()), new IoCData(component));
    }

    @Override
    public <RESULT> void addSingleton(Class<RESULT> clazz, Object component) {
        if (clazz == null || component == null) {
            throw ValidateException.byDetails(
                    "IoC exception. Fail add component to IoC because any is null: clazz = [%s], component = [%s]",
                    clazz,
                    component);
        }

        if (isExistsComponentClass(clazz)) {
            throw new ManyMatchComponentsException(clazz);
        }

        components.put(new IoCKeys(getComponentName(clazz), clazz), new IoCData(component));
    }

    @Override
    public <RESULT> void addSingleton(Class<RESULT> clazz, String caption, Object component) {
        if (clazz == null || component == null || caption == null) {
            throw ValidateException.byDetails(
                    "IoC exception. Fail add component to IoC because any is null: caption = [%s]," +
                            "clazz = [%s], component = [%s]",
                    caption,
                    clazz,
                    component);
        }

        if (isExistsComponentClass(clazz)) {
            throw new ManyMatchComponentsException(clazz);
        }

        if (isExistsComponentCaption(caption)) {
            throw new ManyMatchComponentsException(caption);
        }

        components.put(new IoCKeys(caption, clazz), new IoCData(component));
    }

    @Override
    public <RESULT> RESULT getComponent(String caption) {
        RESULT result = null;
        if (caption != null) {
            IoCKeys key = components.keySet().stream()
                    .filter((k) -> caption.equals(k.getCaption()))
                    .findFirst()
                    .orElse(null);
            if (key != null) {
                result = getInstance(key);
            }
        }

        return result;
    }

    @Override
    public <RESULT> RESULT getComponent(Class<RESULT> clazz) {
        RESULT result = null;
        if (clazz != null) {
            IoCKeys key = components.keySet().stream()
                    .filter((k) -> k.getClazz() != null && ReflectionUtil.isInstanceOf(clazz, k.getClazz()))
                    .findFirst()
                    .orElse(null);
            if (key != null) {
                return getInstance(key);
            }
        }
        return result;
    }

    @Override
    public <RESULT> void closeSessionInstance(Class<RESULT> clazz) {
        if (clazz != null) {
            IoCKeys key = components.keySet().stream()
                    .filter((k) -> k.getClazz() != null && ReflectionUtil.isInstanceOf(clazz, k.getClazz()))
                    .findFirst()
                    .orElse(null);
            if (key != null) {
                closeSessionInstanceProcess(key);
            }
        }
    }

    @Override
    public void closeSessionInstance(String caption) {
        if (caption != null) {
            IoCKeys key = components.keySet().stream()
                    .filter((k) -> caption.equals(k.getCaption()))
                    .findFirst()
                    .orElse(null);
            if (key != null) {
                closeSessionInstanceProcess(key);
            }
        }
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

    private void closeSessionInstanceProcess(IoCKeys key) {
        if (key != null) {
            IoCData instanceData = components.get(key);
            if (instanceData.getType() == InstanceType.SESSION) {
                instanceData.closeSessionInstance();
            } else {
                throw new MatchInstanceTypeException(InstanceType.SESSION, instanceData.getType());
            }
        }
    }

    private <RESULT> RESULT getInstance(IoCKeys key) {
        RESULT componentInstance = null;

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
            }
        } catch (ComponentNotFoundException | ConstructorNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CreateComponentException(key.getClazz(), key.getCaption(), ex);
        }

        return componentInstance;
    }

    private <RESULT> RESULT createInstance(Class clazzInstance, Object[] constructorArguments) {
        return createInstance(clazzInstance, constructorArguments, false);
    }

    private <RESULT> RESULT createInstance(Class instanceClass,
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

    private <RESULT> RESULT postCreateProcess(RESULT instance) {
        //LoggerHandler.injectLogger(instance, this, defaultLoggerClass);
        PostConstructHandler.invokePostConstructMethod(instance);
        ConfigurationHandler.invokeConfigurationMethods(instance);
        //instance = (RESULT)
        //        LoggingHandler.wrapLoggingProxy(instance, this, defaultLoggingClasses.toArray(new Class[0]));
        return instance;
    }

    private <RESULT> RESULT createInstanceByArguments(Class instanceClass, Object[] constructorArguments) {
        Object[] instanceArguments = createInstanceArguments(constructorArguments);
        return (RESULT) ReflectionUtil.createDeclaredInstance(instanceClass, false, instanceArguments);
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

    private <RESULT> RESULT getComponentOrThrow(Class<RESULT> clazz) {
        RESULT component = getComponent(clazz);
        if (component == null) {
            throw new ComponentNotFoundException(clazz);
        }

        return component;
    }

    private <RESULT> RESULT getComponentOrThrow(String componentName) {
        RESULT component = getComponent(componentName);
        if (component == null) {
            throw new ComponentNotFoundException(componentName);
        }

        return component;
    }

    private boolean isExistsComponentCaption(String componentCaption) {
        return components.keySet().stream().anyMatch((k) -> componentCaption.equals(k.getCaption()));
    }

    private boolean isExistsComponentClass(Class componentClass) {
        return components.keySet().stream().anyMatch((k) -> componentClass.equals(k.getClazz()));
    }

    private String getComponentName(Class<?> clazz) {
        return StringUtil.toLowerFirstSymbol(clazz.getSimpleName());
    }
}
