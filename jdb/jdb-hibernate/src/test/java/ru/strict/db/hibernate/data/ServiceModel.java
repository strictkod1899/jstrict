package ru.strict.db.hibernate.data;

import java.util.Arrays;

public enum ServiceModel {
    SERVICE1(1, "SERVICE1"),
    SERVICE2(2, "SERVICE2"),
    SERVICE3(3, "SERVICE3"),
    SERVICE4(4, "SERVICE4");

    private Integer id;
    private String caption;

    ServiceModel(int id, String caption) {
        this.id = id;
        this.caption = caption;
    }

    public Integer getId() {
        return id;
    }

    public String getCaption() {
        return caption;
    }

    public static ServiceModel getById(Integer id){
        if(id == null){
            return null;
        }

        return Arrays.stream(ServiceModel.values())
                                .filter(item -> item.getId().equals(id))
                                .findFirst()
                                .orElse(null);
    }

    public static ServiceModel getByCaption(String caption){
        if(caption == null){
            return null;
        }

        return Arrays.stream(ServiceModel.values())
                .filter(item -> item.getCaption().equals(caption))
                .findFirst()
                .orElse(null);
    }
}
