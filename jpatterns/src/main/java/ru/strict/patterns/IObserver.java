package ru.strict.patterns;

/**
* Описание базовой реализации "наблюдателя" (класс, который наблюдает за Observable)
*/
public interface IObserver {

	/**
	* Обновить состояние объекта
	*/
	void update();	
}
