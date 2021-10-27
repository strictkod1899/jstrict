package ru.strict.util.reflection;

import lombok.Getter;

@Getter
public class Student extends BaseStudent implements StudentAge {
    private int age;

    public Student(String name, int age) {
        super(name);
        this.age = age;
    }

    private Student(String name) {
        super(name);
        this.age = -1;
    }

    @TestAnnotation
    public String getUpperName() {
        return getName().toUpperCase();
    }
}
