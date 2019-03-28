package ru.strict.db.core.repositories;

import ru.strict.db.core.dto.DtoBase;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;
import ru.strict.validates.ValidateBaseValue;

import java.util.List;

/**
 * Расширенные возможности репозитория для выполнения операций с записья используя ее столбец наименования
 * @param <ID> Тип идентификатора
 * @param <DTO> Тип Dto-сущности базы данных
 */
public interface IRepositoryNamed<ID, DTO extends DtoBase<ID>> extends IRepositoryExtension<ID, DTO> {

    /**
     * Чтение записи из базы данных по наименованию
     * @param caption Значение столбца наименования
     * @return
     */
    default DTO readByName(String caption){
        if(ValidateBaseValue.isEmptyOrNull(caption)){
            throw new NullPointerException("caption for read by name is NULL");
        }
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(getTableName(), getColumnWithName(), caption, "="));

        DTO result = readAll(requests).stream().findFirst().orElse(null);
        return result;
    }

    /**
     * Чтение записей из базы данных по наименованию
     * @param caption Значение столбца наименования
     * @return
     */
    default List<DTO> readAllByName(String caption){
        if(ValidateBaseValue.isEmptyOrNull(caption)){
            throw new NullPointerException("caption for read by name is NULL");
        }
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(getTableName(), getColumnWithName(), caption, "="));

        List<DTO> result = readAll(requests);
        return result;
    }

    /**
     * Получить наименование столбца, который выполняет роль наименования записи
     * @return
     */
    String getColumnWithName();
}
