package ru.strict.patterns;

/**
 * Pattern 'Factory'. Описание базовой функцональности фабрики
 * @param <I> Тип создаваемого экземпляра
 * @param <P> Тип передаваемого параметра
 */
public interface IFactory<I, P> {

	/**
	* Создать экземпляр класса
	*/
	I instance(P parameter);
}
