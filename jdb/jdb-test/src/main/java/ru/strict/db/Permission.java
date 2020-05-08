package ru.strict.db;

import ru.strict.models.IModel;

import java.util.HashMap;
import java.util.Map;

public enum Permission implements IModel<Integer> {
    PERMISSION1(1, "PERMISSION1"),
    PERMISSION2(2, "PERMISSION2"),
    PERMISSION3(3, "PERMISSION3"),
    PERMISSION4(4, "PERMISSION4");

    private static final Map<Integer, Permission> VALUES_BY_ID = new HashMap<>();

    static {
        for (Permission item : values()) {
            VALUES_BY_ID.put(item.getId(), item);
        }
    }

    private Integer id;
    private String caption;

    Permission(int id, String caption) {
        this.id = id;
        this.caption = caption;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public String getCaption() {
        return caption;
    }

    public static Permission getById(Integer id) {
        return VALUES_BY_ID.get(id);
    }
}
