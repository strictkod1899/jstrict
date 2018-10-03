package ru.strict.swing.views.dialogs;

import ru.strict.patterns.mvc.views.IView;
import ru.strict.swing.views.utils.CommonViewMethods;

import javax.swing.*;

/**
 * Фрейм диалога
 */
public class DialogBase<M> extends JDialog implements IView {

    private M model;
    private boolean isBuilt;

    public DialogBase(M model) {
        this.model = model;
    }

    @Override
    public DialogBase<M> build(){
        CommonViewMethods.build(this);
        getContentPane().setBackground(getBackground());
        isBuilt = true;
        return this;
    }

    @Override
    public void launch(){
        if(!isBuilt) {
            build();
        }
        setVisible(true);
    }

    @Override
    public void destroy(){
        model = null;
        setVisible(false);
    }

    @Override
    public void refresh(){
        CommonViewMethods.refresh(this);
    }

    @Override
    public void pack() {
        CommonViewMethods.pack(this);
    }

    protected void packHeight() {
        CommonViewMethods.packHeight(this);
    }

    protected void packWidth() {
        CommonViewMethods.packWidth(this);
    }

    protected boolean isBuilt() {
        return isBuilt;
    }

    protected void setBuilt(boolean built) {
        isBuilt = built;
    }

    protected M getModel() {
        return model;
    }

    protected void setModel(M model) {
        this.model = model;
    }
}
