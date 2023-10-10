package com.github.mietteinen.gui;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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

        createBars();

        // Add a component listener to the panel to update the bars when the window is resized.
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                updateBars();
            }
        });
    }

    public ArrayList<Integer> getValues() {
        return values;
    }

    public ArrayList<ValueBar> getBars() {
        return bars;
    }

    public void setValues(ArrayList<Integer> values) {
        this.values = values;
        bars.clear();
        createBars();
    }

    public void updateBars() {
    
        int barWidth = (int) (this.getWidth() * 0.8) / values.size();
        int heightMultiplier = (int) (this.getHeight() * 0.8) / Collections.max(values);
        int xMidpoint = this.getWidth() / 2;
    
        // Update each bar.
        for (int i = 0; i < bars.size(); i++) {
            ValueBar bar = bars.get(i);
            int value = values.get(i);
            int barHeight = value * heightMultiplier;
            int yCoord = (this.getHeight() - barHeight) / 2;
            int xCoord = xMidpoint - (values.size() * barWidth) / 2 + i * barWidth;
            bar.update(value, xCoord, yCoord, barWidth, barHeight);
            bar.draw(this.getGraphics());
        }
    }
    
    public void update(int indexFrom, int indexTo) {
        // Move one value to a different index.
        int value = values.get(indexFrom);
        ValueBar bar = bars.get(indexFrom);
        
        values.remove(indexFrom);
        values.add(indexTo, value);
        
        bar.move(this.getWidth() / 2 - (values.size() * bar.getWidth()) / 2 + indexTo * bar.getWidth());
        bars.remove(indexFrom);
        bars.add(indexTo, bar);
        
        updateBars();
    }
    
    private void createBars() {
        
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
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        
        super.paintComponent(g);
    
        // Clear the screen by filling it with the background color.
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        for (ValueBar bar : bars)
            bar.draw(g);
        
    }
}
