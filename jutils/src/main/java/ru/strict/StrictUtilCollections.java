package ru.strict;

import java.util.*;

/**
 * Управление коллекциями
 */
public class StrictUtilCollections {

    /**
     * Преобразовать строку в массив, без пробелов на концах строк
     * @param str Строка, которыую надо преобразовать в массив
     * @param split Символ, которые разделяет строку на элементы массива
     * @return
     */
    public static String[] strTrimToArray(String str, String split){
        StrictUtilLogger.info(StrictUtilCollections.class, "strTrimToArray - started");
        String[] arrStr = str.split(split);
        for(int i=0; i<arrStr.length; i++)
            arrStr[i].trim();
        StrictUtilLogger.info(StrictUtilCollections.class, "strTrimToArray - finished");
        return arrStr;
    }

    /**
     * Преобразовать строку в коллекцию, без пробелов на концах строк
     * @param str Строка, которыую надо преобразовать в массив
     * @param split Символ, которые разделяет строку на элементы массива
     * @return
     */
    public static Collection<String> strTrimToCollection(String str, String split){
        StrictUtilLogger.info(StrictUtilCollections.class, "strTrimToCollection - started");
        List<String> listStr = (List<String>)arrayStrToCollection(str.split(split));
        for(int i=0; i<listStr.size(); i++)
            listStr.set(i, listStr.get(i).trim());
        StrictUtilLogger.info(StrictUtilCollections.class, "strTrimToCollection - finished");
        return listStr;
    }

    /**
     * Преобразование строковго массива в строковую коллекцию
     * @param arr Массив элементов, который надо преобразовать в колекцию
     * @return
     */
    public static Collection<String> arrayStrToCollection(String[] arr){
        StrictUtilLogger.info(StrictUtilCollections.class, "arrayStrToCollection - started");
        Collection<String> coll = new ArrayList<>();
        for(String field: arr)
            coll.add(field);
        StrictUtilLogger.info(StrictUtilCollections.class, "arrayStrToCollection - finished");
        return coll;
    }

    /**
     * Преобразование строковой коллекции в строковый массив
     * @param collection список элементов, который требуется преобразовать в строкой массив
     * @return
     */
    public static String[] collectionStrToArray(Collection<String> collection){
        StrictUtilLogger.info(StrictUtilCollections.class, "collectionStrToArray - started");
        Iterator<String> iter = collection.iterator();
        String [] arr = new String[collection.size()];
        int i=0;
        while(iter.hasNext()) {
            arr[i] = iter.next();
            i++;
        }
        StrictUtilLogger.info(StrictUtilCollections.class, "collectionStrToArray - finished");
        return arr;
    }

    /**
     * Преобразование коллекции текста в коллекцию объектов
     * @param collection список элементов, который требуется преобразовать
     * @return
     */
    public static Collection<Object> collectionStrToObj(Collection<String> collection){
        StrictUtilLogger.info(StrictUtilCollections.class, "collectionStrToObj - started");
        Collection<Object> coll = new ArrayList<>();
        for(String temp:collection)
            coll.add(temp);
        StrictUtilLogger.info(StrictUtilCollections.class, "collectionStrToObj - finished");
        return coll;
    }

    /**
     * Преобразование коллекции объектов в коллекцию текста
     * @param collection список элементов, который требуется преобразовать
     * @return
     */
    public static Collection<String> collectionObjToStr(Collection<? extends Object> collection){
        StrictUtilLogger.info(StrictUtilCollections.class, "collectionObjToStr - started");
        Collection<String> coll = new ArrayList<>();
        for(Object temp:collection)
            coll.add(String.valueOf(temp.toString()));
        StrictUtilLogger.info(StrictUtilCollections.class, "collectionObjToStr - finished");
        return coll;
    }

    /**
     * Преобразование коллекции объектов в коллекцию текста без пробелов в начале и конце строки
     * @param collection список элементов, который требуется преобразовать
     * @return
     */
    public static Collection<String> collectionObjToStrTrim(Collection<? extends Object> collection){
        StrictUtilLogger.info(StrictUtilCollections.class, "collectionObjToStrTrim - started");
        Collection<String> coll = new ArrayList<>();
        for(Object temp:collection)
            coll.add(String.valueOf(temp.toString()).trim());
        StrictUtilLogger.info(StrictUtilCollections.class, "collectionObjToStrTrim - finished");
        return coll;
    }

    /**
     * Преобразование двумерной коллекции текста в двумерную коллекцию объектов
     * @param collection список элементов, который требуется преобразовать
     * @return
     */
    public static Collection<Collection<Object>> collectionsStrToObj(Collection<Collection<String>> collection){
        StrictUtilLogger.info(StrictUtilCollections.class, "collectionsStrToObj - started");
        Collection<Collection<Object>> coll = new ArrayList<>();
        for(Collection<String> collTemp:collection){
            Collection<Object> objs = new ArrayList<>();
            for(String str:collTemp)
                objs.add(str);
            coll.add(objs);
        }
        StrictUtilLogger.info(StrictUtilCollections.class, "collectionsStrToObj - finished");
        return coll;
    }
}
