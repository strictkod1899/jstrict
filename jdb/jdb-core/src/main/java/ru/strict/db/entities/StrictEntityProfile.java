package ru.strict.db.entities;

/**
 * Основная информация профиля пользователя (имя, фамилия, отчество)
 */
public class StrictEntityProfile<ID> extends StrictEntityBase<ID> {

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
    private StrictEntityUser user;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictEntityProfile() {
        super();
        name = null;
        surname = null;
        middlename = null;
        userId = null;
        user = null;
    }

    public StrictEntityProfile(String name, String surname, String middlename, ID userId) {
        super();
        this.name = name;
        this.surname = surname;
        this.middlename = middlename;
        this.userId = userId;
        user = null;
    }

    public StrictEntityProfile(ID id, String name, String surname, String middlename, ID userId) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.middlename = middlename;
        this.userId = userId;
        user = null;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    /**
     * Получить имя
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Установить имя
     * @param name новое имя
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Получить фамилию
     * @return
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Установить фамилию
     * @param surname новая фамилия
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Получить отчество
     * @return
     */
    public String getMiddlename() {
        return middlename;
    }

    /**
     * Установить отчество
     * @param middlename новое отчество
     */
    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public ID getUserId() {
        return userId;
    }

    public void setUserId(ID userId) {
        this.userId = userId;
    }

    public StrictEntityUser getUser() {
        return user;
    }

    public void setUser(StrictEntityUser user) {
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
        if(obj instanceof StrictEntityProfile) {
            StrictEntityProfile entity = (StrictEntityProfile) obj;
            return super.equals(entity) && name.equals(entity.getName()) && surname.equals(entity.getSurname())
                    && middlename.equals(entity.getMiddlename()) && userId.equals(entity.getUserId())
                    && user.equals(entity.getUser());
        }else
            return false;
    }
    //</editor-fold>
}
