package ru.strict.db.core.common;

/**
 * Тип маппера, который необходимо создать. Используется как параметр в методе instance класса MapperDtoFactory, для создания предопределенных мапперов модуля jdb-core
 */
public enum MapperDtoType {
    COUNTRY,
    CITY,
    ROLE_USER,
    USER,
    USER_ON_ROLE,
    PROFILE,
    PROFILE_INFO,
    JWT_TOKEN,
    JWT_USER_TOKEN
}