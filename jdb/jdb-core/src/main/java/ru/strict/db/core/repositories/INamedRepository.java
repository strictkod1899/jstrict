package ru.strict.db.core.repositories;

import ru.strict.db.core.common.SqlParameter;
import ru.strict.db.core.requests.components.SingleWhere;
import ru.strict.db.core.requests.components.SqlItem;
import ru.strict.models.BaseModel;
import ru.strict.validate.Validator;

import java.util.List;

/**
 * Расширенные возможности репозитория для выполнения операций с записья используя ее столбец наименования
 *
 * @param <ID> Тип идентификатора
 * @param <T> Модель сущности базы данных
 */
public interface INamedRepository<ID, T extends BaseModel<ID>> extends IExtensionRepository<ID, T> {

    /**
     * Чтение записи из базы данных по наименованию
     *
     * @param caption Значение столбца наименования
     * @return
     */
    default T readByName(String caption) {
        Validator.isEmptyOrNull(caption, "caption")
                .reason("caption for read by name is NULL")
                .onThrow();

        SingleWhere where = new SingleWhere(
                new SqlItem(getTable(), getColumnWithName()),
                "=",
                new SqlParameter<>(getColumnWithName(), caption));

        return readAll(where).stream().findFirst().orElse(null);
    }

    /**
     * Чтение записей из базы данных по наименованию
     *
     * @param caption Значение столбца наименования
     * @return
     */
    default List<T> readAllByName(String caption) {
        Validator.isEmptyOrNull(caption, "caption")
                .reason("caption for read all by name is NULL")
                .onThrow();

        SingleWhere where = new SingleWhere(
                new SqlItem(getTable(), getColumnWithName()),
                "=",
                new SqlParameter<>(getColumnWithName(), caption));

        return readAll(where);
    }

    T readByNameFill(String caption);

    List<T> readAllByNameFill(String caption);

    /**
     * Получить наименование столбца, который выполняет роль наименования записи
     *
     * @return
     */
    String getColumnWithName();
}
