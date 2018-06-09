package ru.strict.db.core.entities;

import java.util.Collection;
import java.util.LinkedList;
import ru.strict.utils.StrictUtilHashCode;

/**
 * Роль пользователя в системе (например, администратор, пользователь, неавторизированный пользователь и др.)
 */
public class StrictEntityRoleuser<ID> extends StrictEntityBase<ID>{

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
    private Collection<StrictEntityUser> users;

    //<editor-fold defaultState="collapsed" desc="constructors">
	public StrictEntityRoleuser() {
        super();
        symbols = null;
        description = null;
        users = new LinkedList<>();
    }

    public StrictEntityRoleuser(String symbols, String description) {
    	super();
        this.symbols = symbols;
        this.description = description;
        users = new LinkedList<>();
    }

    public StrictEntityRoleuser(ID id, String symbols, String description) {
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

    public Collection<StrictEntityUser> getUsers() {
        return users;
    }

    public void setUsers(Collection<StrictEntityUser> users) {
        this.users = users;
    }

    /**
     * Добавить пользователя использующего данную роль
     * @param user
     */
    public void addUser(StrictEntityUser user) {
        this.users.add(user);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("entity roleuser [%s]: %s (%s)", String.valueOf(getId()), symbols, description);
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof StrictEntityRoleuser) {
            StrictEntityRoleuser object = (StrictEntityRoleuser) obj;
            return super.equals(object) && symbols.equals(object.getSymbols())
                    && description.equals(object.getDescription())
                    && (users.size() == object.getUsers().size() && users.containsAll(object.getUsers()));
        }else
            return false;
    }

    @Override
    public int hashCode(){
    	int superHashCode = super.hashCode();
        return StrictUtilHashCode.createSubHashCode(superHashCode, symbols, description, users);
    }
    //</editor-fold>
}
