package ru.strict.db.hibernate.entities;

import ru.strict.utils.UtilHashCode;

import javax.persistence.*;
import java.util.UUID;

/**
 * Город
 */
@Entity
@Table(name = "city")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class EntityCity extends EntityNamed {

    /**
     * Идентификатор страны
     */
    @Column(name = "country_id")
    private UUID countryId;

    /**
     * Страна связанная с данным городом
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", insertable = false, updatable = false)
    private EntityCountry country;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(UUID countryId){
        if(countryId == null) {
            throw new NullPointerException("countryId is NULL");
        }

        this.countryId = countryId;
        country = null;
    }

    public EntityCity() {
        super();
        countryId = null;
        country = null;
    }

    public EntityCity(String caption, UUID countryId) {
        super(caption);
        initialize(countryId);
    }

    public EntityCity(UUID id, String caption, UUID countryId) {
        super(id, caption);
        initialize(countryId);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public UUID getCountryId() {
        return countryId;
    }

    public void setCountryId(UUID countryId) {
        if(countryId == null) {
            throw new NullPointerException("countryId is NULL");
        }

        this.countryId = countryId;
    }

    public EntityCountry getCountry() {
        return country;
    }

    public void setCountry(EntityCountry country) {
        this.country = country;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("entity city [%s]: %s, country - %s", String.valueOf(getId()), getCaption(), countryId);
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof EntityCity) {
            EntityCity object = (EntityCity) obj;
            return super.equals(object) && countryId.equals(object.getCountryId()) && country.equals(object.getCountry());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        int superHashCode = super.hashCode();
        return UtilHashCode.createSubHashCode(superHashCode, countryId, country);
    }
    //</editor-fold>
}
