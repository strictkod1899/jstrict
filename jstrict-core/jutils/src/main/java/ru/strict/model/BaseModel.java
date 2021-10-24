package ru.strict.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseModel<ID> implements Model<ID> {
    @EqualsAndHashCode.Exclude
    private ID id;
}
