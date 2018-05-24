package ru.strict.patterns;

public abstract class StrictCompositeBase<SOURCE> implements IStrictCompositeExtension<SOURCE>{

	private IStrictComposite parent;

	public StrictCompositeBase(){
		parent = null;
	}

	@Override
	public void add(IStrictComposite component){
		throw new UnsupportedOperationException();
	}

	@Override
	public void remove(IStrictComposite component){
		throw new UnsupportedOperationException();
	}

	@Override
	public void set(int i, IStrictComposite component){
		throw new UnsupportedOperationException();
	}

	@Override
	public IStrictComposite getChild(int i){
		throw new UnsupportedOperationException();
	}

	@Override
	public IStrictComposite getParent(){
		return parent;
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
	public boolean contains(IStrictComposite component) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}
}
