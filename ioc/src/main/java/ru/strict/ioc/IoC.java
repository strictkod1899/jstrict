package ru.strict.ioc;

import ru.strict.ioc.exceptions.ManyMatchComponentsException;
import ru.strict.ioc.exceptions.MatchInstanceTypeException;
import ru.strict.utils.UtilReflection;

import java.util.HashMap;
import java.util.Map;

/**
 * Чтобы при использовании метода addComponent передать string-переменную как значение параметра конструктору,
 * надо использовать в начале строки символ @, иначе этот параметр будет учитываться как название ioc-компонента.
 * <p><b>Пример использования:</b></p>
 * <code><pre style="background-color: white; font-family: consolas">
 * public class IoC extends ru.strict.ioc.IoC {
 *
 *      private static IoC instance;
 *
 *      public IoC() {
 *          super();
 *          initialize();
 *     }
 *
 *     public static IoC instance(){
 *         if(instance == null){
 *             instance = new IoC();
 *         }
 *
 *         return instance;
 *     }
 *
 *     private void initialize(){
 *         addComponent(A.class, A.class, InstanceType.REQUEST);
 *         addComponent(B.class, B.class, InstanceType.REQUEST, "comp1", "@param2", new Param3());
 *         addSingleton(C.class, new C());
 *         addComponent(D.class, D.class, InstanceType.REQUEST, A.class, B.class);
 *         ...
 *     }
 * }
 * </pre></code>
 */
public class IoC implements IIoC {

    private Map<IoCKeys, IoCData> components;

    public IoC() {
        components = new HashMap<>();
    }

    @Override
    public <RESULT> void addComponent(Class<RESULT> clazz, Class component, InstanceType type, Object...constructorArguments) {
        if(clazz == null || component == null){
            throw new IllegalArgumentException(
                    String.format("IoC exception. Fail add component to IoC because any is null: clazz = [%s], component = [%s]",
                            clazz, component));
        }

        if(isExistsComponentClass(clazz)){
            throw new ManyMatchComponentsException(clazz);
        }

        components.put(new IoCKeys(null, clazz), new IoCData(component, constructorArguments, type));
    }

    @Override
    public void addComponent(String caption, Class component, InstanceType type, Object...constructorArguments) {
        if(caption == null || component == null){
            throw new IllegalArgumentException(
                    String.format("IoC exception. Fail add component to IoC because any is null: caption = [%s], component = [%s]",
                            caption, component));
        }

        if(isExistsComponentCaption(caption)){
            throw new ManyMatchComponentsException(caption);
        }

        components.put(new IoCKeys(caption, component), new IoCData(component, constructorArguments, type));
    }

    @Override
    public <RESULT> void addComponent(Class<RESULT> clazz, String caption, Class component, InstanceType type, Object...constructorArguments) {
        if(caption == null || clazz == null || component == null){
            throw new IllegalArgumentException(
                    String.format("IoC exception. Fail add component to IoC because any is null: caption = [%s], clazz = [%s], component = [%s]",
                            caption, clazz, component));
        }

        if(isExistsComponentClass(clazz)){
            throw new ManyMatchComponentsException(clazz);
        }

        if(isExistsComponentCaption(caption)){
            throw new ManyMatchComponentsException(caption);
        }

        components.put(new IoCKeys(caption, clazz), new IoCData(component, constructorArguments, type));
    }

    @Override
    public void addSingleton(String caption, Object component) {
        if(caption == null || component == null){
            throw new IllegalArgumentException(
                    String.format("IoC exception. Fail add component to IoC because any is null: caption = [%s], component = [%s]",
                            caption, component));
        }

        if(isExistsComponentCaption(caption)){
            throw new ManyMatchComponentsException(caption);
        }

        components.put(new IoCKeys(caption, component.getClass()), new IoCData(component));
    }

    @Override
    public <RESULT> void addSingleton(Class<RESULT> clazz, Object component) {
        if(clazz == null || component == null){
            throw new IllegalArgumentException(
                    String.format("IoC exception. Fail add component to IoC because any is null: clazz = [%s], component = [%s]",
                            clazz, component));
        }

        if(isExistsComponentClass(clazz)){
            throw new ManyMatchComponentsException(clazz);
        }

        components.put(new IoCKeys(null, clazz), new IoCData(component));
    }

    @Override
    public <RESULT> void addSingleton(Class<RESULT> clazz, String caption, Object component) {
        if(caption == null || clazz == null || component == null){
            throw new IllegalArgumentException(
                    String.format("IoC exception. Fail add component to IoC because any is null: caption = [%s], clazz = [%s], component = [%s]",
                            caption, clazz, component));
        }

        if(isExistsComponentClass(clazz)){
            throw new ManyMatchComponentsException(clazz);
        }

        if(isExistsComponentCaption(caption)){
            throw new ManyMatchComponentsException(caption);
        }

        components.put(new IoCKeys(caption, clazz), new IoCData(component));
    }

    @Override
    public <RESULT> RESULT getComponent(String caption) {
        RESULT result = null;
        if(caption != null) {
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
        if(clazz != null) {
            IoCKeys key = components.keySet().stream()
                    .filter((k) -> k.getClazz() != null && UtilReflection.isInstanceOf(clazz, k.getClazz()))
                    .findFirst()
                    .orElse(null);
            if (key != null) {
                result = getInstance(key);
            }
        }
        return result;
    }

    @Override
    public <RESULT> void closeSessionInstance(Class<RESULT> clazz) throws MatchInstanceTypeException {
        if(clazz != null) {
            IoCKeys key = components.keySet().stream()
                    .filter((k) -> k.getClazz() != null && UtilReflection.isInstanceOf(clazz, k.getClazz()))
                    .findFirst()
                    .orElse(null);
            if (key != null) {
                closeSessionInstanceProcess(key);
            }
        }
    }

    @Override
    public void closeSessionInstance(String caption) throws MatchInstanceTypeException {
        if(caption != null) {
            IoCKeys key = components.keySet().stream()
                    .filter((k) -> caption.equals(k.getCaption()))
                    .findFirst()
                    .orElse(null);
            if (key != null) {
                closeSessionInstanceProcess(key);
            }
        }
    }

    private void closeSessionInstanceProcess(IoCKeys key) throws MatchInstanceTypeException {
        if (key != null) {
            IoCData instanceData = components.get(key);
            if (instanceData.getType() == InstanceType.SESSION) {
                instanceData.closeSessionInstance();
            } else {
                throw new MatchInstanceTypeException(InstanceType.SESSION, instanceData.getType());
            }
        }
    }

    private <RESULT> RESULT getInstance(IoCKeys key){
        RESULT result = null;

        IoCData instanceData = components.get(key);
        switch(instanceData.getType()){
            case REQUEST:
                result = createInstance(instanceData.getClazzInstance(), instanceData.getConstructorArguments());
                break;
            case SESSION:
                if(instanceData.getSessionInstance() != null){
                    result = instanceData.getSessionInstance();
                } else {
                    result = createInstance(instanceData.getClazzInstance(), instanceData.getConstructorArguments());
                    instanceData.setSessionInstance(result);
                }
                break;
            case SINGLETON:
                if(instanceData.getSingletonInstance() != null){
                    result = instanceData.getSingletonInstance();
                } else {
                    result = createInstance(instanceData.getClazzInstance(), instanceData.getConstructorArguments());
                    instanceData.setSingletonInstance(result);
                }
                break;
        }

        return result;
    }

    private <RESULT> RESULT createInstance(Class clazzInstance, Object[] constructorArguments){
        if(clazzInstance == null || constructorArguments == null){
            throw new IllegalArgumentException(
                    String.format("IoC exception. Fail add component to IoC because any is null:" +
                                    "clazzInstance = [%s], constructorArguments = [%s]",
                            clazzInstance, constructorArguments));
        }
        Object[] instanceArguments = createInstanceArguments(constructorArguments);
        return (RESULT) UtilReflection.createDeclaredInstance(clazzInstance, instanceArguments);
    }

    private Object[] createInstanceArguments(Object[] createArguments){
        int countArguments = createArguments.length;
        Object[] instanceArguments = new Object[countArguments];

        for(int i = 0; i < countArguments; i++){
            Object instanceArgument = null;
            Object createArgument = createArguments[i];
            if(createArgument instanceof String && !((String)createArgument).startsWith("@")){
                instanceArgument = getComponent((String)createArgument);
            }else if(createArgument instanceof Class){
                instanceArgument = getComponent((Class)createArgument);
                if(instanceArgument == null){
                    instanceArgument = createArgument;
                }
            }else{
                if(createArgument instanceof String && ((String)createArgument).startsWith("@")){
                    instanceArgument = ((String)createArgument).substring(1, ((String)createArgument).length());
                }else{
                    instanceArgument = createArgument;
                }
            }

            instanceArguments[i] = instanceArgument;
        }

        return instanceArguments;
    }

    private boolean isExistsComponentCaption(String componentCaption){
        return components.keySet().stream().anyMatch((k) -> componentCaption.equals(k.getCaption()));
    }

    private boolean isExistsComponentClass(Class componentClass){
        return components.keySet().stream().anyMatch((k) -> componentClass.equals(k.getClazz()));
    }
}
