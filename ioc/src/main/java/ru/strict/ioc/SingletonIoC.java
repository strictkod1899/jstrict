package ru.strict.ioc;

/**
 * Пример использования:
 * public class IoC extends ru.strict.ioc.SingletonIoC {
 *
 *     public IoC() {
 *         super();
 *         init();
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
 *     private void init(){
 *         ...
 *     }
 * }
 */
public class SingletonIoC extends IoC{

    private static IoC instance;

    public SingletonIoC() {
        super();
    }

    protected static void setInstance(IoC instance){
        SingletonIoC.instance = instance;
    }

    protected static <INSATNCE> INSATNCE getInstance() {
        return (INSATNCE) instance;
    }
}
