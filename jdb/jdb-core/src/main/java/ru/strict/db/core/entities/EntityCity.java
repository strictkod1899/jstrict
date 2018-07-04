package ru.strict.db.core.entities;

import ru.strict.utils.UtilHashCode;

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
    private EntityCountry country;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void initialize(ID countryId){
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
