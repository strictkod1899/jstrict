package ru.strict.view.swing;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import ru.strict.view.boundary.Notification;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

abstract class BaseNotification implements Notification {

    @Getter
    private final String message;
    @Getter
    private final Params params;

    private JComponent messageComponent;
    private JButton closeButton;
    private MouseListener closeButtonMouseListener;
    private List<ActionListener> customCloseButtonActions;

    private JDialog dialogFrame;
    private JFrame frame;

    public BaseNotification(String message) {
        this(message, false);
    }

    public BaseNotification(String message, boolean dialog) {
        this.message = message;
        this.params = Params.createDefault(dialog);
        this.customCloseButtonActions = new ArrayList<>();
    }

    @Override
    public void show() {
        initFrame();
        initComponents();

        getFrame().setVisible(true);
    }

    @Override
    public void hide() {
        getFrame().setVisible(false);
    }

    public void repaint() {
        applyParamsToFrame();
        applyParamsForComponents();

        SwingUtil.refresh(getFrame());
    }

    public void addCustomCloseButtonAction(ActionListener actionListener) {
        customCloseButtonActions.add(actionListener);
    }

    private void initFrame() {
        if (params.isDialog()) {
            dialogFrame = new JDialog();
            dialogFrame.setResizable(false);
            dialogFrame.setUndecorated(true);
        } else {
            frame = new JFrame();
            frame.setResizable(false);
            frame.setUndecorated(true);
        }

        var window = getFrame();
        window.setAlwaysOnTop(true);

        applyParamsToFrame();
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

        var originalFont = messageComponent.getFont();
        var newFont = new Font(originalFont.getName(), Font.PLAIN, originalFont.getSize());
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
        for (var customCloseButtonAction : customCloseButtonActions) {
            closeButton.addActionListener(customCloseButtonAction);
        }

        layout.setConstraints(closeButton, layoutConstraints);
        window.add(closeButton);

        applyParamsForComponents();
    }

    private void applyParamsToFrame() {
        var window = getFrame();
        window.setSize(params.getSize());
        window.setPreferredSize(params.getSize());
        window.setLocation(getLocation());

        if (params.isDialog()) {
            dialogFrame.getContentPane().setBackground(params.getBackground());
        } else {
            frame.getContentPane().setBackground(params.getBackground());
        }
    }

    private void applyParamsForComponents() {
        messageComponent.setForeground(params.getMessageColor());
        messageComponent.setBackground(params.getBackground());
        messageComponent.setBorder(BorderFactory.createEmptyBorder());

        if (closeButtonMouseListener != null) {
            closeButton.removeMouseListener(closeButtonMouseListener);
        }
        closeButton.setForeground(params.getButtonColor());
        closeButtonMouseListener = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                closeButton.setForeground(params.getFocusedButtonColor());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                closeButton.setForeground(params.getButtonColor());
            }
        };
        closeButton.addMouseListener(closeButtonMouseListener);
    }

    private Point getLocation() {
        var screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        var windowSize = params.getSize();

        int x;
        int y;
        if (params.getPosition() == WindowPosition.BOTTOM) {
            x = (int) ((screenSize.getWidth() / 2) - (windowSize.getWidth() / 2));
            y = (int) (screenSize.getHeight() - (windowSize.getHeight() + 60));
        } else {
            x = (int) ((screenSize.getWidth() / 2) - (windowSize.getWidth() / 2));
            y = (int) ((screenSize.getHeight() / 2) - (windowSize.getHeight() / 2));
        }

        return new Point(x, y);
    }

    private Window getFrame() {
        return params.isDialog() ? dialogFrame : frame;
    }

    JComponent wrapMessageComponent(JComponent messageComponent) {
        return messageComponent;
    }

    abstract JComponent createMessageComponent(String message);

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Params {
        final boolean dialog;
        Dimension size;
        Color background;
        Color messageColor;
        Color buttonColor;
        Color focusedButtonColor;
        WindowPosition position;

        private Params(boolean dialog) {
            this.dialog = dialog;
        }

        private static Params createDefault(boolean dialog) {
            var params = new Params(dialog);

            params.size = new Dimension(300, 120);
            params.background = new Color(19, 27, 42);
            params.messageColor = new Color(241, 225, 167);
            params.buttonColor = new Color(175, 110, 117);
            params.focusedButtonColor = new Color(193, 73, 83);
            params.position = WindowPosition.BOTTOM;

            return params;
        }
    }

    public enum WindowPosition {
        CENTER,
        BOTTOM
    }
}
