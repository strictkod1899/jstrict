package ru.strict.fx;

import javafx.stage.Modality;
import javafx.stage.StageStyle;

public class DialogBase<M> extends FrameBase<M> {

    public DialogBase(M model) {
        super(model);
    }

    public DialogBase(StageStyle style, M model) {
        super(style, model);
    }

    @Override
    public FrameBase<M> build() {
        super.build();
        initModality(Modality.APPLICATION_MODAL);
        return this;
    }
}
