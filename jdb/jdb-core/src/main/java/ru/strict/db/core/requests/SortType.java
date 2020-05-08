package ru.strict.db.core.requests;

/**
 * Тип сортировки выборки в sql-запросе
 */
public enum SortType {
    /**
     * Сортировка по возрастанию
     */
    ASC("ASC"),
    /**
     * Сортировка по убыванию
     */
    DESC("DESC");

    private String caption;

    SortType(String caption){
        this.caption = caption;
    }

    public String getCaption(){
        return caption;
    }
}
