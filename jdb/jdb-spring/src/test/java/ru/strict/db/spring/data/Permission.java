package ru.strict.db.spring.data;

import java.util.Arrays;

public enum Permission {
    PERMISSION1(1, "PERMISSION1"),
    PERMISSION2(2, "PERMISSION2"),
    PERMISSION3(3, "PERMISSION3"),
    PERMISSION4(4, "PERMISSION4");

    private Integer id;
    private String caption;

    Permission(int id, String caption) {
        this.id = id;
        this.caption = caption;
    }

    public Integer getId() {
        return id;
    }

    public String getCaption() {
        return caption;
    }

    public static Permission getById(Integer id){
        if(id == null){
            return null;
        }

        return Arrays.stream(Permission.values())
                                .filter(item -> item.getId().equals(id))
                                .findFirst()
                                .orElse(null);
    }

    public static Permission getByCaption(String caption){
        if(caption == null){
            return null;
        }

        return Arrays.stream(Permission.values())
                .filter(item -> item.getCaption().equals(caption))
                .findFirst()
                .orElse(null);
    }
}
