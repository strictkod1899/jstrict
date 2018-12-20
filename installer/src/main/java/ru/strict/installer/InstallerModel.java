package ru.strict.installer;

import ru.strict.patterns.mvc.models.ModelBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class InstallerModel extends ModelBase<InstallerStage> {

    private String welcomeText;
    private boolean needAcceptLicense;
    private boolean acceptLicense;
    private List<File> installingFiles;
    private boolean addAppToSetup;

    public InstallerModel(){
        needAcceptLicense = false;
        acceptLicense = false;
        welcomeText = "";
        installingFiles = new ArrayList<>();
        addAppToSetup = true;
    }

    public String getWelcomeText() {
        return welcomeText;
    }

    public void setWelcomeText(String welcomeText) {
        this.welcomeText = welcomeText;
    }

    public boolean isAcceptLicense() {
        return acceptLicense;
    }

    public void setAcceptLicense(boolean acceptLicense) {
        this.acceptLicense = acceptLicense;
    }

    public boolean isNeedAcceptLicense() {
        return needAcceptLicense;
    }

    public void setNeedAcceptLicense(boolean needAcceptLicense) {
        this.needAcceptLicense = needAcceptLicense;
    }

    public List<File> getInstallingFiles() {
        return installingFiles;
    }

    public void addInstallingFile(File file) throws FileNotFoundException {
        if(file != null){
            if(file.exists()){
                installingFiles.add(file);
            } else {
                throw new FileNotFoundException(String.format("File [%s] for installing not found", file.getAbsolutePath()));
            }
        }
    }

    public boolean isAddAppToSetup() {
        return addAppToSetup;
    }

    public void setAddAppToSetup(boolean addAppToSetup) {
        this.addAppToSetup = addAppToSetup;
    }
}
