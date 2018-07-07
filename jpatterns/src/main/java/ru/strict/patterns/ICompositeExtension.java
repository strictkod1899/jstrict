package ru.strict.patterns;

/**
 * Интерфейс расширенной реализации паттерна "Компоновщик"
 * @param <SOURCE> источник хранения дочерних элементов, например, массив, Collection, List и др.
*/
public interface ICompositeExtension<SOURCE, COMPOSIT extends IComposite> extends IComposite<COMPOSIT> {

	/**
	* Заменить элемент на определенной позиции новым
	*/
	void set(int i, COMPOSIT component);

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
	boolean contains(COMPOSIT component);

	/**
	 * Очистить список хранимых элементов
	 */
	void clear();
}
