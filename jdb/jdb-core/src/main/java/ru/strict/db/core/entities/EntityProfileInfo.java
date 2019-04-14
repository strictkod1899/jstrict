package ru.strict.db.core.entities;

import java.util.Date;
import java.util.Objects;


/**
 * Расширенная информация профиля пользователя (имя, фамилия, отчество, дата рождения, телефон, город)
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
     * Идентификатор города
     */
    private ID cityId;

    /**
     * Город связанный с пользователем
     */
    private EntityCity<ID> city;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(Date dateBirth, String phone, ID cityId){
        this.dateBirth = dateBirth;
        this.phone = phone;
        this.cityId = cityId;
        city = null;
    }
    public EntityProfileInfo(){
        super();
        dateBirth = null;
        phone = null;
        cityId = null;
        city = null;
    }

    public EntityProfileInfo(String name, String surname, String middlename, ID userId, Date dateBirth, String phone,
                             ID cityId) {
        super(name, surname, middlename, userId);
        initialize(dateBirth, phone, cityId);
    }

    public EntityProfileInfo(ID id, String name, String surname, String middlename, ID userId, Date dateBirth, String phone,
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

    public EntityCity<ID> getCity() {
        return city;
    }

    public void setCity(EntityCity<ID> city) {
        this.city = city;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    public String toString(){
        return String.format("profileinfo [%s]: %s %s %s\n%tD, phone - %s, city - %s", String.valueOf(getId()),
                getSurname(), getName(), getMiddlename(), dateBirth, phone, cityId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EntityProfileInfo<ID> that = (EntityProfileInfo<ID>) o;
        return Objects.equals(dateBirth, that.dateBirth) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(cityId, that.cityId) &&
                Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dateBirth, phone, cityId, city);
    }

    @Override
    public EntityProfileInfo<ID> clone(){
        EntityProfile<ID> baseClone = super.clone();

        EntityProfileInfo<ID> clone = new EntityProfileInfo<>();
        clone.setId(baseClone.getId());
        clone.setName(baseClone.getName());
        clone.setSurname(baseClone.getSurname());
        clone.setMiddlename(baseClone.getMiddlename());
        clone.setUserId(baseClone.getUserId());
        clone.setUser(baseClone.getUser());
        clone.setDateBirth(dateBirth == null ? null : (Date) dateBirth.clone());
        clone.setPhone(phone);
        clone.setCityId(cityId);
        clone.setCity(city == null ? null : city.clone());
        return clone;
    }
    //</editor-fold>
}
