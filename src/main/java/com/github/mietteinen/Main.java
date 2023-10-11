package com.github.mietteinen;

import com.github.mietteinen.gui.MainWindow;
import com.formdev.flatlaf.FlatDarculaLaf;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (Exception e) {
            System.out.println("Could not set system look and feel.");
        }

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                
                MainWindow app = new MainWindow();
                app.show();
            }
        });
    }
}