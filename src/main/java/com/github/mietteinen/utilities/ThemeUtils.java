package com.github.mietteinen.utilities;

import javax.swing.UIManager;
import java.awt.Color;

public class ThemeUtils {
    
    public static Color getBackgroundColor() {
        return UIManager.getColor("background");
    }

    public static Color getForegroundColor() {
        return UIManager.getColor("foreground");
    }
}
