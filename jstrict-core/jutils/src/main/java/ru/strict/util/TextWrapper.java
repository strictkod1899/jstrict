package ru.strict.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.text.WordUtils;

import java.util.Arrays;

@UtilityClass
public class TextWrapper {

    /**
     * Специальный символ, чтобы apache.WordUtils не удалил пробелы в начале строки
     * Подробнее {@link #protectStartSpaces} и {@link #cleanStartSpaces}
     */
    private static final String SAFE_START_SPACE_SYMBOL = "|";

    public String wrap(String text, int lineMaxLength) {
        return Arrays.stream(text.split("\n"))
                .map(TextWrapper::protectStartSpaces)
                .map(line -> WordUtils.wrap(line, lineMaxLength))
                .map(TextWrapper::cleanStartSpaces)
                .reduce((line1, line2) -> String.format("%s\n%s", line1, line2))
                .orElse("");
    }

    /**
     * Если строка начинается с пробела, то apache.WorkUtils удаляет их.
     * Чтобы этого не допустить, добавляем к таким строкам специальный символ. Позже он будет удален
     */
    private String protectStartSpaces(String line) {
        return line.startsWith(" ") ? SAFE_START_SPACE_SYMBOL + line : line;
    }

    /**
     * Очистить ранее добавленный {@link #protectStartSpaces} специальный символ
     */
    private String cleanStartSpaces(String line) {
        return line.startsWith(SAFE_START_SPACE_SYMBOL) ? line.substring(SAFE_START_SPACE_SYMBOL.length()) : line;
    }
}
