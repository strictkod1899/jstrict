package ru.strict.swing.views.dialogs;

import ru.strict.swing.models.dialogs.StrictModelDialog;
import ru.strict.swing.views.StrictFormBase;
import ru.strict.swing.views.StrictGeneralView;

import javax.swing.JDialog;

/**
 * Фрейм диалога
 */
public class StrictDialog<M extends StrictModelDialog> extends JDialog implements StrictFormBase<M> {

    private M model;
    private StrictGeneralView generalMethods;

    @Override
    public void launch(){
        setVisible(true);
    }

    @Override
    public void updateView(){
        generalMethods.updateView();
    }

    @Override
    public StrictDialog build(M model){
        generalMethods = new StrictGeneralView(this);
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

    public StrictGeneralView getGeneralMethods() {
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
