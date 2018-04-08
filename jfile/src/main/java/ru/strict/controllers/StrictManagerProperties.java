package ru.strict.controllers;

import ru.strict.utils.StrictUtilLogger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Контроллер для обращения к properties файлам
 */
public class StrictManagerProperties{

    /**
     * Объект, для удобной работы со свойствами
     */
    private Properties prop;

    public StrictManagerProperties(File fileProperties) {
        try {
            prop = new Properties();
            prop.load(new FileInputStream(fileProperties));
        } catch (IOException ex) {
            StrictUtilLogger.error(StrictManagerProperties.class, ex.getClass().toString(), ex.getMessage());
        }
    }

    /**
     * Чтение свойства из текущего properties файла
     * @param caption Название свойства, которое необходимо прочитать
     * @return
     */
    public String getValue(String caption){
        StrictUtilLogger.info(StrictManagerProperties.class, "getValue - started");

        try {
            // Разобраться с кодировкой
            String result = new String(prop.getProperty(caption).getBytes("iso-8859-1"), "UTF-8");
            StrictUtilLogger.info(StrictManagerProperties.class, "getValue - finished");
            return result;
        } catch (IOException ex) {
            StrictUtilLogger.error(StrictManagerProperties.class, ex.getClass().toString(), ex.getMessage());
            return null;
        }
    }
}
