package ru.strict.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

    public static String getValue(String filePath, String key) {
        return getValue(filePath, key, null, null);
    }

    public static String getValue(InputStream inputStream, String key) {
        return getValue(inputStream, key, null, null);
    }

    public static String getValueToUTF8(String filePath, String key) {
        return getValue(filePath, key, null, "UTF-8");
    }

    public static String getValueToUTF8(InputStream inputStream, String key) {
        return getValue(inputStream, key, null, "UTF-8");
    }

    public static String getValueToUTF8(String filePath, String key, String fileEncoding) {
        return getValue(filePath, key, fileEncoding, "UTF-8");
    }

    public static String getValueToUTF8(InputStream inputStream, String key, String fileEncoding) {
        return getValue(inputStream, key, fileEncoding, "UTF-8");
    }

    public static String getValue(String filePath, String key, String targetEncoding) {
        return getValue(filePath, key, null, targetEncoding);
    }

    public static String getValue(InputStream inputStream, String key, String targetEncoding) {
        return getValue(inputStream, key, null, targetEncoding);
    }

    /**
     * @param fileEncoding исходная кодировка properties-файла
     * @param targetEncoding кодировка, в которую преобразуется полученное значение
     */
    public static String getValue(String filePath, String key, String fileEncoding, String targetEncoding) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            return getValue(fileInputStream, key, fileEncoding, targetEncoding);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * @param fileEncoding исходная кодировка properties-файла
     * @param targetEncoding кодировка, в которую преобразуется полученное значение
     */
    public static String getValue(InputStream inputStream, String key, String fileEncoding, String targetEncoding) {
        try {
            Properties properties = getValues(inputStream);
            String value = null;
            String propertyValue = properties.getProperty(key);
            if (propertyValue != null) {
                if (fileEncoding != null && targetEncoding != null) {
                    value = new String(propertyValue.getBytes(fileEncoding), targetEncoding);
                } else if (fileEncoding == null && targetEncoding != null) {
                    value = new String(propertyValue.getBytes(), targetEncoding);
                } else {
                    value = propertyValue;
                }
            }
            return value;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Properties getValues(String filePath) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            return getValues(fileInputStream);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Properties getValues(InputStream inputStream) {
        Properties properties = new Properties();

        try {
            properties.load(inputStream);
            return properties;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
