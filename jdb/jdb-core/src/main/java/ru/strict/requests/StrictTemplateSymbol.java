package ru.strict.requests;

import ru.strict.enums.StrictEnumTemplateSymbol;

/**
 * Шаблонный символ сравнения строк
 */
public class StrictTemplateSymbol {
    /**
     * Шаблонный символ сравнения строк
     */
    private String templateSymbol;

    /**
     * Место добавления шаблонного символа
     */
    private StrictEnumTemplateSymbol enumTemplateSymbol;

    public StrictTemplateSymbol(String templateSymbol, StrictEnumTemplateSymbol enumTemplateSymbol) {
        this.templateSymbol = templateSymbol;
        this.enumTemplateSymbol = enumTemplateSymbol;
    }

    public String getTemplateSymbol() {
        return templateSymbol;
    }

    public StrictEnumTemplateSymbol getEnumTemplateSymbol() {
        return enumTemplateSymbol;
    }
}
