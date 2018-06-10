package ru.strict.patterns;

/**
* Описание базовой функцональности макро-команды (выполнение нескольких команд)
*/
public interface IMacroCommand<COMMAND implements ICommand> {

	/**
	* Выполнить набор команд
	*/
	void execute(ICommand[] commands);
}
