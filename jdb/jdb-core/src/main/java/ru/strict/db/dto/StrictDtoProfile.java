package ru.strict.db.dto;

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

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictDtoProfile() {
        super();
        name = null;
        surname = null;
        middlename = null;
    }

    public StrictDtoProfile(String name, String surname, String middlename) {
        super();
        this.name = name;
        this.surname = surname;
        this.middlename = middlename;
    }

    public StrictDtoProfile(ID id, String name, String surname, String middlename) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.middlename = middlename;
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
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("dto[%s]: %s %s %s", String.valueOf(getId()), surname, name, middlename);
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof StrictDtoProfile) {
            StrictDtoProfile entity = (StrictDtoProfile) obj;
            return super.equals(entity) && name.equals(entity.getName()) && surname.equals(entity.getSurname()) && middlename.equals(entity.getMiddlename());
        }else
            return false;
    }
    //</editor-fold>
}
