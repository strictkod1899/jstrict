package ru.strict.db.connections;

import ru.strict.utils.StrictUtilHashCode;

/**
 * Конструктор соединения с базой данных
 * @param <SOURCE> Тип источника подключения к базе данных (например, DataSource, StrictConnectionInfo и др.)
 */
public abstract class StrictCreateConnectionBase<SOURCE> implements StrictCreateConnectionAny {

    /**
     * Источник подключения к базе данных (используется для получения объекта Connection),
     * например, DataSource, StringConnectionInfo и др.
     */
    private SOURCE connectionSource;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictCreateConnectionBase(SOURCE connectionSource) {
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
    public String toString(){
        return String.format("Connection source: %s", connectionSource.toString());
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof StrictCreateConnectionBase) {
            StrictCreateConnectionBase object = (StrictCreateConnectionBase) obj;
            return connectionSource.equals(object.getConnectionSource());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        return StrictUtilHashCode.createHashCode(connectionSource);
    }
    //</editor-fold>
}
