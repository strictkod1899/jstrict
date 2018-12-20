package ru.strict.installer;

import ru.strict.patterns.mvc.views.IView;

public abstract class GUIInstaller extends StepByStepInstaller {

    private IView view;
    private InstallerModel model;

    public GUIInstaller(IView view, InstallerModel model) {
        this.view = view;
        this.model = model;
    }

    protected abstract void installImpl() throws Exception;

    @Override
    protected void welcome() throws Exception{
        model.setStage(InstallerStage.WELCOME);
        view.build();
        view.launch();
    }

    @Override
    protected void acceptLicense() throws Exception{
        if(model.isNeedAcceptLicense()) {
            model.setStage(InstallerStage.ACCEPT_LICENSE);
            view.refresh();
        }
    }

    @Override
    protected void setInstallingFolder() throws Exception{
        if(!model.isNeedAcceptLicense() || (model.isNeedAcceptLicense() && model.isAcceptLicense())){
            model.setStage(InstallerStage.SET_INSTALLING_FOLDER);
            view.refresh();
        } else {
            throw new AcceptLicenseException("License not accepted");
        }
    }

    @Override
    protected void failInstall(Exception ex) {
        model.setStage(InstallerStage.FAIL);
        view.refresh();
    }

    @Override
    protected void installerProcess() throws Exception {
        model.setStage(InstallerStage.INSTALL);
        view.refresh();
        installImpl();
    }

    protected IView getView() {
        return view;
    }

    protected InstallerModel getModel(){
        return model;
    }
}
