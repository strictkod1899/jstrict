package ru.strict;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class StrictUtilConfigLog4j {
    private static Properties property;
    private static String logFile;

    private StrictUtilConfigLog4j(){}

    @SuppressWarnings("static-access")
    public StrictUtilConfigLog4j(String logFile){
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
            StrictUtilLogger.error(StrictUtilConfigLog4j.class, ex.getClass().toString(), ex.getMessage());
            return 0;
        }
    }
    
}
