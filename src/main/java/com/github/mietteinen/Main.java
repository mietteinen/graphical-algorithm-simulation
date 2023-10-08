package com.github.mietteinen;

import com.github.mietteinen.gui.MainWindow;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                
                MainWindow app = new MainWindow();
                app.show();
            }
        });
    }
}