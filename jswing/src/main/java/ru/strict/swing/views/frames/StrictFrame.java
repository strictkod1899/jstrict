package ru.strict.swing.views.frames;

import ru.strict.swing.models.frames.StrictModelFrame;
import ru.strict.swing.views.StrictFormBase;
import ru.strict.swing.views.StrictGeneralView;

import javax.swing.JFrame;

/**
 * Базовый фрейм
 */
public class StrictFrame<M extends StrictModelFrame> extends JFrame implements StrictFormBase<M> {

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
    public StrictFrame build(M model){
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
        System.exit(0);
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
