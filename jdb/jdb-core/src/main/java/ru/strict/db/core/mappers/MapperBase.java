package ru.strict.db.core.mappers;

/**
 * Базовая реализация маппера
 */
public abstract class MapperBase<S, T extends MapTarget>
        implements IMapper<S, T> {

    /**
     * Реализация маппинга объекта
     * @param target Объект, который необходимо преобразовать в тип источника
     * @return
     */
    protected abstract S implementMap(T target);
    /**
     * Реализация маппинга объекта
     * @param source Объект, который необходимо преобразовать в целевой тип
     * @return
     */
    protected abstract T implementMap(S source);

    @Override
    public S map(T t){
        return t == null ? null : implementMap(t);
    }

    @Override
    public T map(S entity){
        return entity == null ? null : implementMap(entity);
    }
}
