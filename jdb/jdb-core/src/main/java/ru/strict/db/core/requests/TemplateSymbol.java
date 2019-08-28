package ru.strict.db.core.requests;


import java.util.Objects;

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
        return String.format("'%s' into point %s", templateSymbol, pointTemplateSymbol.name());
    }

    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof TemplateSymbol) {
            TemplateSymbol object = (TemplateSymbol) obj;
            return Objects.equals(templateSymbol, object.getTemplateSymbol())
                    && Objects.equals(pointTemplateSymbol, object.getPointTemplateSymbol());
        }else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(templateSymbol, pointTemplateSymbol);
    }
    //</editor-fold>
}
