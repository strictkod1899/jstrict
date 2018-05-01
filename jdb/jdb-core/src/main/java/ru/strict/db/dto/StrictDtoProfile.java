package ru.strict.db.dto;

/**
 * Основная информация профиля пользователя (имя, фамилия, отчество)
 */
public class StrictDtoProfile<ID> extends StrictDtoBase<ID> {

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
     * Пользователь
     */
    private StrictDtoUser user;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictDtoProfile() {
        super();
        name = null;
        surname = null;
        middlename = null;
        userId = null;
        user = null;
    }

    public StrictDtoProfile(String name, String surname, String middlename, ID userId) {
        super();
        this.name = name;
        this.surname = surname;
        this.middlename = middlename;
        this.userId = userId;
        user = null;
    }

    public StrictDtoProfile(ID id, String name, String surname, String middlename, ID userId) {
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

    public StrictDtoUser getUser() {
        return user;
    }

    public void setUser(StrictDtoUser user) {
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
        if(obj instanceof StrictDtoProfile) {
            StrictDtoProfile dto = (StrictDtoProfile) obj;
            return super.equals(dto) && name.equals(dto.getName()) && surname.equals(dto.getSurname())
                    && middlename.equals(dto.getMiddlename()) && userId.equals(dto.getUserId())
                    && user.equals(dto.getUser());
        }else
            return false;
    }
    //</editor-fold>
}
