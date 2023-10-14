package com.github.mietteinen.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JDialog;

public class SettingsWindow extends JDialog {
    
    private MainWindow parent;
    private JPanel mainPanel;
    private JPanel saveLoadPanel;

    public SettingsWindow(MainWindow parent) {

        super(parent, "Settings", true);

        this.parent = parent;
        
        setSize(400, 400);
        setVisible(true);
        setResizable(false);
        setAlwaysOnTop(true);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        mainPanel = new JPanel(new GridBagLayout());
        saveLoadPanel = new JPanel(new GridBagLayout());
        add(mainPanel, parent.createGridBagConstraints(0, 0, 1, 1, GridBagConstraints.BOTH));
        add(saveLoadPanel, parent.createGridBagConstraints(0, 1, 1, 1, GridBagConstraints.BOTH));

        // Size the main panel to the size of the window.
        mainPanel.setSize(new Dimension(getWidth(), (int) (getHeight() * 0.9)));
        saveLoadPanel.setSize(new Dimension(getWidth(), (int) (getHeight() - mainPanel.getHeight())));

        mainPanel.setPreferredSize(new Dimension(getWidth(), (int) (getHeight() * 0.9)));
        saveLoadPanel.setPreferredSize(new Dimension(getWidth(), (int) (getHeight() - mainPanel.getHeight())));
    }
}
