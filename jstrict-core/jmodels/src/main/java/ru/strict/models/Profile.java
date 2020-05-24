package ru.strict.models;

import ru.strict.patterns.BaseModel;

import java.util.Objects;

/**
 * Основная информация профиля пользователя (имя, фамилия, отчество)
 */
public class Profile<ID> extends BaseModel<ID> {

    /**
     * Имя
     */
    private String name;
    /**
     * Фамилия
     */
    private String surname;
    /**
     * Идентификатор пользователя
     */
    private ID userId;
    /**
     * Пользователь системы связанный с данным профилем
     */
    private User<ID> user;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void init(String name, String surname, ID userId) {
        if (name == null) {
            throw new IllegalArgumentException("name is NULL");
        } else if (surname == null) {
            throw new IllegalArgumentException("surname is NULL");
        } else if (userId == null) {
            throw new IllegalArgumentException("userId is NULL");
        }

        this.name = name;
        this.surname = surname;
        this.userId = userId;
    }

    public Profile() {
        super();
    }

    public Profile(String name, String surname, ID userId) {
        super();
        init(name, surname, userId);
    }

    public Profile(ID id, String name, String surname, ID userId) {
        super(id);
        init(name, surname, userId);
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

    public ID getUserId() {
        return userId;
    }

    public void setUserId(ID userId) {
        this.userId = userId;
    }

    public User<ID> getUser() {
        return user;
    }

    public void setUser(User<ID> user) {
        this.user = user;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString() {
        return String.format("profile [%s]: %s %s", String.valueOf(getId()), surname, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Profile<ID> object = (Profile<ID>) o;
        return Objects.equals(name, object.name) &&
                Objects.equals(surname, object.surname) &&
                Objects.equals(userId, object.userId) &&
                Objects.equals(user, object.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, userId, user);
    }

    @Override
    public Profile<ID> clone() {
        try {
            Profile<ID> clone = (Profile<ID>) super.clone();
            clone.setUser(user == null ? null : user.clone());
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
    //</editor-fold>
}
