package ru.strict.db.core.requests;

import ru.strict.utils.UtilHashCode;

/**
 * Шаблонный символ сравнения строк
 */
public class TemplateSymbol {

    /**
     * Шаблонный символ сравнения строк
     */
    private String templateSymbol;
    /**
     * Место добавления шаблонного символа
     */
    private PointTemplateSymbol pointTemplateSymbol;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public TemplateSymbol(String templateSymbol, PointTemplateSymbol pointTemplateSymbol) {
        this.templateSymbol = templateSymbol;
        this.pointTemplateSymbol = pointTemplateSymbol;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getTemplateSymbol() {
        return templateSymbol;
    }

    public PointTemplateSymbol getPointTemplateSymbol() {
        return pointTemplateSymbol;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Base override">
    @Override
    public String toString(){
        return String.format("'%s' to %s", templateSymbol, pointTemplateSymbol.name());
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof TemplateSymbol) {
            TemplateSymbol object = (TemplateSymbol) obj;
            return super.equals(object) && templateSymbol.equals(object.getTemplateSymbol())
                    && pointTemplateSymbol.equals(object.getPointTemplateSymbol());
        }else
            return false;
    }

    @Override
    public int hashCode(){
        return UtilHashCode.createHashCode(templateSymbol, pointTemplateSymbol);
    }
    //</editor-fold>
}
