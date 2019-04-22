package ru.strict.utils;

import ru.strict.components.OSType;

import java.io.*;
import java.util.Locale;

/**
 * Системные операции
 */
public class UtilSystem {

    public static final String AUTORUN_WINDOWS_USER_REG_LOCATION = "HKCU\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Run";

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

    /**
     * Получить значение из реестра windows по ключу
     * @param location Путь в реестре
     * @param key Ключ реестра
     * @return
     */
    public static String getWindowsRegKey(String location, String key){
        return WinRegistry.readRegKey(location, key);
    }

    public static void removeFromAutorun(String key){
        setAutorunProgram(null, false, key);
    }

    /**
     * Установить файл для автозапуска
     * @param file Файл, коорый должен автоматически запускаться
     * @param isEnableAutorun Установить/удалить файл в/из автозапуска
     */
    public static void setAutorunProgram(File file, boolean isEnableAutorun){
        setAutorunProgram(file, isEnableAutorun, null);
    }

    /**
     * Установить файл для автозапуска
     * @param file Файл, коорый должен автоматически запускаться
     * @param isEnableAutorun Установить/удалить файл в/из автозапуска
     * @param key Ключ для добавления файла в автозапуск
     */
    public static void setAutorunProgram(File file, boolean isEnableAutorun, String key){
        key = key != null ? key : (file == null ? null : file.getName());

        if(key == null){
            throw new IllegalArgumentException("key is NULL");
        }

        switch (getOperatingSystemType()){
            case WINDOWS:
                try {
                    if(isEnableAutorun){
                        Runtime.getRuntime().exec(String.format("cmd /C reg add %s /v %s /t REG_SZ /d \"%s\" /f", AUTORUN_WINDOWS_USER_REG_LOCATION, key, file.getAbsolutePath()));
                    }else{
                        Runtime.getRuntime().exec(String.format("cmd /C reg delete %s /v %s /f\r\n", AUTORUN_WINDOWS_USER_REG_LOCATION, key));
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case MAC_OS:
                StringBuilder macCommand = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                macCommand.append("<!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDsPropertyList-1.0.dtd\">\n");
                macCommand.append("<plist version=\"1.0\">\n");
                macCommand.append("	<dict>\n");
                macCommand.append("		<key>Label</key>\n");
                macCommand.append("		<string>%s</string>\n");
                macCommand.append("		<key>ProgramArguments</key>\n");
                macCommand.append("		<array>\n");
                macCommand.append("			<string>/usr/bin/open</string>\n");
                macCommand.append("			<string>%s</string>\n");
                macCommand.append("		</array>\n");
                macCommand.append("		<key>RunAtLoad</key>\n");
                macCommand.append("		<true/>\n");
                macCommand.append("	</dict>\n");
                macCommand.append("</plist>");
                File plist = new File(System.getProperty("user.home")
                        + String.format("/Library/LaunchAgents/%s.plist", key));
                String command = String.format(macCommand.toString(), key, file.getAbsolutePath());
                if (isEnableAutorun) {
                    FileWriter writeFile = null;
                    try {
                        writeFile = new FileWriter(plist);
                        writeFile.write(command);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } finally {
                        if (writeFile != null) {
                            try {
                                writeFile.close();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                } else {
                    if (plist.exists()) {
                        plist.delete();
                    }
                }
                break;
            default:
                throw new UnsupportedOperationException(String.format("curren OS type not supported for settings autorun program [%s]", file.getAbsolutePath()));
        }
    }
}
