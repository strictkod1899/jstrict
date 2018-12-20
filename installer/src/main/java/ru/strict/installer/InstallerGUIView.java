package ru.strict.installer;

import ru.strict.patterns.mvc.views.IView;
import ru.strict.patterns.mvc.views.ViewBase;

public class InstallerGUIView extends ViewBase<InstallerModel> {

    public InstallerGUIView(InstallerModel model) {
        super(model);
    }

    @Override
    public void refresh() {

    }

    @Override
    public IView build() {
        return null;
    }

    @Override
    public void launch() {

    }
}
