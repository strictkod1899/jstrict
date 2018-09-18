package ru.strict.db.core.dto;

import java.util.Date;
import ru.strict.utils.UtilHashCode;

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
    private DtoCity<ID> city;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(Date dateBirth, String phone, ID cityId){
        this.dateBirth = dateBirth;
        this.phone = phone;
        this.cityId = cityId;
        city = null;
    }
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
        initialize(dateBirth, phone, cityId);
    }

    public DtoProfileInfo(ID id, String name, String surname, String middlename, ID userId, Date dateBirth, String phone,
                             ID cityId) {
        super(id, name, surname, middlename, userId);
        initialize(dateBirth, phone, cityId);
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

    public DtoCity<ID> getCity() {
        return city;
    }

    public void setCity(DtoCity<ID> city) {
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
                    && cityId.equals(object.getCityId());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        int superHashCode = super.hashCode();
        return UtilHashCode.createSubHashCode(superHashCode, dateBirth, phone, cityId);
    }
    //</editor-fold>
}
