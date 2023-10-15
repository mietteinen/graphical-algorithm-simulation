package com.github.mietteinen.gui;

import javax.swing.JTextPane;

public class mTextPane extends JTextPane {
    
    public mTextPane() {

        super();

        setEditable(false);
        setOpaque(false);
        setContentType("text/html");
    }

    public void append(String text) {
        super.setText(super.getText() + text);
    }

    public void clear() {
        super.setText("");
    }

    public void setText(String text) {
        
        super.setText("<html><div style='text-align:right;'>" + text + "</div></html>");
    }
}
