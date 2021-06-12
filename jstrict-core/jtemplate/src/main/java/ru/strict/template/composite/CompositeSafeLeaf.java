package ru.strict.template.composite;

import java.util.Collections;
import java.util.List;

/**
 * Pattern 'Composite'. Конечный элемент, который не может хранить другие элементы
 * и не выбрасывает исключение при попытке получить дочерние элементы
 */
public abstract class CompositeSafeLeaf<SOURCE extends IComposite<SOURCE>> extends CompositeBase<List<SOURCE>, SOURCE> {

    public CompositeSafeLeaf() {
        super();
    }

    @Override
    public SOURCE getChild(int i) {
        return null;
    }

    @Override
    public List<SOURCE> getChilds() {
        return Collections.emptyList();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean contains(SOURCE component) {
        return false;
    }

    @Override
    public void clear() {
        // ignore
    }
}
