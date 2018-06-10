package ru.strict.patterns;

/**
 * Интерфейс расширенной реализации паттерна "Компоновщик"
 * @param <SOURCE> источник хранения дочерних элементов, например, массив, Collection, List и др.
*/
public interface ICompositeExtension<SOURCE> extends IComposite {

	/**
	* Заменить элемент на определенной позиции новым
	*/
	void set(int i, IComposite component);

	/**
	* Получить все дочерние элементы
	*/
	SOURCE getChilds();

	/**
	 * Количество хранимых элементов
	 */
	int size();

	/**
	 * Список хранимых элементов пустой?
	 */
	boolean isEmpty();

	/**
	 * Содержит ли компонент указанный элемент
	 */
	boolean contains(IComposite component);

	/**
	 * Очистить список хранимых элементов
	 */
	void clear();
}
