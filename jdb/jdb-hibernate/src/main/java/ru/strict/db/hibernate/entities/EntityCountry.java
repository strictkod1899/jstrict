package ru.strict.db.hibernate.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.TreeSet;

/**
 * Страна
 */
@Entity
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
            throw new NullPointerException();
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
            throw new NullPointerException();
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
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof EntityCountry) {
            EntityCountry object = (EntityCountry) obj;
            return super.equals(obj) && Objects.equals(cities, object.getCities());
        }else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(getId(), getCaption(), cities);
    }
    //</editor-fold>
}
