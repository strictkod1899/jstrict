package ru.strict.patterns;

/**
* Интерфейс реализации паттерна "Компоновщик"
*/
public interface IStrictComposite{

	/**
	* Добавить элемент в структуру
	*/
	void add(IStrictComposite component);

	/**
	* Удалить элемент из структуры
	*/
	void remove(IStrictComposite component);

	/**
	* Получить дочерний элемент по определенной позиции
	*/
	IStrictComposite getChild(int i);

	/**
	* Получить родительский элемент
	*/
	IStrictComposite getParent();
}
