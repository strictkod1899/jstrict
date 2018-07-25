package ru.strict.db.hibernate.entities;

import ru.strict.utils.UtilHashCode;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Основная информация профиля пользователя (имя, фамилия, отчество)
 */
@Entity
@Table(name = "profile")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class EntityProfile extends EntityBase {

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
    private UUID userId;

    /**
     * Пользователь системы связанный с данным профилем
     */
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

    public EntityProfile() {
        super();
        name = null;
        surname = null;
        middlename = null;
        userId = null;
        user = null;
    }

    public EntityProfile(String name, String surname, String middlename, UUID userId) {
        super();
        initialize(name, surname, middlename, userId);
    }

    public EntityProfile(UUID id, String name, String surname, String middlename, UUID userId) {
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
        if(obj!=null && obj instanceof EntityProfile) {
            EntityProfile object = (EntityProfile) obj;
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
