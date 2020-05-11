package ru.strict.patterns;

/**
 * Pattern 'Command'. Описание базовой функцональности команды с возможностью отмены предыдущей выполненной команды
 */
public interface IUndoCommand extends ICommand {

    /**
     * Отменить результат выполнения предыдущей команды
     */
    void undo();
}
