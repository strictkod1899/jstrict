package ru.strict.view.swing;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Arrays;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class TopPanel extends JPanel {
    final TopPanelParams params;

    GridBagLayout layout;
    GridBagConstraints layoutConstraints;
    JPanel leftPanel;
    JPanel rightPanel;
    JMenuBar menuBar;

    public TopPanel(TopPanelParams params) {
        this.params = params;

        initComponents();
        initWindowMoving();
    }

    private void initComponents() {
        layout = new GridBagLayout();
        layoutConstraints = new GridBagConstraints();
        setLayout(layout);
        setBackground(params.getBackground());

        layoutConstraints.gridx = 0;
        layoutConstraints.weightx = 1.0D;
        layoutConstraints.insets = new Insets(0, 0, 0, 0);
        layoutConstraints.fill = 2;
        layoutConstraints.anchor = 17;

        initLeftPanel();
        initRightPanel();

        layout.setConstraints(leftPanel, layoutConstraints);
        add(leftPanel);
        layoutConstraints.gridx = 1;
        layoutConstraints.weightx = 0.0D;
        layout.setConstraints(rightPanel, layoutConstraints);
        add(rightPanel);

        if (params.getMenuList().isEmpty()) {
            return;
        }

        initMenu();
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 1;
        layoutConstraints.gridwidth = 0;
        layout.setConstraints(this.menuBar, layoutConstraints);
        add(this.menuBar);
    }

    private void initLeftPanel() {
        var leftPanel = new JPanel();

        var layout = new GridBagLayout();
        var constraints = new GridBagConstraints();
        constraints.fill = 1;
        constraints.insets = new Insets(5, 8, 5, 8);
        constraints.weightx = 1.0D;
        leftPanel.setLayout(layout);
        leftPanel.setBackground(params.getBackground());

        var titleLabel = new JLabel(params.getTitle());
        titleLabel.setForeground(getForeground());
        params.getIconPath().ifPresent(iconPath -> {
            titleLabel.setIcon(ImageUtil.resizeImage(iconPath, params.getIconSize(), params.getIconSize()));
        });

        leftPanel.add(titleLabel);
        layout.setConstraints(titleLabel, constraints);

        this.leftPanel = leftPanel;
    }

    private void initRightPanel() {
        var exitButtonMouse = new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                setButtonPressedValue(event, true);
                event.getComponent().setBackground(ViewColor.FOCUS_BACKGROUND.getColor());
            }

            public void mouseReleased(MouseEvent event) {
                setButtonPressedValue(event, false);
                event.getComponent().setBackground(getBackground());
                params.getExitButtonAction().handle();
            }
        };

        var turnButtonMouse = new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                setButtonPressedValue(event, true);
                event.getComponent().setBackground(ViewColor.FOCUS_BACKGROUND.getColor());
            }

            public void mouseReleased(MouseEvent event) {
                setButtonPressedValue(event, false);
                event.getComponent().setBackground(getBackground());

                var currentParent = getParent();
                while(currentParent != null) {
                    if (currentParent instanceof JFrame) {
                        ((JFrame) currentParent).setState(1);
                        currentParent.setVisible(false);
                        break;
                    } else {
                        currentParent = currentParent.getParent();
                    }
                }
            }
        };

        var exitButton = SwingUtil.createImageButton(
                params.getHGap(),
                params.getVGap(),
                getBackground(),
                ViewColor.PRESS_BACKGROUND.getColor(),
                Icon.CLOSE.getImageFromResources(params.getButtonSize()),
                exitButtonMouse
        );

        var turnButton = SwingUtil.createImageButton(
                params.getHGap(),
                params.getVGap(),
                getBackground(),
                ViewColor.FOCUS_BACKGROUND.getColor(),
                Icon.TURN.getImageFromResources(params.getButtonSize()),
                turnButtonMouse
        );
        var changeSizeToSmallButton = SwingUtil.createImageButton(
                params.getHGap(),
                params.getVGap(),
                getBackground(),
                ViewColor.FOCUS_BACKGROUND.getColor(),
                Icon.CHANGE_SIZE.getImageFromResources(params.getButtonSize())
        );
        var changeSizeToFullButton = SwingUtil.createImageButton(
                params.getHGap(),
                params.getVGap(),
                getBackground(),
                ViewColor.FOCUS_BACKGROUND.getColor(),
                Icon.CHANGE_SIZE_FULL.getImageFromResources(params.getButtonSize())
        );


        changeSizeToSmallButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                setButtonPressedValue(event, true);
                event.getComponent().setBackground(ViewColor.FOCUS_BACKGROUND.getColor());
            }

            public void mouseReleased(MouseEvent event) {
                setButtonPressedValue(event, false);
                event.getComponent().setBackground(getBackground());
                getParent()
                        .setSize((int) ((double) getParent()
                                        .getParent()
                                        .getParent()
                                        .getParent()
                                        .getPreferredSize().width / 1.3D),
                                (int) ((double) getParent()
                                        .getParent()
                                        .getParent()
                                        .getParent()
                                        .getPreferredSize().height / 1.3D));
                changeSizeToFullButton.setVisible(true);
                changeSizeToSmallButton.setVisible(false);
                getParent().validate();
                getParent().repaint();
            }
        });
        changeSizeToFullButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                setButtonPressedValue(event, true);
                event.getComponent().setBackground(ViewColor.FOCUS_BACKGROUND.getColor());
            }

            public void mouseReleased(MouseEvent event) {
                setButtonPressedValue(event, false);
                event.getComponent().setBackground(getBackground());
                getParent()
                        .setSize(getParent()
                                        .getParent()
                                        .getParent()
                                        .getParent()
                                        .getPreferredSize().width,
                                getParent()
                                        .getParent()
                                        .getParent()
                                        .getParent()
                                        .getPreferredSize().height);
                getParent().setLocation(0, 0);
                changeSizeToFullButton.setVisible(false);
                changeSizeToSmallButton.setVisible(true);
                getParent().validate();
                getParent().repaint();
            }
        });
        changeSizeToFullButton.setVisible(false);

        var activeButtons = new ArrayList<JPanel>(4);
        if (params.isVisibleTurnButton()) {
            activeButtons.add(turnButton);
        }
        if (params.isVisibleChangeSizeButton()) {
            activeButtons.add(changeSizeToSmallButton);
            activeButtons.add(changeSizeToFullButton);
        }
        if (params.isVisibleExitButton()) {
            activeButtons.add(exitButton);
        }

        var rightPanel = new JPanel();
        rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        rightPanel.setBackground(getBackground());
        activeButtons.forEach(rightPanel::add);

        this.rightPanel = rightPanel;
    }

    private void initMenu() {
        this.menuBar = new JMenuBar();
        params.getMenuList().forEach(menuBar::add);
    }

    private void initWindowMoving() {
        if (!params.isEnableWindowMoving()) {
            return;
        }

        var mouseListener = new MouseMotionMoved(params.getParentWindow());
        addMouseListener(this, mouseListener);
    }

    private void addMouseListener(Component component, MouseMotionMoved mouseListener) {
        component.addMouseMotionListener(mouseListener);
        component.addMouseListener(mouseListener);

        if (component instanceof JPanel panel) {
            Arrays.stream(panel.getComponents()).forEach(c -> {
                addMouseListener(c, mouseListener);
            });
        }
    }

    private void setButtonPressedValue(MouseEvent event, boolean value) {
        var listeners = ((JPanel) event.getSource()).getMouseListeners();

        for (var listener : listeners) {
            if (!(listener instanceof ChangeBackgroundMouseAction)) {
                continue;
            }

            ((ChangeBackgroundMouseAction) listener).setButPressed(value);
        };
    }

    private static class MouseMotionMoved extends MouseAdapter implements MouseMotionListener {
        private final Container parent;
        private Point position;

        public MouseMotionMoved(Container parent) {
            this.parent = parent;
        }

        public void mouseDragged(MouseEvent event) {
            if (parent.getWidth() == Toolkit.getDefaultToolkit().getScreenSize().width ||
                    parent.getHeight() == Toolkit.getDefaultToolkit().getScreenSize().height) {
                return;
            }

            int thisX = parent.getLocation().x;
            int thisY = parent.getLocation().y;
            int xMoved = thisX + event.getX() - (thisX + position.x);
            int yMoved = thisY + event.getY() - (thisY + position.y);
            int X = thisX + xMoved;
            int Y = thisY + yMoved;

            parent.setLocation(X, Y);
        }

        public void mousePressed(MouseEvent event) {
            position = event.getPoint();
        }
    }

    @FunctionalInterface
    public interface Action {
        void handle();
    }
}
