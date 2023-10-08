package com.github.mietteinen.gui;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Collections;
import java.util.ArrayList;

public class SortingVisualizer extends JPanel {
    
    private ArrayList<Integer> values;
    private ArrayList<ValueBar> bars;

    public SortingVisualizer(ArrayList<Integer> values) {
        this.values = values;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    
        int barWidth = this.getWidth() / values.size();
        int yCoord = this.getHeight() / 2;

        for (int i = 0; i < values.size(); i++) {

            int value = values.get(i);

            // Calculate the height of the bar based on the value.
            int barHeight = value * (this.getHeight() / Collections.max(values));

            // Calculate the x coordinate of the bar.
            // The bars should be evenly distributed across the window.
            int xCoord = i * barWidth;

            // Create a new ValueBar object and draw it.
            ValueBar bar = new ValueBar(value, xCoord, yCoord, barWidth, barHeight, Color.BLACK);
            bars.add(bar);
            bar.draw(g);
        }
    }

    public void update(int indexFrom, int indexTo) {

    }
}