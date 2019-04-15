package ru.strict.db.core.entities;


import java.util.Objects;

/**
 * Город
 */
public class EntityCity<ID> extends EntityNamed<ID> {

    /**
     * Идентификатор страны
     */
    private ID countryId;

    /**
     * Страна связанная с данным городом
     */
    private EntityCountry<ID> country;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(ID countryId){
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

    public EntityCity(String caption, ID countryId) {
        super(caption);
        initialize(countryId);
    }

    public EntityCity(ID id, String caption, ID countryId) {
        super(id, caption);
        initialize(countryId);
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public ID getCountryId() {
        return countryId;
    }

    public void setCountryId(ID countryId) {
        this.countryId = countryId;
    }

    public EntityCountry<ID> getCountry() {
        return country;
    }

    public void setCountry(EntityCountry<ID> country) {
        setCountry(country, true);
    }

    protected void setCountrySafe(EntityCountry<ID> country) {
        setCountry(country, false);
    }

    private void setCountry(EntityCountry<ID> country, boolean isCircleMode){
        if(isCircleMode && country != null){
            country.addCitySafe(this);
        }

        this.country = country;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("city [%s]: %s, country - %s", String.valueOf(getId()), getCaption(), countryId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EntityCity<ID> that = (EntityCity<ID>) o;
        return Objects.equals(countryId, that.countryId) &&
                Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), countryId, country);
    }

    @Override
    public EntityCity<ID> clone(){
        try {
            EntityCity<ID> clone = (EntityCity<ID>) super.clone();

            clone.setCountry(country == null ? null : country.clone());
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
    //</editor-fold>
}
