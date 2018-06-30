package ru.strict.swing.views.dialogs;

import ru.strict.swing.models.dialogs.ModelDialog;
import ru.strict.swing.views.FormBase;
import ru.strict.swing.views.GeneralView;

import javax.swing.JDialog;

/**
 * Фрейм диалога
 */
public class Dialog<M extends ModelDialog> extends JDialog implements FormBase<M> {

    private M model;
    private GeneralView generalMethods;

    @Override
    public void launch(){
        setVisible(true);
    }

    @Override
    public void updateView(){
        generalMethods.updateView();
    }

    @Override
    public Dialog build(M model){
        generalMethods = new GeneralView(this);
        generalMethods.build(model);
        return this;
    }

    @Override
    public void pack() {
        generalMethods.pack();
    }

    @Override
    public void packHeight() {
        generalMethods.packHeight();
    }

    @Override
    public void packWidth() {
        generalMethods.packWidth();
    }

    @Override
    public void destroy(){
        setVisible(false);
    }

    public GeneralView getGeneralMethods() {
        return generalMethods;
    }

    @Override
    public void setModel(M model) {
        this.model = model;
    }

    @Override
    public M getModel() {
        return model;
    }

}
