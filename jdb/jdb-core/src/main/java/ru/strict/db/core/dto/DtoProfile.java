package ru.strict.db.core.dto;

import ru.strict.utils.UtilHashCode;

/**
 * Основная информация профиля пользователя (имя, фамилия, отчество)
 */
public class DtoProfile<ID> extends DtoBase<ID> {

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
    private DtoUserBase<ID> user;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(String name, String surname, String middlename, ID userId){
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

    public DtoProfile() {
        super();
        name = null;
        surname = null;
        middlename = null;
        userId = null;
        user = null;
    }

    public DtoProfile(String name, String surname, String middlename, ID userId) {
        super();
        initialize(name, surname, middlename, userId);
    }

    public DtoProfile(ID id, String name, String surname, String middlename, ID userId) {
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
            throw new NullPointerException();
        }

        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if(surname == null) {
            throw new NullPointerException();
        }

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
        if(userId == null) {
            throw new NullPointerException();
        }

        this.userId = userId;
    }

    public DtoUserBase<ID> getUser() {
        return user;
    }

    public void setUser(DtoUserBase<ID> user) {
        setUser(user, true);
    }

    protected void setUserSafe(DtoUserBase<ID> user) {
        setUser(user, false);
    }

    private void setUser(DtoUserBase<ID> user, boolean isCircleMode) {
        if(isCircleMode && user != null){
            user.setProfileSafe(this);
        }

        this.user = user;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("dto profile [%s]: %s %s %s", String.valueOf(getId()), surname, name, middlename);
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof DtoProfile) {
            DtoProfile object = (DtoProfile) obj;
            return super.equals(object) && name.equals(object.getName()) && surname.equals(object.getSurname())
                    && middlename.equals(object.getMiddlename()) && userId.equals(object.getUserId());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        int superHashCode = super.hashCode();
        return UtilHashCode.createSubHashCode(superHashCode, name, surname, middlename, userId);
    }
    //</editor-fold>
}
