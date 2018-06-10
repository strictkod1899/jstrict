package ru.strict.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

/**
 * Управление системным треем
 */
public class UtilTray {

    /**
     * Загрузить приложение в системный трей
     *
     * @param tooltip Всплывающая подсказка при наведении на значок
     * @param pathIcon Путь к изображению значка
     * @param eventDoubleClick Событие двойного нажатия по значку
     * @param isExitVisible Отображать кнопку закрытия программы
     * @param items Элементы всплывающего меню
     */
    public static void setTray(String tooltip, String pathIcon, ActionListener eventDoubleClick
            , boolean isExitVisible, JMenuItem...items){
        UtilLogger.info(UtilTray.class, "setTray - started");
        final TrayIcon trayIcon;

        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();

            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
            catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                    UnsupportedLookAndFeelException ex) {}

            final JPopupMenu popup = new JPopupMenu();

            // Добавление элементов всплывающего меню
            Arrays.asList(items).forEach(i->popup.add(i));

            if(isExitVisible)
             popup.add(getItemExit());

            Image image = Toolkit.getDefaultToolkit().getImage(pathIcon);
            // Устанавливаем изображение в трее и всплывающую подсказку
            trayIcon = new TrayIcon(image, tooltip);
            trayIcon.setImageAutoSize(true);
            // Событие двойного нажатия
            trayIcon.addActionListener(eventDoubleClick);
            // Событие вызова всплывающего меню
            trayIcon.addMouseListener(new ActionPopup(popup));

            try {
                tray.add(trayIcon);
            } catch (AWTException ex) {
                UtilLogger.error(UtilTray.class, "System tray not can be added"
                        , ex.getClass().toString(), ex.getMessage());
            }

        } else {
            UtilLogger.error(UtilTray.class, "System tray not supported");
        }
        UtilLogger.info(UtilTray.class, "setTray - finished");
    }

    private static JMenuItem getItemExit(){
        JMenuItem itemExit = new JMenuItem("Выход");
        itemExit.addActionListener(new ActionExit());
        return itemExit;
    }

    /**
     * События выхова всплывающего меню
     */
    private static class ActionPopup extends MouseAdapter{

        private final JPopupMenu popup;

        ActionPopup(JPopupMenu popup){
            this.popup = popup;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.isPopupTrigger()) {
                popup.setLocation(e.getX(), e.getY());
                popup.setInvoker(popup);
                popup.setVisible(true);
            }
        }
    }

    /**
     * Событие закрытия программы
     */
    private static class ActionExit implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
