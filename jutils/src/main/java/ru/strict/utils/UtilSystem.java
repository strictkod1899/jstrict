package ru.strict.utils;

import java.io.File;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

/**
 * Системные операции
 */
public class UtilSystem {

    /**
     * Получить путь до директории класса
     * @param appClass
     * @return
     * @throws URISyntaxException
     */
    public static String getPathByClass(Class appClass) throws URISyntaxException {
        String result = null;

        if(appClass != null) {
            try {
                return new File(appClass.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
            } catch (URISyntaxException ex) {
                throw ex;
            }
        }

        return result;
    }
}
