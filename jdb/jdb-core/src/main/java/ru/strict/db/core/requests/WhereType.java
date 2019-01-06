package ru.strict.db.core.requests;

public enum WhereType {
    OR("OR"),
    AND("AND");

    private String caption;

    WhereType(String caption) {
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }
}
