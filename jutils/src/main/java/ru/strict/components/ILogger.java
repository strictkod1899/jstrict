package ru.strict.components;

 interface ILogger {
     void trace(String message);

    /**
     * Информационное сообщение логирования
     * @param format Сообщение для String.format
     * @param args Аргументы для String.format
     */
     void trace(String format, String...args);

    /**
     * Информационное сообщение логирования для Debug-режима
     * @param message Сообщение исключения
     */
     void debug(String message);

    /**
     * Информационное сообщение логирования для Debug-режима
     * @param format Сообщение для String.format
     * @param args Аргументы для String.format
     */
     void debug(String format, String...args);

    /**
     * Информационное сообщение логирования
     * @param message Сообщение исключения
     */
     void info(String message);

    /**
     * Информационное сообщение логирования
     * @param format Сообщение для String.format
     * @param args Аргументы для String.format
     */
     void info(String format, String...args);

    /**
     * Предупреждающее сообщения логирования
     * @param message Сообщение исключения
     */
     void warn(String message);

    /**
     * Сообщение предупреждения
     * @param format Сообщение для String.format
     * @param args Аргументы для String.format
     */
     void warn(String format, String...args);

    /**
     * Логирование исключения
     * @param message Сообщение исключения
     */
     void error(String message);

    /**
     * Логирование исключения
     * <p><b>Пример использования:</b></p>
     * <code><pre style="background-color: white; font-family: consolas">
     *      Logger.error(ex.getClass().toString(), ex.getMessage());
     * </pre></code>
     * @param type Тип исключения
     * @param message Сообщение исключения
     */
     void error(String type,  String message);

    /**
     * Логирование исключения
     * <p><b>Пример использования:</b></p>
     * <code><pre style="background-color: white; font-family: consolas">
     *      Logger.error(ex.getClass().toString(), "My message", ex.getMessage());
     * </pre></code>
     * @param customMessage Пользовательское (дополнительное) сообщение
     * @param type Тип исключения
     * @param message Сообщение исключения
     */
     void error(String type, String customMessage, String message);

    /**
     * Сообщение исключения
     * @param format Сообщение для String.format
     * @param args Аргументы для String.format
     */
     void error(String format, String...args);

    /**
     * Логирование исключения
     * @param message Сообщение исключения
     */
     void fatal(String message);

    /**
     * Логирование исключения
     * <p><b>Пример использования:</b></p>
     * <code><pre style="background-color: white; font-family: consolas">
     *      Logger.error(ex.getClass().toString(), ex.getMessage());
     * </pre></code>
     * @param type Тип исключения
     * @param message Сообщение исключения
     */
     void fatal(String type,  String message);

    /**
     * Логирование исключения
     * <p><b>Пример использования:</b></p>
     * <code><pre style="background-color: white; font-family: consolas">
     *      Logger.error(ex.getClass().toString(), "My message", ex.getMessage());
     * </pre></code>
     * @param customMessage Пользовательское (дополнительное) сообщение
     * @param type Тип исключения
     * @param message Сообщение исключения
     */
     void fatal(String type, String customMessage, String message);

    /**
     * Сообщение исключения
     * @param format Сообщение для String.format
     * @param args Аргументы для String.format
     */
     void fatal(String format, String...args);
}
