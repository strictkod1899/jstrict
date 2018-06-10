package ru.strict.patterns;

import java.util.LinkedList;
import java.util.List;

/**
* Композитный элемент, который может хранить другие элементы
*/
public abstract class Composite extends CompositeBase<List> {

	private List<IComposite> childs;

	public Composite(){
		super();
		childs = new LinkedList<>();
	}

	@Override
	public void add(IComposite component){
		childs.add(component);
	}

	@Override
	public void remove(IComposite component){
		childs.remove(component);
	}

	@Override
	public void set(int i, IComposite component){
		childs.set(i, component);
	}

	@Override
	public IComposite getChild(int i){
		return childs.get(i);
	}

	@Override
	public List<IComposite> getChilds(){
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
	public boolean contains(IComposite component) {
		return childs.contains(component);
	}

	@Override
	public void clear() {
		childs.clear();
	}
}
