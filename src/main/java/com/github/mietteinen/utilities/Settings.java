/**
 * Filename:    Settings.java
 * Author:      Tomi Miettinen
 * Date:        10/2023
 * Description: This class is used to store the settings
 *              of the application. Includes only simple
 *              getters and setters.
 */

package com.github.mietteinen.utilities;

public class Settings {

    private String algorithm;
    private boolean lightMode;
    private boolean drawOutline;

    public Settings(String algorithm, boolean lightMode, boolean drawOutline) {
        this.algorithm = algorithm;
        this.lightMode = lightMode;
        this.drawOutline = drawOutline;
    }
    
    public String getAlgorithm() {
        return algorithm;
    }

    public boolean getLightMode() {
        return lightMode;
    }

    public boolean getDrawOutline() {
        return drawOutline;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public void setLightMode(boolean lightMode) {
        this.lightMode = lightMode;
    }

    public void setDrawOutline(boolean drawOutline) {
        this.drawOutline = drawOutline;
    }
}
