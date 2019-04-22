package ru.strict.patterns;

/**
 * Pattern 'Observer'. Описание базовой функциональности наблюдаемого класса (класс за которым наблюдает Observer)
 * @param <O> Типы объектов, которые могут наблюдать за данным экземпляром
*/
public interface IObservable<O> {

	/**
	* Добавить класс-наблюдатель за текущим объектом
	*/
	void addObserver(O observer);

	/**
	* Удалить класс-наблюдатель
	*/
	void removeObserver(O observer);
}
