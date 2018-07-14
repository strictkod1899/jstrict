package ru.strict.db.core.repositories;

import ru.strict.db.core.dto.DtoBase;

/**
 * Расширенные возможности репозитория для выполнения операций с записья используя ее столбец наименования
 * @param <ID> Тип идентификатора
 * @param <DTO> Тип Dto-сущности базы данных
 */
public interface IRepositoryNamed <ID, DTO extends DtoBase> extends IRepository<ID, DTO> {

    /**
     * Чтение записи из базы данных по наименованию
     * @param caption Значение столбца наименования
     * @return
     */
    DTO readByName(String caption);
}
