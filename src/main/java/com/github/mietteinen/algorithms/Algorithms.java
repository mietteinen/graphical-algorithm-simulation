package com.github.mietteinen.algorithms;

import com.github.mietteinen.gui.SortingVisualizer;
import com.github.mietteinen.gui.ValueBar;

import java.awt.Color;
import java.util.ArrayList;

public class Algorithms {
    
    private enum Algorithm {
        BUBBLE_SORT
    }

    private static int timeBetweenFrames;

    // Implement bubbleSort in a way that it can be used with the SortingVisualizer.
    public static void bubbleSort(SortingVisualizer visualizer) {

        ArrayList<Integer> lst = visualizer.getValues();
        ArrayList<ValueBar> bars = visualizer.getBars();

        int size = lst.size();

        for (int i = 0; i < size; i++) {
            for (int j = 1; j < (size - i); j++) {
                
                ValueBar currentBar = bars.get(j - 1);
                
                // Update the bars.
                currentBar.setColor(Color.RED);
                visualizer.updateBars();
                
                try {
                    Thread.sleep(timeBetweenFrames);
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted!");
                }
                
                // Swap the values if the current value is greater than the next value.
                if (j - 1 >= 0 && j < size) {
                    
                    if (lst.get(j - 1) > lst.get(j)) {
                        
                        visualizer.update(j - 1, j);
                    }
                    
                }
                
                
                // Reset the color.
                currentBar.setColor(Color.WHITE);
            }
        }
        checkOrder(visualizer);
    }

    public static void checkOrder(SortingVisualizer visualizer) {

        ArrayList<Integer> lst = visualizer.getValues();
        ArrayList<ValueBar> bars = visualizer.getBars();
        ValueBar currentBar;

        for (int i = 0; i < lst.size(); i++) {

            currentBar = bars.get(i);

            // Check for the last item in the list.
            if (i == lst.size() - 1) {
                currentBar.setColor(Color.GREEN);
                visualizer.updateBars();
                break;
            }

            // Check if the current item is greater than the next item.
            if (lst.get(i) > lst.get(i + 1)) {
                currentBar.setColor(Color.RED);
            } else {
                currentBar.setColor(Color.GREEN);
            }

            visualizer.updateBars();

            try {
                Thread.sleep(timeBetweenFrames);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted!");
            }
        }
    }

    public static void setSpeed(int framesPerSecond) {
        
        if (framesPerSecond == 0) {
            timeBetweenFrames = 0;
            return;
        }

        // Calculate the time between frames in milliseconds.
        timeBetweenFrames = 1000 / framesPerSecond;
    }
}
