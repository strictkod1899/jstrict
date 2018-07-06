package ru.strict.patterns;

public abstract class CompositeBase<SOURCE> implements ICompositeExtension<SOURCE> {

	private IComposite parent;

	public CompositeBase(){
		parent = null;
	}

	@Override
	public void add(IComposite component){
		throw new UnsupportedOperationException();
	}

	@Override
	public void remove(IComposite component){
		throw new UnsupportedOperationException();
	}

	@Override
	public void set(int i, IComposite component){
		throw new UnsupportedOperationException();
	}

	@Override
	public IComposite getChild(int i){
		throw new UnsupportedOperationException();
	}

	@Override
	public IComposite getParent(){
		return parent;
	}

	@Override
	public void setParent(IComposite parent){
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
	public boolean contains(IComposite component) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}
}
