package ru.strict.installer;

public abstract class StepByStepInstaller extends InstallerBase {

    protected abstract void welcome() throws Exception;
    protected abstract void acceptLicense() throws Exception;
    protected abstract void setInstallingFolder() throws Exception;

    @Override
    protected final void prepare() throws Exception{
        welcome();
        acceptLicense();
        setInstallingFolder();
    }
}
