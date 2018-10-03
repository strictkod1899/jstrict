package ru.strict.swing.views.frames;

import ru.strict.swing.views.IForm;
import ru.strict.swing.views.utils.CommonViewMethods;

import javax.swing.*;

/**
 * Базовый фрейм
 */
public class FrameBase<M> extends JFrame implements IForm {

    private M model;

    public FrameBase(M model) {
        this.model = model;
    }

    @Override
    public FrameBase<M> build(){
        CommonViewMethods.build(this);
        getContentPane().setBackground(getBackground());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return this;
    }

    @Override
    public void launch(){
        build();
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

    protected void setModel(M model) {
        this.model = model;
    }
}
