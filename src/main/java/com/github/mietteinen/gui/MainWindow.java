package com.github.mietteinen.gui;

import javax.swing.*;

import com.github.mietteinen.algorithms.Algorithms;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Random;

public class MainWindow extends JFrame {
    
    private JFrame window;
    private JPanel mainPanel;
    private JPanel controlPanel;
    private SortingVisualizer visualizer;

    GridBagConstraints gbcVisualizer;

    public MainWindow() {

        window = new JFrame();

        window.setTitle("Life is a Simulation");
        window.setSize(800, 600);
        window.setMinimumSize(new Dimension(640, 480));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));

        mainPanel = new JPanel(new GridBagLayout());
        controlPanel = new JPanel(new GridBagLayout());
        
        // Make the main panel take up 80% of the window's height.
        mainPanel.setPreferredSize(new Dimension(window.getWidth(), 
                                          (int) (window.getHeight() * 0.8)));
        
        // Make the control panel take up the rest of the window's height.
        controlPanel.setPreferredSize(new Dimension(window.getWidth(), 
                                             (int) (window.getHeight()
                                               - mainPanel.getHeight())));

        // Add the panels to the window.
        window.add(mainPanel, BorderLayout.NORTH);
        window.add(controlPanel, BorderLayout.SOUTH);

        // Add a component listener to the window to update the panel height.
        window.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                updatePanelHeight();
            }
        });
        
        visualizer = new SortingVisualizer(mainPanel, randomList(10));
        
        // Set the constraints for the visualizer and add it to mainPanel.
        gbcVisualizer = new GridBagConstraints();
        gbcVisualizer.gridx = 0;
        gbcVisualizer.gridy = 0;
        gbcVisualizer.weightx = 1.0;
        gbcVisualizer.weighty = 1.0;
        gbcVisualizer.fill = GridBagConstraints.BOTH;
        mainPanel.add(visualizer, gbcVisualizer);

        mainPanel.setBackground(Color.BLACK);
        controlPanel.setBackground(Color.GREEN);

        Algorithms.bubbleSort(visualizer.getValues(), visualizer.getBars());
    }

    public void show() {
        window.setVisible(true);
    }

    public void updatePanelHeight() {

        int newMainPanelHeight = (int) (window.getHeight() * 0.8);
        int newControlPanelHeight = (int) (window.getHeight() - newMainPanelHeight);

        mainPanel.setPreferredSize(new Dimension(window.getWidth(), newMainPanelHeight));
        controlPanel.setPreferredSize(new Dimension(window.getWidth(), newControlPanelHeight));

        if (!window.isResizable()) {
            window.pack();
        }
    }

    public ArrayList<Integer> randomList(int size) {

        ArrayList<Integer> list = new ArrayList<Integer>();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(100 - 10 + 1) + 10);
        }

        return list;
    }
}
