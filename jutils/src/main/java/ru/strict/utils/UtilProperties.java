package ru.strict.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Управления properties-файлами
 */
public class UtilProperties {

    /**
     * Чтение свойства из properties файла
     * @param pathFile путь к properties файлу
     * @param caption название свойства, которое необходимо прочитать
     * @return
     */
    public static String getValue(String pathFile, String caption){
        UtilLogger.info(UtilProperties.class, "getValue - started");
        FileInputStream fileInputStream;
        //Инициализируем специальный объект Properties типа Hashtable для удобной работы с данными
        Properties prop = new Properties();

        try {
            //Обращаемся к файлу и получаем данные
            fileInputStream = new FileInputStream(pathFile);
            prop.load(fileInputStream);
            // Получаем свойство в кодировке UTF-8
            String result = new String(prop.getProperty(caption).getBytes("iso-8859-1"), "UTF-8");
            UtilLogger.info(UtilProperties.class, "getValue - finished");
            return result;
        } catch (IOException e) {
            UtilLogger.error(UtilProperties.class, e.getClass().toString(), e.getMessage());
            return "";
        }
    }
}