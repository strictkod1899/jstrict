package ru.strict.patterns;

/**
 * Pattern 'Factory'. Описание базовой функцональности фабрики
 * @param <RESULT> Тип создаваемого экземпляра
 * @param <PARAMETER> Тип передаваемого параметра
 */
public interface IFactory<RESULT, PARAMETER> {

	/**
	* Создать экземпляр класса
	*/
	RESULT instance(PARAMETER parameter);
}
