package ru.strict.view.swing;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;
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

@FieldDefaults(level = AccessLevel.PRIVATE)
abstract class BaseNotification implements Notification {

    @Getter
    final String message;
    @Getter
    final Params params;

    JDialog dialogFrame;
    JFrame frame;

    public BaseNotification(String message) {
        this(message, false);
    }

    public BaseNotification(String message, boolean dialog) {
        this.message = message;
        this.params = Params.createDefault(dialog);
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

    private void initFrame() {
        if (params.isDialog()) {
            dialogFrame = new JDialog();
            dialogFrame.setResizable(false);
            dialogFrame.setUndecorated(true);
            dialogFrame.getContentPane().setBackground(params.getBackground());
        } else {
            frame = new JFrame();
            frame.setResizable(false);
            frame.setUndecorated(true);
            frame.getContentPane().setBackground(params.getBackground());
        }

        var window = getFrame();
        window.setAlwaysOnTop(true);
        window.setSize(params.getSize());
        window.setPreferredSize(params.getSize());
        window.setLocation(getLocation());
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

        var messageComponent = createMessageComponent(message);
        var messageComponentWrapper = wrapMessageComponent(messageComponent);

        var originalFont = messageComponent.getFont();
        var newFont = new Font(originalFont.getName(), Font.PLAIN, originalFont.getSize());
        messageComponent.setFont(newFont);
        messageComponent.setForeground(params.getMessageColor());
        messageComponent.setBackground(params.getBackground());
        messageComponent.setBorder(BorderFactory.createEmptyBorder());

        layout.setConstraints(messageComponentWrapper, layoutConstraints);
        window.add(messageComponentWrapper);

        var closeButtonActions = new ArrayList<ActionListener>();
        closeButtonActions.add((event) -> hide());
        closeButtonActions.addAll(params.getCustomCloseButtonActions());

        var buttonsPanel = new JPanel();
        var buttonsPanelLayout = new GridBagLayout();
        buttonsPanel.setLayout(buttonsPanelLayout);
        buttonsPanel.setBackground(params.getBackground());

        var buttonsPanelLayoutConstraints = new GridBagConstraints();
        buttonsPanelLayoutConstraints.insets = new Insets(2,15,2,15);
        buttonsPanelLayoutConstraints.fill = GridBagConstraints.CENTER;

        if (params.getButtonsAlignment() == ButtonsAlignment.HORIZONTAL) {
            buttonsPanelLayoutConstraints.gridx = GridBagConstraints.RELATIVE;
            buttonsPanelLayoutConstraints.gridheight = GridBagConstraints.REMAINDER;
        } else {
            buttonsPanelLayoutConstraints.gridwidth = GridBagConstraints.REMAINDER;
            buttonsPanelLayoutConstraints.gridy = GridBagConstraints.RELATIVE;
        }
        var closeButton = createButton("Закрыть", closeButtonActions);
        buttonsPanelLayout.setConstraints(closeButton, buttonsPanelLayoutConstraints);
        buttonsPanel.add(closeButton);

        for (var customButtonParams : params.getCustomButtons()) {
            var customButton = createButton(customButtonParams.getText(), customButtonParams.getActions());
            buttonsPanelLayout.setConstraints(customButton, buttonsPanelLayoutConstraints);
            buttonsPanel.add(customButton);
        }

        layout.setConstraints(buttonsPanel, layoutConstraints);
        window.add(buttonsPanel);
    }

    private JButton createButton(String text, List<ActionListener> actions) {
        var button = new JButton();
        button.setText(text);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setForeground(params.getButtonColor());
        button.addMouseListener(new FocusButtonMouseListener(button));
        for (var action : actions) {
            button.addActionListener(action);
        }

        return button;
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
        final List<ActionListener> customCloseButtonActions;
        final List<CustomButtonParams> customButtons;
        Dimension size;
        Color background;
        Color messageColor;
        Color buttonColor;
        Color focusedButtonColor;
        WindowPosition position;
        ButtonsAlignment buttonsAlignment;

        private Params(boolean dialog) {
            this.dialog = dialog;
            this.customCloseButtonActions = new ArrayList<>();
            this.customButtons = new ArrayList<>();
        }

        private static Params createDefault(boolean dialog) {
            var params = new Params(dialog);

            params.size = new Dimension(300, 120);
            params.background = new Color(19, 27, 42);
            params.messageColor = new Color(241, 225, 167);
            params.buttonColor = new Color(175, 110, 117);
            params.focusedButtonColor = new Color(193, 73, 83);
            params.position = WindowPosition.BOTTOM;
            params.buttonsAlignment = ButtonsAlignment.HORIZONTAL;

            return params;
        }

        public void addCustomCloseButtonAction(ActionListener actionListener) {
            customCloseButtonActions.add(actionListener);
        }

        public void addCustomButton(CustomButtonParams customButtonParams) {
            customButtons.add(customButtonParams);
        }
    }

    @Value
    @Builder
    public static class CustomButtonParams {
        String text;
        List<ActionListener> actions;
    }

    public enum WindowPosition {
        CENTER,
        BOTTOM
    }

    public enum ButtonsAlignment {
        VERTICAL,
        HORIZONTAL
    }

    @RequiredArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    private class FocusButtonMouseListener extends MouseAdapter {
        JButton button;

        @Override
        public void mouseEntered(MouseEvent e) {
            button.setForeground(params.getFocusedButtonColor());
        }

        @Override
        public void mouseExited(MouseEvent e) {
            button.setForeground(params.getButtonColor());
        }
    }
}
