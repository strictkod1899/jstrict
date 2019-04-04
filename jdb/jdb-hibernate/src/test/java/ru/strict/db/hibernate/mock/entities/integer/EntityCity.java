package ru.strict.db.hibernate.mock.entities.integer;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "city")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class EntityCity extends ru.strict.db.hibernate.entities.EntityCity<Integer> {
    public EntityCity() {
        super();
    }

    public EntityCity(String caption, Integer countryId) {
        super(caption, countryId);
    }

    public EntityCity(Integer integer, String caption, Integer countryId) {
        super(integer, caption, countryId);
    }
}