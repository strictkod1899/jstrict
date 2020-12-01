package ru.strict.ioc.components;

import java.util.List;

public class Controller1 {
    private final List<IService> services;

    public Controller1(List<IService> services) {
        this.services = services;
    }

    public List<IService> getServices() {
        return services;
    }
}
