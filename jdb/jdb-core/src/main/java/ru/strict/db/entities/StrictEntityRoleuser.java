package ru.strict.db.entities;

public class StrictEntityRoleuser<ID> extends StrictEntityBase<ID>{

    /**
     * Набор символов характеризующих роль
     */
    private String symbols;

    /**
     * Описание роли
     */
    private String description;

    //<editor-fold defaultState="collapsed" desc="constructors">
	public StrictEntityRoleuser() {
        super();
        symbols = null;
        description = null;
    }

    public StrictEntityRoleuser(String symbols, String description) {
    	super();
        this.symbols = symbols;
        this.description = description;
    }

    public StrictEntityRoleuser(ID id, String symbols, String description) {
        super(id);
        this.symbols = symbols;
        this.description = description;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
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
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("entity[%s]: %s (%s)", String.valueOf(getId()), symbols, description);
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof StrictEntityRoleuser) {
            StrictEntityRoleuser entity = (StrictEntityRoleuser) obj;
            return super.equals(entity) && symbols.equals(entity.getSymbols()) && description.equals(entity.getDescription());
        }else
            return false;
    }
    //</editor-fold>
}
