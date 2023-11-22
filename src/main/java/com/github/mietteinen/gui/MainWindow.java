/**
 * Filename:    MainWindow.java
 * Author:      Tomi Miettinen
 * Date:        10/2023
 * Description: The main window of the program. Contains
 *              the visualizer and the control panel.
 */

package com.github.mietteinen.gui;

import com.github.mietteinen.algorithms.Algorithms;
import com.github.mietteinen.utilities.ThemeUtils;
import static com.github.mietteinen.utilities.ThemeUtils.createGridBagConstraints;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import static java.awt.GridBagConstraints.EAST;
import static java.awt.GridBagConstraints.WEST;

import java.io.File;

import java.util.ArrayList;
import java.util.Random;


public class MainWindow extends JFrame {
    
    // Main window and panels.
    private JFrame window;
    private JPanel mainPanel;
    private JPanel controlPanel;
    private SettingsWindow settingsWindow;
    
    // All buttons.
    private JButton startButton;
    private JButton stopButton;
    private JButton shuffleButton;
    private JButton settingsButton;

    // Sliders and spinners.
    private JSlider sizeSlider;
    private JSpinner sizeSpinner;
    private JSlider speedSlider;
    private JSpinner speedSpinner;

    private SortingVisualizer visualizer;

    private Color backgroundColor;
    private Color foregroundColor;

    GridBagConstraints gbcVisualizer;

    public MainWindow() {

        this.backgroundColor = ThemeUtils.getBackgroundColor();
        this.foregroundColor = ThemeUtils.getForegroundColor();

        window = new JFrame();

        window.setTitle("Life is a Simulation");
        window.setSize(800, 600);
        window.setMinimumSize(new Dimension(640, 480));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));
        
        mainPanel = new JPanel(new GridBagLayout());
        controlPanel = new JPanel(new GridBagLayout());
        
        updatePanelHeight();
        
        // Add the panels to the window.
        window.add(mainPanel, BorderLayout.NORTH);
        window.add(controlPanel, BorderLayout.SOUTH);
        
        // Add a component listener to the window to update the panel height.
        window.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                
                updatePanelHeight();
                
                // Hide the labels of the sliders if the window is too small.
                if (window.getWidth() < 1150 && window.getHeight() < 575) {
                    sizeSlider.setPaintLabels(false);
                    speedSlider.setPaintLabels(false);
                } else {
                    sizeSlider.setPaintLabels(true);
                    speedSlider.setPaintLabels(true);
                }
            }
        });
        
        settingsWindow = new SettingsWindow(this);
        System.out.println(settingsWindow.getMainSettingsInstance());
        visualizer = new SortingVisualizer(mainPanel, randomList(10), settingsWindow.getMainSettingsInstance());
        
        // Set the constraints for the visualizer and add it to mainPanel.
        gbcVisualizer = new GridBagConstraints();
        gbcVisualizer.gridx = 0;
        gbcVisualizer.gridy = 0;
        gbcVisualizer.weightx = 1.0;
        gbcVisualizer.weighty = 1.0;
        gbcVisualizer.fill = GridBagConstraints.BOTH;
        mainPanel.add(visualizer, gbcVisualizer);

        mainPanel.setBackground(foregroundColor);

        setLightMode(settingsWindow.getLightMode());
        
        setupControlPanel();

        Algorithms.setSpeed(speedSlider.getValue());
    }

    public void show() {
        window.setVisible(true);
    }

    /**
     * Updates the height of the main and control panels.
     */
    public void updatePanelHeight() {

        int newMainPanelHeight = (int) (window.getHeight() * 0.7);
        int newControlPanelHeight = (int) (window.getHeight() - newMainPanelHeight);

        mainPanel.setPreferredSize(new Dimension(window.getWidth(), newMainPanelHeight));
        controlPanel.setPreferredSize(new Dimension(window.getWidth(), newControlPanelHeight));

        if (!window.isResizable()) {
            window.pack();
        }
    }

    /**
     * Creates a list of random integers.
     * @param size: The size of the list as Integer.
     * @return: The list of random integers as ArrayList.
     */
    public ArrayList<Integer> randomList(int size) {

        ArrayList<Integer> list = new ArrayList<Integer>();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(100 - 10 + 1) + 10);
        }
        
        return list;
    }

    public File getSettingsFile() {
        return new File("settings.json");
    }
    
    /**
     * Sets the look and feel of the program to either
     * light or dark.
     * @param light: True if the light theme should be used.
     */
    public void setLightMode(boolean light) {

        LookAndFeel newLookAndFeel;

        if (light) {
            newLookAndFeel = new FlatLightLaf();
        } else {
            newLookAndFeel = new FlatDarculaLaf();
        }

        try {
            UIManager.setLookAndFeel(newLookAndFeel);
        } catch (Exception e) {
            System.out.println("Could not set system look and feel.");
        }

        refreshUI();
    }

    /**
     * Sorts the list using the selected algorithm. Sorting
     * is done on a separate thread so the other one can be
     * used to access the GUI.
     * @param algorithm: The algorithm to be used as String.
     */
    private void sort(String algorithm) {
        Thread sortingThread = new Thread(() -> {

            // Enable and disable the wanted buttons
            // at the start of the sorting.
            shuffleButton.setEnabled(false);
            startButton.setEnabled(false);
            stopButton.setEnabled(true);

            switch (algorithm) {

                case "Bubble Sort":
                    Algorithms.bubbleSort(visualizer);
                    break;

                case "Selection Sort":
                    Algorithms.selectionSort(visualizer);
                    break;

                case "Merge Sort":
                    Algorithms.mergeSortMain(visualizer);
                    break;

                default:
                    break;
            }

            // Enable and disable the wanted buttons
            // at the end of the sorting.
            stopButton.setEnabled(false);
            startButton.setEnabled(true);
            shuffleButton.setEnabled(true);

        });
        
        // Stop the sorting if the stop button is pressed.
        stopButton.addActionListener(e -> {
            // Interrupt sortingThread.
            sortingThread.interrupt();
        });
        
        sortingThread.start();
    }

    /**
     * Sets up the control panel. Adds all the components
     * to the panel.
     */
    private void setupControlPanel() {

        // Set up the control panel.
        controlPanel.setLayout(new GridBagLayout());
        controlPanel.setBackground(backgroundColor);
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        /////////////////////////////////////////////////
        // PANEL FOR THE MAIN BUTTONS OF THE PROGRAM. //
        ///////////////////////////////////////////////
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(backgroundColor);
        
        // Create a button that shuffles the list.
        shuffleButton = new JButton("Shuffle");
        shuffleButton.addActionListener(e -> {
            visualizer.setValues(randomList(sizeSpinner.getValue().hashCode()));
            visualizer.updateBars();
        });
        
        // Create a button that starts the sorting.
        startButton = new JButton("Start");
        startButton.addActionListener(e -> {
            sort(settingsWindow.getSelectedAlgorithm());
        });
        
        stopButton = new JButton("Stop");
        settingsButton = new JButton("Settings");

        settingsButton.addActionListener(e -> {
            settingsWindow.open();
        });
        
        buttonPanel.add(startButton, createGridBagConstraints(0, 0, 1, 1, WEST));
        buttonPanel.add(stopButton, createGridBagConstraints(1, 0, 1, 1, WEST));
        buttonPanel.add(shuffleButton, createGridBagConstraints(0, 1, 2, 1, WEST));
        buttonPanel.add(settingsButton, createGridBagConstraints(2, 1, 1, 1, WEST));
        

        /////////////////////////////////////////////////////////
        // PANEL FOR CHANGING THE SPEED AND SIZE OF THE LIST. //
        ///////////////////////////////////////////////////////
        JPanel speedSizePanel = new JPanel(new GridBagLayout());
        speedSizePanel.setBackground(backgroundColor);
        
        // Create a slider and spinner that can be used to
        // change the size of the list.
        sizeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);
        sizeSlider.setMajorTickSpacing(10);
        sizeSlider.setMinorTickSpacing(5);
        sizeSlider.setPaintTicks(true);
        sizeSlider.setPaintLabels(true);

        SpinnerModel sizeModel = new SpinnerNumberModel(10, 10, 1000, 10);
        sizeSpinner = new JSpinner(sizeModel);
        sizeSpinner.setPreferredSize(new Dimension(75, 25));

        sizeSlider.addChangeListener(e -> {
            int value = sizeSlider.getValue();
            sizeSpinner.setValue(value);
        });

        sizeSpinner.addChangeListener(e -> {
            int value = sizeSpinner.getValue().hashCode();
            sizeSlider.setValue(value);
        });
        
        // Create a slider and spinner that can be used to
        // change the speed of the sorting.
        speedSlider = new JSlider(JSlider.HORIZONTAL, 0, 50, 1);
        speedSlider.setMajorTickSpacing(10);
        speedSlider.setMinorTickSpacing(5);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        
        SpinnerModel speedModel = new SpinnerNumberModel(1, 0, 500, 10);
        speedSpinner = new JSpinner(speedModel);
        speedSpinner.setPreferredSize(new Dimension(75, 25));

        speedSlider.addChangeListener(e -> {
            int value = speedSlider.getValue();
            Algorithms.setSpeed(value);
            speedSpinner.setValue(value);
        });
        
        speedSpinner.addChangeListener(e -> {
            int value = speedSpinner.getValue().hashCode();
            Algorithms.setSpeed(value);
            speedSlider.setValue(value);
        });

        JPanel hSeparatorPanel = new JPanel(new GridBagLayout());
        hSeparatorPanel.setBackground(backgroundColor);
        hSeparatorPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        hSeparatorPanel.add(new JSeparator(JSeparator.HORIZONTAL), createGridBagConstraints(0, 0, 1, 1, WEST));

        speedSizePanel.add(new JLabel("SIZE SLIDER:"), createGridBagConstraints(0, 0, 1, 1, EAST));
        speedSizePanel.add(sizeSlider, createGridBagConstraints(1, 0, 2, 1, WEST));

        speedSizePanel.add(new JLabel("CUSTOM SIZE:"), createGridBagConstraints(0, 1, 1, 1, EAST));
        speedSizePanel.add(sizeSpinner, createGridBagConstraints(1, 1, 1, 1, WEST));
        
        speedSizePanel.add(hSeparatorPanel, createGridBagConstraints(0, 2, 3, 1, WEST));
        
        speedSizePanel.add(new JLabel("SPEED SLIDER:"), createGridBagConstraints(0, 3, 1, 1, EAST));
        speedSizePanel.add(speedSlider, createGridBagConstraints(1, 3, 2, 1, WEST));
        
        speedSizePanel.add(new JLabel("CUSTOM SPEED: "), createGridBagConstraints(0, 4, 1, 1, EAST));
        speedSizePanel.add(speedSpinner, createGridBagConstraints(1, 4, 2, 1, WEST));

        JPanel vSeparatorPanel = new JPanel(new GridBagLayout());
        vSeparatorPanel.setBackground(backgroundColor);
        vSeparatorPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        vSeparatorPanel.add(new JSeparator(JSeparator.VERTICAL), createGridBagConstraints(0, 0, 1, 1, WEST));

        ///////////////////////////////////////////////
        // ADD THE COMPONENTS TO THE CONTROL PANEL. //
        /////////////////////////////////////////////
        controlPanel.add(speedSizePanel, createGridBagConstraints(3, 0, 1, 3, WEST));
        controlPanel.add(vSeparatorPanel, createGridBagConstraints(2, 0, 1, 3, WEST));
        controlPanel.add(buttonPanel, createGridBagConstraints(0, 0, 1, 2, WEST));
    }

    /**
     * Refreshes the UI of the program.
     */
    protected void refreshUI() {

        visualizer.setSettingsInstance(settingsWindow.getMainSettingsInstance());

        SwingUtilities.updateComponentTreeUI(window);
        settingsWindow.updateTheme();
        visualizer.updateBars();
    }
}
