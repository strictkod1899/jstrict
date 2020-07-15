package ru.strict.patterns.composite;

/**
 * Pattern 'Composite'. Базовая реализация композитных элементов.
 * Методы для добавления дочерних элементов выбрасывают исключения.
 *
 * @param <COLLECTION> Источник хранения дочерних элементов, например, массив, Collection, List и др.
 * @param <SOURCE> Элемент композиции. Используется для указания родительского класса композии и дочерних классов
 */
public abstract class CompositeBase<COLLECTION, SOURCE extends IComposite<SOURCE>>
        implements ICompositeExtension<COLLECTION, SOURCE> {

    private SOURCE parent;

    public CompositeBase() {
        parent = null;
    }

    @Override
    public void add(SOURCE component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(SOURCE component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void set(int i, SOURCE component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public SOURCE getChild(int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public SOURCE getParent() {
        return parent;
    }

    @Override
    public void setParent(SOURCE parent) {
        this.parent = parent;
    }

    @Override
    public COLLECTION getChilds() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(SOURCE component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }
}
