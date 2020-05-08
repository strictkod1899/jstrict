package ru.strict.db.core.repositories;

import ru.strict.db.core.requests.DbSelectItem;
import ru.strict.db.core.requests.DbRequests;
import ru.strict.db.core.requests.DbWhereItem;
import ru.strict.models.BaseModel;
import ru.strict.validate.ValidateBaseValue;

import java.util.List;

/**
 * Расширенные возможности репозитория для выполнения операций с записья используя ее столбец наименования
 * @param <ID> Тип идентификатора
 * @param <T> Модель сущности базы данных
 */
public interface INamedRepository<ID, T extends BaseModel<ID>> extends IExtensionRepository<ID, T> {

    /**
     * Чтение записи из базы данных по наименованию
     * @param caption Значение столбца наименования
     * @return
     */
    default T readByName(String caption){
        if(ValidateBaseValue.isEmptyOrNull(caption)){
            throw new IllegalArgumentException("caption for read by name is NULL");
        }
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), getColumnWithName()), caption, "="));

        return readAll(requests).stream().findFirst().orElse(null);
    }

    /**
     * Чтение записей из базы данных по наименованию
     * @param caption Значение столбца наименования
     * @return
     */
    default List<T> readAllByName(String caption){
        if(ValidateBaseValue.isEmptyOrNull(caption)){
            throw new IllegalArgumentException("caption for read by name is NULL");
        }
        DbRequests requests = new DbRequests();
        requests.addWhere(new DbWhereItem(new DbSelectItem(getTable(), getColumnWithName()), caption, "="));

        return readAll(requests);
    }

    T readByNameFill(String caption);

    List<T> readAllByNameFill(String caption);

    /**
     * Получить наименование столбца, который выполняет роль наименования записи
     * @return
     */
    String getColumnWithName();
}
