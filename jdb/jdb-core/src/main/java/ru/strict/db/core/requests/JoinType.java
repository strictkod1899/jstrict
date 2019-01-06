package ru.strict.db.core.requests;

/**
 * Тип конструкции join в sql-запросе
 */
public enum JoinType {
    INNER("INNER"),
    LEFT("LEFT OUTER"),
    RIGHT("RIGHT OUTER");

    private String caption;

    JoinType(String caption){
        this.caption = caption;
    }

    public String getCaption(){
        return caption;
    }
}
