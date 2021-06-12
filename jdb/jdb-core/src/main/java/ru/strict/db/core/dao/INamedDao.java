package ru.strict.db.core.dao;

import ru.strict.db.core.common.SqlParameter;
import ru.strict.db.core.query.components.SingleWhere;
import ru.strict.db.core.query.components.SqlItem;
import ru.strict.template.model.BaseModel;
import ru.strict.validate.Validator;

import java.util.List;

/**
 * Расширенные возможности DAO для выполнения операций с записья используя ее столбец наименования
 *
 * @param <ID> Тип идентификатора
 * @param <T> Модель сущности базы данных
 */
public interface INamedDao<ID, T extends BaseModel<ID>> extends IDao<ID, T> {

    /**
     * Чтение записи из базы данных по наименованию
     *
     * @param caption Значение столбца наименования
     * @return
     */
    default T readByName(String caption) {
        Validator.isEmptyOrNull(caption, "caption");

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
        Validator.isEmptyOrNull(caption, "caption");

        SingleWhere where = new SingleWhere(
                new SqlItem(getTable(), getColumnWithName()),
                "=",
                new SqlParameter<>(getColumnWithName(), caption));

        return readAll(where);
    }

    /**
     * Получить наименование столбца, который выполняет роль наименования записи
     *
     * @return
     */
    String getColumnWithName();
}
