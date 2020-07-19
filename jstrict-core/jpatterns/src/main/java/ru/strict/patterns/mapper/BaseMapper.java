package ru.strict.patterns.mapper;

/**
 * Базовая реализация маппера
 */
public abstract class BaseMapper<S, T> implements IMapper<S, T> {
    /**
     * Реализация маппинга объекта
     *
     * @param source Объект, который необходимо преобразовать в целевой тип
     */
    protected abstract T implementMap(S source) throws Exception;

    @Override
    public T map(S source) {
        try {
            return source == null ? null : implementMap(source);
        } catch (Exception ex) {
            throw new MappingException(ex);
        }
    }
}
