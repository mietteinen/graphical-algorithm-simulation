/**
 * Filename:    ThemeUtils.java
 * Author:      Tomi Miettinen
 * Date:        10/2023
 * Description: This class is a static utility class used
 *              for theming-related tasks.
 */

package com.github.mietteinen.utilities;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.GridBagConstraints;

public class ThemeUtils {
    
    public static Color getBackgroundColor() {
        return UIManager.getColor("background");
    }

    public static Color getForegroundColor() {
        return UIManager.getColor("foreground");
    }

    /**
     * Creates a GridBagConstraints object with the given
     * parameters. The fill is set to BOTH and the weight
     * is set to 1.0.
     * @param x: The x coordinate of the component.
     * @param y: The y coordinate of the component.
     * @param gridWidth: The width of the component.
     * @param gridHeight: The height of the component.
     * @param anchor: The anchor of the component.
     * @return: The GridBagConstraints object.
     */
    public static GridBagConstraints createGridBagConstraints(int x, int y, int gridWidth, int gridHeight, int anchor) {

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = gridWidth;
        gbc.gridheight = gridHeight;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = anchor;
        return gbc;
    }

    /**
     * Creates a GridBagConstraints object with the given parameters.  
     * The fill is only horizontal and the weight is 0.5.
     * @param x: The x coordinate of the component.
     * @param y: The y coordinate of the component.
     * @param width: The width of the component.
     * @param height: The height of the component.
     * @param anchor: The anchor of the component.
     * @return: A GridBagConstraints object.
     */
    public static GridBagConstraints createSettingGridBagConstraints(int x, int y, int width, int height, int anchor) {
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = width;
        c.gridheight = height;
        c.anchor = anchor;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.5;
        
        return c;
    }
}
