package ru.strict.utils;

import java.io.PrintStream;

/**
 * Системные операции
 */
public class UtilSystem {

    /**
     * Применить системную кодировку для вывода сообщений в консоли
     */
    public static void applySystemEncodingForConsoleOutput(){
        String consoleEncoding = System.getProperty("consoleEncoding");
        if (consoleEncoding != null) {
            try {
                System.setOut(new PrintStream(System.out, true, consoleEncoding));
            } catch (java.io.UnsupportedEncodingException ex) {
                System.err.println("Unsupported encoding set for console: "+consoleEncoding);
            }
        }
    }

}
