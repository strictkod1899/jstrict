package ru.strict.swing.views.components;

import ru.strict.utils.UtilLogger;
import ru.strict.swing.models.panels.StrictModelPanelState;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * Класс панели состояния, расположенной вверху окна для закрытия, сворачивания и изменения размера окна программы
 */
public class StrictPanelState<M extends StrictModelPanelState> extends StrictPanel<M> {

    private GridBagLayout layout;
    private GridBagConstraints constraints;
    private StrictMenuDefault strictMenu;

    @Override
    public StrictPanelState build(M model) {
        super.build(model);
        UtilLogger.info(StrictPanelState.class, "build - started");
        layout = new GridBagLayout();
        constraints = new GridBagConstraints();

        // Установка диспетчера компоновки
        setLayout(layout);
        // Установка цвета фона
        setBackground(model.getBackground());

        constraints.gridx = 0;
        constraints.weightx = 1;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.WEST;
        layout.setConstraints(model.getPanelLeft(), constraints);
        add(model.getPanelLeft());
        constraints.gridx = 1;
        layout.setConstraints(model.getPanelRight(), constraints);
        add(model.getPanelRight());
        strictMenu = new StrictMenuDefault(this, layout, constraints);
        UtilLogger.info(StrictPanelState.class, "build - finished");
        return this;
    }

    /**
     * Добавление заголовков разделов меню
     * @param menuTitles Заголовки добавляемых разделов меню
     * @return
     */
    public JMenuBar addMenuTitles(String...menuTitles){
        return strictMenu.addMenus(menuTitles);
    }

    /**
     * Добавление разделов меню
     * @param menus Добавляемые разделы меню
     * @return
     */
    public JMenuBar addMenus(JMenu...menus){
        return strictMenu.addMenus(menus);
    }

    /**
     * Добавление пунктов меню к разделу
     * @param menu Раздел меню, в который добавляются пункты
     * @param items Добавляемые пункты меню
     * @return
     */
    public JMenuBar addMenu(JMenu menu, JMenuItem...items){
        return strictMenu.addMenu(menu, items);
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
