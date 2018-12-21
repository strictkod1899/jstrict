package ru.strict.swing;

/**
 * Доступные для использования значки
 */
public enum Icon {

    /**
     * Сворачивание окна программы
     */
    TURN("turn.png"),
    /**
     * Изменение размеров окна программы
     */
    CHANGE_SIZE("changeSize.png"),

    /**
     * Изменение размеров окна программы до максимума
     */
    CHANGE_SIZE_FULL("sizefull.png"),

    /**
     * Закрыть программу
     */
    CLOSE("close.png"),

    /**
     * Меню
     */
    MENU("menu.png"),

    /**
     * Добавить
     */
    ADD("add.png"),

    /**
     * Добавить пользователя
     */
    ADD_USER("adduser.png"),

    /**
     * Удалить
     */
    REMOVE("remove.png"),

    /**
     * Удалить пользователя
     */
    REMOVE_USER("removeuser.png"),

    /**
     * Галочка
     */
    SELECT("select.png"),

    /**
     * Настройки
     */
    SETTINGS("settings.png"),

    /**
     * Выход
     */
    EXIT("exit.png"),

    /**
     * Шаг назад
     */
    BACK("back.png"),

    /**
     * Помощь
     */
    HELP("help.png"),

    /**
     * Файл
     */
    FILE("file.png"),

    /**
     * Файлы
     */
    FILES("files.png"),

    /**
     * Просмотр
     */
    VIEW("view.png"),

    /**
     * Обновить
     */
    REFRESH("refresh.png"),

    /**
     * Звезда
     */
    STAR("star.png"),

    /**
     * Поиск
     */
    SEARCH("search.png"),

    /**
     * Древовидная структура
     */
    NODE("node.png");

    private String path;
    public final String DIRECT_ICON = "images/icons/";

    Icon(String path){
        this.path = DIRECT_ICON + path;
    }

    public String getPath() {
        return path;
    }
}
