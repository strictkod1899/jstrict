package ru.strict.patterns;

/**
 * Pattern 'Command'. Описание базовой функцональности макро-команды (выполнение нескольких команд)
 * @param <COMMAND> Выполняемые команды
 */
public interface IMacroCommand<COMMAND extends ICommand> {

	/**
	* Выполнить набор команд
	*/
	void execute(COMMAND[] commands);
}
