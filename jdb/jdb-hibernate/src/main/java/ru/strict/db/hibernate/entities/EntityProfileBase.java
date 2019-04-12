package ru.strict.db.hibernate.entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * Основная информация профиля пользователя (имя, фамилия, отчество)
 */
@Table(name = "profile")
@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class EntityProfileBase<ID> extends EntityBase<ID> {

    /**
     * Имя
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Фамилия
     */
    @Column(name = "surname", nullable = false)
    private String surname;

    /**
     * Отчетство
     */
    @Column(name = "middlename", nullable = true)
    private String middlename;

    /**
     * Идентификатор пользователя
     */
    @Column(name = "userx_id", nullable = false)
    private ID userId;

    /**
     * Пользователь системы связанный с данным профилем
     */
    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "userx_id", insertable = false, updatable = false)
    private EntityUser<ID> user;

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

    public EntityProfileBase() {
        super();
        name = null;
        surname = null;
        middlename = null;
        userId = null;
        user = null;
    }

    public EntityProfileBase(String name, String surname, String middlename, ID userId) {
        super();
        initialize(name, surname, middlename, userId);
    }

    public EntityProfileBase(ID id, String name, String surname, String middlename, ID userId) {
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

    public EntityUser<ID> getUser() {
        return user;
    }

    public void setUser(EntityUser<ID> user) {
        setUser(user, true);
    }

    protected void setUserSafe(EntityUser<ID> user) {
        setUser(user, false);
    }

    private void setUser(EntityUser<ID> user, boolean isCircleMode) {
        if(isCircleMode && user != null){
            user.setProfileSafe(this);
        }

        this.user = user;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("entity profile [%s]: %s %s %s", String.valueOf(getId()), surname, name, middlename);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EntityProfileBase<ID> that = (EntityProfileBase<ID>) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(middlename, that.middlename) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, surname, middlename, userId, user);
    }
    //</editor-fold>
}
