package ru.strict.utils;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.Iterator;

/**
 * Операции манипулирования формой
 */
public class UtilFrame {

    // Проверяем была ли нажата какая-нибудь кнопка или нет
    public static boolean isButPressed = false;

    /**
     * Получить значение компонента по содержанию предшествующего JLabel. Для JComboBox и JList возвращается выбранный индекс
     * @param components Проверяемые компоненты
     * @param tfCaption Значение JLabel (без учета регистра), после которого получаем значение компонента
     * @return
     */
    public static Object getValueComponentIgnoreCase(Component[] components, String tfCaption){
        for(int i=0; i<components.length; i++){
            Component component = components[i];
            if(component instanceof JLabel){
                if(((JLabel)component).getText().equalsIgnoreCase(tfCaption)){
                    Component componentResult = components[i+1];
                    if(componentResult instanceof JTextComponent)
                        return ((JTextComponent)componentResult).getText();
                    else if(componentResult instanceof JComboBox)
                        return ((JComboBox)componentResult).getSelectedIndex();
                    else if(componentResult instanceof JList)
                        return ((JList)componentResult).getSelectedIndex();
                    else if(componentResult instanceof JToggleButton)
                        return ((JToggleButton)componentResult).isSelected();
                    else if(componentResult instanceof JSpinner)
                        return ((JSpinner)componentResult).getValue();
                    else if(componentResult instanceof JSlider)
                        return ((JSlider)componentResult).getValue();
                    else
                        return null;
                }
            }
        }
        return null;
    }

    /**
     * Получить значение компонента по содержанию предшествующего JLabel. Для JComboBox и JList возвращается выбранный индекс
     * @param components Проверяемые компоненты
     * @param tfCaption Значение JLabel, после которого получаем значение компонента
     * @return
     */
    public static Object getValueComponent(Component[] components, String tfCaption){
        for(int i=0; i<components.length; i++){
            Component component = components[i];
            if(component instanceof JLabel){
                if(((JLabel)component).getText().equals(tfCaption)){
                    Component componentResult = components[i+1];
                    if(componentResult instanceof JTextComponent)
                        return ((JTextComponent)componentResult).getText();
                    else if(componentResult instanceof JComboBox)
                        return ((JComboBox)componentResult).getSelectedIndex();
                    else if(componentResult instanceof JList)
                        return ((JList)componentResult).getSelectedIndex();
                    else if(componentResult instanceof JToggleButton)
                        return ((JToggleButton)componentResult).isSelected();
                    else if(componentResult instanceof JSpinner)
                        return ((JSpinner)componentResult).getValue();
                    else if(componentResult instanceof JSlider)
                        return ((JSlider)componentResult).getValue();
                    else
                        return null;
                }
            }
        }
        return null;
    }

    /**
     * Получить компонент по содержанию предшествующего JLabel
     * @param components Проверяемые компоненты
     * @param tfCaption Значение JLabel (без учета регистра), после которого получаем значение компонента
     * @return
     */
    public static Component getComponentIgnoreCase(Component[] components, String tfCaption){
        for(int i=0; i<components.length; i++){
            Component component = components[i];
            if(component instanceof JLabel){
                if(((JLabel)component).getText().equalsIgnoreCase(tfCaption)){
                    Component componentResult = components[i+1];
                    if(componentResult instanceof Component)
                        return componentResult;
                    else
                        return null;
                }
            }
        }
        return null;
    }

    /**
     * Получить компонент по содержанию предшествующего JLabel
     * @param components Проверяемые компоненты
     * @param tfCaption Значение JLabel, после которого получаем значение компонента
     * @return
     */
    public static Component getComponent(Component[] components, String tfCaption){
        for(int i=0; i<components.length; i++){
            Component component = components[i];
            if(component instanceof JLabel){
                if(((JLabel)component).getText().equals(tfCaption)){
                    Component componentResult = components[i+1];
                    if(componentResult instanceof Component)
                        return componentResult;
                    else
                        return null;
                }
            }
        }
        return null;
    }

    /**
     * Создать кнопку в виде JLabel, которая меняет цвет фона при наведении мыши
     * @param title Текст кнопки
     * @param color Цвет кнопки
     * @param font Шрифт кнопки
     * @return
     */
    public static JLabel buildButLable(String title, Color color, Font font){
        JLabel lab = new JLabel(title);
        lab.setBackground(color);
        lab.setFont(font);
        lab.addMouseListener(new MouseActionForLabel(lab));
        return lab;
    }

    /**
     * Создать кнопку с надписью, которая меняет цвет при наведении курсора мыши
     * @param hgap Отступы от текста по горизонтали
     * @param vgap Отступы от текста по вертикали
     * @param baseColor Базовый (Начальный) цвет кнопки
     * @param selectColor Цвет кнопки при наведении курсора мыши
     * @param text Текст кнопки
     * @param font Шрифт текста
     * @param mouse События реагирующие на действия мыши
     * @return
     */
    public static JPanel buildButLabel(int hgap, int vgap, final Color baseColor, final Color selectColor, String text, Font font, MouseListener...mouse){
        UtilLogger.info(UtilFrame.class, "StrictControllerFrame.buildButLabel - started");
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, hgap, vgap);
        final JPanel panel = new JPanel();
        panel.setLayout(layout);
        panel.setBackground(baseColor);
        if(mouse[0]!=null) {
            for(int i=0; i<mouse.length; i++)
                panel.addMouseListener(mouse[i]);
        }
        panel.addMouseListener(new MouseAction(panel, selectColor, baseColor));

        JLabel lab = new JLabel(text);
        if(font!=null)
            lab.setFont(font);
        panel.add(lab);
        UtilLogger.info(UtilFrame.class, "StrictControllerFrame.buildButLabel - finished");
        return panel;
    }

    /**
     * Создать кнопки с надписями, которые меняют цвет при наведении курсора мыши
     * @param hgap Отступы от текста по горизонтали
     * @param vgap Отступы от текста по вертикали
     * @param baseColor Базовый (Начальный) цвет кнопкок
     * @param selectColor Цвет кнопок при наведении курсора мыши
     * @param font Шрифт текста
     * @param mouse Событие реагирующие на действия мыши
     * @param texts Текст кнопкок
     * @return
     */
    public static JPanel[] buildButLabels(int hgap, int vgap, final Color baseColor, final Color selectColor, Font font, MouseListener mouse, String...texts){
        UtilLogger.info(UtilFrame.class, "StrictControllerFrame.buildButLabels - started");
        JPanel [] arrPanel;
        if(texts!=null) {
            arrPanel = new JPanel[texts.length];
            for(int i=0; i<arrPanel.length; i++) {
                arrPanel[i] = buildButLabel(hgap, vgap, baseColor, selectColor, texts[i], font, mouse);
            }
        }else {
            arrPanel = new JPanel[1];
            arrPanel[0] = buildButLabel(hgap, vgap, baseColor, selectColor, "", font, mouse);
        }

        UtilLogger.info(UtilFrame.class, "StrictControllerFrame.buildButLabels - finished");
        return arrPanel;
    }

    /**
     * Создать кнопку с изображением, которая меняет цвет при наведении курсора мыши
     * @param hgap Отступы от изображения по горизонтали
     * @param vgap Отступы от изоюражения по вертикали
     * @param baseColor Базовый (Начальный) цвет кнопки
     * @param selectColor Цвет кнопки при наведении курсора мыши
     * @param image Изображение кнопки
     * @param mouse События реагирующие на действия мыши
     * @return
     */
    public static JPanel buildButImg(int hgap, int vgap, final Color baseColor, final Color selectColor, ImageIcon image, MouseListener...mouse){
        UtilLogger.info(UtilFrame.class, "StrictControllerFrame.buildButImg - started");
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, hgap, vgap);
        JPanel panel = new JPanel();
        panel.setLayout(layout);
        panel.setBackground(baseColor);
        if(mouse!=null) {
            for(int i=0; i<mouse.length; i++)
                panel.addMouseListener(mouse[i]);
        }
        panel.addMouseListener(new MouseAction(panel, selectColor, baseColor));

        JLabel lab = new JLabel();
        if(image!=null)
            lab.setIcon(image);
        panel.add(lab);
        UtilLogger.info(UtilFrame.class, "StrictControllerFrame.buildButImg - finished");
        return panel;
    }

    /**
     * Создать кнопки с изображениями, которые меняют цвет при наведении курсора мыши
     * @param hgap Отступы от изображения по горизонтали
     * @param vgap Отступы от изображения по вертикали
     * @param baseColor Базовый (Начальный) цвет кнопок
     * @param selectColor Цвет кнопок при наведении курсора мыши
     * @param mouse Событие реагирующие на действия мыши
     * @param images Изображения кнопок
     * @return
     */
    public static JPanel[] buildButImgs(int hgap, int vgap, final Color baseColor, final Color selectColor, MouseListener mouse, ImageIcon...images){
        UtilLogger.info(UtilFrame.class, "StrictControllerFrame.buildButImgs - started");
        JPanel [] arrPanel;
        if(images!=null) {
            arrPanel = new JPanel[images.length];
            for(int i=0; i<arrPanel.length; i++) {
                arrPanel[i] = buildButImg(hgap, vgap, baseColor, selectColor, images[i], mouse);
            }
        }else {
            arrPanel = new JPanel[1];
            arrPanel[0] = buildButLabel(hgap, vgap, baseColor, selectColor, "", null, mouse);
        }

        UtilLogger.info(UtilFrame.class, "StrictControllerFrame.buildButImgs - finished");
        return arrPanel;
    }

    /**
     * Создать кнопку с изображением и текстом, которая меняет цвет при наведении курсора мыши
     * @param hgap Отступы от изображения по горизонтали
     * @param vgap Отступы от изоюражения по вертикали
     * @param baseColor Базовый (Начальный) цвет кнопки
     * @param selectColor Цвет кнопки при наведении курсора мыши
     * @param image Изображение кнопки
     * @param text Текст кнопки
     * @param mouse События реагирующие на действия мыши
     * @return
     */
    public static JPanel buildButImgText(int hgap, int vgap, final Color baseColor, final Color selectColor, ImageIcon image, String text, Font font, MouseListener...mouse){
        UtilLogger.info(UtilFrame.class, "StrictControllerFrame.buildButImgText - started");
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, hgap, vgap);
        final JPanel panel = new JPanel();
        panel.setLayout(layout);
        panel.setBackground(baseColor);
        if(mouse!=null) {
            for(int i=0; i<mouse.length; i++)
                panel.addMouseListener(mouse[i]);
        }
        panel.addMouseListener(new MouseAction(panel, selectColor, baseColor));

        JLabel lab = new JLabel();
        if(font!=null)
            lab.setFont(font);
        if(text!=null)
            lab.setText(text);
        if(image!=null)
            lab.setIcon(image);
        panel.add(lab);
        UtilLogger.info(UtilFrame.class, "StrictControllerFrame.buildButImgText - finished");
        return panel;
    }

    /**
     * Создать кнопки с изображениями и текстом, которые меняют цвет при наведении курсора мыши
     * @param hgap Отступы от изображения по горизонтали
     * @param vgap Отступы от изображения по вертикали
     * @param baseColor Базовый (Начальный) цвет кнопок
     * @param selectColor Цвет кнопок при наведении курсора мыши
     * @param mouses Событие реагирующие на действия мыши
     * @param texts Текст кнопкок
     * @param images Изображения кнопок
     * @return
     */
    public static JPanel[] buildButImgsTexts(int hgap, int vgap, final Color baseColor, final Color selectColor,
                                             MouseListener [] mouses, String [] texts, Font font, ImageIcon...images){
        UtilLogger.info(UtilFrame.class, "StrictControllerFrame.buildButImgsTexts - started");
        JPanel [] arrPanel;
        if(images!=null) {
            arrPanel = new JPanel[images.length];
            for(int i=0; i<arrPanel.length; i++) {
                arrPanel[i] = buildButImgText(hgap, vgap, baseColor, selectColor, images[i], texts[i], font, mouses[i]);
            }
        }else {
            arrPanel = new JPanel[1];
            arrPanel[0] = buildButLabel(hgap, vgap, baseColor, selectColor, "", null, mouses[0]);
        }

        UtilLogger.info(UtilFrame.class, "StrictControllerFrame.buildButImgsTexts - finished");
        return arrPanel;
    }

    /**
     * Добавление списка полей на определенный контейнер
     * @param labNames Наименования добавляемых полей
     * @param layout Менеджер компоновки панели на которую добавляются поля
     * @param components Компоненты добавляемые после полей
     */
    public static JPanel createPanelTfLabels(Collection<String> labNames, LayoutManager layout, JComponent...components){
        JPanel container = new JPanel();
        container.setLayout(layout);
        labNames.stream()
                .forEach(e->{
                    container.add(new JLabel(e));
                    container.add(new JTextField());
                });

        for(JComponent comp:components)
            container.add(comp);

        return container;
    }

    /**
     * Добавление списка полей на определенный контейнер используя GridBagLayout
     * @param labNames Наименования добавляемых полей
     * @param layout Объект компоновки GridBagLayout
     * @param c Объект компоновки GridBagConstraints
     * @param components Компоненты добавляемые после полей
     */
    public static JPanel createPanelTfLabels(Collection<String> labNames, GridBagLayout layout, GridBagConstraints c, JComponent...components){
        JPanel container = new JPanel();
        container.setLayout(layout);

        labNames.stream()
                .forEach(e->{
                    JLabel lab = new JLabel(e);
                    container.add(lab);
                    layout.setConstraints(lab, c);
                    JTextField tf = new JTextField();
                    container.add(tf);
                    layout.setConstraints(tf, c);
                });

        for(JComponent comp:components)
            container.add(comp);

        return container;
    }

    /**
     * Добавление списка полей на определенный контейнер используя стандартную реализацию GridBagLayout
     * (Компоненты располагаются друг под другом, каждый в отдельной строке)
     * @param labNames Наименования добавляемых полей
     * @param components Компоненты добавляемые после полей
     */
    public static JPanel createPanelTfLabelsRow(Collection<String> labNames, JComponent...components){
        JPanel container = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        container.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = GridBagConstraints.RELATIVE;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.insets = new Insets(5,5,5,5);

        labNames.stream()
                .forEach(e->{
                    JLabel lab = new JLabel(e);
                    container.add(lab);
                    layout.setConstraints(lab, c);
                    JTextField tf = new JTextField();
                    container.add(tf);
                    layout.setConstraints(tf, c);
                });

        for(JComponent comp:components) {
            container.add(comp);
            layout.setConstraints(comp, c);
        }

        return container;
    }

    /**
     * Добавление списка полей на определенный контейнер используя стандартную реализацию GridBagLayout
     * (Компоненты располагаются друг под другом, каждый в отдельной строке)
     * @param labNames Наименования добавляемых полей
     * @param listValues Значения, которые присваиваются текстовым полям после JLabel
     * @param components Компоненты добавляемые после полей
     */
    //TODO: Сделать проверку, чтобы labNames было равно listValues
    public static JPanel createPanelTfLabelsRow(Collection<String> labNames, Collection<String> listValues, JComponent...components){
        JPanel container = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        container.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = GridBagConstraints.RELATIVE;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.insets = new Insets(5,5,5,5);

        final Iterator<String> values = listValues.iterator();

        labNames.stream()
                .forEach(e->{
                    JLabel lab = new JLabel(e);
                    container.add(lab);
                    layout.setConstraints(lab, c);
                    JTextField tf = new JTextField(String.valueOf(values.next()).trim());
                    container.add(tf);
                    layout.setConstraints(tf, c);
                });

        for(JComponent comp:components) {
            container.add(comp);
            layout.setConstraints(comp, c);
        }

        return container;
    }

    /**
     * Добавление списка полей на определенный контейнер
     * @param labNames Наименования добавляемых полей
     * @param container Панель на которую добавляются компоненты
     * @param layout Менеджер компоновки панели на которую добавляются поля
     * @param components Компоненты добавляемые после полей
     */
    public static JPanel addTfLabels(Collection<String> labNames, JPanel container, LayoutManager layout, JComponent...components){
        container.setLayout(layout);
        labNames.stream()
                .forEach(e->{
                    container.add(new JLabel(e));
                    container.add(new JTextField());
                });

        for(JComponent comp:components)
            container.add(comp);

        return container;
    }

    /**
     * Добавление списка полей на определенный контейнер используя GridBagLayout
     * @param labNames Наименования добавляемых полей
     * @param container Панель на которую добавляются компоненты
     * @param layout Объект компоновки GridBagLayout
     * @param c Объект компоновки GridBagConstraints
     * @param components Компоненты добавляемые после полей
     */
    public static JPanel addTfLabels(Collection<String> labNames, JPanel container, GridBagLayout layout, GridBagConstraints c, JComponent...components){
        container.setLayout(layout);

        labNames.stream()
                .forEach(e->{
                    JLabel lab = new JLabel(e);
                    container.add(lab);
                    layout.setConstraints(lab, c);
                    JTextField tf = new JTextField();
                    container.add(tf);
                    layout.setConstraints(tf, c);
                });

        for(JComponent comp:components)
            container.add(comp);

        return container;
    }

    /**
     * Добавление списка полей на определенный контейнер используя стандартную реализацию GridBagLayout (Компоненты располагаются друг под другом, каждый в отдельной строке)
     * @param labNames Наименования добавляемых полей
     * @param container Панель на которую добавляются компоненты
     * @param components Компоненты добавляемые после полей
     */
    public static JPanel addTfLabelsRow(Collection<String> labNames, JPanel container, JComponent...components){
        GridBagLayout layout = new GridBagLayout();
        container.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = GridBagConstraints.RELATIVE;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.insets = new Insets(5,5,5,5);

        labNames.stream()
                .forEach(e->{
                    JLabel lab = new JLabel(e);
                    container.add(lab);
                    layout.setConstraints(lab, c);
                    JTextField tf = new JTextField();
                    container.add(tf);
                    layout.setConstraints(tf, c);
                });

        for(JComponent comp:components) {
            container.add(comp);
            layout.setConstraints(comp, c);
        }

        return container;
    }
























    /**
     * Добавление списка полей на определенный контейнер
     * @param labNames Наименования добавляемых полей
     * @param layout Менеджер компоновки панели на которую добавляются поля
     * @param components Компоненты добавляемые после полей
     */
    public static JPanel createPanelLabels(Collection<String> labNames, LayoutManager layout, JComponent...components){
        JPanel container = new JPanel();
        container.setLayout(layout);
        labNames.stream()
                .forEach(e-> container.add(new JLabel(e)));

        for(JComponent comp:components)
            container.add(comp);

        return container;
    }

    /**
     * Добавление списка полей на определенный контейнер используя GridBagLayout
     * @param labNames Наименования добавляемых полей
     * @param layout Объект компоновки GridBagLayout
     * @param c Объект компоновки GridBagConstraints
     * @param components Компоненты добавляемые после полей
     */
    public static JPanel createPanelLabels(Collection<String> labNames, GridBagLayout layout, GridBagConstraints c, JComponent...components){
        JPanel container = new JPanel();
        container.setLayout(layout);

        labNames.stream()
                .forEach(e->{
                    JLabel lab = new JLabel(e);
                    container.add(lab);
                    layout.setConstraints(lab, c);
                });

        for(JComponent comp:components)
            container.add(comp);

        return container;
    }

    /**
     * Добавление списка полей на определенный контейнер используя стандартную реализацию GridBagLayout
     * (Компоненты располагаются друг под другом, каждый в отдельной строке)
     * @param labNames Наименования добавляемых полей
     * @param components Компоненты добавляемые после полей
     */
    public static JPanel createPanelLabelsRow(Collection<String> labNames, JComponent...components){
        JPanel container = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        container.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = GridBagConstraints.RELATIVE;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.insets = new Insets(5,5,5,5);

        labNames.stream()
                .forEach(e->{
                    JLabel lab = new JLabel(e);
                    container.add(lab);
                    layout.setConstraints(lab, c);
                });

        for(JComponent comp:components) {
            container.add(comp);
            layout.setConstraints(comp, c);
        }

        return container;
    }

    /**
     * Добавление списка полей на определенный контейнер
     * @param labNames Наименования добавляемых полей
     * @param container Панель на которую добавляются компоненты
     * @param layout Менеджер компоновки панели на которую добавляются поля
     * @param components Компоненты добавляемые после полей
     */
    public static JPanel addLabels(Collection<String> labNames, JPanel container, LayoutManager layout, JComponent...components){
        container.setLayout(layout);
        labNames.stream()
                .forEach(e-> container.add(new JLabel(e)));

        for(JComponent comp:components)
            container.add(comp);

        return container;
    }

    /**
     * Добавление списка полей на определенный контейнер используя GridBagLayout
     * @param labNames Наименования добавляемых полей
     * @param container Панель на которую добавляются компоненты
     * @param layout Объект компоновки GridBagLayout
     * @param c Объект компоновки GridBagConstraints
     * @param components Компоненты добавляемые после полей
     */
    public static JPanel addLabels(Collection<String> labNames, JPanel container, GridBagLayout layout, GridBagConstraints c, JComponent...components){
        container.setLayout(layout);

        labNames.stream()
                .forEach(e->{
                    JLabel lab = new JLabel(e);
                    container.add(lab);
                    layout.setConstraints(lab, c);
                });

        for(JComponent comp:components)
            container.add(comp);

        return container;
    }

    /**
     * Добавление списка полей на определенный контейнер используя стандартную реализацию GridBagLayout (Компоненты располагаются друг под другом, каждый в отдельной строке)
     * @param labNames Наименования добавляемых полей
     * @param container Панель на которую добавляются компоненты
     * @param components Компоненты добавляемые после полей
     */
    public static JPanel addLabelsRow(Collection<String> labNames, JPanel container, JComponent...components){
        GridBagLayout layout = new GridBagLayout();
        container.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = GridBagConstraints.RELATIVE;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.insets = new Insets(5,5,5,5);

        labNames.stream()
                .forEach(e->{
                    JLabel lab = new JLabel(e);
                    container.add(lab);
                    layout.setConstraints(lab, c);
                });

        for(JComponent comp:components) {
            container.add(comp);
            layout.setConstraints(comp, c);
        }

        return container;
    }

    /**
     * Добавление списка различных компонентов на определенный контейнер используя стандартную реализацию GridBagLayout
     * (Компоненты располагаются друг под другом, каждый в отдельной строке)
     * @param labNames Наименования добавляемых полей
     * @param values Список значений добавляемых компонентов. Этот список связан с коллекцией "Наименования добавляемых полей".
     *                      Первый элемент коллекции значений относится к первому элементу коллекции наименования полей.
     * @param components Компоненты добавляемые после полей
     */
    public static JPanel createPanelComponentsRow(Collection<String> labNames, Collection<Object> values, JComponent...components){
        JPanel container = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        container.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = GridBagConstraints.RELATIVE;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.insets = new Insets(5,5,5,5);

        Iterator<Object> iterValues = values.iterator();
        for(String labName:labNames){
            JLabel lab = new JLabel(labName);
            container.add(lab);
            layout.setConstraints(lab, c);

            Object value = iterValues.next();
            if(value instanceof String){
                JTextField tf = new JTextField(String.valueOf(value));
                container.add(tf);
                layout.setConstraints(tf, c);
            }else if(value instanceof Collection){
                DefaultComboBoxModel cmbModel = new DefaultComboBoxModel();
                Collection coll = (Collection)value;
                coll.stream().forEach(e->cmbModel.addElement(e));

                JComboBox cmb = new JComboBox(cmbModel);
                container.add(cmb);
                layout.setConstraints(cmb, c);
            }
        }

        for(JComponent comp:components) {
            container.add(comp);
            layout.setConstraints(comp, c);
        }

        return container;
    }

    /**
     * Событие реагирующее на наведение мыши на кнопку для смены цвета
     */
    private static class MouseAction extends MouseAdapter{
        private JPanel panel;
        private Color selectColor, baseColor;

        public MouseAction(JPanel panel, Color selectColor, Color baseColor) {
            this.panel = panel;
            this.selectColor = selectColor;
            this.baseColor = baseColor;
        }

        @Override
        public void mouseEntered(MouseEvent event) {
            if(!isButPressed) {
                panel.setBackground(selectColor);
                panel.repaint();
            }
        }

        @Override
        public void mouseExited(MouseEvent event) {
            if(!isButPressed) {
                panel.setBackground(baseColor);
                panel.repaint();
            }
        }
    }

    /**
     * Событие реагирующее на наведение мыши на JLabel для смены цвета
     */
    private static class MouseActionForLabel extends MouseAdapter{

        private JLabel lab;

        public MouseActionForLabel(JLabel lab) {
            this.lab = lab;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if(!isButPressed) {
                lab.setOpaque(true);
                lab.repaint();
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(!isButPressed) {
                lab.setOpaque(false);
                lab.repaint();
            }
        }
    }

    /**
     * Расчитать размер элемента на основе используемого размера окна и пользовательского соотношения
     * @param widthFrame Ширина окна
     * @param heightFrame Высота окна
     * @param ratioUser Процентное соотношение ширины окна с шириной элемента
     * @return
     */
    public static int calcSizeByRatio(int widthFrame, int heightFrame, double ratioUser){
        // Расчитываем сотношение ширины и высоты фрейма. В идеале должно быть 1.6
        double ratioScreenSize = Math.abs(1 - Math.abs(1.6 - (double)widthFrame/(double)heightFrame));
        // На основе расчитанного соотношения рассчитаем размер элемента
        return (int)(ratioScreenSize * (double)widthFrame/100 * ratioUser);
    }
}
