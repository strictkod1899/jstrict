package ru.strict.patterns;

/**
* Интерфейс реализации паттерна "Компоновщик"
*/
public interface IComposite {

	/**
	* Добавить элемент в структуру
	*/
	void add(IComposite component);

	/**
	* Удалить элемент из структуры
	*/
	void remove(IComposite component);

	/**
	* Получить дочерний элемент по определенной позиции
	*/
	IComposite getChild(int i);

	/**
	* Получить родительский элемент
	*/
	IComposite getParent();

	/**
	* Установить родительский элемент
	*/
	void setParent(IComposite parent);
}
