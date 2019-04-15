package ru.strict.db.core.entities;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.TreeSet;



/**
 * Страна
 */
public class EntityCountry<ID> extends EntityNamed<ID> {

    /**
     * Города свзяанные со страной
     */
    private Collection<EntityCity<ID>> cities;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public EntityCountry() {
        super();
        cities = new TreeSet<>();
    }

    public EntityCountry(String caption) {
        super(caption);
        cities = new TreeSet<>();
    }

    public EntityCountry(ID id, String caption) {
        super(id, caption);
        cities = new TreeSet<>();
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public Collection<EntityCity<ID>> getCities() {
        return cities;
    }

    public void setCities(Collection<EntityCity<ID>> cities) {
        if(cities == null) {
            throw new IllegalArgumentException("cities is NULL");
        }

        for(EntityCity<ID> city : cities){
            city.setCountrySafe(this);
        }

        this.cities = cities;
    }

    public void addCity(EntityCity<ID> city){
        addCity(city, true);
    }

    protected void addCitySafe(EntityCity<ID> city){
        addCity(city, false);
    }

    private void addCity(EntityCity<ID> city, boolean isCircleMode){
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

    public void addCities(Collection<EntityCity<ID>> cities){
        if(cities!=null) {
            for(EntityCity<ID> city : cities){
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
        EntityCountry<ID> that = (EntityCountry<ID>) o;
        return Objects.equals(cities, that.cities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cities);
    }

    @Override
    public EntityCountry<ID> clone(){
        try {
            EntityCountry<ID> clone = (EntityCountry<ID>) super.clone();

            clone.cities = new TreeSet<>();
            for(EntityCity<ID> city : this.cities){
                clone.addCity(city.clone());
            }
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
    //</editor-fold>
}
