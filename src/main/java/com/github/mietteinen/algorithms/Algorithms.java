package com.github.mietteinen.algorithms;

import com.github.mietteinen.gui.SortingVisualizer;
import com.github.mietteinen.gui.ValueBar;

import java.awt.Color;
import java.util.ArrayList;

public class Algorithms {
    
    private enum Algorithm {
        BUBBLE_SORT
    }

    // Implement bubbleSort in a way that it can be used with the SortingVisualizer.
    public static void bubbleSort(SortingVisualizer visualizer) {

        ArrayList<Integer> lst = visualizer.getValues();
        ArrayList<ValueBar> bars = visualizer.getBars();

        int size = lst.size();
        int temp = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 1; j < (size - i); j++) {
                
                ValueBar currentBar = bars.get(j);
                ValueBar previousBar = bars.get(j - 1);

                // Check if j-1 and j are valid indices for both lst and bars
                if (j - 1 >= 0 && j < size) {

                    // Update the bars.
                    //currentBar.setColor(Color.RED);
                    //previousBar.setColor(Color.RED);

                    //visualizer.updateBars();

                    if (lst.get(j - 1) > lst.get(j)) {
                        temp = lst.get(j - 1);

                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            System.out.println("Thread interrupted!");
                        }

                        visualizer.update(j - 1, j);
                        //currentBar.drawOver(visualizer.getGraphics());
                        //previousBar.drawOver(visualizer.getGraphics());
                        //visualizer.updateBars();
                    }

                    //visualizer.updateBars();
                }
                //System.out.println("Bar color: " + currentBar.getColor());
                // Reset the colors
                //currentBar.setColor(Color.WHITE);
                //previousBar.setColor(Color.WHITE);
                //System.out.println("Bar color: " + currentBar.getColor());
            }
        }
        //checkOrder(lst, bars);
    }

    public static void checkOrder(ArrayList<Integer> lst, ArrayList<ValueBar> bars) {

        for (int i = 0; i < lst.size() - 1; i++) {
            if (lst.get(i) < lst.get(i + 1)) {
                bars.get(i).setColor(Color.GREEN);
            }
        }
        System.out.println("List is sorted!");
    }
}
