package ru.strict.db.core.repositories;

/**
 * Стандартные наборы столбцов различных таблиц
 */
public enum DefaultColumns {
    CITY(new String[]{ "caption", "country_id" }),
    COUNTRY(new String[]{ "caption" }),
    FILE_STORAGE(new String[]{ "filename",
            "extension",
            "displayname",
            "content",
            "filepath",
            "create_date",
            "type",
            "status" }),
    JWT_TOKEN(new String[]{ "access_token",
            "refresh_token",
            "expire_time_access",
            "expire_time_refresh",
            "issued_at",
            "issuer",
            "subject",
            "not_before",
            "audience",
            "secret",
            "algorithm",
            "type",
            "userx_id" }),
    PERMISSION_ON_ROLE(new String[]{ "permission_id", "role_id" }),
    PROFILE(new String[]{ "name", "surname", "userx_id" }),
    PROFILE_DETAILS(new String[]{ "name",
            "surname",
            "middlename",
            "userx_id",
            "is_man",
            "datebirth",
            "phone",
            "city_id" }),
    ROLE(new String[]{ "code", "description" }),
    USER(new String[]{ "username",
            "passwordencode",
            "email",
            "is_blocked",
            "is_deleted",
            "is_confirm_email",
            "salt",
            "secret" }),
    USER_ON_ROLE(new String[]{ "userx_id", "role_id" });

    private String[] columns;

    DefaultColumns(String[] columns) {
        this.columns = columns;
    }

    public String[] columns() {
        return columns;
    }

    public String get(int index) {
        return columns[index];
    }
}
