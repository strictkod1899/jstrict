package ru.strict.swing.controllers.panels;

import io.jsonwebtoken.lang.Collections;
import ru.strict.swing.utils.MouseActionChangeBackground;
import ru.strict.swing.views.IForm;
import ru.strict.swing.views.components.PanelState;
import ru.strict.utils.UtilLogger;
import ru.strict.utils.UtilResources;
import ru.strict.swing.models.panels.ModelPanelState;
import ru.strict.swing.enums.Colors;
import ru.strict.swing.enums.Icons;
import ru.strict.swing.utils.UtilSwing;
import ru.strict.utils.UtilImage;
import ru.strict.validates.ValidateBaseValue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Контроллер для управления панелью состояния, находящейся в верху окна
 */
public class ControllerPanelState<O extends PanelState, M extends ModelPanelState>
        extends ControllerPanel<O, M> {

    public ControllerPanelState(O object, M model) {
        super(object, model);
    }

    private static final double RATIO_COMP = 0.7;
    private static final double RATIO_GAP_INNER = 0.6;
    private static final int MIN_SIZE_ICON = 10;
    private static final int MAX_SIZE_ICON = 15;

    @Override
    public O build() {
        UtilLogger.info(ControllerPanelState.class, "build - started");
        // Получение размера значков и отступов на верхней панели относительно установленного соотношения
        int sizeButton = getSizeIcon();
        int sizeIcon = (int)(sizeButton*1.7);
        int sizeFont = 12;
        int vgap = getVgap();
        int hgap = vgap * 2;

        Font font =  new Font(Font.DIALOG_INPUT, Font.PLAIN, sizeFont);

        // Получаем массив кнопок (Свернуть, Изменить размер)
        final JPanel[] buttonsControl = UtilSwing.createButtonsImage(hgap, vgap,
                Colors.BACKGROUND_COMP.getColor(), Colors.BACKGROUND_SELECT.getColor(), null,
                UtilImage.resizeImage(UtilResources.getResourceAsFileTemp(Icons.TURN.getPath()).getAbsolutePath()
                        , sizeButton, sizeButton),
                UtilImage.resizeImage(UtilResources.getResourceAsFileTemp(Icons.CHANGE_SIZE.getPath()).getAbsolutePath()
                        , sizeButton, sizeButton),
                UtilImage.resizeImage(UtilResources.getResourceAsFileTemp(Icons.CHANGE_SIZE_FULL.getPath()).getAbsolutePath()
                        , sizeButton, sizeButton));

        Icon icon = null;
        if(getModel().getPathIcon()!=null && ValidateBaseValue.isNotEmptyOrNull(getModel().getPathIcon())) {
            icon = UtilImage.resizeImage(getModel().getPathIcon(), sizeIcon, sizeIcon);
        }

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
                event.getComponent().setBackground(Colors.BACKGROUND_COMP.getColor());
                ((JFrame)getModel().getParent()).setState(JFrame.ICONIFIED);
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
                event.getComponent().setBackground(Colors.BACKGROUND_COMP.getColor());
                getModel().getParent().setSize((int)((double)((IForm)getObject().getParent().getParent().getParent().getParent()).getModel().getWidth()/1.3),
                        (int)((double)((IForm)getObject().getParent().getParent().getParent().getParent()).getModel().getHeight()/1.3));
                buttonsControl[2].setVisible(true);
                buttonsControl[1].setVisible(false);
                getModel().getParent().validate();
                getModel().getParent().repaint();
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
                event.getComponent().setBackground(Colors.BACKGROUND_COMP.getColor());
                getModel().getParent().setSize(((IForm)getObject().getParent().getParent().getParent().getParent()).getModel().getWidth()
                        , ((IForm)getObject().getParent().getParent().getParent().getParent()).getModel().getHeight());
                getModel().getParent().setLocation(0, 0);
                buttonsControl[2].setVisible(false);
                buttonsControl[1].setVisible(true);
                getModel().getParent().validate();
                getModel().getParent().repaint();
            }
        });

        buttonsControl[2].setVisible(false);

        // Кнпока "Закрыть окно"
        JPanel panelExit = UtilSwing.createButtonImage(hgap, vgap,
                Colors.BACKGROUND_COMP.getColor(), Colors.BACKGROUND_SELECT_FIRE.getColor(),
                UtilImage.resizeImage(UtilResources.getResourceAsFileTemp(Icons.CLOSE.getPath()).getAbsolutePath()
                        , sizeButton, sizeButton), new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent event) {
                        setButtonPressedValue(event, true);
                        event.getComponent().setBackground(Colors.BACKGROUND_SELECT.getColor());
                    }

                    @Override
                    public void mouseReleased(MouseEvent event) {
                        setButtonPressedValue(event, false);
                        event.getComponent().setBackground(Colors.BACKGROUND_COMP.getColor());
                        ((IForm)getObject().getParent().getParent().getParent().getParent()).destroy();
                        getModel().getActionExit().actionPerformed(null);
                    }
                });


        List<JPanel> buttonsUsed = new ArrayList<>(4);
        // Отключаем ввидимость кнопок на основе значений из модели
        if(!getModel().isVisibleTurn()) {
            buttonsControl[0].setVisible(false);
        }else{
            if(getModel().getParent() instanceof JFrame) {
                buttonsUsed.add(buttonsControl[0]);
            }
        }

        if(!getModel().isVisibleChangeSize()) {
            buttonsControl[1].setVisible(false);
        }else{
            buttonsUsed.add(buttonsControl[1]);
            buttonsUsed.add(buttonsControl[2]);
        }

        if(!getModel().isVisibleExit()) {
            panelExit.setVisible(false);
        }else{
            buttonsUsed.add(panelExit);
        }

        UtilLogger.info(ControllerPanelState.class, "build - finished");

        return (O) build(getModel().getTitle(), getModel().isMoveForm(), font, vgap, Colors.BACKGROUND_COMP.getColor(),
                icon,
                getModel().getParent(), buttonsUsed.toArray(new JPanel[buttonsUsed.size()]));
    }

    /**
     * Создать панел сосотояния (С пользовательским заголовком и кнопками управления)
     * @param title Заголовок
     * @param font Шрифт заголовка
     * @param hgap Отступы между компонентами по горизонтали
     * @param background Цвет панели
     * @param parent JFrame на котором расположена панель
     * @param arrPanel Набор кнопок на панели
     * @return
     */
    private PanelState build(String title, boolean isMoveForm, Font font, int hgap, Color background, Icon icon, Window parent, JPanel...arrPanel){
        UtilLogger.info(ControllerPanelState.class, "build (return PanelState) - started");
        JPanel panelLeft = createPanelTitle(title, hgap, background, font, icon);
        JPanel panelRight = createButControl(background, arrPanel);
        if(isMoveForm) {
            MouseMotionMoved mouse = new MouseMotionMoved(parent);
            panelLeft.addMouseListener(mouse);
            panelRight.addMouseListener(mouse);
            panelLeft.addMouseMotionListener(mouse);
            panelRight.addMouseMotionListener(mouse);
        }
        // Инициализируем модель и объект контроллера
        getModel().setPanelLeft(panelLeft);
        getModel().setPanelRight(panelRight);
        getModel().setBackground(background);
        UtilLogger.info(ControllerPanelState.class, "build (return PanelState) - finished");
        return super.build();
    }

    /**
     * Создать область заголовка
     * @param title Текст заголовка
     * @param hgap Отступ по горизонтали
     * @param color Цвет области с заголовком
     * @param font Шрифт текста
     * @return
     */
    private JPanel createPanelTitle(String title, int hgap, Color color, Font font, Icon icon){
        UtilLogger.info(ControllerPanelState.class, "createPanelTitle - started");
        JPanel panelTitle = new JPanel();

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = new Insets(5, 8, 5, 8);
        constraints.weightx = 1;

        panelTitle.setLayout(layout);
        panelTitle.setBackground(color);
        // Заголовок
        JLabel labTitle = new JLabel(title);
        if(icon!=null) {
            labTitle.setIcon(icon);
        }
        labTitle.setForeground(getModel().getForeground());
        if(font!=null) {
            labTitle.setFont(font);
        }
        panelTitle.add(labTitle);
        layout.setConstraints(labTitle, constraints);
        UtilLogger.info(ControllerPanelState.class, "createPanelTitle - finished");
        return panelTitle;
    }

    /**
     * Создать область с кнопками управления
     * @param color Цвет области
     * @param components Добавляемые компоненты
     * @return
     */
    private JPanel createButControl(Color color, JComponent...components){
        UtilLogger.info(ControllerPanelState.class, "createButControl - started");
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        panelButtons.setBackground(color);
        for(int i=0; i<components.length; i++) {
            panelButtons.add(components[i]);
        }
        UtilLogger.info(ControllerPanelState.class, "createButControl - finished");
        return panelButtons;
    }

    /**
     * Событие перемещения окна
     */
    private class MouseMotionMoved extends MouseAdapter implements MouseMotionListener{

        private final Window parent;
        private Point position;

        MouseMotionMoved(final Window parent){
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

    private int getSizeIcon(){
        int sizeIcon = UtilSwing.calcSizeByRatio(Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height,
                RATIO_COMP);
        if(sizeIcon> MAX_SIZE_ICON)
            sizeIcon = MAX_SIZE_ICON;
        else if(sizeIcon< MIN_SIZE_ICON)
            sizeIcon = MIN_SIZE_ICON;
        return sizeIcon;
    }

    private int getVgap(){
        return UtilSwing.calcSizeByRatio(Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height,
                RATIO_GAP_INNER);
    }

    private void setButtonPressedValue(MouseEvent event, boolean value){
        MouseListener[] listeners = ((JPanel)event.getSource()).getMouseListeners();
        for(MouseListener listener : listeners){
            if(listener instanceof MouseActionChangeBackground){
                ((MouseActionChangeBackground)listener).setButPressed(value);
            }
        }
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
