package ru.strict.db;

import ru.strict.db.repositories.StrictRepositoryAny;
import ru.strict.utils.StrictUtilLogger;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Управление соединением с базой данных
 */
public class StrictManagerDatabase<SOURCE> {

    /**
     * Источник подключения к базе данных (используется для получения объекта Connection),
     * например, DataSource, StringConnectionInfo и др.
     */
    private SOURCE connectionSource;

    /**
     * Используемые репозитории
     */
    private Map<String, StrictRepositoryAny> repositories;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictManagerDatabase(SOURCE connectionSource) {
        this.connectionSource = connectionSource;
        repositories = new LinkedHashMap<>();
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
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

    public SOURCE getConnectionSource() {
        return connectionSource;
    }
    //</editor-fold>
}
