package ru.strict.swing.views.components;

import javax.swing.*;
import java.awt.*;

/**
 * Класс предоставляет методы для управления меню определнного окна
 */
public class MenuDefault {

    /**
     * Окно на которое добавляется меню
     */
    private PanelState panelState;

    /**
     * Добавляемое меню
     */
    private JMenuBar menuBar;

    GridBagLayout layout;
    GridBagConstraints constraints;

    public MenuDefault(PanelState panelState, GridBagLayout layout, GridBagConstraints constraints) {
        this.panelState = panelState;
        this.layout = layout;
        this.constraints = constraints;
    }

    /**
     * Добавление заголовков разделов меню
     * @param menuTitles Заголовки добавляемых разделов меню
     * @return
     */
    public JMenuBar addMenus(String...menuTitles){
        checkExistMenuBar();

        for(String title:menuTitles) {
            JMenu menu = new JMenu(title);
            menuBar.add(menu);
        }
        return menuBar;
    }

    /**
     * Добавление разделов меню
     * @param menus Добавляемые разделы меню
     * @return
     */
    public JMenuBar addMenus(JMenu...menus){
        checkExistMenuBar();

        for(JMenu menu:menus)
            menuBar.add(menu);
        return menuBar;
    }

    /**
     * Добавление раздела к меню с перечисленными пунктами
     * @param menu Раздел меню, в который добавляются пункты
     * @param items Добавляемые пункты меню
     * @return
     */
    public JMenuBar addMenu(JMenu menu, JMenuItem...items){
        for(JMenuItem item:items)
            menu.add(item);

        checkExistMenuBar();

        menuBar.add(menu);
        return menuBar;
    }

    /**
     * Проверка существования меню бара и если его нет то оно создается
     */
    private void checkExistMenuBar(){
        if(menuBar==null)
            setJMenuBar(new JMenuBar());
    }

    /**
     * Установить меню
     * @param menuBar Экземпляр JMenuBar
     */
    public void setJMenuBar(JMenuBar menuBar){
        constraints.gridx=0;
        constraints.gridy=1;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        if(this.menuBar==null) {
            this.menuBar = menuBar;
            layout.setConstraints(this.menuBar, constraints);
            panelState.add(this.menuBar);
        }else
            this.menuBar = menuBar;
    }
}
