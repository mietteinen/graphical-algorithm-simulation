package com.github.mietteinen.gui;

import java.awt.Graphics;
import java.awt.Color;

public class ValueBar {
    
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
        g.setColor(color);
        g.fillRect(xCoord, yCoord, width, height);

        // Outline the bar.
        g.setColor(Color.BLACK);
        g.drawRect(xCoord, yCoord, width, height);
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

    public void setColor(Color color) {
        this.color = color;
    }
}
