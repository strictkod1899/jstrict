package ru.strict.repositories;

import ru.strict.requests.StrictDbWheres;
import ru.strict.entities.StrictEntityBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public abstract class StrictRepositoryBase<E extends StrictEntityBase> implements StrictRepositoryAny<E>{

    /**
     * Объект соединения с базой данных
     */
    private Connection connection;

    /**
     * Список значений при выборке значений SELECT
     */
    private List<E> objects;

    /**
     * Запрос на выборку данных
     */
    private String sqlSelect;

    public StrictRepositoryBase(Connection connection, String sqlSelect) {
        this.connection = connection;
        this.sqlSelect = sqlSelect;
    }

    @Override
    public List<E> readList(StrictDbWheres wheres) {
        if(objects == null)
            objects = new LinkedList<>();

        if(objects.isEmpty())
            objects = createObjects(wheres);

        return objects;
    }

    /**
     * Перечитать текущие значения
     * @param wheres
     * @return
     */
    public List<E> rereadList(StrictDbWheres wheres) {
        if(objects == null)
            objects = new LinkedList<>();

        objects = createObjects(wheres);

        return objects;
    }

    /**
     * Получение списка объектов через запрос к базе данных
     * @return
     */
    private List<E> createObjects(StrictDbWheres wheres){
        Statement statement;
        ResultSet resultSet;
        List<E> result = new LinkedList<>();
        try {
            statement = getConnection().createStatement();

            resultSet = statement.executeQuery(getSqlSelect() + (wheres==null?"":wheres.toString()));
            while(resultSet.next())
                result.add(initObject(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Connection getConnection() {
        return connection;
    }

    public String getSqlSelect() {
        return sqlSelect;
    }

    public void setSqlSelect(String sqlSelect) {
        this.sqlSelect = sqlSelect;
    }

    /**
     * Инциализация объекта выборки используя результат запроса
     * @param resultSet
     * @return
     */
    public abstract E initObject(ResultSet resultSet);
}
