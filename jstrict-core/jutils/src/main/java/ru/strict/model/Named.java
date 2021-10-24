package ru.strict.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Named<ID> extends BaseModel<ID> implements NamedModel<ID> {
    private String name;

    public Named(String name) {
        super();
        this.name = name;
    }

    public Named(ID id, String name) {
        super(id);
        this.name = name;
    }
}
