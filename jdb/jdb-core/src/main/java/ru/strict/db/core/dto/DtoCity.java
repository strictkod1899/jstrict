package ru.strict.db.core.dto;

import ru.strict.utils.UtilHashCode;

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
            throw new NullPointerException("countryId is NULL");
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
        if(countryId == null) {
            throw new NullPointerException("countryId is NULL");
        }

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
        return String.format("dto city [%s]: %s, country - %s", String.valueOf(getId()), getCaption(), countryId);
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof DtoCity) {
            DtoCity object = (DtoCity) obj;
            return super.equals(object) && countryId.equals(object.getCountryId());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        int superHashCode = super.hashCode();
        return UtilHashCode.createSubHashCode(superHashCode, countryId);
    }
    //</editor-fold>
}
