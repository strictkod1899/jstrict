package ru.strict.utils;

import ru.strict.components.OSType;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Locale;

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

    public static OSType getOperatingSystemType() {
        OSType result = null;
        if (result == null) {
            String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
            if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0)) {
                result = OSType.MAC_OS;
            } else if (OS.indexOf("win") >= 0) {
                result = OSType.WINDOWS;
            } else if (OS.indexOf("nux") >= 0) {
                result = OSType.LINUX;
            } else {
                result = OSType.OTHER;
            }
        }
        return result;
    }
}
