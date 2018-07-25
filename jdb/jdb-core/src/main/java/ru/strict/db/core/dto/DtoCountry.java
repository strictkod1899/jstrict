package ru.strict.db.core.dto;

import java.util.Collection;
import java.util.LinkedList;

import ru.strict.utils.UtilHashCode;

/**
 * Страна
 */
public class DtoCountry<ID> extends DtoNamed<ID> {

    /**
     * Города свзяанные со страной
     */
    private Collection<DtoCity> cities;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public DtoCountry() {
        super();
        cities = new LinkedList<>();
    }

    public DtoCountry(String caption) {
        super(caption);
        cities = new LinkedList<>();
    }

    public DtoCountry(ID id, String caption) {
        super(id, caption);
        cities = new LinkedList<>();
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public Collection<DtoCity> getCities() {
        return cities;
    }

    public void setCities(Collection<DtoCity> cities) {
        if(cities == null) {
            throw new NullPointerException();
        }

        this.cities = cities;
    }

    public void addCity(DtoCity city){
        if(city == null) {
            throw new NullPointerException();
        }

        if(cities != null){
            cities.add(city);
        }
    }

    public void addCities(Collection<DtoCity> cities){
        if(this.cities!=null) {
            this.cities.addAll(cities);
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
            return super.equals(object) && (cities.size() == object.getCities().size() && cities.containsAll(object.getCities()));
        }else
            return false;
    }

    @Override
    public int hashCode(){
        int superHashCode = super.hashCode();
        return UtilHashCode.createSubHashCode(superHashCode, cities);
    }
    //</editor-fold>
}
