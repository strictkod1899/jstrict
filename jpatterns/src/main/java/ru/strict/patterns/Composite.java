package ru.strict.patterns;

import java.util.LinkedList;
import java.util.List;

/**
* Композитный элемент, который может хранить другие элементы
*/
public abstract class Composite<COMPOSIT extends IComposite> extends CompositeBase<List, COMPOSIT> {

	private List<COMPOSIT> childs;

	public Composite(){
		super();
		childs = new LinkedList<>();
	}

	@Override
	public void add(COMPOSIT component){
		childs.add(component);
	}

	@Override
	public void remove(COMPOSIT component){
		childs.remove(component);
	}

	@Override
	public void set(int i, COMPOSIT component){
		childs.set(i, component);
	}

	@Override
	public COMPOSIT getChild(int i){
		return childs.get(i);
	}

	@Override
	public List<COMPOSIT> getChilds(){
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
	public boolean contains(COMPOSIT component) {
		return childs.contains(component);
	}

	@Override
	public void clear() {
		childs.clear();
	}
}
