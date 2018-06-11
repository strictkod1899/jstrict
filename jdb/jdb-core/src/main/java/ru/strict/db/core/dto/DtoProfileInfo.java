package ru.strict.db.core.dto;

import ru.strict.db.core.entities.EntityProfile;
import ru.strict.utils.UtilHashCode;

import java.util.Date;

/**
 * Расширенная информация профиля пользователя (имя, фамилия, отчество, дата рождения, телефон, город)
 */
public class DtoProfileInfo<ID> extends DtoProfile<ID> {

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
    private DtoCity city;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public DtoProfileInfo(){
        super();
        dateBirth = null;
        phone = null;
        cityId = null;
        city = null;
    }

    public DtoProfileInfo(String name, String surname, String middlename, ID userId, Date dateBirth, String phone,
                          ID cityId) {
        super(name, surname, middlename, userId);
        this.dateBirth = dateBirth;
        this.phone = phone;
        this.cityId = cityId;
        city = null;
    }

    public DtoProfileInfo(ID id, String name, String surname, String middlename, ID userId, Date dateBirth, String phone,
                          ID cityId) {
        super(id, name, surname, middlename, userId);
        this.dateBirth = dateBirth;
        this.phone = phone;
        this.cityId = cityId;
        city = null;
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

    public ID getCityId() {
        return cityId;
    }

    public void setCityId(ID cityId) {
        this.cityId = cityId;
    }

    public DtoCity getCity() {
        return city;
    }

    public void setCity(DtoCity city) {
        this.city = city;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    public String toString(){
        return String.format("entity profileinfo [%s]: %s %s %s\n%tD, phone - %s, city - %s", String.valueOf(getId()),
                getSurname(), getName(), getMiddlename(), dateBirth, phone, cityId);
    }

    public boolean equals(Object obj){
        if(obj!=null && obj instanceof DtoProfileInfo){
            DtoProfileInfo object = (DtoProfileInfo) obj;
            return super.equals(object) && dateBirth.equals(object.getDateBirth()) && phone.equals(object.getPhone())
                    && cityId.equals(object.getCityId()) && city.equals(object.getCity());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        int superHashCode = super.hashCode();
        return UtilHashCode.createSubHashCode(superHashCode, dateBirth, phone, cityId, city);
    }
    //</editor-fold>
}
