package ru.strict.swing.views.frames;

import ru.strict.patterns.mvc.views.IView;
import ru.strict.swing.views.utils.CommonViewMethods;

import javax.swing.*;

/**
 * Базовый фрейм
 */
public class FrameBase<M> extends JFrame implements IView {

    private M model;
    private boolean isBuilt;

    public FrameBase(M model) {
        this.model = model;
    }

    @Override
    public FrameBase<M> build(){
        CommonViewMethods.build(this);
        getContentPane().setBackground(getBackground());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
