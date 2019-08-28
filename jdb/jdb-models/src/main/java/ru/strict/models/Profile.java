package ru.strict.models;

import java.util.Objects;

/**
 * Основная информация профиля пользователя (имя, фамилия, отчество)
 */
public class Profile<ID> extends DtoBase<ID> {

    /**
     * Имя
     */
    private String name;

    /**
     * Фамилия
     */
    private String surname;

    /**
     * Отчетство
     */
    private String middlename;

    /**
     * Идентификатор пользователя
     */
    private ID userId;

    /**
     * Пользователь системы связанный с данным профилем
     */
    private UserBase<ID> user;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(String name, String surname, String middlename, ID userId){
        if(name == null) {
            throw new IllegalArgumentException("name is NULL");
        } else if(surname == null) {
            throw new IllegalArgumentException("surname is NULL");
        } else if(userId == null) {
            throw new IllegalArgumentException("userId is NULL");
        }

        this.name = name;
        this.surname = surname;
        this.middlename = middlename;
        this.userId = userId;
        user = null;
    }

    public Profile() {
        super();
        name = null;
        surname = null;
        middlename = null;
        userId = null;
        user = null;
    }

    public Profile(String name, String surname, String middlename, ID userId) {
        super();
        initialize(name, surname, middlename, userId);
    }

    public Profile(ID id, String name, String surname, String middlename, ID userId) {
        super(id);
        initialize(name, surname, middlename, userId);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public ID getUserId() {
        return userId;
    }

    public void setUserId(ID userId) {
        this.userId = userId;
    }

    public UserBase<ID> getUser() {
        return user;
    }

    public void setUser(UserBase<ID> user) {
        setUser(user, true);
    }

    protected void setUserSafe(UserBase<ID> user) {
        setUser(user, false);
    }

    private void setUser(UserBase<ID> user, boolean isCircleMode) {
        if(isCircleMode && user != null){
            user.addProfileSafe(this);
        }

        this.user = user;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("profile [%s]: %s %s %s", String.valueOf(getId()), surname, name, middlename);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Profile<ID> object = (Profile<ID>) o;
        return Objects.equals(name, object.name) &&
                Objects.equals(surname, object.surname) &&
                Objects.equals(middlename, object.middlename) &&
                Objects.equals(userId, object.userId) &&
                Objects.equals(user, object.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hashCodeWithoutUser(), user);
    }

    protected int hashCodeWithoutUser(){
        return Objects.hash(super.hashCode(), name, surname, middlename, userId);
    }

    @Override
    public Profile<ID> clone(){
        try {
            Profile<ID> clone = (Profile<ID>) super.clone();
            clone.setUser(user == null ? null : user.clone());
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected Profile<ID> cloneSafeUser(UserBase<ID> user){
        try {
            Profile<ID> clone = (Profile<ID>) super.clone();
            clone.setUser(user);
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
    //</editor-fold>
}