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

        for (int i = 0; i < size; i++) {
            for (int j = 1; j < (size - i); j++) {
                
                ValueBar currentBar = bars.get(j - 1);

                // Check if j-1 and j are valid indices for both lst and bars
                if (j - 1 >= 0 && j < size) {

                    // Update the bars.
                    currentBar.setColor(Color.RED);

                    visualizer.updateBars();

                    if (lst.get(j - 1) > lst.get(j)) {

                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            System.out.println("Thread interrupted!");
                        }

                        visualizer.update(j - 1, j);
                        visualizer.updateBars();
                    }

                }

                // Reset the colors
                currentBar.setColor(Color.WHITE);
                visualizer.updateBars();
            }
        }
        checkOrder(visualizer);
    }

    public static void checkOrder(SortingVisualizer visualizer) {

        ArrayList<Integer> lst = visualizer.getValues();
        ArrayList<ValueBar> bars = visualizer.getBars();

        for (int i = 0; i < lst.size(); i++) {

            // Check for the last item in the list.
            if (i == lst.size() - 1) {
                bars.get(i).setColor(Color.GREEN);
                visualizer.updateBars();
                break;
            }

            // Check if the current item is greater than the next item.
            if (lst.get(i) > lst.get(i + 1)) {
                bars.get(i).setColor(Color.RED);
            } else {
                bars.get(i).setColor(Color.GREEN);
            }

            visualizer.updateBars();

            // Wait 500 milliseconds to make the check easier to see.
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted!");
            }
        }
    }
}
