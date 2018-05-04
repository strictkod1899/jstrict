package ru.strict.db.dto;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Роль пользователя в системе (например, администратор, пользователь, неавторизированный пользователь и др.)
 */
public class StrictDtoRoleuser<ID> extends StrictDtoBase<ID>{

    /**
     * Набор символов характеризующих роль
     */
    private String symbols;

    /**
     * Описание роли
     */
    private String description;

    /**
     * Пользователи свзяанные с ролью
     */
    private Collection<StrictDtoUser> users;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictDtoRoleuser() {
        super();
        symbols = null;
        description = null;
        users = new LinkedList<>();
    }

    public StrictDtoRoleuser(String symbols, String description) {
        super();
        this.symbols = symbols;
        this.description = description;
        users = new LinkedList<>();
    }

    public StrictDtoRoleuser(ID id, String symbols, String description) {
        super(id);
        this.symbols = symbols;
        this.description = description;
        users = new LinkedList<>();
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

    public Collection<StrictDtoUser> getUsers() {
        return users;
    }

    public void setUsers(Collection<StrictDtoUser> users) {
        this.users = users;
    }

    public void addUser(StrictDtoUser user) {
        this.users.add(user);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("dto roleuser [%s]: %s (%s)", String.valueOf(getId()), symbols, description);
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof StrictDtoRoleuser) {
            StrictDtoRoleuser object = (StrictDtoRoleuser) obj;
            return super.equals(object) && symbols.equals(object.getSymbols())
                    && description.equals(object.getDescription())
                    && (users.size() == object.getUsers().size() && users.containsAll(object.getUsers()));
        }else
            return false;
    }
    //</editor-fold>
}
