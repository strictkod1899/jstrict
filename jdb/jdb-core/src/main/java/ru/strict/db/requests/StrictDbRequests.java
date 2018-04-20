package ru.strict.db.requests;

import java.util.*;

/**
 * Набор условий для добавления к запросу
 * //
 *
 * Пример использования:
 * StrictDbRequests wheres = new StrictDbRequests(false);
 * // Считаются все значения книг, наименование которых начинается с буквы "И"
 * wheres.add(new StrictDbRequest("books.name", "И", "LIKE", new StrictTemplateSymbol("%", StrictEnumTemplateSymbol.END), true));
 */
public class StrictDbRequests extends LinkedList<StrictDbRequest> {

    /**
     * Добавлять AND между условиями
     */
    private boolean boolAnd;

    public StrictDbRequests(boolean boolAnd) {
        this.boolAnd = boolAnd;
    }

    public String getSql(){
        String result = "";
        if(!isEmpty())
            result+=" WHERE ";

        String symbol;
        if(boolAnd)
            symbol = "AND";
        else
            symbol = "OR";

        if(size()>0)
            result += get(0).toString() + " ";

        for(int i=1; i<size(); i++)
            result += symbol + " " + get(i).toString() + " ";

        return result;
    }

    @Override
    public String toString(){
        String result = "";
        if(!isEmpty())
            result+=" WHERE ";

        String symbol;
        if(boolAnd)
            symbol = "AND";
        else
            symbol = "OR";

        if(size()>0)
            result += get(0).toString() + " ";

        for(int i=1; i<size(); i++)
            result += symbol + " " + get(i).toString() + " ";

        return result;
    }
}
