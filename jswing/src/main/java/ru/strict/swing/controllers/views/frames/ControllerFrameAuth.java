package ru.strict.swing.controllers.views.frames;

import ru.strict.swing.views.frames.FrameAuth;
import ru.strict.utils.UtilLogger;
import ru.strict.swing.controllers.views.dialogs.ControllerDialogReg;
/*import ru.strict.db.StrictControllerDatabase;
import ru.strict.db.StrictUtilsDatabase;
import ru.strict.jdbc.common.StrictEnumDbTypes;*/
import ru.strict.swing.models.frames.ModelFrameAuth;
import ru.strict.swing.models.dialogs.ModelDialogReg;
import ru.strict.swing.views.dialogs.DialogReg;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;

public class ControllerFrameAuth<O extends FrameAuth, M extends ModelFrameAuth>
        extends ControllerFrameDefault<O, M> {

    public ControllerFrameAuth(O object, M model) {
        super(object, model);
    }

    @Override
    public O build() {
        // Событие регистрации
        getModel().setActionReg(this::actionReg);
        // Событие авторизации
        getModel().setActionSignin(this::actionSignin);
        // Событие авторизации под логином Windows
        getModel().setActionSigninWindows(this::actionSigninWindows);
        return super.build();
    }

    /**
     * Событие регистрации
     * @param event
     */
    public void actionReg(ActionEvent event){
        UtilLogger.info(ControllerFrameAuth.class, "actionReg - started");
        ModelDialogReg modelDialogReg = new ModelDialogReg();
        modelDialogReg.setTitle("Регистрация");
        modelDialogReg.setWidth(Toolkit.getDefaultToolkit().getScreenSize().width/3);
        modelDialogReg.setHeight((int)((double)Toolkit.getDefaultToolkit().getScreenSize().height/1.5));

        ControllerDialogReg controllerDialogReg = new ControllerDialogReg(
                new DialogReg(),
                modelDialogReg
        );
        controllerDialogReg.build();
        controllerDialogReg.launch();
        UtilLogger.info(ControllerFrameAuth.class, "actionReg - finished");
    }

    /**
     * Событие авторизации
     * @param event
     */
    //TODO: Доступ к ресурсу базы данных и его составляющих элементов должен задавться через модель
    public void actionSignin(ActionEvent event){
        /*
        sLogger.info("ControllerFrameAuth.actionSignin - started");
        Connection connection = StrictUtilsDatabase.createConnection(StrictUtilsProperties.getValue("resources/properties/dbsettings.properties", "dbname")
                , StrictEnumDbTypes.POSTGRESQL
                , StrictUtilsProperties.getValue("resources/properties/dbsettings.properties", "dbuser")
                , StrictUtilsProperties.getValue("resources/properties/dbsettings.properties", "dbpassword"));
        StrictControllerDatabase controllerDatabase = new StrictControllerDatabase(connection, new StrictModelDatabase());

        Component[] components = ((JComponent)event.getSource()).getParent().getComponents();

        /*
        TODO: В контроллерах базы данных нет реализации readList, т.к. надо разобраться с StrictDbWheres
        StrictEntityUser user = (StrictEntityUser)controllerDatabase.addRepository("users", new StrictRepositoryUsers(connection)).readList().stream()
            .filter(e -> ((StrictEntityUser)e).getUsername().trim()
                    .equals(String.valueOf(StrictUtilsFrame.getValueComponentIgnoreCase(components, "Логин:"))))
            .filter(e -> ((StrictEntityUser)e).getPasswordmd5().trim()
                    .equals(StrictUtilsHash.hashMd5Apache(
                            String.valueOf(StrictUtilsFrame.getValueComponentIgnoreCase(components, "Пароль:")))))
            .findFirst().orElse(null);

        try {
            ((StrictRepositoryBase)controllerDatabase.getRepositorys().get("users")).getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(user==null){
            JOptionPane.showMessageDialog(getObject(), "Введенны неверные данные", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }

        getModel().setUser(user);*/
    }

    /**
     * Событие авторизации под логином  Windows
     * @param event
     */
    public void actionSigninWindows(ActionEvent event){
        /*
        sLogger.info("ControllerFrameAuth.actionSigninWindows - started");
        Connection connection = StrictUtilsDatabase.createConnection(StrictUtilsProperties.getValue("resources/properties/dbsettings.properties", "dbname")
                , StrictEnumDbTypes.POSTGRESQL
                , StrictUtilsProperties.getValue("resources/properties/dbsettings.properties", "dbuser")
                , StrictUtilsProperties.getValue("resources/properties/dbsettings.properties", "dbpassword"));
        StrictControllerDatabase controllerDatabase = new StrictControllerDatabase(connection, new StrictModelDatabase());

        /*
        TODO: В контроллерах базы данных нет реализации readList, т.к. надо разобраться с StrictDbWheres
        StrictEntityUser user = (StrictEntityUser)controllerDatabase.addRepository("users", new StrictRepositoryUsers(connection))
                .readList().stream()
                .filter(e -> ((StrictEntityUser)e).getUsername().trim().equals(System.getProperty("user.name")))
                .findFirst().orElse(null);

        if(user==null) {
            user = new StrictEntityUser(System.getProperty("user.name"), "", null);
            ((StrictRepositoryUsers)controllerDatabase.getRepositorys().get("users")).create(user);
            user = ((StrictRepositoryUsers)controllerDatabase.getRepositorys().get("users")).readList().stream()
                    .filter(e -> e.getUsername().trim().equals(System.getProperty("user.name")))
                    .findFirst().orElse(null);
            getModel().setUser(user);
        }else{
            user = ((StrictRepositoryUsers)controllerDatabase.getRepositorys().get("users")).readList().stream()
                    .filter(e -> e.getUsername().trim().equals(System.getProperty("user.name")))
                    .findFirst().orElse(null);
            getModel().setUser(user);
        }

        try {
            ((StrictRepositoryBase)controllerDatabase.getRepositorys().get("users")).getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public O getObject() {
        return super.getObject();
    }

    @Override
    public void setObject(O object) {
        super.setObject(object);
    }

    @Override
    public M getModel() {
        return super.getModel();
    }

    @Override
    public void setModel(M model) {
        super.setModel(model);
    }

    @Override
    public void destroy(){
        System.exit(-1);
    }
}