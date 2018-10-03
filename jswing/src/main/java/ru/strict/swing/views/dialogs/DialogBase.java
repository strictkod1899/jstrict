package ru.strict.swing.views.dialogs;

import ru.strict.swing.views.IForm;
import ru.strict.swing.views.utils.CommonViewMethods;

import javax.swing.*;

/**
 * Фрейм диалога
 */
public class DialogBase<M> extends JDialog implements IForm {

    private M model;

    public DialogBase(M model) {
        this.model = model;
        build();
    }

    private void build(){
        CommonViewMethods.build(this);
        getContentPane().setBackground(getBackground());
    }

    @Override
    public void launch(){
        setVisible(true);
    }

    @Override
    public void destroy(){
        System.exit(0);
    }

    @Override
    public void refresh(){
        CommonViewMethods.refresh(this);
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

    protected M getModel() {
        return model;
    }
}
