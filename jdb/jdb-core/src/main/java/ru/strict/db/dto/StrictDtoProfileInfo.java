package ru.strict.db.dto;

import java.util.Date;
import ru.strict.utils.StrictUtilHashCode;

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
    private String country;

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
        country = null;
        city = null;
        address = null;
    }

    public StrictDtoProfileInfo(String name, String surname, String middlename, ID userId, Date dateBirth, String phone,
                                String country, String city, String address) {
        super(name, surname, middlename, userId);
        this.dateBirth = dateBirth;
        this.phone = phone;
        this.country = country;
        this.city = city;
        this.address = address;
    }

    public StrictDtoProfileInfo(ID id, String name, String surname, String middlename, ID userId,
                                Date dateBirth, String phone, String country, String city, String address) {
        super(id, name, surname, middlename, userId);
        this.dateBirth = dateBirth;
        this.phone = phone;
        this.country = country;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
        return String.format("dto profileinfo [%s]: %s %s %s\n%tD, phone: %s, %s %s %s", String.valueOf(getId()), getSurname(), getName(), getMiddlename(),
                dateBirth, phone, country, city, address);
    }

    public boolean equals(Object obj){
        if(obj instanceof StrictDtoProfileInfo){
            StrictDtoProfileInfo object = (StrictDtoProfileInfo) obj;
            return super.equals(object) && dateBirth==object.getDateBirth() && phone.equals(object.getPhone())
                    && country.equals(object.getCountry()) && city.equals(object.getCity())
                    && address.equals(object.getAddress());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        int superHashCode = super.hashCode();
        return StrictUtilHashCode.createSubHashCode(superHashCode, dateBirth, phone, country, city, address);
    }
    //</editor-fold>
}
