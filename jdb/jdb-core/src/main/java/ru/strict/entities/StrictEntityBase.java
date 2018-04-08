package ru.strict.entities;

/**
 * Класс определяет базовый функционал сущности (entity)
 * @param <ID> Тип поля ID
 */
public abstract class StrictEntityBase<ID extends Long> {

    /**
     * id записи
     */
    private ID id;

    public StrictEntityBase() {
    }

    public StrictEntityBase(ID id) {
        this.id = id;
    }

    /**
     * Получить id записи
     * @return
     */
    public ID getId() {
        return id;
    }

    /**
     * Установить новый id записи
     * @param id id записи
     */
    public void setId(ID id) {
        this.id = id;
    }
}
