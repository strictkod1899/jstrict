package ru.strict.patterns;

/**
 * Базовая реализация маппера
 */
public abstract class MapperBase<S, T extends MapTarget> implements IMapper<S, T> {

    /**
     * Реализация маппинга объекта
     * @param target Объект, который необходимо преобразовать в тип источника
     * @return
     */
    protected abstract S implementMap(T target) throws Exception;
    /**
     * Реализация маппинга объекта
     * @param source Объект, который необходимо преобразовать в целевой тип
     * @return
     */
    protected abstract T implementMap(S source) throws Exception;

    @Override
    public S map(T t) {
        try {
            return t == null ? null : implementMap(t);
        } catch (Exception ex){
            throw new MappingException(ex);
        }
    }

    @Override
    public T map(S source) {
        try {
            return source == null ? null : implementMap(source);
        } catch (Exception ex){
            throw new MappingException(ex);
        }
    }
}
