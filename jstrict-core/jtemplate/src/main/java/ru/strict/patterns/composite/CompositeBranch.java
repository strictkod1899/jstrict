package ru.strict.patterns.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Pattern 'Composite'. Композитный элемент, который может хранить другие элементы
 *
 * @param <SOURCE> Элемент композиции. Используется для указания родительского класса композии и дочерних классов
 */
public abstract class CompositeBranch<SOURCE extends IComposite<SOURCE>> extends CompositeBase<List<SOURCE>, SOURCE> {

    private List<SOURCE> childs;

    public CompositeBranch() {
        super();
        childs = new ArrayList<>();
    }

    @Override
    public void add(SOURCE component) {
        childs.add(component);
    }

    @Override
    public void remove(SOURCE component) {
        childs.remove(component);
    }

    @Override
    public void set(int i, SOURCE component) {
        childs.set(i, component);
    }

    @Override
    public SOURCE getChild(int i) {
        return childs.get(i);
    }

    @Override
    public List<SOURCE> getChilds() {
        return childs;
    }

    @Override
    public int size() {
        return childs.size();
    }

    @Override
    public boolean isEmpty() {
        return childs.isEmpty();
    }

    @Override
    public boolean contains(SOURCE component) {
        return childs.contains(component);
    }

    @Override
    public void clear() {
        childs.clear();
    }
}
