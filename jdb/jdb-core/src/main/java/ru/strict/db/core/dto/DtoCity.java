package ru.strict.db.core.dto;

import java.util.Objects;

/**
 * Город
 */
public class DtoCity<ID> extends DtoNamed<ID> {

    /**
     * Идентификатор страны
     */
    private ID countryId;

    /**
     * Страна связанная с данным городом
     */
    private DtoCountry<ID> country;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(ID countryId){
        if(countryId == null) {
            throw new IllegalArgumentException("countryId is NULL");
        }

        this.countryId = countryId;
        country = null;
    }

    public DtoCity() {
        super();
        countryId = null;
        country = null;
    }

    public DtoCity(String caption, ID countryId) {
        super(caption);
        initialize(countryId);
    }

    public DtoCity(ID id, String caption, ID countryId) {
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

    public DtoCountry<ID> getCountry() {
        return country;
    }

    public void setCountry(DtoCountry<ID> country) {
        setCountry(country, true);
    }

    protected void setCountrySafe(DtoCountry<ID> country) {
        setCountry(country, false);
    }

    private void setCountry(DtoCountry<ID> country, boolean isCircleMode){
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
        DtoCity<ID> dtoCity = (DtoCity<ID>) o;
        return Objects.equals(countryId, dtoCity.countryId) &&
                Objects.equals(country, dtoCity.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), countryId, country);
    }
    //</editor-fold>
}
