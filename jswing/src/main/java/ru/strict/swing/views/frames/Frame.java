package ru.strict.swing.views.frames;

import ru.strict.swing.models.frames.ModelFrame;
import ru.strict.swing.views.FormBase;
import ru.strict.swing.views.GeneralView;

import javax.swing.JFrame;

/**
 * Базовый фрейм
 */
public class Frame<M extends ModelFrame> extends JFrame implements FormBase<M> {

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
    public Frame build(M model){
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
        System.exit(0);
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