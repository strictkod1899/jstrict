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
        EntityProfile clone = new EntityProfile();

        clone.setId(getId());
        clone.setName(getName());
        clone.setSurname(getSurname());
        clone.setMiddlename(getMiddlename());
        clone.setUserId(getUserId());
        clone.setUser(getUser() == null ? null : getUser().clone());
        return clone;
    }
}
