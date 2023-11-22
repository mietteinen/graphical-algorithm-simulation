/**
 * Filename:    SettingsWindow.java
 * Author:      Tomi Miettinen
 * Date:        10/2023
 * Description: The Settings dialog box for the application.
 *              Kept hidden until the settings button is
 *              pressed.
 */

package com.github.mietteinen.gui;

import com.github.mietteinen.algorithms.Algorithms;
import com.github.mietteinen.utilities.Settings;
import static com.github.mietteinen.utilities.ThemeUtils.createGridBagConstraints;
import static com.github.mietteinen.utilities.ThemeUtils.createSettingGridBagConstraints;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.SwingUtilities;
import javax.swing.DefaultComboBoxModel;

import java.nio.file.Files;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class SettingsWindow extends JDialog {

    // Struct with main settings.
    private Settings settings;

    private MainWindow parent;
    private JPanel mainPanel;
    private JPanel saveLoadPanel;
    
    // All buttons.
    private JButton saveButton;
    private JButton loadButton;
    
    // All text areas.
    private mTextPane greetingTextArea;
    private mTextPane infoTextArea; 
    
    private DefaultComboBoxModel<String> algorithms;
    
    private JComboBox<String> algorithmComboBox;
    private JCheckBox lightModeCheckBox; 
    private JCheckBox drawOutlineCheckBox;

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

        // Create the settings panel struct.
        this.settings = new Settings(algorithmComboBox.getSelectedItem().toString(), 
                                     lightModeCheckBox.isSelected(),
                                     drawOutlineCheckBox.isSelected());
    }
    
    /**
     * Opens the settings window.
     */
    public void open() {
        setVisible(!isVisible());
    }
    
    /**
     * Retrieves the selected algorithm from the
     * algorithm combo box.
     * @return: The selected algorithm as a String.
     */
    public String getSelectedAlgorithm() {
        return (String) algorithmComboBox.getSelectedItem();
    }

    public boolean getLightMode() {
        return lightModeCheckBox.isSelected();
    }

    public Settings getMainSettingsInstance() {
        return this.settings;
    }

    public void updateTheme() {
        SwingUtilities.updateComponentTreeUI(mainPanel);
        SwingUtilities.updateComponentTreeUI(saveLoadPanel);
    }
    
    /**
     * Sets up the main panel. Adds all settings
     * to the panel.
     */
    private void setupMainPanel() {
        
        mainPanel = new JPanel(new GridBagLayout());
        
        add(mainPanel, createGridBagConstraints(0, 0, 1, 1, GridBagConstraints.NORTH));
        
        mainPanel.setSize(new Dimension(getWidth(), (int) (getHeight() * 0.9)));
        mainPanel.setPreferredSize(new Dimension(getWidth(), (int) (getHeight() * 0.9)));
        mainPanel.setBorder(mainBorders);
        
        // Add the sorting algorithm picker.
        mTextPane sortingAlgorithmLabel = new mTextPane(true);
        sortingAlgorithmLabel.setText("Sorting algorithm:");
        mainPanel.add(sortingAlgorithmLabel, createSettingGridBagConstraints(0, 0, 1, 1, GridBagConstraints.NORTHEAST));
        
        algorithmComboBox = new JComboBox<String>();
        algorithmComboBox.setModel(algorithms);
        mainPanel.add(algorithmComboBox, createSettingGridBagConstraints(1, 0, 1, 1, GridBagConstraints.NORTH));
        
        // Add the light mode checkbox.
        mTextPane lightModeLabel = new mTextPane(true);
        lightModeLabel.setText("Light mode:");
        mainPanel.add(lightModeLabel, createSettingGridBagConstraints(0, 1, 1, 1, GridBagConstraints.NORTHEAST));
        
        lightModeCheckBox = new JCheckBox();
        
        // React to the change of the checkbox.
        lightModeCheckBox.addActionListener(e -> {
                parent.refreshUI(lightModeCheckBox.isSelected());
        });
        mainPanel.add(lightModeCheckBox, createSettingGridBagConstraints(1, 1, 1, 1, GridBagConstraints.NORTH));

        // Add the draw outline checkbox.
        mTextPane drawOutlineLabel = new mTextPane(true);
        drawOutlineLabel.setText("Draw outline:");
        mainPanel.add(drawOutlineLabel, createSettingGridBagConstraints(0, 2, 1, 1, GridBagConstraints.NORTHEAST));

        drawOutlineCheckBox = new JCheckBox();

        // React to the change of the checkbox.
        drawOutlineCheckBox.addActionListener(e -> {
            settings.setDrawOutline(drawOutlineCheckBox.isSelected());
            parent.refreshUI(settings.getLightMode());
        });
        mainPanel.add(drawOutlineCheckBox, createSettingGridBagConstraints(1, 2, 1, 1, GridBagConstraints.NORTH));
    }
    
    /**
     * Lays out the components for the save and load panel.
     */
    private void setupSaveLoadPanel() {
        
        saveLoadPanel = new JPanel(new GridBagLayout());
        
        add(saveLoadPanel, createGridBagConstraints(0, 1, 1, 1, GridBagConstraints.CENTER));

        saveLoadPanel.setSize(new Dimension(getWidth(), (int) (getHeight() - mainPanel.getHeight())));
        saveLoadPanel.setPreferredSize(new Dimension(getWidth(), (int) (getHeight() - mainPanel.getHeight())));
        
        // Add the save button.
        saveButton = new JButton("Save to JSON");
        saveButton.setSize(new Dimension(150, 40));
        saveButton.addActionListener(e -> {
            saveToJSON();
        });
        saveLoadPanel.add(saveButton, createSettingGridBagConstraints(1, 0, 1, 1, GridBagConstraints.CENTER));
        
        // Add the load button.
        loadButton = new JButton("Load from JSON");
        loadButton.setSize(new Dimension(150, 40));
        loadButton.addActionListener(e -> {
            loadFromJSON();
        });
        saveLoadPanel.add(loadButton, createSettingGridBagConstraints(0, 0, 1, 1, GridBagConstraints.CENTER));
    }
    
    /**
     * Sets up the two text areas inside the save and load panel.
     * The first text area is used to greet the user based on the
     * current time. The second text area is used to display
     * status messages.
     */
    private void setupInfoTextArea() {
        
        greetingTextArea = new mTextPane(false);
        greetingTextArea.setText(greetingTextBasedOnTime());

        infoTextArea = new mTextPane(false);
        infoTextArea.setText("");
        
        saveLoadPanel.add(greetingTextArea, createSettingGridBagConstraints(0, 1, 2, 1, GridBagConstraints.CENTER));
        saveLoadPanel.add(infoTextArea, createSettingGridBagConstraints(0, 2, 2, 1, GridBagConstraints.CENTER));
    }
    
    /**
     * Saves the current settings to a JSON file.
     */
    private void saveToJSON() {

        // Get the File object for the settings file.
        File settingsFile = parent.getSettingsFile();
        
        // Create the settings file if it does not exist.
        if (!settingsFile.exists()) {

            try {
                Files.createFile(settingsFile.toPath());

            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
                infoTextArea.setText("Could not create settings file.");
                return;
            }
        }
        
        // Update the settings object with the current settings.
        settings.setAlgorithm(algorithmComboBox.getSelectedItem().toString());
        settings.setLightMode(lightModeCheckBox.isSelected());
        settings.setDrawOutline(drawOutlineCheckBox.isSelected());
        
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(settings);
            
            Files.writeString(settingsFile.toPath(), json);
            
            infoTextArea.setText("Settings saved successfully.");
            
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
            infoTextArea.setText("Could not save settings.");
        }
    }
    
    /**
     * Loads the settings from a JSON file.
     */
    protected void loadFromJSON() {

        // Get the File object for the settings file.
        File settingsFile = parent.getSettingsFile();
        
        if (settingsFile.exists()) {
            
            try (FileReader reader = new FileReader(settingsFile)) {
                
                Gson gson = new GsonBuilder().create();
                Settings loadedSettings = gson.fromJson(reader, Settings.class);

                // Update your current settings with the loaded settings
                settings = loadedSettings;
                applySettings();
                
                infoTextArea.setText("Settings loaded successfully.");
                reader.close();
                
            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
                infoTextArea.setText("Could not load settings.");
                
            } catch (NullPointerException e) {
                System.out.println("NullPointerException in loadFromJSON: " + e.getMessage());
                infoTextArea.setText("Could not load settings.");
            }

        } else {
            infoTextArea.setText("Settings file does not exist.");
        }
    }

    /**
     * Applies the settings to the components.
     */
    private void applySettings() {

        algorithmComboBox.setSelectedItem(settings.getAlgorithm());
        lightModeCheckBox.setSelected(settings.getLightMode());
        drawOutlineCheckBox.setSelected(settings.getDrawOutline());
        
        parent.refreshUI(settings.getLightMode());
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
}
