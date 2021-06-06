package ru.strict.db.core.query.components;

public enum SortType {
    ASC("ASC"),
    DESC("DESC");

    private String caption;

    SortType(String caption){
        this.caption = caption;
    }

    public String getCaption(){
        return caption;
    }
}
