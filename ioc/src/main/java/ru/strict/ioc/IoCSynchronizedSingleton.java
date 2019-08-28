package ru.strict.ioc;

/**
 * Пример использования:
 * public class IoC extends ru.strict.ioc.IoCSynchronizedSingleton {
 *
 *     public IoC() {
 *         super();
 *         initialize();
 *     }
 *
 *     public static IoC instance(){
 *         if(getInstance() == null || !(getInstance() instanceof IoC)){
 *             setInstance(new IoC());
 *         }
 *
 *         return getInstance();
 *     }
 *
 *     private void initialize(){
 *         ...
 *     }
 * }
 */
public class IoCSynchronizedSingleton extends IoCSynchronized{

    private static IoCSynchronized instance;

    public IoCSynchronizedSingleton() {
        super();
    }

    protected static void setInstance(IoCSynchronized instance){
        IoCSynchronizedSingleton.instance = instance;
    }

    protected static <INSATNCE> INSATNCE getInstance() {
        return (INSATNCE) instance;
    }
}
