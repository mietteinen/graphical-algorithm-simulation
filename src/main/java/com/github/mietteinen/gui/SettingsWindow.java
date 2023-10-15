package com.github.mietteinen.gui;

import com.github.mietteinen.algorithms.Algorithms;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class SettingsWindow extends JDialog {
    
    private MainWindow parent;
    private JPanel mainPanel;
    private JPanel saveLoadPanel;

    // All buttons.
    private JButton saveButton;
    private JButton loadButton;

    private JTextPane infoTextArea; 

    private JComboBox<String> algorithmComboBox;

    private DefaultComboBoxModel<String> algorithms;
    private Border mainBorders;

    public SettingsWindow(MainWindow parent) {

        super(parent, "Simulation Settings", true);

        this.parent = parent;
        this.algorithms = Algorithms.getAlgorithms();
        this.mainBorders = BorderFactory.createEmptyBorder(0, 5, 0, 5);
        
        setSize(400, 400);
        setVisible(false);
        setResizable(false);
        setAlwaysOnTop(true);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        setupMainPanel();
        setupSaveLoadPanel();
        setupInfoTextArea();
    }
    
    /**
     * Opens the settings window.
     */
    public void open() {
        setVisible(true);
    }

    /**
     * Retrieves the selected algorithm from the
     * algorithm combo box.
     * @return: The selected algorithm as a String.
     */
    public String getSelectedAlgorithm() {
        return (String) algorithmComboBox.getSelectedItem();
    }
    
    /**
     * Sets up the main panel. Adds all settings
     * to the panel.
     */
    private void setupMainPanel() {
        
        mainPanel = new JPanel(new GridBagLayout());

        add(mainPanel, createSettingGridBagConstraints(0, 0, 1, 1, GridBagConstraints.NORTH));
        
        mainPanel.setSize(new Dimension(getWidth(), (int) (getHeight() * 0.9)));
        mainPanel.setPreferredSize(new Dimension(getWidth(), (int) (getHeight() * 0.9)));
        mainPanel.setBorder(mainBorders);

        // Add the sorting algorithm picker.
        mTextPane sortingAlgorithmLabel = new mTextPane();
        sortingAlgorithmLabel.setText("Sorting algorithm:");
        mainPanel.add(sortingAlgorithmLabel, createSettingGridBagConstraints(0, 0, 1, 1, GridBagConstraints.NORTHEAST));

        algorithmComboBox = new JComboBox<String>();
        algorithmComboBox.setModel(algorithms);
        mainPanel.add(algorithmComboBox, createSettingGridBagConstraints(1, 0, 1, 1, GridBagConstraints.NORTH));

        // Add the light mode checkbox.
        mTextPane lightModeLabel = new mTextPane();
        lightModeLabel.setText("Light mode:");
        mainPanel.add(lightModeLabel, createSettingGridBagConstraints(0, 1, 1, 1, GridBagConstraints.NORTHEAST));

        JCheckBox lightModeCheckBox = new JCheckBox();
        
        // React to the change of the checkbox.
        lightModeCheckBox.addActionListener(e -> {
            if (lightModeCheckBox.isSelected()) {
                parent.setLightMode(true);
            } else {
                parent.setLightMode(false);
            }
        });
        mainPanel.add(lightModeCheckBox, createSettingGridBagConstraints(1, 1, 1, 1, GridBagConstraints.NORTH));
    }
    
    /**
     * Sets up the save and load panel.
     */
    private void setupSaveLoadPanel() {
        
        saveLoadPanel = new JPanel(new GridBagLayout());

        add(saveLoadPanel, createSettingGridBagConstraints(0, 1, 1, 1, GridBagConstraints.CENTER));

        saveLoadPanel.setSize(new Dimension(getWidth(), (int) (getHeight() - mainPanel.getHeight())));
        saveLoadPanel.setPreferredSize(new Dimension(getWidth(), (int) (getHeight() - mainPanel.getHeight())));

        // Add the save button.
        saveButton = new JButton("Save to JSON");
        saveButton.setSize(new Dimension(150, 40));
        saveLoadPanel.add(saveButton, createSettingGridBagConstraints(1, 0, 1, 1, GridBagConstraints.CENTER));

        // Add the load button.
        loadButton = new JButton("Load from JSON");
        loadButton.setSize(new Dimension(150, 40));
        saveLoadPanel.add(loadButton, createSettingGridBagConstraints(0, 0, 1, 1, GridBagConstraints.CENTER));
    }

    /**
     * Sets up the info text area. At first
     * the area greets the user based on the current time.
     * It is later updated to display possible errors or
     * successful saves and loads.
     */
    private void setupInfoTextArea() {

        infoTextArea = new JTextPane();
        infoTextArea.setEditable(false);

        infoTextArea.setContentType("text/html");
        infoTextArea.setText("<html><center>" + greetingTextBasedOnTime() + "</center></html>");

        saveLoadPanel.add(infoTextArea, createSettingGridBagConstraints(0, 1, 2, 1, GridBagConstraints.CENTER));
        
    }
    
    /**
     * Returns a greeting text based on the current time.
     * @return: A greeting String.
     */
    private String greetingTextBasedOnTime() {
        
        // Use current time to wish the user a good day.
        int hour = java.time.LocalTime.now().getHour();
    
        if (hour >= 0 && hour < 5) {
            return "Having a good night?";
        } else if (hour >= 5 && hour < 12) {
            return "Good morning! Go get it!";
        } else if (hour >= 12 && hour < 18) {
            return "Good afternoon! Getting it done?";
        } else if (hour >= 18 && hour < 24) {
            return "Good evening! Time to relax?";
        } else {
            return "Hello!";
        }
    }

    /**
     * Creates a GridBagConstraints object with the given parameters.  
     * The fill is only horizontal and the weight is 0.5.
     * @param x: The x coordinate of the component.
     * @param y: The y coordinate of the component.
     * @param width: The width of the component.
     * @param height: The height of the component.
     * @param anchor: The anchor of the component.
     * @return: A GridBagConstraints object.
     */
    private GridBagConstraints createSettingGridBagConstraints(int x, int y, int width, int height, int anchor) {
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = width;
        c.gridheight = height;
        c.anchor = anchor;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.5;

        return c;
    }
}
