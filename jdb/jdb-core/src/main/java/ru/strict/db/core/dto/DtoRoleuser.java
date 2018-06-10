package ru.strict.db.core.dto;

import java.util.Collection;
import java.util.LinkedList;
import ru.strict.utils.UtilHashCode;

/**
 * Роль пользователя в системе (например, администратор, пользователь, неавторизированный пользователь и др.)
 */
public class DtoRoleuser<ID> extends DtoBase<ID> {

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
    private Collection<DtoUser> users;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public DtoRoleuser() {
        super();
        symbols = null;
        description = null;
        users = new LinkedList<>();
    }

    public DtoRoleuser(String symbols, String description) {
        super();
        this.symbols = symbols;
        this.description = description;
        users = new LinkedList<>();
    }

    public DtoRoleuser(ID id, String symbols, String description) {
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

    public Collection<DtoUser> getUsers() {
        return users;
    }

    public void setUsers(Collection<DtoUser> users) {
        this.users = users;
    }

    /**
     * Добавить пользователя использующего данную роль
     * @param user
     */
    public void addUser(DtoUser user) {
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
        if(obj!=null && obj instanceof DtoRoleuser) {
            DtoRoleuser object = (DtoRoleuser) obj;
            return super.equals(object) && symbols.equals(object.getSymbols())
                    && description.equals(object.getDescription())
                    && (users.size() == object.getUsers().size() && users.containsAll(object.getUsers()));
        }else
            return false;
    }

    @Override
    public int hashCode(){
        int superHashCode = super.hashCode();
        return UtilHashCode.createSubHashCode(superHashCode, symbols, description, users);
    }
    //</editor-fold>
}
