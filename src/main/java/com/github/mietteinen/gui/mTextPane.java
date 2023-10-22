/**
 * Filename:    mTextPane.java
 * Author:      Tomi Miettinen
 * Date:        10/2023
 * Description: A subclass of JTextPane that allows for
 *              easier text alignment without having to
 *              use HTML tags.
 */

package com.github.mietteinen.gui;

import javax.swing.JTextPane;

public class mTextPane extends JTextPane {

    private String textAlignment;
    
    public mTextPane(boolean rightAlign) {

        super();

        this.textAlignment = rightAlign ? "right" : "center";

        setEditable(false); setOpaque(false); setContentType("text/html");
    }

    public void setText(String text) {
        
        super.setText("<html><div style='text-align:" + textAlignment + ";'>" + text + "</div></html>");
    }

    public void append(String text) {
        super.setText(getText() + "<html><div style='text-align:" + textAlignment + ";'>" + text + "</div></html>");
    }

    public void clear() {
        super.setText("");
    }
}
