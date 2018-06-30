package ru.strict.swing.views.frames;

import ru.strict.swing.models.frames.ModelFrameAuth;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * Фрейм авторизации
 */
public class FrameAuth<M extends ModelFrameAuth> extends FrameDefault<M> {

    @Override
    public FrameAuth build(M model) {
        super.build(model);
        this.setLocationRelativeTo(null);

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints cons = new GridBagConstraints();
        getPanelContent().setLayout(layout);

        cons.gridy = GridBagConstraints.RELATIVE;
        cons.gridwidth = GridBagConstraints.REMAINDER;
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.insets = new Insets(3, 5, 3, 5);
        cons.weightx = 1;

        JLabel labLogin = new JLabel("Логин:");
        labLogin.setFont(model.getFontText());
        JTextField tfLogin = new JTextField();
        tfLogin.setFont(model.getFontText());
        JLabel labPassword = new JLabel("Пароль:");
        labPassword.setFont(model.getFontText());
        JPasswordField tfPassword = new JPasswordField();
        tfPassword.setFont(model.getFontTextPassword());
        JButton butSignin = new JButton("Авторизироваться");
        butSignin.setFont(model.getFontText());
        butSignin.addActionListener(model.getActionSignin());

        layout.setConstraints(labLogin, cons);
       getPanelContent().add(labLogin);
        layout.setConstraints(tfLogin, cons);
        getPanelContent().add(tfLogin);
        layout.setConstraints(labPassword, cons);
        getPanelContent().add(labPassword);
        layout.setConstraints(tfPassword, cons);
        getPanelContent().add(tfPassword);
        layout.setConstraints(butSignin, cons);
        getPanelContent().add(butSignin);
        // Если отображение авторизации с использованием windows разрешено, до добавлям данную кнопку
        if(model.isVisibleButWindows()) {
            JButton butSigninWindows = new JButton("Авторизироваться под логином Windows");
            butSigninWindows.setFont(model.getFontText());
            butSigninWindows.addActionListener(model.getActionSigninWindows());
            layout.setConstraints(butSigninWindows, cons);
            getPanelContent().add(butSigninWindows);
        }
        // Если отображение регистрации разрешено, до добавлям данную кнопку
        if(model.isVisibleButReg()) {
            JButton butReg = new JButton("Регистрация");
            butReg.setFont(model.getFontText());
            butReg.addActionListener(model.getActionReg());
            layout.setConstraints(butReg, cons);
            getPanelContent().add(butReg);
        }

        return this;
    }

    @Override
    public void setModel(M model) {
        super.setModel(model);
    }

    @Override
    public M getModel() {
        return super.getModel();
    }


}
