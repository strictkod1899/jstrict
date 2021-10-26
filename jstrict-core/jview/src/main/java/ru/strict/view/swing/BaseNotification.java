package ru.strict.view.swing;

import lombok.Data;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

abstract class BaseNotification implements Notification {

    @Getter
    private final String message;
    @Getter
    private final boolean dialog;
    @Getter
    private final Settings settings;

    private JComponent messageComponent;
    private JButton closeButton;
    private MouseListener closeButtonMouseListener;

    private JDialog dialogFrame;
    private JFrame frame;

    public BaseNotification(String message) {
        this(message, false);
    }

    public BaseNotification(String message, boolean dialog) {
        this.message = message;
        this.dialog = dialog;
        this.settings = createSettings();

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
        applySettingsForFrame();
        applySettingsForComponent();

        getFrame().repaint();
    }

    Settings createSettings() {
        var settings = new Settings();
        settings.setSize(new Dimension(300, 120));
        settings.setBackground(new Color(19, 27, 42));
        settings.setMessageColor(new Color(241, 225, 167));
        settings.setButtonColor(new Color(175, 110, 117));
        settings.setFocusedButtonColor(new Color(193, 73, 83));
        settings.setPosition(WindowPosition.BOTTOM);

        return settings;
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

        messageComponent = createMessageComponent(message);
        var messageComponentWrapper = wrapMessageComponent(messageComponent);

        Font originalFont = messageComponent.getFont();
        Font newFont = new Font(originalFont.getName(), Font.PLAIN, originalFont.getSize());
        messageComponent.setFont(newFont);
        layout.setConstraints(messageComponentWrapper, layoutConstraints);
        window.add(messageComponentWrapper);

        closeButton = new JButton();
        closeButton.setText("Закрыть");
        closeButton.setOpaque(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setBorderPainted(false);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener((event) -> hide());

        layout.setConstraints(closeButton, layoutConstraints);
        window.add(closeButton);

        applySettingsForComponent();
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

        applySettingsForFrame();
    }

    private void applySettingsForFrame() {
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

    private void applySettingsForComponent() {
        messageComponent.setForeground(settings.getMessageColor());
        messageComponent.setBackground(settings.getBackground());
        messageComponent.setBorder(BorderFactory.createEmptyBorder());

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

    JComponent wrapMessageComponent(JComponent messageComponent) {
        return messageComponent;
    }

    abstract JComponent createMessageComponent(String message);

    @Data
    public static final class Settings {
        private Dimension size;
        private Color background;
        private Color messageColor;
        private Color buttonColor;
        private Color focusedButtonColor;
        private WindowPosition position;
    }

    public enum WindowPosition {
        CENTER,
        BOTTOM
    }
}
