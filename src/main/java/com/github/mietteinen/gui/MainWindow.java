package com.github.mietteinen.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class MainWindow extends JFrame {
    
    private JFrame window;
    private JPanel mainPanel;
    private JPanel controlPanel;

    public MainWindow() {

        window = new JFrame();

        window.setTitle("Life is a Simulation");
        window.setSize(800, 600);
        window.setMinimumSize(new Dimension(640, 480));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);

        mainPanel = new JPanel();
        controlPanel = new JPanel();

        mainPanel.setLayout(new BorderLayout());

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

        // Create a new SortingVisualizer object and add it to the main panel.
        SortingVisualizer visualizer = new SortingVisualizer(mainPanel, randomList(10));
        mainPanel.add(visualizer);
        //visualizer.setSize((int) (mainPanel.getWidth() * 0.8), 
        //                   (int) (mainPanel.getHeight() * 0.8));
    }

    public void show() {
        window.setVisible(true);
    }

    public void updatePanelHeight() {
        
        int newMainPanelHeight = (int) (window.getHeight() * 0.8);
        int newControlPanelHeight = (int) (window.getHeight() - newMainPanelHeight);

        mainPanel.setSize(new Dimension(window.getWidth(), newMainPanelHeight));
        controlPanel.setSize(new Dimension(window.getWidth(), newControlPanelHeight));

        mainPanel.revalidate();
        controlPanel.revalidate();
    }

    public ArrayList<Integer> randomList(int size) {

        ArrayList<Integer> list = new ArrayList<Integer>();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(100));
        }

        return list;
    }
}
