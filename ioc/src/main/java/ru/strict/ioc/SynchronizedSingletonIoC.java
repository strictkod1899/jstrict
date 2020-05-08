package ru.strict.ioc;

/**
 * Пример использования:
 * public class IoC extends ru.strict.ioc.SynchronizedSingletonIoC {
 * <p>
 * public IoC() {
 * super();
 * init();
 * }
 * <p>
 * public static IoC instance(){
 * if(getInstance() == null || !(getInstance() instanceof IoC)){
 * setInstance(new IoC());
 * }
 * <p>
 * return getInstance();
 * }
 * <p>
 * private void init(){
 * ...
 * }
 * }
 */
public class SynchronizedSingletonIoC extends SynchronizedIoC {

    private static SynchronizedIoC instance;

    public SynchronizedSingletonIoC() {
        super();
    }

    protected static void setInstance(SynchronizedIoC instance) {
        SynchronizedSingletonIoC.instance = instance;
    }

    protected static <INSATNCE> INSATNCE getInstance() {
        return (INSATNCE) instance;
    }
}
