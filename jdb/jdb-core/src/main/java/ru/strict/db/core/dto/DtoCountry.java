package ru.strict.db.core.dto;

import java.util.Collection;
import java.util.Objects;
import java.util.TreeSet;

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
            throw new IllegalArgumentException("cities is NULL");
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
            throw new IllegalArgumentException("city is NULL");
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
        return String.format("country [%s]: %s", String.valueOf(getId()), getCaption());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DtoCountry<ID> object = (DtoCountry<ID>) o;
        return Objects.equals(cities, object.cities);
    }

    @Override
    public int hashCode() {
        int citiesHashCode = 1;
        for(DtoCity<ID> city : cities){
            citiesHashCode = 31 * citiesHashCode + (city == null ? 0 : city.hashCodeWithoutCountry());
        }

        return Objects.hash(super.hashCode(), citiesHashCode);
    }

    @Override
    public DtoCountry<ID> clone(){
        try {
            DtoCountry<ID> clone = (DtoCountry<ID>) super.clone();

            clone.cities = new TreeSet<>();
            for(DtoCity<ID> city : this.cities){
                clone.addCity(city.cloneSafeCountry(clone));
            }
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
    //</editor-fold>
}
