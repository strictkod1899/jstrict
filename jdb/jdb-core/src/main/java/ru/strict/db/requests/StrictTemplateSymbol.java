package ru.strict.db.requests;

/**
 * Шаблонный символ сравнения строк
 */
public class StrictTemplateSymbol {

    /**
     * Шаблонный символ сравнения строк
     */
    private String templateSymbol;
    /**
     * Место добавления шаблонного символа
     */
    private StrictPointTemplateSymbol pointTemplateSymbol;

    //<editor-fold defaultState="collapsed" desc="constructors">
    public StrictTemplateSymbol(String templateSymbol, StrictPointTemplateSymbol pointTemplateSymbol) {
        this.templateSymbol = templateSymbol;
        this.pointTemplateSymbol = pointTemplateSymbol;
    }
    //</editor-fold>

    //<editor-fold defaultState="collapsed" desc="Get/Set">
    public String getTemplateSymbol() {
        return templateSymbol;
    }

    public StrictPointTemplateSymbol getPointTemplateSymbol() {
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
        if(obj instanceof StrictTemplateSymbol) {
            StrictTemplateSymbol object = (StrictTemplateSymbol) obj;
            return super.equals(object) && templateSymbol.equals(object.getTemplateSymbol())
                    && pointTemplateSymbol.equals(object.getPointTemplateSymbol());
        }else
            return false;
    }
    //</editor-fold>
}
