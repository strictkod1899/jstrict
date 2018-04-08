package ru.strict;

import ru.strict.repositories.StrictRepositoryAny;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Управление базой данных
 */
public class StrictManagerDatabase {

    private Connection connection;
    /**
     * Используемые репозитории
     */
    private Map<String, StrictRepositoryAny> repositories;

    public StrictManagerDatabase(Connection connection) {
        this.connection = connection;
        repositories = new LinkedHashMap<>();
    }

    /**
     * Выполнить запрос на выборку данных
     * @param sql Sql запрос на выборку данных
     * @return
     */
    public ResultSet qSelectValue(String sql){
        ResultSet rs = null;

        try {
            rs = connection.createStatement().executeQuery(sql);
        } catch (SQLException ex) {
            StrictUtilLogger.error(StrictManagerDatabase.class, ex.getClass().toString(), ex.getMessage());
        }
        return rs;
    }

    /**
     * Получить наименования столбцов из переданного запроса
     * @param sql Sql запрос из которого происходит выборка наименований столбцов
     * @return
     */
    public List<String> qSelectColumnCaption(String sql){
        List<String> result = new ArrayList<>();

        try {
            ResultSet rs = connection.createStatement().executeQuery(sql);
            ResultSetMetaData metaData = rs.getMetaData();
            int size = metaData.getColumnCount();
            for(int i=1; i<=size; i++)
                result.add(metaData.getColumnLabel(i));
        } catch (SQLException ex) {
            StrictUtilLogger.error(StrictManagerDatabase.class, ex.getClass().toString(), ex.getMessage());
        }
        return result;
    }

    /**
     * Добавить новый репозиторий
     * @param key Ключ доступа к репозиторию
     * @param repository Добавляемый репозиторий
     */
    public StrictRepositoryAny addRepository(String key, StrictRepositoryAny repository) {
        repositories.put(key, repository);
        return repository;
    }

    /**
     * Получить список репоизториев, которые использует данный контроллер
     * @return
     */
    public Map<String, StrictRepositoryAny> getRepositories() {
        return repositories;
    }

    /**
     * Получить соединение с базой данных
     */
    public Connection getConnection() {
        return connection;
    }
    /**
     * Установить соединение с базой данных
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
