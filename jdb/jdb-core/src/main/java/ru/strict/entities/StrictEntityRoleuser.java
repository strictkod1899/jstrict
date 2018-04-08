package ru.strict.entities;

public class StrictEntityRoleuser extends StrictEntityBase<Long>{

    /**
     * Набор символов характеризующие роль
     */
    private String symbols;

    /**
     * Описание роли
     */
    private String description;

    public StrictEntityRoleuser(String symbols, String description) {
        this.symbols = symbols;
        this.description = description;
    }

    public StrictEntityRoleuser(Long id, String symbols, String description) {
        super(id);
        this.symbols = symbols;
        this.description = description;
    }

    public String getSymbols() {
        return symbols;
    }

    public void setSymbols(String symbols) {
        this.symbols = symbols;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
