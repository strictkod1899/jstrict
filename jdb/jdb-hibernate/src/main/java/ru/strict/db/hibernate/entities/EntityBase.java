package ru.strict.db.hibernate.entities;

import ru.strict.utils.UtilHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * Базовый entity-класс
 */
@MappedSuperclass
public abstract class EntityBase implements Serializable {

    /**
     * Идентификатор записи
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")
    private UUID id;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public EntityBase() {
        id = null;
    }

    public EntityBase(UUID id) {
        if(id == null) {
            throw new NullPointerException("id is NULL");
        }

        this.id = id;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        if(id == null) {
            throw new NullPointerException("id is NULL");
        }

        this.id = id;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("entity base [%s]", String.valueOf(id));
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof EntityBase && obj!=null) {
            EntityBase object = (EntityBase) obj;
            return id.equals(object.getId());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        return UtilHashCode.createHashCode(id);
    }
    //</editor-fold>
}
