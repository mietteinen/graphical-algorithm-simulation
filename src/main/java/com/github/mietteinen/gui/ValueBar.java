package com.github.mietteinen.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JComponent;

public class ValueBar extends JComponent {
    
    private int value;
    private int xCoord;
    private int yCoord;
    private int width;
    private int height;
    private Color color;

    public ValueBar(int value, int x, int y, int width, int height, Color color) {
        this.value = value;
        this.xCoord = x;
        this.yCoord = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void draw(Graphics g) {

        // Draw the bar.
        // g.setColor(color);
        // g.fillRect(xCoord, yCoord, width, height);

        // Outline the bar.
        // g.setColor(Color.BLACK);
        // g.drawRect(xCoord, yCoord, width, height);

        Graphics2D g2d = (Graphics2D) g.create();

        // Set the color and shape of the bar.
        g2d.setColor(color);
        RoundRectangle2D bar = new RoundRectangle2D.Double(xCoord, yCoord, width, height, 10, 10);

        // Draw the bar.
        g2d.fill(bar);

        // Outline the bar.
        g2d.setColor(Color.BLACK);
        g2d.draw(bar);
    }

    public void update(int value, int x, int y, int width, int height) {
        this.value = value;
        this.xCoord = x;
        this.yCoord = y;
        this.width = width;
        this.height = height;
    }

    public void move(int x) {
        this.xCoord = x;
    }

    public int getValue() {
        return value;
    }
    
    public int getWidth() {
        return width;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
