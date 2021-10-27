package ru.strict.util.reflection;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseStudent implements StudentName {
    private String name;
}
