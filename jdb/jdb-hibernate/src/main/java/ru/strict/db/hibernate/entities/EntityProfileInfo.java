package ru.strict.db.hibernate.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * Расширенная информация профиля пользователя (имя, фамилия, отчество, дата рождения, телефон, город)
 */
@Entity
@Table(name = "profile")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class EntityProfileInfo extends EntityProfileBase {

    /**
     * Дата рождения
     */
    @Column(name = "datebirth", nullable = true)
    private Date dateBirth;

    /**
     * Номер телефона
     */
    @Column(name = "phone", nullable = true)
    private String phone;

    /**
     * Идентификатор города
     */
    @Column(name = "city_id", nullable = true)
    private Long cityId;

    /**
     * Город связанный с пользователем
     */
    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "city_id", insertable = false, updatable = false)
    private EntityCity city;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(Date dateBirth, String phone, Long cityId){
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

    public EntityProfileInfo(String name, String surname, String middlename, Long userId, Date dateBirth, String phone,
                             Long cityId) {
        super(name, surname, middlename, userId);
        initialize(dateBirth, phone, cityId);
    }

    public EntityProfileInfo(Long id, String name, String surname, String middlename, Long userId, Date dateBirth, String phone,
                             Long cityId) {
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

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public EntityCity getCity() {
        return city;
    }

    public void setCity(EntityCity city) {
        this.city = city;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    public String toString(){
        return String.format("entity profileinfo [%s]: %s %s %s\n%tD, phone - %s, city - %s", String.valueOf(getId()),
                getSurname(), getName(), getMiddlename(), dateBirth, phone, cityId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EntityProfileInfo object = (EntityProfileInfo) o;
        return Objects.equals(dateBirth, object.dateBirth) &&
                Objects.equals(phone, object.phone) &&
                Objects.equals(cityId, object.cityId) &&
                Objects.equals(city, object.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dateBirth, phone, cityId, city);
    }

    @Override
    public EntityProfileInfo clone(){
        try {
            EntityProfileInfo clone = (EntityProfileInfo) super.clone();

            clone.setDateBirth(dateBirth == null ? null : (Date) dateBirth.clone());
            clone.setCity(city == null ? null : city.clone());
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
    //</editor-fold>
}
