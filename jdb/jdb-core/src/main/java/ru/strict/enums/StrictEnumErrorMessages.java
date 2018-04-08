package ru.strict.enums;

/**
 * Доступные к отображению сообщения связанные с работой базы данных
 */
public enum StrictEnumErrorMessages {

    TITLE_ERROR("Ошибка")
    , TITLE_ERROR_ADD("Ошибка добавления")
    , TITLE_ERROR_CHANGE("Ошибка изменения")
    , TITLE_ERROR_DELETE("Ошибка удаления")

    , MSG_ERROR("Введены некорректные данные")
    , MSG_ERROR_NO_SELECTED_ROW("Выберите строку перед продолжением операции")
    , MSG_ERROR_EMPTY_CMB("Выберите значение из списка")
    , MSG_ERROR_NOTCORRECT_ROW("Выбрана некорректная строка")
    , MSG_ERROR_NULL("Не заполнены обязательные поля")
    ;

    private String message;

    StrictEnumErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
