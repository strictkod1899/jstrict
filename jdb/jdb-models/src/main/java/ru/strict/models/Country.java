package ru.strict.models;

import java.util.Collection;
import java.util.Objects;
import java.util.TreeSet;

/**
 * Страна
 */
public class Country<ID> extends DtoNamed<ID> {

    /**
     * Города свзяанные со страной
     */
    private Collection<City<ID>> cities;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public Country() {
        super();
        cities = new TreeSet<>();
    }

    public Country(String caption) {
        super(caption);
        cities = new TreeSet<>();
    }

    public Country(ID id, String caption) {
        super(id, caption);
        cities = new TreeSet<>();
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public Collection<City<ID>> getCities() {
        return cities;
    }

    public void setCities(Collection<City<ID>> cities) {
        if(cities == null) {
            throw new IllegalArgumentException("cities is NULL");
        }

        for(City<ID> city : cities){
            city.setCountrySafe(this);
        }

        this.cities = cities;
    }

    public void addCity(City<ID> city){
        addCity(city, true);
    }

    protected void addCitySafe(City<ID> city){
        addCity(city, false);
    }

    private void addCity(City<ID> city, boolean isCircleMode){
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

    public void addCities(Collection<City<ID>> cities){
        if(cities!=null) {
            for(City<ID> city : cities){
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
        Country<ID> object = (Country<ID>) o;
        return Objects.equals(cities, object.cities);
    }

    @Override
    public int hashCode() {
        int citiesHashCode = 1;
        for(City<ID> city : cities){
            citiesHashCode = 31 * citiesHashCode + (city == null ? 0 : city.hashCodeWithoutCountry());
        }

        return Objects.hash(super.hashCode(), citiesHashCode);
    }

    @Override
    public Country<ID> clone(){
        try {
            Country<ID> clone = (Country<ID>) super.clone();

            clone.cities = new TreeSet<>();
            for(City<ID> city : this.cities){
                clone.addCity(city.cloneSafeCountry(clone));
            }
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
    //</editor-fold>
}
