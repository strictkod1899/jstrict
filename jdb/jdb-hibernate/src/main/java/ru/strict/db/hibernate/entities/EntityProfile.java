package ru.strict.db.hibernate.entities;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "profile")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class EntityProfile extends EntityProfileBase {

    @Override
    public EntityProfile clone(){
        try {
            EntityProfile clone = (EntityProfile) super.clone();
            clone.setUser(getUser() == null ? null : getUser().clone());
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
