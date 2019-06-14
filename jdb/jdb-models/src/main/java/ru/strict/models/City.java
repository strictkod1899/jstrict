package ru.strict.models;

import java.util.Objects;

/**
 * Город
 */
public class City<ID> extends DtoNamed<ID> {

    /**
     * Идентификатор страны
     */
    private ID countryId;

    /**
     * Страна связанная с данным городом
     */
    private Country<ID> country;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(ID countryId){
        if(countryId == null) {
            throw new IllegalArgumentException("countryId is NULL");
        }

        this.countryId = countryId;
        country = null;
    }

    public City() {
        super();
        countryId = null;
        country = null;
    }

    public City(String caption, ID countryId) {
        super(caption);
        initialize(countryId);
    }

    public City(ID id, String caption, ID countryId) {
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

    public Country<ID> getCountry() {
        return country;
    }

    public void setCountry(Country<ID> country) {
        setCountry(country, true);
    }

    protected void setCountrySafe(Country<ID> country) {
        setCountry(country, false);
    }

    private void setCountry(Country<ID> country, boolean isCircleMode){
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
        City<ID> dtoCity = (City<ID>) o;
        return Objects.equals(countryId, dtoCity.countryId) &&
                Objects.equals(country, dtoCity.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hashCodeWithoutCountry(), country);
    }

    @Override
    public City<ID> clone(){
        try {
            City<ID> clone = (City<ID>) super.clone();

            clone.setCountry(country == null ? null : country.clone());
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected int hashCodeWithoutCountry(){
        return Objects.hash(super.hashCode(), countryId);
    }

    protected City<ID> cloneSafeCountry(Country<ID> country){
        try {
            City<ID> clone = (City<ID>) super.clone();

            clone.setCountry(country);
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
    //</editor-fold>
}
