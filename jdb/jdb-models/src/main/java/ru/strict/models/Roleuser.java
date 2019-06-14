package ru.strict.models;

import java.util.Collection;
import java.util.Objects;
import java.util.TreeSet;

/**
 * Роль пользователя в системе (например, администратор, пользователь, неавторизированный пользователь и др.)
 */
public class Roleuser<ID> extends DtoBase<ID> {

    /**
     * Набор символов характеризующих роль
     */
    private String code;

    /**
     * Описание роли
     */
    private String description;

    /**
     * Пользователи свзяанные с ролью
     */
    private Collection<UserBase<ID>> users;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(String code, String description){
        if(code == null) {
            throw new IllegalArgumentException("code is NULL");
        }

        this.code = code;
        this.description = description;
        users = new TreeSet<>();
    }

    public Roleuser() {
        super();
        code = null;
        description = null;
        users = new TreeSet<>();
    }

    public Roleuser(String code, String description) {
        super();
        initialize(code, description);
    }

    public Roleuser(ID id, String code, String description) {
        super(id);
        initialize(code, description);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<UserBase<ID>> getUsers() {
        return users;
    }

    public void setUsers(Collection<UserBase<ID>> users) {
        if(users == null) {
            throw new IllegalArgumentException("users is NULL");
        }

        for(UserBase<ID> user : users){
            user.addRoleSafe(this);
        }

        this.users = users;
    }

    public void addUser(UserBase<ID> user){
        addUser(user, true);
    }

    protected void addUserSafe(UserBase<ID> user){
        addUser(user, false);
    }

    private void addUser(UserBase<ID> user, boolean isCircleMode){
        if(user == null) {
            throw new IllegalArgumentException("user is NULL");
        }

        if(user != null){
            if(isCircleMode) {
                user.addRoleSafe(this);
            }
            users.add(user);
        }
    }

    public void addUsers(Collection<UserBase<ID>> users){
        if(users!=null) {
            for(UserBase<ID> user : users){
                addUser(user);
            }
        }
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("roleuser [%s]: %s (%s)", String.valueOf(getId()), code, description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Roleuser<ID> object = (Roleuser<ID>) o;
        return Objects.equals(code, object.code) &&
                Objects.equals(description, object.description) &&
                Objects.equals(users, object.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hashCodeWithoutUser(), users);
    }

    @Override
    public Roleuser<ID> clone(){
        try {
            Roleuser<ID> clone = (Roleuser<ID>) super.clone();

            clone.users = new TreeSet<>();
            for(UserBase<ID> user : this.users){
                clone.addUser(user.clone());
            }
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected int hashCodeWithoutUser(){
        return Objects.hash(super.hashCode(), code, description);
    }

    protected Roleuser<ID> cloneSafeUser(UserBase<ID> user){
        try {
            Roleuser<ID> clone = (Roleuser<ID>) super.clone();

            clone.addUser(user);
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
    //</editor-fold>
}
