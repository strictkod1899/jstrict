package ru.strict.components;

 public interface ILogger {
     void log(LogLevel logLevel, String format, Object...args);

     void trace(String message);

    /**
     * Информационное сообщение логирования
     * @param format Сообщение для String.format
     * @param args Аргументы для String.format
     */
     void trace(String format, Object...args);

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
     void debug(String format, Object...args);

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
     void info(String format, Object...args);

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
     void warn(String format, Object...args);


     /**
      * Логирование предупреждения
      * <p><b>Пример использования:</b></p>
      * <code><pre style="background-color: white; font-family: consolas">
      *      Logger.warn(ex);
      * </pre></code>
      * @param ex Исключение
      */
     void warn(Exception ex);

     /**
      * Логирование предупреждения
      * <p><b>Пример использования:</b></p>
      * <code><pre style="background-color: white; font-family: consolas">
      *      Logger.warn("My message", ex);
      * </pre></code>
      * @param customMessage Пользовательское (дополнительное) сообщение
      * @param ex Исключение
      */
     void warn(String customMessage, Exception ex);

     /**
     * Логирование исключения
     * @param message Сообщение исключения
     */
     void error(String message);

    /**
     * Логирование исключения
     * <p><b>Пример использования:</b></p>
     * <code><pre style="background-color: white; font-family: consolas">
     *      Logger.error(ex);
     * </pre></code>
     * @param ex Исключение
     */
     void error(Exception ex);

    /**
     * Логирование исключения
     * <p><b>Пример использования:</b></p>
     * <code><pre style="background-color: white; font-family: consolas">
     *      Logger.error("My message", ex);
     * </pre></code>
     * @param customMessage Пользовательское (дополнительное) сообщение
     * @param ex Исключение
     */
     void error(String customMessage, Exception ex);

    /**
     * Сообщение исключения
     * @param format Сообщение для String.format
     * @param args Аргументы для String.format
     */
     void error(String format, Object...args);

    /**
     * Логирование исключения
     * @param message Сообщение исключения
     */
     void fatal(String message);

    /**
     * Логирование исключения
     * <p><b>Пример использования:</b></p>
     * <code><pre style="background-color: white; font-family: consolas">
     *      Logger.fatal(ex);
     * </pre></code>
     * @param ex Исключение
     */
     void fatal(Exception ex);

    /**
     * Логирование исключения
     * <p><b>Пример использования:</b></p>
     * <code><pre style="background-color: white; font-family: consolas">
     *      Logger.fatal("My message", ex);
     * </pre></code>
     * @param customMessage Пользовательское (дополнительное) сообщение
     * @param ex Исключение
     */
     void fatal(String customMessage, Exception ex);

    /**
     * Сообщение исключения
     * @param format Сообщение для String.format
     * @param args Аргументы для String.format
     */
     void fatal(String format, Object...args);
}
