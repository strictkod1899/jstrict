package ru.strict.template.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NamedModel<ID> extends BaseModel<ID> implements INamedModel<ID> {
    private String name;

    public NamedModel() {
        super();
    }

    public NamedModel(String name) {
        super();
        this.name = name;
    }

    public NamedModel(ID id, String name) {
        super(id);
        this.name = name;
    }
}
