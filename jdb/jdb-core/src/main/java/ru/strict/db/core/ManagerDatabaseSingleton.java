package ru.strict.db.core;

public class ManagerDatabaseSingleton<SOURCE> extends ManagerDatabase<SOURCE> {

    private static ManagerDatabase instance;

    private ManagerDatabaseSingleton(SOURCE connectionSource) {
        super(connectionSource);
    }

    public static <SOURCE> void initialize(SOURCE connectionSource){
        if(instance == null){
            instance = new ManagerDatabaseSingleton(connectionSource);
        }
    }

    public static ManagerDatabase instance(){
        return instance;
    }
}
