package com.github.mietteinen.algorithms;

import com.github.mietteinen.gui.SortingVisualizer;
import com.github.mietteinen.gui.ValueBar;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

public class Algorithms {

    private static DefaultComboBoxModel<String> algorithms;
    private static int timeBetweenFrames;

    // Statically initialize the algorithms list.
    static {
        algorithms = new DefaultComboBoxModel<String>();
        algorithms.addElement("Bubble Sort");
        algorithms.addElement("Selection Sort");
    }

    /**
     * Sorts the values using the bubble sort algorithm. Updates
     * the bars after each iteration, changing the color of the
     * bars that are being compared.
     * @param visualizer: The SortingVisualizer object that contains
     *      the values and the bars.
     */
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

                    // Reset the color of the bars.
                    for (ValueBar bar : visualizer.getBars()) {
                        bar.setColor(Color.WHITE);
                    }
                    visualizer.updateBars();

                    return;
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

    /**
     * Sorts the values using the selection sort algorithm. Updates
     * the bars after each iteration, changing the color of the
     * bars that are being compared.
     * @param visualizer: The SortingVisualizer object that contains
     *     the values and the bars.
     */
    public static void selectionSort(SortingVisualizer visualizer) {

        ArrayList<Integer> lst = visualizer.getValues();
        ArrayList<ValueBar> bars = visualizer.getBars();

        int size = lst.size();

        for (int i = 0; i < size - 1; i++) {

            int minIndex = i;

            ValueBar currentSmallestBar = bars.get(minIndex);
            
            for (int j = i + 1; j < size; j++) {
                
                ValueBar currentCompareBar = bars.get(j);
                
                currentSmallestBar.setColor(Color.RED);
                currentCompareBar.setColor(Color.LIGHT_GRAY);
                visualizer.updateBars();
                
                try {
                    
                    Thread.sleep(timeBetweenFrames / 2);
                    
                } catch (InterruptedException e) {
                    
                    System.out.println("Thread interrupted!");
                    
                    // Reset the color of the bars.
                    for (ValueBar bar : visualizer.getBars()) {
                        bar.setColor(Color.WHITE);
                    }
                    visualizer.updateBars();
                    
                    return;
                }

                if (lst.get(j) < lst.get(minIndex)) {
                    minIndex = j;
                    
                    currentSmallestBar.setColor(Color.WHITE);
                    currentSmallestBar = bars.get(minIndex);
                    currentSmallestBar.setColor(Color.RED);
                }
                currentCompareBar.setColor(Color.WHITE);
            }

            bars.get(i).setColor(Color.RED);
            visualizer.updateBars();
            try {
                
                Thread.sleep(timeBetweenFrames / 2);
                
            } catch (InterruptedException e) {
                
                System.out.println("Thread interrupted!");
                
                // Reset the color of the bars.
                for (ValueBar bar : visualizer.getBars()) {
                    bar.setColor(Color.WHITE);
                }
                visualizer.updateBars();
                
                return;
            }
            visualizer.update(minIndex, i);


            currentSmallestBar.setColor(Color.YELLOW);
        }
        bars.get(size - 1).setColor(Color.YELLOW);
        visualizer.updateBars();
        checkOrder(visualizer);
    }
    
    /**
     * Checks if the values are in order. Updates the bars
     * after each iteration, changing the color of the bars
     * that are being checked currently.
     * @param visualizer: The SortingVisualizer object that contains
     *     the values and the bars.
     */
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

    public static DefaultComboBoxModel<String> getAlgorithms() {
        return algorithms;
    }
}
