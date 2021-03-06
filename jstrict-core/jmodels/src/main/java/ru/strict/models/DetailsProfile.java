package ru.strict.models;

import java.util.Date;
import java.util.Objects;

/**
 * Расширенная информация профиля пользователя (имя, фамилия, отчество, дата рождения, телефон, город)
 */
public class DetailsProfile<ID> extends Profile<ID> {

    /**
     * Отчетство
     */
    private String middlename;
    /**
     * Пол = Мужчина
     */
    private Boolean man;
    /**
     * Дата рождения
     */
    private Date dateBirth;
    /**
     * Номер телефона
     */
    private String phone;
    /**
     * Идентификатор города
     */
    private ID cityId;
    /**
     * Город связанный с пользователем
     */
    private City<ID> city;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void init(String middlename, Boolean man, Date dateBirth, String phone, ID cityId) {
        this.middlename = middlename;
        this.man = man;
        this.dateBirth = dateBirth;
        this.phone = phone;
        this.cityId = cityId;
    }

    public DetailsProfile() {
        super();
    }

    public DetailsProfile(String name,
            String surname,
            String middlename,
            ID userId,
            Boolean man,
            Date dateBirth,
            String phone,
            ID cityId) {
        super(name, surname, userId);
        init(middlename, man, dateBirth, phone, cityId);
    }

    public DetailsProfile(ID id,
            String name,
            String surname,
            String middlename,
            ID userId,
            Boolean man,
            Date dateBirth,
            String phone,
            ID cityId) {
        super(id, name, surname, userId);
        init(middlename, man, dateBirth, phone, cityId);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public Boolean isMan() {
        return man;
    }

    public void setMan(Boolean man) {
        this.man = man;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ID getCityId() {
        return cityId;
    }

    public void setCityId(ID cityId) {
        this.cityId = cityId;
    }

    public City<ID> getCity() {
        return city;
    }

    public void setCity(City<ID> city) {
        this.city = city;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString() {
        return String.format("profileinfo [%s]: %s %s %s\n%tD, phone - %s, city - %s", String.valueOf(getId()),
                getSurname(), getName(), getMiddlename(), dateBirth, phone, cityId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        DetailsProfile<ID> object = (DetailsProfile<ID>) o;
        return Objects.equals(dateBirth, object.dateBirth) &&
                Objects.equals(middlename, object.middlename) &&
                Objects.equals(man, object.man) &&
                Objects.equals(phone, object.phone) &&
                Objects.equals(cityId, object.cityId) &&
                Objects.equals(city, object.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), middlename, man, dateBirth, phone, cityId, city);
    }

    @Override
    public DetailsProfile<ID> clone() {
        DetailsProfile<ID> clone = (DetailsProfile<ID>) super.clone();

        clone.setDateBirth(dateBirth == null ? null : (Date) dateBirth.clone());
        clone.setCity(city == null ? null : city.clone());
        return clone;
    }
    //</editor-fold>
}
