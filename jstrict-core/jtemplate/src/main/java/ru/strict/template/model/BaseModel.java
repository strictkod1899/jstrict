package ru.strict.template.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseModel<ID> implements IModel<ID> {
    @EqualsAndHashCode.Exclude
    private ID id;
}
