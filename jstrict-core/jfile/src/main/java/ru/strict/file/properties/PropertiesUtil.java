package ru.strict.file.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Управления properties-файлами
 */
public class PropertiesUtil {

    /**
     * Чтение значение из properties файла
     *
     * @param pathFile путь к properties файлу
     * @param caption название свойства, которое необходимо прочитать
     * @return
     */
    public static String getValue(String pathFile, String caption) {
        return getValue(pathFile, caption, null, null);
    }

    /**
     * Чтение значение из properties файла
     *
     * @param caption название свойства, которое необходимо прочитать
     * @return
     */
    public static String getValue(InputStream inputStream, String caption) {
        return getValue(inputStream, caption, null, null);
    }


    /**
     * Чтение значение из properties в кодировке UTF-8
     *
     * @param pathFile путь к properties файлу
     * @param caption название свойства, которое необходимо прочитать
     * @return
     */
    public static String getValueToUTF8(String pathFile, String caption) {
        return getValue(pathFile, caption, null, "UTF-8");
    }

    /**
     * Чтение значение из properties в кодировке UTF-8
     *
     * @param caption название свойства, которое необходимо прочитать
     * @return
     */
    public static String getValueToUTF8(InputStream inputStream, String caption) {
        return getValue(inputStream, caption, null, "UTF-8");
    }

    /**
     * Чтение значение из properties в кодировке UTF-8
     *
     * @param pathFile путь к properties файлу
     * @param caption название свойства, которое необходимо прочитать
     * @param encodingFile исходная кодировка properties-файла
     * @return
     */
    public static String getValueToUTF8(String pathFile, String caption, String encodingFile) {
        return getValue(pathFile, caption, encodingFile, "UTF-8");
    }

    /**
     * Чтение значение из properties в кодировке UTF-8
     *
     * @param caption название свойства, которое необходимо прочитать
     * @param encodingFile исходная кодировка properties-файла
     * @return
     */
    public static String getValueToUTF8(InputStream inputStream, String caption, String encodingFile) {
        return getValue(inputStream, caption, encodingFile, "UTF-8");
    }

    /**
     * Чтение значение из properties в указанной кодировке
     *
     * @param pathFile путь к properties файлу
     * @param caption название свойства, которое необходимо прочитать
     * @param encodingOutput кодировка, в которую преобразуется полученное значение
     * @return
     */
    public static String getValue(String pathFile, String caption, String encodingOutput) {
        return getValue(pathFile, caption, null, encodingOutput);
    }

    /**
     * Чтение значение из properties в указанной кодировке
     *
     * @param caption название свойства, которое необходимо прочитать
     * @param encodingOutput кодировка, в которую преобразуется полученное значение
     * @return
     */
    public static String getValue(InputStream inputStream, String caption, String encodingOutput) {
        return getValue(inputStream, caption, null, encodingOutput);
    }

    /**
     * Чтение значения из properties файла
     *
     * @param pathFile путь к properties файлу
     * @param caption название свойства, которое необходимо прочитать
     * @param encodingFile исходная кодировка properties-файла
     * @param encodingOutput кодировка, в которую преобразуется полученное значение
     * @return
     */
    public static String getValue(String pathFile, String caption,
            String encodingFile, String encodingOutput) {
        try (FileInputStream fileInputStream = new FileInputStream(pathFile)) {
            return getValue(fileInputStream, caption, encodingFile, encodingOutput);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Чтение значения из properties файла
     *
     * @param caption название свойства, которое необходимо прочитать
     * @param encodingFile исходная кодировка properties-файла
     * @param encodingOutput кодировка, в которую преобразуется полученное значение
     * @return
     */
    public static String getValue(InputStream inputStream, String caption,
            String encodingFile, String encodingOutput) {
        //Инициализируем специальный объект Properties типа Hashtable для удобной работы с данными
        Properties prop = new Properties();

        try {
            //Обращаемся к файлу и получаем данные
            prop.load(inputStream);
            // Получаем свойство
            String result = null;
            String propValue = prop.getProperty(caption);
            if (propValue != null) {
                if (encodingFile != null && encodingOutput != null) {
                    result = new String(propValue.getBytes(encodingFile), encodingOutput);
                } else if (encodingFile == null && encodingOutput != null) {
                    result = new String(propValue.getBytes(), encodingOutput);
                } else {
                    result = propValue;
                }
            }
            return result;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Чтение свойств из properties файла
     *
     * @return
     */
    public static Properties getValues(String filePath) {
        //Инициализируем специальный объект Properties типа Hashtable для удобной работы с данными
        Properties result = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            //Обращаемся к файлу и получаем данные
            result.load(fileInputStream);
            return result;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Чтение свойств из properties файла
     *
     * @return
     */
    public static Properties getValues(InputStream inputStream) {
        //Инициализируем специальный объект Properties типа Hashtable для удобной работы с данными
        Properties result = new Properties();

        try {
            //Обращаемся к файлу и получаем данные
            result.load(inputStream);
            return result;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
