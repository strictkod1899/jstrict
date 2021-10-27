package ru.strict.util.reflection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Robot {
    public Integer id;
    private String name;

    public Robot(String name) {
        this.name = name;
    }

    @TestAnnotation
    public void addPrefixToName() {
        this.name = "robot-" + this.name;
    }

    @TestAnnotation
    public void addSuffixToName() {
        this.name = this.name + "-test";
    }
}
