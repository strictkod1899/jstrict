package ru.strict.db.hibernate.entities;

import java.util.Objects;
import javax.persistence.*;

/**
 * Город
 */
@Entity
@Table(name = "city")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
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
            throw new IllegalArgumentException("countryId is NULL");
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EntityCity object = (EntityCity) o;
        return Objects.equals(countryId, object.countryId) &&
                Objects.equals(country, object.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hashCodeWithoutCountry(), country);
    }

    @Override
    public EntityCity clone(){
        try {
            EntityCity clone = (EntityCity) super.clone();

            clone.setCountry(country == null ? null : country.clone());
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected int hashCodeWithoutCountry(){
        return Objects.hash(super.hashCode(), countryId);
    }

    protected EntityCity cloneSafeCountry(EntityCountry country){
        try {
            EntityCity clone = (EntityCity) super.clone();

            clone.setCountry(country);
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
    //</editor-fold>
}
