package ru.strict.view.swing;

import lombok.Data;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NotificationView implements Notification {

    @Getter
    private final String message;
    @Getter
    private final boolean dialog;
    @Getter
    private final Settings settings;

    private JLabel messageLabel;
    private JButton closeButton;
    private MouseListener closeButtonMouseListener;

    private JDialog dialogFrame;
    private JFrame frame;

    public NotificationView(String message) {
        this(message, false);
    }

    public NotificationView(String message, boolean dialog) {
        this.message = message;
        this.dialog = dialog;
        this.settings = new Settings();

        initFrame();
        initSettings();
        initComponents();
    }

    @Override
    public void show() {
        getFrame().setVisible(true);
    }

    @Override
    public void hide() {
        getFrame().setVisible(false);
    }

    public void repaint() {
        applySettings();
        fillComponentSettings();

        getFrame().repaint();
    }

    private void initComponents() {
        var window = getFrame();
        var layout = new GridBagLayout();
        window.setLayout(layout);

        var layoutConstraints = new GridBagConstraints();
        layoutConstraints.insets = new Insets(2,15,2,15);
        layoutConstraints.gridy = GridBagConstraints.RELATIVE;
        layoutConstraints.gridwidth = GridBagConstraints.REMAINDER;
        layoutConstraints.fill = GridBagConstraints.CENTER;

        messageLabel = new JLabel(message);
        Font originalFont = messageLabel.getFont();
        Font newFont = new Font(originalFont.getName(), Font.PLAIN, originalFont.getSize());
        messageLabel.setFont(newFont);
        layout.setConstraints(messageLabel, layoutConstraints);
        window.add(messageLabel);

        closeButton = new JButton();
        closeButton.setText("Закрыть");
        closeButton.setOpaque(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setBorderPainted(false);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener((event) -> hide());

        layout.setConstraints(closeButton, layoutConstraints);
        window.add(closeButton);

        fillComponentSettings();
    }

    private void initSettings() {
        var window = getFrame();
        window.setAlwaysOnTop(true);

        if (dialog) {
            dialogFrame.setResizable(false);
            dialogFrame.setUndecorated(true);
        } else {
            frame.setResizable(false);
            frame.setUndecorated(true);
        }

        applySettings();
    }

    private void applySettings() {
        var window = getFrame();
        window.setSize(settings.getSize());
        window.setPreferredSize(settings.getSize());
        window.setLocation(getLocation());

        if (dialog) {
            dialogFrame.getContentPane().setBackground(settings.getBackground());
        } else {
            frame.getContentPane().setBackground(settings.getBackground());
        }
    }

    private void fillComponentSettings() {
        messageLabel.setForeground(settings.getMessageColor());

        if (closeButtonMouseListener != null) {
            closeButton.removeMouseListener(closeButtonMouseListener);
        }
        closeButton.setForeground(settings.getButtonColor());
        closeButtonMouseListener = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                closeButton.setForeground(settings.getFocusedButtonColor());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                closeButton.setForeground(settings.getButtonColor());
            }
        };
        closeButton.addMouseListener(closeButtonMouseListener);
    }

    private void initFrame() {
        if (dialog) {
            dialogFrame = new JDialog();
        } else {
            frame = new JFrame();
        }
    }

    private Point getLocation() {
        int x;
        int y;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = settings.getSize();
        if (settings.getPosition() == WindowPosition.BOTTOM) {
            x = (int) ((screenSize.getWidth() / 2) - (windowSize.getWidth() / 2));
            y = (int) (screenSize.getHeight() - (windowSize.getHeight() + 60));
        } else {
            x = (int) ((screenSize.getWidth() / 2) - (windowSize.getWidth() / 2));
            y = (int) ((screenSize.getHeight() / 2) - (windowSize.getHeight() / 2));
        }

        return new Point(x, y);
    }

    private Window getFrame() {
        return dialog ? dialogFrame : frame;
    }

    @Data
    public static final class Settings {
        private Dimension size;
        private Color background;
        private Color messageColor;
        private Color buttonColor;
        private Color focusedButtonColor;
        private WindowPosition position;

        private Settings() {
            size = new Dimension(300, 120);
            background = new Color(19, 27, 42);
            messageColor = new Color(241, 225, 167);
            buttonColor = new Color(175, 110, 117);
            focusedButtonColor = new Color(193, 73, 83);
            position = WindowPosition.BOTTOM;
        }
    }

    public enum WindowPosition {
        CENTER,
        BOTTOM
    }
}
