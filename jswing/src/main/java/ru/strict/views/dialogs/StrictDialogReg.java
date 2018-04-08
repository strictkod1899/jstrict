package ru.strict.views.dialogs;

import ru.strict.utils.StrictUtilLogger;
import ru.strict.models.dialogs.StrictModelDialogReg;

import javax.swing.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * Фрейм диалога регистрации
 */
public class StrictDialogReg<M extends StrictModelDialogReg> extends StrictDialogDefault<M>{

    @Override
    public StrictDialogReg build(M model) {
        super.build(model);
        StrictUtilLogger.info(StrictDialogReg.class, "build - started");
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
        JTextField tfPassword = new JTextField();
        tfPassword.setFont(model.getFontText());
        JLabel labPassword2 = new JLabel("Повторите пароль:");
        labPassword2.setFont(model.getFontText());
        JTextField tfPassword2 = new JTextField();
        tfPassword2.setFont(model.getFontText());

        JButton butReg = new JButton("Регистрация");
        butReg.setFont(model.getFontText());
        butReg.addActionListener(model.getActionReg());

        JButton butBack = new JButton("Вернуться назад");
        butBack.setFont(model.getFontText());
        butBack.addActionListener(model.getActionBack());

        layout.setConstraints(labLogin, cons);
        getPanelContent().add(labLogin);
        layout.setConstraints(tfLogin, cons);
        getPanelContent().add(tfLogin);
        layout.setConstraints(labPassword, cons);
        getPanelContent().add(labPassword);
        layout.setConstraints(tfPassword, cons);
        getPanelContent().add(tfPassword);
        layout.setConstraints(labPassword2, cons);
        getPanelContent().add(labPassword2);
        layout.setConstraints(tfPassword2, cons);
        getPanelContent().add(tfPassword2);

        layout.setConstraints(butReg, cons);
        getPanelContent().add(butReg);
        layout.setConstraints(butBack, cons);
        getPanelContent().add(butBack);
        StrictUtilLogger.info(StrictDialogReg.class, "build - finished");
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
