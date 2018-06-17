package ru.strict.db.core.entities;

import java.util.Collection;
import ru.strict.utils.UtilHashCode;

/**
 * Страна
 */
public class EntityCountry<ID> extends EntityNamed<ID> {

    /**
     * Пользователи свзяанные с ролью
     */
    private Collection<EntityCity> cities;

    public EntityCountry() {
        super();
        cities = new LinkedList<>();
    }

    public EntityCountry(String caption) {
        super(caption);
        cities = new LinkedList<>();
    }

    public EntityCountry(ID id, String caption) {
        super(id, caption);
        cities = new LinkedList<>();
    }

    public Collection<EntityCity> getCities() {
        return cities;
    }

    public void setCities(Collection<EntityCity> cities) {
        this.cities = cities;
    }

    public void addCity(EntityCity city){
        cities.add(city);
    }

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
