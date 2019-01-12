package ru.strict.utils;

import java.io.*;
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
                File file = new File(appClass.getProtectionDomain().getCodeSource().getLocation().toURI());
                if(file.isDirectory()) {
                    result = file.getPath();
                } else {
                    result = file.getParentFile().getPath();
                }
            } catch (URISyntaxException ex) {
                throw ex;
            }
        }

        return result;
    }

    public static Process executeCommand(String command){
        try {
            Process proc = Runtime.getRuntime().exec(command);
            return proc;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Process executeCommand(Process proc, String command){
        try {
            OutputStream out = proc.getOutputStream();
            out.write(command.getBytes());
            out.flush();
            out.close();
            return proc;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
