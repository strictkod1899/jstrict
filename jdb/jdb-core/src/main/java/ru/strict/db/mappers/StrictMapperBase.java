package ru.strict.db.mappers;

/**
 * Базовая реализация маппера
 */
public abstract class StrictMapperBase<S, T extends MapTarget>
        implements StrictMapperAny<S, T> {

    // TODO: Добавить trim() при установке string-значений

    /**
     * Реализация маппинга объекта
     * @param target Объект, который необходимо преобразовать в другой тип
     * @return
     */
    protected abstract S implementMap(T target);
    /**
     * Реализация маппинга объекта
     * @param source Объект, который необходимо преобразовать в другой тип
     * @return
     */
    protected abstract T implementMap(S source);

    @Override
    public S map(T t){
        return t ==null ? null : implementMap(t);
    }

    @Override
    public T map(S entity){
        return entity==null ? null : implementMap(entity);
    }
}
