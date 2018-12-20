package ru.strict.installer;

public abstract class InstallerBase implements Installer {

    protected abstract void prepare() throws Exception;

    protected abstract void installerProcess() throws Exception;

    protected abstract void failInstall(Exception ex);

    @Override
    public final void install() {
        try {
            prepare();
            installerProcess();
        }catch (Exception ex){
            failInstall(ex);
        }
    }
}
