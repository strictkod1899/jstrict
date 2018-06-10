package ru.strict.file.controllers;

import ru.strict.utils.UtilLogger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Контроллер для обращения к properties файлам
 */
public class ControllerProperties {

    /**
     * Объект, для удобной работы со свойствами
     */
    private Properties prop;

    public ControllerProperties(File fileProperties) {
        try {
            prop = new Properties();
            prop.load(new FileInputStream(fileProperties));
        } catch (IOException ex) {
            UtilLogger.error(ControllerProperties.class, ex.getClass().toString(), ex.getMessage());
        }
    }

    /**
     * Чтение свойства из текущего properties файла
     * @param caption Название свойства, которое необходимо прочитать
     * @return
     */
    public String getValue(String caption){
        UtilLogger.info(ControllerProperties.class, "getValue - started");

        try {
            // Разобраться с кодировкой
            String result = new String(prop.getProperty(caption).getBytes("iso-8859-1"), "UTF-8");
            UtilLogger.info(ControllerProperties.class, "getValue - finished");
            return result;
        } catch (IOException ex) {
            UtilLogger.error(ControllerProperties.class, ex.getClass().toString(), ex.getMessage());
            return null;
        }
    }
}
