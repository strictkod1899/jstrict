package ru.strict.db.mappers;

/**
 * Базовая реализация маппера
 */
public abstract class StrictMapperBase<S extends MapSource, T extends MapTarget>
        implements StrictMapperAny<S, T> {

    protected abstract S implementMap(T t);
    protected abstract T implementMap(S entity);

    @Override
    public S map(T t){
        return t ==null ? null : implementMap(t);
    }

    @Override
    public T map(S entity){
        return entity==null ? null : implementMap(entity);
    }
}
