package ru.strict.utils;

/**
* Класс описывает статический метод для определения имени текущего класса
*/
public class StrictUtilClassName {

    /**
     * Определить имя текщего класса
     * @return
     */
    public static String getCurrentClassname(){
        StrictUtilLogger.info(StrictUtilClassName.class, "getCurrentClassname");
        try{
            throw new RuntimeException();
        }catch(RuntimeException ex){
            return ex.getStackTrace()[1].getClassName();
        }
        
    }
}
