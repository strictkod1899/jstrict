package ru.strict.models.dialogs;

//import ru.strict.jdbc.entities.StrictEntityUser;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Модель формы диалога регистрации
 */
public class StrictModelDialogReg extends StrictModelDialogDefault{

    //private StrictEntityUser user;

    private ActionListener actionReg;

    private ActionListener actionBack;

    /**
     * Стандартная инициализация содержимого
     */
    private void initDefault(){
        //this.user = null;
        this.actionReg = null;
        this.actionBack = null;
    }

    public StrictModelDialogReg() {
        super();
        initDefault();
    }

    /**
     * Зарегистрированный пользователь
     */
    /*public StrictEntityUser getUser() {
        return user;
    }*/

    /**
     * Зарегистрированный пользователь
     */
    /*public void setUser(StrictEntityUser user) {
        this.user = user;
    }*/

    /**
     * Событие регистрации
     */
    public ActionListener getActionReg() {
        return actionReg;
    }

    /**
     * Событие регистрации
     */
    public void setActionReg(ActionListener actionReg) {
        this.actionReg = actionReg;
    }

    /**
     * Событие отмены регистрации
     */
    public ActionListener getActionBack() {
        return actionBack;
    }

    /**
     * Событие отмены регистрации
     */
    public void setActionBack(ActionListener actionBack) {
        this.actionBack = actionBack;
    }
}
