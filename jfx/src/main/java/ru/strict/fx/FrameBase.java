package ru.strict.fx;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.strict.patterns.mvc.views.IView;

public class FrameBase<M> extends Stage implements IView {
    private M model;
    private boolean isBuilt;

    public FrameBase(M model) {
        super();
        this.model = model;
    }

    public FrameBase(StageStyle style, M model) {
        super(style);
        this.model = model;
    }

    @Override
    public FrameBase<M> build(){
        setMinWidth(400);
        setMinHeight(300);

        getIcons().clear();
        Group root = new Group();
        Scene scene = new Scene(root);
        scene.setFill(Paint.valueOf(Color.BACKGROUND_FORM.getCode()));
        setScene(scene);

        isBuilt = true;
        return this;
    }

    @Override
    public void launch(){
        if(!isBuilt) {
            build();
        }
        show();
    }

    @Override
    public void destroy(){
        System.exit(0);
    }

    @Override
    public void refresh(){}

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
