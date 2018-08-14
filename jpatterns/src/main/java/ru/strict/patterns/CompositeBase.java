package ru.strict.patterns;

/**
 * Pattern 'Composite'. Базовая реализация компзитных элементв.
 * Методы для добавлени дочерних элементов выбрасывают исключения.
 * @param <COMPOSIT> Элемент композиции. Используется для указания родительского класса композии и дочерних классов
 * @param <SOURCE> Источник хранения дочерних элементов, например, массив, Collection, List и др.
 */
public abstract class CompositeBase<SOURCE, COMPOSIT extends IComposite>
		implements ICompositeExtension<SOURCE, COMPOSIT> {

	private COMPOSIT parent;

	public CompositeBase(){
		parent = null;
	}

	@Override
	public void add(COMPOSIT component){
		throw new UnsupportedOperationException();
	}

	@Override
	public void remove(COMPOSIT component){
		throw new UnsupportedOperationException();
	}

	@Override
	public void set(int i, COMPOSIT component){
		throw new UnsupportedOperationException();
	}

	@Override
	public COMPOSIT getChild(int i){
		throw new UnsupportedOperationException();
	}

	@Override
	public COMPOSIT getParent(){
		return parent;
	}

	@Override
	public void setParent(COMPOSIT parent){
		this.parent = parent;
	}

	@Override
	public SOURCE getChilds(){
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
	public boolean contains(COMPOSIT component) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}
}
