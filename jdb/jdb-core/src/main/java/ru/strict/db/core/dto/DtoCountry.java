package ru.strict.db.core.dto;

import java.util.Collection;
import java.util.TreeSet;

import ru.strict.utils.UtilHashCode;

/**
 * Страна
 */
public class DtoCountry<ID> extends DtoNamed<ID> {

    /**
     * Города свзяанные со страной
     */
    private Collection<DtoCity<ID>> cities;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public DtoCountry() {
        super();
        cities = new TreeSet<>();
    }

    public DtoCountry(String caption) {
        super(caption);
        cities = new TreeSet<>();
    }

    public DtoCountry(ID id, String caption) {
        super(id, caption);
        cities = new TreeSet<>();
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public Collection<DtoCity<ID>> getCities() {
        return cities;
    }

    public void setCities(Collection<DtoCity<ID>> cities) {
        if(cities == null) {
            throw new NullPointerException();
        }

        for(DtoCity<ID> city : cities){
            city.setCountrySafe(this);
        }

        this.cities = cities;
    }

    public void addCity(DtoCity<ID> city){
        addCity(city, true);
    }

    protected void addCitySafe(DtoCity<ID> city){
        addCity(city, false);
    }

    private void addCity(DtoCity<ID> city, boolean isCircleMode){
        if(city == null) {
            throw new NullPointerException();
        }

        if(cities != null){
            if(isCircleMode) {
                city.setCountrySafe(this);
            }
            cities.add(city);
        }
    }

    public void addCities(Collection<DtoCity<ID>> cities){
        if(cities!=null) {
            for(DtoCity<ID> city : cities){
                addCity(city);
            }
        }
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("dto country [%s]: %s", String.valueOf(getId()), getCaption());
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof DtoCountry) {
            DtoCountry object = (DtoCountry) obj;
            return super.equals(object);
        }else
            return false;
    }

    @Override
    public int hashCode(){
        int superHashCode = super.hashCode();
        return UtilHashCode.createSubHashCode(superHashCode);
    }
    //</editor-fold>
}
