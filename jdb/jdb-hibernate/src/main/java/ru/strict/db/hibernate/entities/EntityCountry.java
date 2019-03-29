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
public class EntityCountry<ID> extends EntityNamed<ID> {

    /**
     * Города свзяанные со страной
     */
    @OneToMany(mappedBy = "countryId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
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
            throw new NullPointerException();
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
            throw new NullPointerException();
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
