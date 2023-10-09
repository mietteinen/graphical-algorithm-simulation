package com.github.mietteinen.gui;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Collections;
import java.util.ArrayList;

public class SortingVisualizer extends JPanel {
    
    private JPanel mainPanel;
    private ArrayList<Integer> values;
    private ArrayList<ValueBar> bars;

    public SortingVisualizer(JPanel mainPanel, ArrayList<Integer> values) {
        this.mainPanel = mainPanel;
        this.values = values;
        this.bars = new ArrayList<ValueBar>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        bars.clear();
    
        int barWidth = (int) (this.getWidth() * 0.8) / values.size();
        int heightMultiplier = (int) (this.getHeight() * 0.8) / Collections.max(values);
        int xMidpoint = this.getWidth() / 2;

        for (int i = 0; i < values.size(); i++) {

            int value = values.get(i);

            // Calculate the height of the bar based on the value.
            int barHeight = value * heightMultiplier;

            // Calculate the x coordinate of the bar.
            // The bars should be evenly distributed across the window.
            int yCoord = (mainPanel.getHeight() - barHeight) / 2;
            
            // Position the bars relative to the midpoint of the window.
            int xCoord = xMidpoint - (values.size() * barWidth) / 2 + i * barWidth;

            // Create a new ValueBar object and draw it.
            ValueBar bar = new ValueBar(value, xCoord, yCoord, barWidth, barHeight, Color.WHITE);
            bars.add(bar);
            bar.draw(g);
        }
    }

    public void update(int indexFrom, int indexTo) {

    }
}
