package ru.strict.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Управления properties-файлами
 */
public class UtilProperties {

    /**
     * Чтение значение из properties файла
     * @param pathFile путь к properties файлу
     * @param caption название свойства, которое необходимо прочитать
     * @return
     */
    public static String getValue(String pathFile, String caption){
        return getValue(pathFile, caption, null, null);
    }

    /**
     * Чтение значение из properties в кодировке UTF-8
     * @param pathFile путь к properties файлу
     * @param caption название свойства, которое необходимо прочитать
     * @return
     */
    public static String getValueToUTF8(String pathFile, String caption){
        return getValue(pathFile, caption, null, "UTF-8");
    }

    /**
     * Чтение значение из properties в кодировке UTF-8
     * @param pathFile путь к properties файлу
     * @param caption название свойства, которое необходимо прочитать
     * @param encodingFile исходная кодировка properties-файла
     * @return
     */
    public static String getValueToUTF8(String pathFile, String caption, String encodingFile){
        return getValue(pathFile, caption, encodingFile, "UTF-8");
    }

    /**
     * Чтение значение из properties в указанной кодировке
     * @param pathFile путь к properties файлу
     * @param caption название свойства, которое необходимо прочитать
     * @param encodingOutput кодировка, в которую преобразуется полученное значение
     * @return
     */
    public static String getValue(String pathFile, String caption, String encodingOutput){
        return getValue(pathFile, caption, null, encodingOutput);
    }

    /**
     * Чтение значения из properties файла
     * @param pathFile путь к properties файлу
     * @param caption название свойства, которое необходимо прочитать
     * @param encodingFile исходная кодировка properties-файла
     * @param encodingOutput кодировка, в которую преобразуется полученное значение
     * @return
     */
    public static String getValue(String pathFile, String caption,
                                  String encodingFile, String encodingOutput){
        UtilLogger.info(UtilProperties.class, "getValue - started");
        //Инициализируем специальный объект Properties типа Hashtable для удобной работы с данными
        Properties prop = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream(pathFile);){
            //Обращаемся к файлу и получаем данные
            prop.load(fileInputStream);
            // Получаем свойство
            String result = null;
            if(encodingFile != null && encodingOutput != null) {
                result = new String(prop.getProperty(caption).getBytes(encodingFile), encodingOutput);
            }else if(encodingFile == null && encodingOutput != null){
                result = new String(prop.getProperty(caption).getBytes(), encodingOutput);
            }else{
                result = prop.getProperty(caption);
            }
            UtilLogger.info(UtilProperties.class, "getValue - finished");
            return result;
        } catch (IOException e) {
            UtilLogger.error(UtilProperties.class, e.getClass().toString(), e.getMessage());
            return "";
        }
    }

    /**
     * Чтение свойств из properties файла
     * @param pathFile путь к properties файлу
     * @return
     */
    public static Properties getValues(String pathFile){
        UtilLogger.info(UtilProperties.class, "getValues - started");
        //Инициализируем специальный объект Properties типа Hashtable для удобной работы с данными
        Properties result = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream(pathFile);){
            //Обращаемся к файлу и получаем данные
            result.load(fileInputStream);
            // Получаем свойство в кодировке UTF-8
            UtilLogger.info(UtilProperties.class, "getValues - finished");
            return result;
        } catch (IOException e) {
            UtilLogger.error(UtilProperties.class, e.getClass().toString(), e.getMessage());
            return null;
        }
    }
}
