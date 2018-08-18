package ru.strict.db.hibernate.entities;

import ru.strict.utils.UtilHashCode;

import javax.persistence.*;
import java.util.UUID;

/**
 * Основная информация профиля пользователя (имя, фамилия, отчество)
 */
@Table(name = "profile")
@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class EntityProfileBase extends EntityBase {

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
    private UUID userId;

    /**
     * Пользователь системы связанный с данным профилем
     */
    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "userx_id", insertable = false, updatable = false)
    private EntityUser user;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(String name, String surname, String middlename, UUID userId){
        if(name == null) {
            throw new NullPointerException("name is NULL");
        } else if(surname == null) {
            throw new NullPointerException("surname is NULL");
        } else if(userId == null) {
            throw new NullPointerException("userId is NULL");
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

    public EntityProfileBase(String name, String surname, String middlename, UUID userId) {
        super();
        initialize(name, surname, middlename, userId);
    }

    public EntityProfileBase(UUID id, String name, String surname, String middlename, UUID userId) {
        super(id);
        initialize(name, surname, middlename, userId);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null) {
            throw new NullPointerException("name is NULL");
        }

        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if(surname == null) {
            throw new NullPointerException("surname is NULL");
        }

        this.surname = surname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        if(userId == null) {
            throw new NullPointerException("userId is NULL");
        }

        this.userId = userId;
    }

    public EntityUser getUser() {
        return user;
    }

    public void setUser(EntityUser user) {
        this.user = user;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("entity profile [%s]: %s %s %s", String.valueOf(getId()), surname, name, middlename);
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof EntityProfileBase) {
            EntityProfileBase object = (EntityProfileBase) obj;
            return super.equals(object) && name.equals(object.getName()) && surname.equals(object.getSurname())
                    && middlename.equals(object.getMiddlename()) && userId.equals(object.getUserId())
                    && user.equals(object.getUser());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        int superHashCode = super.hashCode();
        return UtilHashCode.createSubHashCode(superHashCode, name, surname, middlename, userId, user);
    }
    //</editor-fold>
}
