package ru.strict.db.hibernate.entities;

import java.util.Objects;
import javax.persistence.*;

/**
 * Город
 */
@Entity
public class EntityCity extends EntityNamed<Long> {

    /**
     * Идентификатор страны
     */
    @Column(name = "country_id", nullable = false)
    private Long countryId;

    /**
     * Страна связанная с данным городом
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", insertable = false, updatable = false, nullable = false)
    private EntityCountry country;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(Long countryId){
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

    public EntityCity(String caption, Long countryId) {
        super(caption);
        initialize(countryId);
    }

    public EntityCity(Long id, String caption, Long countryId) {
        super(id, caption);
        initialize(countryId);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        if(countryId == null) {
            throw new NullPointerException("countryId is NULL");
        }

        this.countryId = countryId;
    }

    public EntityCountry getCountry() {
        return country;
    }

    public void setCountry(EntityCountry country) {
        setCountry(country, true);
    }

    protected void setCountrySafe(EntityCountry country) {
        setCountry(country, false);
    }

    private void setCountry(EntityCountry country, boolean isCircleMode){
        if(isCircleMode && country != null){
            country.addCitySafe(this);
        }

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
            return super.equals(obj) && Objects.equals(countryId, object.getCountryId())
                    && Objects.equals(country, object.getCountry());
        }else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(getId(), getCaption(), countryId);
    }
    //</editor-fold>
}
