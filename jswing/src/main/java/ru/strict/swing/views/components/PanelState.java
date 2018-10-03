package ru.strict.swing.views.components;

import ru.strict.swing.enums.Colors;
import ru.strict.swing.enums.Icons;
import ru.strict.swing.utils.MouseActionChangeBackground;
import ru.strict.swing.utils.UtilSwing;
import ru.strict.swing.views.IForm;
import ru.strict.utils.UtilImage;
import ru.strict.utils.UtilResources;
import ru.strict.validates.ValidateBaseValue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс панели состояния, расположенной вверху окна для закрытия, сворачивания и изменения размера окна программы
 */
public class PanelState extends PanelBase {

    private boolean isMoveForm;
    private boolean isVisibleTurn;
    private boolean isVisibleChangeSize;
    private boolean isVisibleExit;
    private int hGap;
    private int vGap;
    private String title;
    private int buttonSize;
    private int iconSize;
    private String iconPath;
    private ActionListener actionExit;
    private Font font;

    private GridBagLayout layout;
    private GridBagConstraints constraints;
    private MenuDefault strictMenu;

    public PanelState() {
        super();
        buttonSize = 12;
        iconSize = (int)(buttonSize * 1.5);
        vGap = 10;
        hGap = vGap * 2;
        iconPath = null;
        actionExit = null;
        isVisibleTurn = true;
        isVisibleChangeSize = true;
        isVisibleExit = true;
        isMoveForm = true;
        font = new Font(Font.DIALOG_INPUT, Font.PLAIN, 12);
        build();
    }

    @Override
    public void refresh() {
        super.refresh();
        build();
    }

    private void build() {
        layout = new GridBagLayout();
        constraints = new GridBagConstraints();
        // Установка диспетчера компоновки
        setLayout(layout);
        setBackground(Colors.BACKGROUND_COMP.getColor());

        constraints.gridx = 0;
        constraints.weightx = 1;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.WEST;

        JPanel panelLeft = createPanelLeft();
        JPanel panelRight = createPanelRight();

        if(isMoveForm) {
            MouseMotionMoved mouse = new MouseMotionMoved(getParent());
            panelLeft.addMouseListener(mouse);
            panelRight.addMouseListener(mouse);
            panelLeft.addMouseMotionListener(mouse);
            panelRight.addMouseMotionListener(mouse);
        }

        layout.setConstraints(panelLeft, constraints);
        add(panelLeft);
        constraints.gridx = 1;
        constraints.weightx = 0;
        layout.setConstraints(panelRight, constraints);
        add(panelRight);
        strictMenu = new MenuDefault(this, layout, constraints);

        panelLeft.setSize(new Dimension(0, getPreferredSize().height));
        panelLeft.setPreferredSize(new Dimension(0, getPreferredSize().height));
    }

    private JPanel createPanelLeft(){
        JPanel panelLeft = new JPanel();

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(5, 8, 5, 8);
        constraints.weightx = 1;

        panelLeft.setLayout(layout);
        panelLeft.setBackground(getBackground());
        // Заголовок
        JLabel labTitle = new JLabel(title);
        if(!ValidateBaseValue.isEmptyOrNull(iconPath)) {
            labTitle.setIcon(UtilImage.resizeImage(iconPath, iconSize, iconSize));
        }
        labTitle.setForeground(getForeground());
        if(font != null) {
            labTitle.setFont(font);
        }
        panelLeft.add(labTitle);
        layout.setConstraints(labTitle, constraints);
        return panelLeft;
    }

    private JPanel createPanelRight(){
        // Получаем массив кнопок (Свернуть, Изменить размер)
        final JPanel[] buttonsControl = UtilSwing.createButtonsImage(hGap, vGap,
                getBackground(), Colors.BACKGROUND_SELECT.getColor(), null,
                UtilImage.resizeImage(UtilResources.getResourceAsFileTemp(Icons.TURN.getPath()).getAbsolutePath(),
                        buttonSize, buttonSize),
                UtilImage.resizeImage(UtilResources.getResourceAsFileTemp(Icons.CHANGE_SIZE.getPath()).getAbsolutePath(),
                        buttonSize, buttonSize),
                UtilImage.resizeImage(UtilResources.getResourceAsFileTemp(Icons.CHANGE_SIZE_FULL.getPath()).getAbsolutePath(),
                        buttonSize, buttonSize));

        // Кнопка "Свернуть окно"
        buttonsControl[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                setButtonPressedValue(event, true);
                event.getComponent().setBackground(Colors.BACKGROUND_SELECT.getColor());
            }

            @Override
            public void mouseReleased(MouseEvent event) {
                setButtonPressedValue(event, false);
                event.getComponent().setBackground(getBackground());
                ((JFrame)getParent()).setState(JFrame.ICONIFIED);
            }
        });

        // Кнопка "Уменьшить размер окна"
        buttonsControl[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                setButtonPressedValue(event, true);
                event.getComponent().setBackground(Colors.BACKGROUND_SELECT.getColor());
            }

            @Override
            public void mouseReleased(MouseEvent event) {
                setButtonPressedValue(event, false);
                event.getComponent().setBackground(getBackground());
                getParent().setSize((int)((double) getParent().getParent().getParent().getParent().getPreferredSize().width/1.3),
                        (int)((double) getParent().getParent().getParent().getParent().getPreferredSize().height/1.3));
                buttonsControl[2].setVisible(true);
                buttonsControl[1].setVisible(false);
                getParent().validate();
                getParent().repaint();
            }
        });

        //Кнопка "Увеличить размер окна"
        buttonsControl[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                setButtonPressedValue(event, true);
                event.getComponent().setBackground(Colors.BACKGROUND_SELECT.getColor());
            }

            @Override
            public void mouseReleased(MouseEvent event) {
                setButtonPressedValue(event, false);
                event.getComponent().setBackground(getBackground());
                getParent().setSize(getParent().getParent().getParent().getParent().getPreferredSize().width
                        , getParent().getParent().getParent().getParent().getPreferredSize().height);
                getParent().setLocation(0, 0);
                buttonsControl[2].setVisible(false);
                buttonsControl[1].setVisible(true);
                getParent().validate();
                getParent().repaint();
            }
        });

        buttonsControl[2].setVisible(false);

        // Кнпока "Закрыть окно"
        JPanel panelExit = UtilSwing.createButtonImage(hGap, vGap,
                getBackground(), Colors.BACKGROUND_SELECT_FIRE.getColor(),
                UtilImage.resizeImage(UtilResources.getResourceAsFileTemp(Icons.CLOSE.getPath()).getAbsolutePath(),
                        buttonSize, buttonSize), new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent event) {
                        setButtonPressedValue(event, true);
                        event.getComponent().setBackground(Colors.BACKGROUND_SELECT.getColor());
                    }

                    @Override
                    public void mouseReleased(MouseEvent event) {
                        setButtonPressedValue(event, false);
                        event.getComponent().setBackground(getBackground());
                        ((IForm) getParent().getParent().getParent().getParent()).destroy();
                        actionExit.actionPerformed(null);
                    }
                });


        List<JPanel> buttonsUsed = new ArrayList<>(4);
        // Отключаем ввидимость кнопок на основе значений из модели
        if(!isVisibleTurn) {
            buttonsControl[0].setVisible(false);
        }else{
            if(getParent() instanceof JFrame) {
                buttonsUsed.add(buttonsControl[0]);
            }
        }

        if(!isVisibleChangeSize) {
            buttonsControl[1].setVisible(false);
        }else{
            buttonsUsed.add(buttonsControl[1]);
            buttonsUsed.add(buttonsControl[2]);
        }

        if(!isVisibleExit) {
            panelExit.setVisible(false);
        }else{
            buttonsUsed.add(panelExit);
        }

        JPanel panelRight = new JPanel();
        panelRight.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panelRight.setBackground(getBackground());
        for(JComponent component : buttonsUsed) {
            panelRight.add(component);
        }
        return panelRight;
    }

    /**
     * Событие перемещения окна
     */
    private class MouseMotionMoved extends MouseAdapter implements MouseMotionListener {

        private final Container parent;
        private Point position;

        MouseMotionMoved(final Container parent){
            this.parent = parent;
        }

        public void mouseDragged(MouseEvent e) {
            // Если ширина и высота фрейма равны максимуму, тогда перемещать окно запрещено
            if(parent.getWidth()!=Toolkit.getDefaultToolkit().getScreenSize().width
                    && parent.getHeight()!=Toolkit.getDefaultToolkit().getScreenSize().height) {
                int thisX = parent.getLocation().x;
                int thisY = parent.getLocation().y;
                int xMoved = (thisX + e.getX()) - (thisX + position.x);
                int yMoved = (thisY + e.getY()) - (thisY + position.y);
                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                parent.setLocation(X, Y);
            }
        }

        public void mousePressed(MouseEvent e) {
            position = e.getPoint();
            parent.getComponentAt(position);
        }
    }

    private void setButtonPressedValue(MouseEvent event, boolean value){
        MouseListener[] listeners = ((JPanel)event.getSource()).getMouseListeners();
        for(MouseListener listener : listeners){
            if(listener instanceof MouseActionChangeBackground){
                ((MouseActionChangeBackground)listener).setButPressed(value);
            }
        }
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

    public boolean isMoveForm() {
        return isMoveForm;
    }

    public void setMoveForm(boolean moveForm) {
        isMoveForm = moveForm;
    }

    public boolean isVisibleTurn() {
        return isVisibleTurn;
    }

    public void setVisibleTurn(boolean visibleTurn) {
        isVisibleTurn = visibleTurn;
    }

    public boolean isVisibleChangeSize() {
        return isVisibleChangeSize;
    }

    public void setVisibleChangeSize(boolean visibleChangeSize) {
        isVisibleChangeSize = visibleChangeSize;
    }

    public boolean isVisibleExit() {
        return isVisibleExit;
    }

    public void setVisibleExit(boolean visibleExit) {
        isVisibleExit = visibleExit;
    }

    public int gethGap() {
        return hGap;
    }

    public void sethGap(int hGap) {
        this.hGap = hGap;
    }

    public int getvGap() {
        return vGap;
    }

    public void setvGap(int vGap) {
        this.vGap = vGap;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getButtonSize() {
        return buttonSize;
    }

    public void setButtonSize(int buttonSize) {
        this.buttonSize = buttonSize;
    }

    public int getIconSize() {
        return iconSize;
    }

    public void setIconSize(int iconSize) {
        this.iconSize = iconSize;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public ActionListener getActionExit() {
        return actionExit;
    }

    public void setActionExit(ActionListener actionExit) {
        this.actionExit = actionExit;
    }

    @Override
    public Font getFont() {
        return font;
    }

    @Override
    public void setFont(Font font) {
        this.font = font;
    }
}
