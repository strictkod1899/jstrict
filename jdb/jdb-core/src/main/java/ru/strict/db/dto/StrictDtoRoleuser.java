package ru.strict.db.dto;

public class StrictDtoRoleuser<ID> extends StrictDtoBase<ID>{

    /**
     * Набор символов характеризующих роль
     */
    private String symbols;

    /**
     * Описание роли
     */
    private String description;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictDtoRoleuser() {
        super();
        symbols = null;
        description = null;
    }

    public StrictDtoRoleuser(String symbols, String description) {
        super();
        this.symbols = symbols;
        this.description = description;
    }

    public StrictDtoRoleuser(ID id, String symbols, String description) {
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
        return String.format("%s: %s (%s)", super.toString(), symbols, description);
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof StrictDtoRoleuser) {
            StrictDtoRoleuser entity = (StrictDtoRoleuser) obj;
            return super.equals(entity) && symbols.equals(entity.getSymbols()) && description.equals(entity.getDescription());
        }else
            return false;
    }
    //</editor-fold>
}
