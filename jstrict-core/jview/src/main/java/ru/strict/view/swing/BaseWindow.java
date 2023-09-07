package ru.strict.view.swing;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BaseWindow {
    final BaseWindowParams params;

    @Getter(value = AccessLevel.PROTECTED)
    JFrame frame;
    @Getter(value = AccessLevel.PROTECTED)
    JPanel centerPanel;

    public BaseWindow(@Nonnull BaseWindowParams params) {
        this.params = params;
    }

    public void show() {
        frame.setVisible(true);
        frame.setState(0);
    }

    public void hide() {
        frame.setVisible(false);
    }

    public void close() {
        frame.dispatchEvent(new WindowEvent(getFrame(), WindowEvent.WINDOW_CLOSING));
    }

    public void init() {
        frame = new JFrame();
        initFrame(frame);

        var topPanelParams = initTopPanelParams().build();
        var topPanel = new TopPanel(topPanelParams);

        centerPanel = createCenterPanel();
        initComponents();

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
    }

    protected void initFrame(@Nonnull JFrame frame) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        frame.setUndecorated(true);
        frame.setPreferredSize(new Dimension(params.getWidth(), params.getHeight()));
        frame.setSize(params.getWidth(), params.getHeight());
        frame.setBackground(params.getBackground());
        frame.getContentPane().setBackground(params.getBackground());

        params.getLogoFile().ifPresent(logoFile -> {
            frame.setIconImage((new ImageIcon(logoFile.getAbsolutePath())).getImage());
        });
    }

    protected TopPanelParamsBuilder initTopPanelParams() {
        var topPanelParamsBuilder = TopPanelParams.builder().
                parentWindow(frame).
                title(params.getTitle());
        params.getLogoFile().ifPresent(logoFile -> {
            topPanelParamsBuilder.iconPath(logoFile.getAbsolutePath());
        });

        return topPanelParamsBuilder;
    }

    @Nonnull
    protected abstract JPanel createCenterPanel();
    protected abstract void initComponents();
}
