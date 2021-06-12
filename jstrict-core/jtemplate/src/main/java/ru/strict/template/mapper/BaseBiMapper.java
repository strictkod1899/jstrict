package ru.strict.template.mapper;

/**
 * Базовая реализация двухстороннего маппера
 */
public abstract class BaseBiMapper<S, T extends MapTarget> extends BaseMapper<S, T>
        implements IBiMapper<S, T> {
    /**
     * Реализация маппинга объекта
     *
     * @param target Объект, который необходимо преобразовать в тип источника
     */
    protected abstract S implementMap(T target) throws Exception;

    @Override
    public S map(T t) {
        try {
            return t == null ? null : implementMap(t);
        } catch (Exception ex) {
            throw new MappingException(ex);
        }
    }
}
