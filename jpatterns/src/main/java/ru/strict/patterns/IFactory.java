package ru.strict.patterns;

/**
 * Pattern 'Factory'. Описание базовой функцональности фабрики
 * @param <R> Тип создаваемого экземпляра
 * @param <P> Тип передаваемого параметра
 */
public interface IFactory<R, P> {

	/**
	* Создать экземпляр класса
	*/
	R instance(P parameter);
}
