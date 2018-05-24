package ru.strict.patterns;

import java.util.LinkedList;
import java.util.List;

/**
* Композитный элемент, который может хранить другие элементы
*/
public abstract class StrictComposite extends StrictCompositeBase<List>{

	private List<IStrictComposite> childs;

	public StrictComposite(){
		super();
		childs = new LinkedList<>();
	}

	@Override
	public void add(IStrictComposite component){
		childs.add(component);
	}

	@Override
	public void remove(IStrictComposite component){
		childs.remove(component);
	}

	@Override
	public void set(int i, IStrictComposite component){
		childs.set(i, component);
	}

	@Override
	public IStrictComposite getChild(int i){
		return childs.get(i);
	}

	@Override
	public List<IStrictComposite> getChilds(){
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
	public boolean contains(IStrictComposite component) {
		return childs.contains(component);
	}

	@Override
	public void clear() {
		childs.clear();
	}
}
