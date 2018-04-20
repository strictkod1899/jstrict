package ru.strict.db.enums;

/**
 * Доступные к отображению сообщения связанные с работой базы данных
 */
public enum StrictEnumErrorMessages {

    TITLE_ERROR("Ошибка"),
    TITLE_ERROR_ADD("Ошибка добавления"),
    TITLE_ERROR_CHANGE("Ошибка изменения"),
    TITLE_ERROR_DELETE("Ошибка удаления"),

    MSG_ERROR_INCORRECT("Введены некорректные данные"),
    MSG_ERROR_NULL("Не заполнены обязательные поля")
    ;

    private String message;

    StrictEnumErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
