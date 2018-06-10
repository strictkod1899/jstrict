package ru.strict.patterns;

/**
* Описание базовой функцональности фабрики
*/
public interface IFactory<I, P> {

	/**
	* Создать экземпляр класса
	*/
	I instance(P parameter);
}
