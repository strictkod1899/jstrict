package ru.strict.swing.utils;

import ru.strict.swing.views.components.TextFieldPlaceholder;
import ru.strict.utils.UtilLogger;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Операции манипулирования формой
 */
public class UtilSwing {

    public static void refresh(Component component){
        component.validate();
        component.invalidate();
        component.repaint();
    }

    /**
     * Получить значение компонента по содержанию предшествующего JLabel (без учета регистра)
     * @param components Проверяемые компоненты
     * @param tfCaption Значение JLabel, после которого получаем значение компонента
     * @return Для JComboBox и JList возвращается выбранный индекс. Если элемент не поддерживается, вернется значение null
     */
    public static Object getValueComponentByLabelIgnoreCase(Component[] components, String tfCaption){
        for(int i=0; i<components.length; i++){
            Component component = components[i];
            if(component instanceof JLabel){
                if(((JLabel)component).getText().equalsIgnoreCase(tfCaption)){
                    Component componentResult = components[i+1];
                    if(componentResult instanceof JTextComponent) {
                        return ((JTextComponent) componentResult).getText();
                    }else if(componentResult instanceof JComboBox) {
                        return ((JComboBox) componentResult).getSelectedIndex();
                    }else if(componentResult instanceof JList) {
                        return ((JList) componentResult).getSelectedIndex();
                    }else if(componentResult instanceof JToggleButton) {
                        return ((JToggleButton) componentResult).isSelected();
                    }else if(componentResult instanceof JSpinner) {
                        return ((JSpinner) componentResult).getValue();
                    }else if(componentResult instanceof JSlider) {
                        return ((JSlider) componentResult).getValue();
                    }else {
                        return null;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Получить значение компонента по содержанию предшествующего JLabel (без учета регистра)
     * @param components Проверяемые компоненты
     * @param tfCaption Значение JLabel, после которого получаем значение компонента
     * @return Для JComboBox и JList возвращается выбранный индекс. Если элемент не поддерживается, вернется значение null
     */
    public static Object getValueComponentByLabel(Component[] components, String tfCaption){
        for(int i=0; i<components.length; i++){
            Component component = components[i];
            if(component instanceof JLabel){
                if(((JLabel)component).getText().equals(tfCaption)){
                    Component componentResult = components[i+1];
                    if(componentResult instanceof JTextComponent) {
                        return ((JTextComponent) componentResult).getText();
                    }else if(componentResult instanceof JComboBox) {
                        return ((JComboBox) componentResult).getSelectedIndex();
                    }else if(componentResult instanceof JList) {
                        return ((JList) componentResult).getSelectedIndex();
                    }else if(componentResult instanceof JToggleButton) {
                        return ((JToggleButton) componentResult).isSelected();
                    }else if(componentResult instanceof JSpinner) {
                        return ((JSpinner) componentResult).getValue();
                    }else if(componentResult instanceof JSlider) {
                        return ((JSlider) componentResult).getValue();
                    }else {
                        return null;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Получить компонент по содержанию предшествующего JLabel (без учета регистра)
     * @param components Проверяемые компоненты
     * @param tfCaption Значение JLabel, после которого получаем компонент
     * @return
     */
    public static Component getComponentByLabelIgnoreCase(Component[] components, String tfCaption){
        for(int i=0; i<components.length; i++){
            Component component = components[i];
            if(component instanceof JLabel){
                if(((JLabel)component).getText().equalsIgnoreCase(tfCaption)){
                    Component componentResult = components[i+1];
                    return componentResult;
                }
            }
        }
        return null;
    }

    /**
     * Получить компонент по содержанию предшествующего JLabel
     * @param components Проверяемые компоненты
     * @param tfCaption Значение JLabel, после которого получаем компонент
     * @return
     */
    public static Component getComponentByLabel(Component[] components, String tfCaption){
        for(int i=0; i<components.length; i++){
            Component component = components[i];
            if(component instanceof JLabel){
                if(((JLabel)component).getText().equals(tfCaption)){
                    Component componentResult = components[i+1];
                    return componentResult;
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
    public static JLabel createButtonLabel(String title, Color color, Font font){
        JLabel lab = new JLabel(title);
        lab.setBackground(color);
        lab.setFont(font);
        lab.addMouseListener(new MouseActionChangeBackgroundForLabel(lab));
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
    public static JPanel createButtonLabel(int hgap, int vgap, final Color baseColor, final Color selectColor, String text, Font font, MouseListener...mouse){
        UtilLogger.info(UtilSwing.class, "StrictControllerFrame.createButtonLabel - started");
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, hgap, vgap);
        final JPanel panel = new JPanel();
        panel.setLayout(layout);
        panel.setBackground(baseColor);
        if(mouse[0]!=null) {
            for(int i=0; i<mouse.length; i++) {
                panel.addMouseListener(mouse[i]);
            }
        }
        panel.addMouseListener(new MouseActionChangeBackground(panel, selectColor, baseColor));

        JLabel lab = new JLabel(text);
        if(font!=null) {
            lab.setFont(font);
        }
        panel.add(lab);
        UtilLogger.info(UtilSwing.class, "StrictControllerFrame.createButtonLabel - finished");
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
    public static JPanel[] createButtonLabels(int hgap, int vgap, final Color baseColor, final Color selectColor, Font font, MouseListener mouse, String...texts){
        UtilLogger.info(UtilSwing.class, "StrictControllerFrame.createButtonLabels - started");
        JPanel [] arrPanel;
        if(texts!=null) {
            arrPanel = new JPanel[texts.length];
            for(int i=0; i<arrPanel.length; i++) {
                arrPanel[i] = createButtonLabel(hgap, vgap, baseColor, selectColor, texts[i], font, mouse);
            }
        }else {
            arrPanel = new JPanel[1];
            arrPanel[0] = createButtonLabel(hgap, vgap, baseColor, selectColor, "", font, mouse);
        }

        UtilLogger.info(UtilSwing.class, "StrictControllerFrame.createButtonLabels - finished");
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
    public static JPanel createButtonImage(int hgap, int vgap, final Color baseColor, final Color selectColor, ImageIcon image, MouseListener...mouse){
        UtilLogger.info(UtilSwing.class, "StrictControllerFrame.createButtonImage - started");
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, hgap, vgap);
        JPanel panel = new JPanel();
        panel.setLayout(layout);
        panel.setBackground(baseColor);
        if(mouse!=null) {
            for(int i=0; i<mouse.length; i++) {
                panel.addMouseListener(mouse[i]);
            }
        }
        panel.addMouseListener(new MouseActionChangeBackground(panel, selectColor, baseColor));

        JLabel lab = new JLabel();
        if(image!=null) {
            lab.setIcon(image);
        }
        panel.add(lab);
        UtilLogger.info(UtilSwing.class, "StrictControllerFrame.createButtonImage - finished");
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
    public static JPanel[] createButtonsImage(int hgap, int vgap, final Color baseColor, final Color selectColor, MouseListener mouse, ImageIcon...images){
        UtilLogger.info(UtilSwing.class, "StrictControllerFrame.createButtonsImage - started");
        JPanel [] arrPanel;
        if(images!=null) {
            arrPanel = new JPanel[images.length];
            for(int i=0; i<arrPanel.length; i++) {
                arrPanel[i] = createButtonImage(hgap, vgap, baseColor, selectColor, images[i], mouse);
            }
        }else {
            arrPanel = new JPanel[1];
            arrPanel[0] = createButtonLabel(hgap, vgap, baseColor, selectColor, "", null, mouse);
        }

        UtilLogger.info(UtilSwing.class, "StrictControllerFrame.createButtonsImage - finished");
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
    public static JPanel createButtonImageText(int hgap, int vgap, final Color baseColor, final Color selectColor, ImageIcon image, String text, Font font, MouseListener...mouse){
        UtilLogger.info(UtilSwing.class, "StrictControllerFrame.createButtonImageText - started");
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, hgap, vgap);
        final JPanel panel = new JPanel();
        panel.setLayout(layout);
        panel.setBackground(baseColor);
        if(mouse!=null) {
            for(int i=0; i<mouse.length; i++) {
                panel.addMouseListener(mouse[i]);
            }
        }
        panel.addMouseListener(new MouseActionChangeBackground(panel, selectColor, baseColor));

        JLabel lab = new JLabel();
        if(font!=null) {
            lab.setFont(font);
        }
        if(text!=null) {
            lab.setText(text);
        }
        if(image!=null) {
            lab.setIcon(image);
        }
        panel.add(lab);
        UtilLogger.info(UtilSwing.class, "StrictControllerFrame.createButtonImageText - finished");
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
    public static JPanel[] createButtonsImageText(int hgap, int vgap, final Color baseColor, final Color selectColor,
                                                  MouseListener [] mouses, String [] texts, Font font, ImageIcon...images){
        UtilLogger.info(UtilSwing.class, "StrictControllerFrame.createButtonsImageText - started");
        JPanel [] arrPanel;
        if(images!=null) {
            arrPanel = new JPanel[images.length];
            for(int i=0; i<arrPanel.length; i++) {
                arrPanel[i] = createButtonImageText(hgap, vgap, baseColor, selectColor, images[i], texts[i], font, mouses[i]);
            }
        }else {
            arrPanel = new JPanel[1];
            arrPanel[0] = createButtonLabel(hgap, vgap, baseColor, selectColor, "", null, mouses[0]);
        }

        UtilLogger.info(UtilSwing.class, "StrictControllerFrame.createButtonsImageText - finished");
        return arrPanel;
    }

    /**
     * Добавление списка полей на определенный контейнер
     * @param labNames Наименования добавляемых полей
     * @param layout Менеджер компоновки панели на которую добавляются поля
     * @param components Компоненты добавляемые после полей
     */
    public static JPanel createPanelWithComponentsByLabel(List<String> labNames, LayoutManager layout, JComponent...components){
        JPanel container = new JPanel();
        container.setLayout(layout);

        for(int i = 0; i< labNames.size(); i++){
            container.add(new JLabel(labNames.get(i)));
            container.add(components[i]);
        }

        return container;
    }

    /**
     * Добавление списка полей на определенный контейнер, используя GridBagLayout
     * @param labNames Наименования добавляемых полей
     * @param layout Объект компоновки GridBagLayout
     * @param c Объект компоновки GridBagConstraints
     * @param components Компоненты добавляемые после полей
     */
    public static JPanel createPanelWithComponentsByLabel(List<String> labNames, GridBagLayout layout, GridBagConstraints c, JComponent...components){
        JPanel container = new JPanel();
        container.setLayout(layout);

        for(int i = 0; i< labNames.size(); i++){
            JLabel lab = new JLabel(labNames.get(i));
            container.add(lab);
            layout.setConstraints(lab, c);
            container.add(components[i]);
            layout.setConstraints(components[i], c);
        }

        return container;
    }

    /**
     * Добавление списка полей на определенный контейнер используя стандартную реализацию GridBagLayout
     * (Компоненты располагаются друг под другом, каждый в отдельной строке)
     * @param labNames Наименования добавляемых полей
     * @param components Компоненты добавляемые после полей
     */
    public static JPanel createPanelTfLabelsRow(List<String> labNames, JComponent...components){
        JPanel container = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        container.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = GridBagConstraints.RELATIVE;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.insets = new Insets(5,5,5,5);

        for(int i = 0; i< labNames.size(); i++){
            JLabel lab = new JLabel(labNames.get(i));
            container.add(lab);
            layout.setConstraints(lab, c);
            container.add(components[i]);
            layout.setConstraints(components[i], c);
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
    public static JPanel createPanelTfLabelsRow(List<String> labNames, List<String> listValues, TextFieldPlaceholder...components){
        JPanel container = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        container.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = GridBagConstraints.RELATIVE;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.insets = new Insets(5,5,5,5);


        for(int i = 0; i< labNames.size(); i++){
            JLabel lab = new JLabel(labNames.get(i));
            container.add(lab);
            layout.setConstraints(lab, c);
            components[i].setPlaceholder(String.valueOf(listValues.get(i)).trim());
            container.add(components[i]);
            layout.setConstraints(components[i], c);
        }

        return container;
    }

    /**
     * Расчитать размер элемента на основе используемого размера окна и пользовательского соотношения
     * @param widthFrame Ширина окна
     * @param heightFrame Высота окна
     * @param ratioUser Процентное соотношение ширины окна с шириной элемента, например 0.9
     * @return
     */
    public static int calcSizeByRatio(int widthFrame, int heightFrame, double ratioUser){
        // Расчитываем сотношение ширины и высоты фрейма. В идеале должно быть 1.6
        double ratioScreenSize = Math.abs(1 - Math.abs(1.6 - (double)widthFrame/(double)heightFrame));
        // На основе расчитанного соотношения рассчитаем размер элемента
        return (int)(ratioScreenSize * (double)widthFrame/100 * ratioUser);
    }
}
