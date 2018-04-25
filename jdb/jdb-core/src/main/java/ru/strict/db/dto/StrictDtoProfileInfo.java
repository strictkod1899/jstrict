package ru.strict.db.dto;

import java.util.Date;

/**
 * Расширенная информация профиля пользователя (имя, фамилия, отчество, дата рождения, телефон,
 * старана, город, адрес)
 */
public class StrictDtoProfileInfo<ID> extends StrictDtoProfile<ID> {

    /**
     * Дата рождения
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

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictDtoProfileInfo(){
        super();
        dateBirth = null;
        phone = null;
        counrty = null;
        city = null;
        address = null;
    }

    public StrictDtoProfileInfo(String name, String surname, String lastname, Date dateBirth, String phone, String counrty, String city, String address) {
        super(name, surname, lastname);
        this.dateBirth = dateBirth;
        this.phone = phone;
        this.counrty = counrty;
        this.city = city;
        this.address = address;
    }

    public StrictDtoProfileInfo(ID id, String name, String surname, String lastname, Date dateBirth, String phone, String counrty, String city, String address) {
        super(id, name, surname, lastname);
        this.dateBirth = dateBirth;
        this.phone = phone;
        this.counrty = counrty;
        this.city = city;
        this.address = address;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
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
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    public String toString(){
        return String.format("dto[%s]: %s %s %s\n%tD, phone: %s, %s %s %s", String.valueOf(getId()), getSurname(), getName(), getMiddlename(),
                dateBirth, phone, counrty, city, address);
    }

    public boolean equals(Object obj){
        if(obj instanceof StrictDtoProfileInfo){
            StrictDtoProfileInfo entity = (StrictDtoProfileInfo) obj;
            return super.equals(entity) && dateBirth==entity.getDateBirth() && phone.equals(entity.getPhone())
                    && counrty.equals(entity.getCounrty()) && city.equals(entity.getCity())
                    && address.equals(entity.getAddress());
        }else
            return false;
    }
    //</editor-fold>
}
