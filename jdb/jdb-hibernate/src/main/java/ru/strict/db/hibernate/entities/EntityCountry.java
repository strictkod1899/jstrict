package ru.strict.db.hibernate.entities;

import ru.strict.utils.UtilHashCode;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.UUID;

/**
 * Страна
 */
@Entity
@Table(name = "country")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class EntityCountry extends EntityNamed {

    /**
     * Пользователи связанные с ролью
     */
    @OneToMany(mappedBy = "countryId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<EntityCity> cities;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public EntityCountry() {
        super();
        cities = new LinkedList<>();
    }

    public EntityCountry(String caption) {
        super(caption);
        cities = new LinkedList<>();
    }

    public EntityCountry(UUID id, String caption) {
        super(id, caption);
        cities = new LinkedList<>();
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public void addCity(EntityCity city){
        if(city == null) {
            throw new NullPointerException();
        }

        if(cities != null){
            cities.add(city);
        }
    }

    public Collection<EntityCity> getCities() {
        return cities;
    }

    public void setCities(Collection<EntityCity> cities) {
        if(cities == null) {
            throw new NullPointerException();
        }

        this.cities = cities;
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
