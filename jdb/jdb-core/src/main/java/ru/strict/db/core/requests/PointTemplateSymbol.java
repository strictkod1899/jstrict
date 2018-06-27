package ru.strict.db.core.requests;

/**
 * Место добавления шаблонного символа sql-запроса.
 * <p><b>Пример использования:</b></p>
 * <p>Результат применения END для шаблонного символа '%'</p>
 * <code><pre style="background-color: white; font-family: consolas">
 *      WHERE table.username LIKE 'Konstant%'
 * </pre></code>
 */
public enum PointTemplateSymbol {
    /**
     * Добавить шаблонный символ перед фразой
     */
    BEGIN,
    /**
     * Добавить шаблонный символ в конце фразы
     */
    END,
    /**
     * Добавить шаблонный символ с обеих сторон фразы
     */
    BOTH;
}
