package ru.strict.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NamedModel<ID> extends BaseModel<ID> implements Named<ID> {
    private String name;

    public NamedModel(String name) {
        super();
        this.name = name;
    }

    public NamedModel(ID id, String name) {
        super(id);
        this.name = name;
    }
}
