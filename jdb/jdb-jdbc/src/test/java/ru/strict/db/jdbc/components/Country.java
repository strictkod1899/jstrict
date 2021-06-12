package ru.strict.db.jdbc.components;

import ru.strict.template.model.NamedModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Страна
 */
public class Country<ID> extends NamedModel<ID> {

    /**
     * Города свзяанные со страной
     */
    private List<City<ID>> cities;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public Country() {
        super();
        cities = new ArrayList<>();
    }

    public Country(String caption) {
        super(caption);
        cities = new ArrayList<>();
    }

    public Country(ID id, String caption) {
        super(id, caption);
        cities = new ArrayList<>();
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public List<City<ID>> getCities() {
        return cities;
    }

    public void setCities(List<City<ID>> cities) {
        if (cities == null) {
            throw new IllegalArgumentException("cities is NULL");
        }

        this.cities = cities;
    }

    public void addCity(City<ID> city) {
        if (city == null) {
            throw new IllegalArgumentException("city is NULL");
        }

        this.cities.add(city);
    }

    public void addCities(List<City<ID>> cities) {
        if (cities != null) {
            for (City<ID> city : cities) {
                addCity(city);
            }
        }
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString() {
        return String.format("country [%s]: %s", String.valueOf(getId()), getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Country<ID> object = (Country<ID>) o;
        return Objects.equals(cities, object.cities);
    }

    @Override
    public int hashCode() {
        int citiesHashCode = 1;
        for (City<ID> city : cities) {
            citiesHashCode = 31 * citiesHashCode + (city == null ? 0 : city.hashCode());
        }

        return Objects.hash(super.hashCode(), citiesHashCode);
    }

    @Override
    public Country<ID> clone() {
        try {
            Country<ID> clone = (Country<ID>) super.clone();

            clone.cities = new ArrayList<>();
            for (City<ID> city : this.cities) {
                clone.addCity(city.clone());
            }
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
    //</editor-fold>
}
