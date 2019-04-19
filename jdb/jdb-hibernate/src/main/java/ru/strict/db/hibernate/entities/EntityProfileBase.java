package ru.strict.db.hibernate.entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * Основная информация профиля пользователя (имя, фамилия, отчество)
 */
@MappedSuperclass
public abstract class EntityProfileBase extends EntityBase<Long> {

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
    private Long userId;

    /**
     * Пользователь системы связанный с данным профилем
     */
    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "userx_id", insertable = false, updatable = false)
    private EntityUser user;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(String name, String surname, String middlename, Long userId){
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

    public EntityProfileBase(String name, String surname, String middlename, Long userId) {
        super();
        initialize(name, surname, middlename, userId);
    }

    public EntityProfileBase(Long id, String name, String surname, String middlename, Long userId) {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public EntityUser getUser() {
        return user;
    }

    public void setUser(EntityUser user) {
        setUser(user, true);
    }

    protected void setUserSafe(EntityUser user) {
        setUser(user, false);
    }

    private void setUser(EntityUser user, boolean isCircleMode) {
        if(isCircleMode && user != null){
            user.addProfileSafe(this);
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
        EntityProfileBase object = (EntityProfileBase) o;
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
    public EntityProfileBase clone(){
        try {
            EntityProfileBase clone = (EntityProfileBase) super.clone();
            clone.setUser(user == null ? null : user.clone());
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected EntityProfileBase cloneSafeUser(EntityUser user){
        try {
            EntityProfileBase clone = (EntityProfileBase) super.clone();
            clone.setUser(user);
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
    //</editor-fold>
}
