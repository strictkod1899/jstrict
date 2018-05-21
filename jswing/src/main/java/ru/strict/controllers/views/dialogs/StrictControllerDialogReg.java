package ru.strict.controllers.views.dialogs;

/*import ru.strict.db.StrictControllerDatabase;
import ru.strict.db.StrictUtilsDatabase;
import ru.strict.jdbc.entities.StrictEntityUser;
import ru.strict.jdbc.repositories.StrictRepositoryBase;
import ru.strict.jdbc.repositories.StrictRepositoryUsers;
import ru.strict.jdbc.common.StrictEnumDbTypes;*/
import ru.strict.models.dialogs.StrictModelDialogReg;
import ru.strict.views.dialogs.StrictDialogReg;

import java.awt.event.ActionEvent;

public class StrictControllerDialogReg<O extends StrictDialogReg, M extends StrictModelDialogReg>
        extends StrictControllerDialogDefault<O, M>{

    public StrictControllerDialogReg(O object, M model) {
        super(object, model);
    }

    @Override
    public O build() {
        // Событие регистрации
        getModel().setActionReg(this::actionReg);
        // Событие шага назада
        getModel().setActionBack(this::actionBack);
        return super.build();
    }

    /**
     * Событие регистрации
     * @param event
     */
    private void actionReg(ActionEvent event){
        /*
        Connection connection = StrictUtilsDatabase.createConnection(StrictUtilsProperties.getValue("resources/properties/dbsettings.properties", "dbname")
                , StrictEnumDbTypes.POSTGRESQL
                , StrictUtilsProperties.getValue("resources/properties/dbsettings.properties", "dbuser")
                , StrictUtilsProperties.getValue("resources/properties/dbsettings.properties", "dbpassword"));
        StrictControllerDatabase controllerDatabase = new StrictControllerDatabase(connection, new StrictModelDatabase());
        Component[] components = ((JComponent)event.getSource()).getParent().getComponents();

        // Если введеные пароли совпадают
        if(String.valueOf(StrictUtilsFrame.getValueComponentIgnoreCase(components, "Пароль:")).equals(
                String.valueOf(StrictUtilsFrame.getValueComponentIgnoreCase(components, "Повторите пароль:")))) {
            // Сохраняем данные в базу данных
            StrictEntityUser user = new StrictEntityUser(
                    String.valueOf(StrictUtilsFrame.getValueComponentIgnoreCase(components, "Логин:")),
                    StrictUtilsHash.hashMd5Apache(String.valueOf(StrictUtilsFrame.getValueComponentIgnoreCase(components, "Пароль:"))),
                    null);

            controllerDatabase.addRepository("users", new StrictRepositoryUsers(connection)).create(user);
            try {
                ((StrictRepositoryBase)controllerDatabase.getRepositorys().get("users")).getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(getObject(), "Регистрация прошла успешно", "Оповещение", JOptionPane.INFORMATION_MESSAGE);
            getObject().setVisible(false);
        }else{
            JOptionPane.showMessageDialog(getObject(), "Введенные пароли не совпадают", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
        */
    }

    /**
     * Событие отмены регистрации
     * @param event
     */
    private void actionBack(ActionEvent event){
        getObject().setVisible(false);
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
}
