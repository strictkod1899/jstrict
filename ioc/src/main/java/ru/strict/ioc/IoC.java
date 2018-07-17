package ru.strict.ioc;

import ru.strict.utils.UtilReflection;

import java.util.HashMap;
import java.util.Map;

public class IoC implements IIoC {

    private Map<IoCKeys, IoCData> components;

    public IoC() {
        components = new HashMap<>();
    }

    @Override
    public <RESULT> void addComponent(Class<RESULT> clazz, Class component, Object...constructorArguments) {
        if(clazz == null || component ==null){
            return;
        }

        components.put(new IoCKeys(null, clazz), new IoCData(component, constructorArguments));
    }

    @Override
    public void addComponent(String caption, Class component, Object...constructorArguments) {
        if(caption == null || component == null){
            return;
        }

        components.put(new IoCKeys(caption, component.getClass()), new IoCData(component, constructorArguments));
    }

    @Override
    public <RESULT> void addComponent(String caption, Class<RESULT> clazz, Class component, Object...constructorArguments) {
        if(caption == null || clazz == null || component ==null){
            return;
        }

        components.put(new IoCKeys(caption, clazz), new IoCData(component, constructorArguments));
    }

    @Override
    public <RESULT> RESULT getComponent(String caption) {
        RESULT result = null;
        if(caption != null) {
            IoCKeys key = components.keySet().stream().filter((k) -> caption.equals(k.getCaption())).findFirst().orElse(null);
            if (key != null) {
                IoCData instanceData = components.get(key);
                Object[] instanceArguments = createInstanceArguments(instanceData.getConstructorArguments());

                if(instanceData != null){
                    result = (RESULT) UtilReflection.createInstance(instanceData.getClazzInstance(), instanceArguments);
                }
            }
        }

        return result;
    }

    @Override
    public <RESULT> RESULT getComponent(Class<RESULT> clazz) {
        RESULT result = null;
        if(clazz != null) {
            IoCKeys key = components.keySet().stream().filter((k) -> clazz.equals(k.getClazz())).findFirst().orElse(null);
            if (key != null) {
                IoCData instanceData = components.get(key);
                Object[] instanceArguments = createInstanceArguments(instanceData.getConstructorArguments());

                if(instanceData != null){
                    result = (RESULT) UtilReflection.createInstance(instanceData.getClazzInstance(), instanceArguments);
                }
            }
        }
        return result;
    }

    private Object[] createInstanceArguments(Object[] createArguments){
        int countArguments = createArguments.length;
        Object[] instanceArguments = new Object[countArguments];

        for(int i = 0; i < countArguments; i++){
            Object instanceArgument = null;
            Object createArgument = createArguments[i];
            if(createArgument instanceof String){
                instanceArgument = getComponent((String)createArgument);
            }else{
                instanceArgument = getComponent((Class)createArgument);
            }

            instanceArguments[i] = instanceArgument;
        }

        return instanceArguments;
    }

}
