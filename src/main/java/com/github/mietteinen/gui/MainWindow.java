package com.github.mietteinen.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class MainWindow extends JFrame {
    
    private JFrame window;

    public MainWindow() {

        window = new JFrame();

        window.setTitle("Life is a Simulation");
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        JPanel controlPanel = new JPanel();

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
        SortingVisualizer visualizer = new SortingVisualizer(randomList(100));
        mainPanel.add(visualizer);
    }

    public void show() {
        window.setVisible(true);
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
