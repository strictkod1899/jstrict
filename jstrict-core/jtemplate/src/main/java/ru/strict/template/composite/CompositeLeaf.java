package ru.strict.template.composite;

import java.util.List;

/**
 * Pattern 'Composite'. Конечный элемент, который не может хранить другие элементы
 */
public abstract class CompositeLeaf<SOURCE extends IComposite<SOURCE>> extends CompositeBase<List<SOURCE>, SOURCE> {

    public CompositeLeaf() {
        super();
    }
}
