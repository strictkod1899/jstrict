package ru.strict.entities;

import java.util.Date;

/**
 * Класс определяет entity человека, с набором информационных полей
 */
public class StrictEntityProfileInfo extends StrictEntityProfile<Long> {

    /**
     * Возраст
     */
    private Date dateBirth;

    /**
     * Номер телефона
     */
    private String phone;

    /**
     * Страна
     */
    private String counrty;

    /**
     * Город
     */
    private String city;

    /**
     * Адрес
     */
    private String address;

    public StrictEntityProfileInfo(){
        super();
    }

    public StrictEntityProfileInfo(String name, String surname, String lastname, Date dateBirth, String phone, String counrty, String city, String address) {
        super(name, surname, lastname);
        this.dateBirth = dateBirth;
        this.phone = phone;
        this.counrty = counrty;
        this.city = city;
        this.address = address;
    }

    public StrictEntityProfileInfo(Long id, String name, String surname, String lastname, Date dateBirth, String phone, String counrty, String city, String address) {
        super(id, name, surname, lastname);
        this.dateBirth = dateBirth;
        this.phone = phone;
        this.counrty = counrty;
        this.city = city;
        this.address = address;
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

    public String getCounrty() {
        return counrty;
    }

    public void setCounrty(String counrty) {
        this.counrty = counrty;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
