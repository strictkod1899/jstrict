package ru.strict.db.core.connections;

import ru.strict.utils.UtilHashCode;

/**
 * Конструктор соединения с базой данных
 * @param <SOURCE> Тип источника подключения к базе данных (DataSource, ConnectionInfo)
 */
public abstract class CreateConnectionBase<SOURCE, CONNECTION> implements ICreateConnection<CONNECTION> {

    /**
     * Источник подключения к базе данных (DataSource, ConnectionInfo). <br/>
     * <i><b>Примечание:</b> используется для получения объекта Connection</i>
     */
    private SOURCE connectionSource;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public CreateConnectionBase(SOURCE connectionSource) {
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
        if(obj!=null && obj instanceof CreateConnectionBase) {
            CreateConnectionBase object = (CreateConnectionBase) obj;
            return connectionSource.equals(object.getConnectionSource());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        return UtilHashCode.createHashCode(connectionSource);
    }
    //</editor-fold>
}
