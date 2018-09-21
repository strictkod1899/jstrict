package ru.strict.swing.views.dialogs;

import ru.strict.swing.models.ModelFormBase;
import ru.strict.swing.views.IForm;
import ru.strict.swing.views.utils.CommonViewMethods;

import javax.swing.JDialog;

/**
 * Фрейм диалога
 */
public class DialogBase<M extends ModelFormBase>
        extends JDialog
        implements IForm<M> {

    private M model;
    private boolean isBuilt;

    @Override
    public DialogBase build(M model){
        this.model = model;
        CommonViewMethods.build(this, model);
        isBuilt = true;
        return this;
    }

    @Override
    public void launch(){
        setVisible(true);
    }

    @Override
    public void destroy(){
        setVisible(false);
    }

    @Override
    public void updateView(){
        CommonViewMethods.updateView(this);
    }

    @Override
    public boolean isBuilt() {
        return isBuilt;
    }

    @Override
    public void pack() {
        CommonViewMethods.pack(this);
    }

    @Override
    public void packHeight() {
        CommonViewMethods.packHeight(this);
    }

    @Override
    public void packWidth() {
        CommonViewMethods.packWidth(this);
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
