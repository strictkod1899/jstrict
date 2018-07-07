package ru.strict.patterns;

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
