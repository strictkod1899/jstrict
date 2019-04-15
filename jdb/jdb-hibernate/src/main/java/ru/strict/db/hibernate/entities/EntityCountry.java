package ru.strict.db.hibernate.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.TreeSet;

/**
 * Страна
 */
@Entity
@Table(name = "country")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class EntityCountry extends EntityNamed<Long> {

    /**
     * Города свзяанные со страной
     */
    @OneToMany(mappedBy = "countryId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Collection<EntityCity> cities;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public EntityCountry() {
        super();
        cities = new TreeSet<>();
    }

    public EntityCountry(String caption) {
        super(caption);
        cities = new TreeSet<>();
    }

    public EntityCountry(Long id, String caption) {
        super(id, caption);
        cities = new TreeSet<>();
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public Collection<EntityCity> getCities() {
        return cities;
    }

    public void setCities(Collection<EntityCity> cities) {
        if(cities == null) {
            throw new IllegalArgumentException("cities is NULL");
        }

        for(EntityCity city : cities){
            city.setCountrySafe(this);
        }

        this.cities = cities;
    }

    public void addCity(EntityCity city){
        addCity(city, true);
    }

    protected void addCitySafe(EntityCity city){
        addCity(city, false);
    }

    private void addCity(EntityCity city, boolean isCircleMode){
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

    public void addCities(Collection<EntityCity> cities){
        if(cities!=null) {
            for(EntityCity city : cities){
                addCity(city);
            }
        }
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("entity country [%s]: %s", String.valueOf(getId()), getCaption());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EntityCountry object = (EntityCountry) o;
        return Objects.equals(cities, object.cities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cities);
    }

    @Override
    public EntityCountry clone(){
        try {
            EntityCountry clone = (EntityCountry) super.clone();

            clone.cities = new TreeSet<>();
            for(EntityCity city : this.cities){
                clone.addCity(city.clone());
            }
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
    //</editor-fold>
}
