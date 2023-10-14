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

    /**
     * Draw the bars that represent numbers
     * in a list.
     * @param g: Graphics object.
     */
    public void draw(Graphics g) {

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

    /**
     * Update the bar's value, x and y coordinates, width and height.
     * @param value: The new value of the bar.
     * @param x: The new x coordinate of the bar.
     * @param y: The new y coordinate of the bar.
     * @param width: The new width of the bar.
     * @param height: The new height of the bar.
     */
    public void update(int value, int x, int y, int width, int height) {
        this.value = value;
        this.xCoord = x;
        this.yCoord = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Update the bar's x coordinate.
     * @param x: The new x coordinate of the bar.
     */
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
