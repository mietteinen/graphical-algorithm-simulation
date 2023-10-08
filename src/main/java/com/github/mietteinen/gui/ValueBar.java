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
        g.setColor(color);
        g.fillRect(xCoord, yCoord, width, height);
    }

    public void move(int x) {
        this.xCoord = x;
    }

    public int getValue() {
        return value;
    }
}
