package ru.strict.db.hibernate.mock.entities.integer;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "country")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class EntityCountry extends ru.strict.db.hibernate.entities.EntityCountry<Integer> {
    public EntityCountry() {
        super();
    }

    public EntityCountry(String caption) {
        super(caption);
    }

    public EntityCountry(Integer integer, String caption) {
        super(integer, caption);
    }
}
