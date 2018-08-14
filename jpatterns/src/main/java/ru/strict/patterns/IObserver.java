package ru.strict.patterns;

/**
* Pattern 'Observer'. Описание базовой реализации "наблюдателя" (класс, который наблюдает за Observable)
*/
public interface IObserver {

	/**
	* Обновить состояние объекта, при смене состояния
	*/
	void update(Object...parameters);
}
