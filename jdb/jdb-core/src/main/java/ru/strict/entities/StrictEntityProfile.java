package ru.strict.entities;

/**
 * Класс описывает entity человека
 */
public class StrictEntityProfile<ID extends Long> extends StrictEntityBase<ID> {

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

    public StrictEntityProfile() {
        super();
    }

    public StrictEntityProfile(String name, String surname, String middlename) {
        super();
        this.name = name;
        this.surname = surname;
        this.middlename = middlename;
    }

    public StrictEntityProfile(ID id, String name, String surname, String middlename) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.middlename = middlename;
    }

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
}
