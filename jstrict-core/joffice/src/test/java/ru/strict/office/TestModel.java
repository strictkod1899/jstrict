package ru.strict.office;

public class TestModel {
    private String name;
    private int age;
    private String description;

    public TestModel(String name, int age, String description) {
        this.name = name;
        this.age = age;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDescription() {
        return description;
    }
}
