package ru.strict.db.core.connection;


import java.util.Objects;

/**
 * Конструктор соединения с базой данных
 *
 * @param <SOURCE> Тип источника подключения к базе данных (DataSource, ConnectionInfo)
 */
public abstract class BaseConnectionCreator<SOURCE, CONNECTION> implements IConnectionCreator<CONNECTION> {

    /**
     * Источник подключения к базе данных (DataSource, ConnectionInfo). <br/>
     * <i><b>Примечание:</b> используется для получения объекта Connection</i>
     */
    private SOURCE connectionSource;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public BaseConnectionCreator(SOURCE connectionSource) {
        this.connectionSource = connectionSource;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public SOURCE getConnectionSource() {
        return connectionSource;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString() {
        return connectionSource.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseConnectionCreator<?, ?> that = (BaseConnectionCreator<?, ?>) o;
        return Objects.equals(connectionSource, that.connectionSource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connectionSource);
    }
    //</editor-fold>
}
