package ru.strict.db.hibernate.entities;

import org.hibernate.annotations.GenericGenerator;
import ru.strict.utils.UtilData;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Базовый entity-класс
 */
@MappedSuperclass
public abstract class EntityBase<ID> implements Serializable, Comparable<EntityBase<ID>>, Cloneable {

    /**
     * Идентификатор записи
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private ID id;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public EntityBase() {
        id = null;
    }

    public EntityBase(ID id) {
        if(id == null) {
            throw new IllegalArgumentException("id is NULL");
        }

        this.id = id;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("entity base [%s]", String.valueOf(id));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityBase<ID> object = (EntityBase<ID>) o;
        return Objects.equals(id, object.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(EntityBase<ID> object){
        return UtilData.compareTo(getId(), object.getId());
    }
    //</editor-fold>
}
