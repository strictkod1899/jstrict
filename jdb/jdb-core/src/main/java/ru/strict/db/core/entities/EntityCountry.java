package ru.strict.db.core.entities;

import java.util.Collection;
import java.util.TreeSet;

import ru.strict.utils.UtilHashCode;

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
