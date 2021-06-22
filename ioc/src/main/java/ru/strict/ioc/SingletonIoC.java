package ru.strict.ioc;

/**
 * Пример использования:
 * public class IoC extends ru.strict.ioc.SingletonIoC {
 * <p>
 * public static IoC instance() {
 *     if(getInstance() == null || !(getInstance() instanceof IoC)) {
 *         setInstance(new IoC());
 *     }
 * <p>
 *     return getInstance();
 * }
 * <p>
 *     protected void configure() {
 *     ...
 *     }
 * }
 */
public abstract class SingletonIoC extends BaseIoC {

    private static BaseIoC instance;

    public SingletonIoC() {
        super();
    }

    protected static void setInstance(BaseIoC instance) {
        SingletonIoC.instance = instance;
    }

    protected static <INSATNCE> INSATNCE getInstance() {
        return (INSATNCE) instance;
    }
}
