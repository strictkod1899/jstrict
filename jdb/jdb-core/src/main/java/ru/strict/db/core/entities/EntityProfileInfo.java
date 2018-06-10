package ru.strict.db.core.entities;

import java.util.Date;
import ru.strict.utils.StrictUtilHashCode;

/**
 * Расширенная информация профиля пользователя (имя, фамилия, отчество, дата рождения, телефон,
 * старана, город, адрес)
 */
public class EntityProfileInfo<ID> extends EntityProfile<ID> {

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

    //<editor-fold defaultState="collapsed" desc="constructors">
    public EntityProfileInfo(){
        super();
        dateBirth = null;
        phone = null;
        country = null;
        city = null;
    }

    public EntityProfileInfo(String name, String surname, String middlename, ID userId, Date dateBirth,
                             String phone, String country, String city) {
        super(name, surname, middlename, userId);
        this.dateBirth = dateBirth;
        this.phone = phone;
        this.country = country;
        this.city = city;
    }

    public EntityProfileInfo(ID id, String name, String surname, String middlename, ID userId, Date dateBirth,
                             String phone, String country, String city) {
        super(id, name, surname, middlename, userId);
        this.dateBirth = dateBirth;
        this.phone = phone;
        this.country = country;
        this.city = city;
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
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    public String toString(){
        return String.format("entity profileinfo [%s]: %s %s %s\n%tD, phone: %s, %s %s", String.valueOf(getId()), getSurname(), getName(), getMiddlename(),
                dateBirth, phone, country, city);
    }

    public boolean equals(Object obj){
        if(obj!=null && obj instanceof EntityProfileInfo){
            EntityProfileInfo object = (EntityProfileInfo) obj;
            return super.equals(object) && dateBirth.equals(object.getDateBirth()) && phone.equals(object.getPhone())
                    && country.equals(object.getCountry()) && city.equals(object.getCity());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        int superHashCode = super.hashCode();
        return StrictUtilHashCode.createSubHashCode(superHashCode, dateBirth, phone, country, city);
    }
    //</editor-fold>
}
