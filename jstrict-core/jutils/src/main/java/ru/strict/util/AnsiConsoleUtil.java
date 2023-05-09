package ru.strict.util;

import org.fusesource.jansi.AnsiConsole;

/**
 * Взаимодействие с конслью, воспринимающей ANSI-коды
 */
public class AnsiConsoleUtil {

    /**
     * Установить AnsiConsole как системную System.out
     */
    public static void install() {
        AnsiConsole.systemInstall();
    }

    /**
     * Удалить AnsiConsole из системной System.out
     */
    public static void uninstall() {
        AnsiConsole.systemUninstall();
    }

    public static void print(String text, AnsiColor color) {
        print(formatToAnsiColor(text, color));
    }

    public static void println(String text, AnsiColor color) {
        println(formatToAnsiColor(text, color));
    }

    public static void print(String text) {
        AnsiConsole.out.print(text);
    }

    public static void println(String text) {
        AnsiConsole.out.println(text);
    }

    /**
     * Обернуть текст в ansi-код указанного цвета
     */
    public static String formatToAnsiColor(String text, AnsiColor color) {
        return String.format("%s%s%s", color.getColorCode(), text, AnsiColor.RESET.getColorCode());
    }
}
