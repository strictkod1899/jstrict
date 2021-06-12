package ru.strict.db.jdbc.components;

import ru.strict.template.model.NamedModel;

import java.util.Objects;

/**
 * Город
 */
public class City<ID> extends NamedModel<ID> {

    /**
     * Идентификатор страны
     */
    private ID countryId;

    /**
     * Страна связанная с данным городом
     */
    private Country<ID> country;

    //<editor-fold defaultState="collapsed" desc="constructors">
    private void init(ID countryId) {
        if (countryId == null) {
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
        init(countryId);
    }

    public City(ID id, String caption, ID countryId) {
        super(id, caption);
        init(countryId);
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
        this.country = country;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString() {
        return String.format("city [%s]: %s, country - %s", String.valueOf(getId()), getName(), countryId);
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
        City<ID> city = (City<ID>) o;
        return Objects.equals(countryId, city.countryId) &&
                Objects.equals(country, city.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), countryId, country);
    }

    @Override
    public City<ID> clone() {
        try {
            City<ID> clone = (City<ID>) super.clone();

            clone.setCountry(country == null ? null : country.clone());
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
    //</editor-fold>
}
