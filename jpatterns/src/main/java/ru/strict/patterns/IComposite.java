package ru.strict.patterns;

/**
 * Pattern 'Composite'. Интерфейс реализации паттерна "Компоновщик"*
 * @param <COMPOSIT> Элемент композиции. Используется для указания родительского класса композии
*/
public interface IComposite<COMPOSIT extends IComposite> {

	/**
	* Добавить элемент в структуру
	*/
	void add(COMPOSIT component);

	/**
	* Удалить элемент из структуры
	*/
	void remove(COMPOSIT component);

	/**
	* Получить дочерний элемент по определенной позиции
	*/
	COMPOSIT getChild(int i);

	/**
	* Получить родительский элемент
	*/
	COMPOSIT getParent();

	/**
	* Установить родительский элемент
	*/
	void setParent(COMPOSIT parent);
}
