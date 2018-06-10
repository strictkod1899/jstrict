package ru.strict.swing.models.frames;

//import ru.strict.jdbc.entities.StrictEntityUser;
import ru.strict.swing.enums.StrictEnumFonts;
import ru.strict.utils.UtilFrame;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Модель формы диалога авторизации
 */
public class StrictModelFrameAuth extends StrictModelFrameDefault{

    //private StrictEntityUser user;

    private Font fontTextPassword;

    private boolean visibleButReg;

    private boolean visibleButWindows;

    private ActionListener actionSignin;

    private ActionListener actionSigninWindows;

    private ActionListener actionReg;

    private void initDefault(){
        //this.user = null;
        this.fontTextPassword = new Font(StrictEnumFonts.BASE.getFontName(), Font.PLAIN,
                UtilFrame.calcSizeByRatio(Toolkit.getDefaultToolkit().getScreenSize().width,
                        Toolkit.getDefaultToolkit().getScreenSize().height,
                        RATIO_FONT_TEXT));
        this.visibleButReg = true;
        this.visibleButWindows = false;
        this.actionSignin = null;
        this.actionSigninWindows = null;
        this.actionReg = null;
    }

    public StrictModelFrameAuth() {
        super();
        initDefault();
    }

    /**
     * Авторизированный пользователь
     */
   /* public StrictEntityUser getUser() {
        return user;
    }*/

    /**
     * Авторизированный пользователь
     */
    /*public void setUser(StrictEntityUser user) {
        this.user = user;
    }*/

    /**
     * Шрифт текста поля для пароля
     */
    public Font getFontTextPassword() {
        return fontTextPassword;
    }

    /**
     * Шрифт текста поля для пароля
     */
    public void setFontTextPassword(Font fontTextPassword) {
        this.fontTextPassword = fontTextPassword;
    }

    /**
     * Отображение кнопки регистрации
     */
    public boolean isVisibleButReg() {
        return visibleButReg;
    }

    /**
     * Отображение кнопки регистрации
     */
    public void setVisibleButReg(boolean visibleButReg) {
        this.visibleButReg = visibleButReg;
    }

    /**
     * Отображение кнопки авторизация под логином Windows
     */
    public boolean isVisibleButWindows() {
        return visibleButWindows;
    }

    /**
     * Отображение кнопки авторизация под логином Windows
     */
    public void setVisibleButWindows(boolean visibleButWindows) {
        this.visibleButWindows = visibleButWindows;
    }

    /**
     * Событие авторизации
     */
    public ActionListener getActionSignin() {
        return actionSignin;
    }

    /**
     * Событие авторизации
     */
    public void setActionSignin(ActionListener actionSignin) {
        this.actionSignin = actionSignin;
    }

    /**
     * Событие авторизации под логином Windows
     */
    public ActionListener getActionSigninWindows() {
        return actionSigninWindows;
    }

    /**
     * Событие авторизации под логином Windows
     */
    public void setActionSigninWindows(ActionListener actionSigninWindows) {
        this.actionSigninWindows = actionSigninWindows;
    }

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
}
