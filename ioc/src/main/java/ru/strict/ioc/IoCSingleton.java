package ru.strict.ioc;

/**
 * Пример использования:
 * public class IoC extends ru.strict.ioc.IoCSingleton {
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
public class IoCSingleton extends IoC{

    private static IoC instance;

    public IoCSingleton() {
        super();
    }

    protected static void setInstance(IoC instance){
        IoCSingleton.instance = instance;
    }

    protected static <INSATNCE> INSATNCE getInstance() {
        return (INSATNCE) instance;
    }
}
