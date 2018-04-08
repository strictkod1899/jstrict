package ru.strict.requests;

import java.util.*;

/**
 * Набор условий для добавления к запросу
 *
 * Пример использования:
 * StrictDbWheres wheres = new StrictDbWheres(false);
 * // Считаются все значения книг, наименование которых начинается с буквы "И"
 * wheres.add(new StrictDbWhere("books.name", "И", "LIKE", new StrictTemplateSymbol("%", StrictEnumTemplateSymbol.END), true));
 */
public class StrictDbWheres extends LinkedList<StrictDbWhere> {

    /**
     * Добавлять AND между условиями
     */
    private boolean boolAnd;

    public StrictDbWheres(boolean boolAnd) {
        this.boolAnd = boolAnd;
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
