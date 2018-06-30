package ru.strict.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

/**
 * Класс для инициализации конфигурации log4j
 *
 * Использование:
 * private static Logger logger;
 * 
 * logger = Logger.getsLogger(this.getClass());
 * StrictConfigLog4j config = new StrictConfigLog4j(<путь до файла конфигураций log4j>);
 * config.init();
 */
public class UtilConfigLog4j {
    private static Properties property;
    private static String logFile;

    private UtilConfigLog4j(){}

    @SuppressWarnings("static-access")
    public UtilConfigLog4j(String logFile){
        this.logFile = logFile;
        property = new Properties();
    }

    /**
     * Инициализация log4j
     */
    public int init(){
        try {
            property.load(new FileInputStream(logFile));
            PropertyConfigurator.configure(property);
            return 1;
        } catch (IOException ex) {
            UtilLogger.error(UtilConfigLog4j.class, ex.getClass().toString(), ex.getMessage());
            return 0;
        }
    }
    
}
