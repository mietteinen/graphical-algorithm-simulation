/**
 * Filename:    Algorithms.java
 * Author:      Tomi Miettinen
 * Date:        10/2023
 * Description: Contains the implementations of different
 *              sorting algorithms, specifically engineered
 *              to work with the SortingVisualizer class.
 */

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
        algorithms.addElement("Merge Sort");
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
     * Starts the merge sort algorithm.
     * @param visualizer: The SortingVisualizer object that contains
     *    the values and the bars.
     */
    public static void mergeSortMain(SortingVisualizer visualizer) {

        ArrayList<Integer> lst = visualizer.getValues();

        mergeSort(visualizer, 0, lst.size() - 1);
        checkOrder(visualizer);
    }

    /**
     * Sorts the values using the merge sort algorithm. Updates
     * the bars after each iteration, changing the color of the
     * bounds of each sublist.
     * @param visualizer: The SortingVisualizer object that contains
     *     the values and the bars.
     * @param left: The leftmost index of the sublist.
     * @param right: The rightmost index of the sublist.
     */
    private static void mergeSort(SortingVisualizer visualizer, int left, int right) {

        ArrayList<ValueBar> bars = visualizer.getBars();

        ValueBar currentLeftBar = bars.get(left);
        ValueBar currentRightBar = bars.get(right);

        if (left >= right) {
            return;
        }

        int middle = (left + right) / 2;

        ValueBar currentMiddleBar = bars.get(middle);

        currentLeftBar.setColor(Color.RED);
        currentRightBar.setColor(Color.RED);
        currentMiddleBar.setColor(Color.YELLOW);
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

        for (int i = middle + 1; i <= right; i++) {
            bars.get(i).setColor(Color.WHITE);
            visualizer.updateBars();
        }

        mergeSort(visualizer, left, middle);
        visualizer.updateBars();

        mergeSort(visualizer, middle + 1, right);
        visualizer.updateBars();

        merge(visualizer, left, middle, right);
        visualizer.updateBars();
    }

    /**
     * Merges two sublists together. Changes the color of the bars
     * that have been merged to yellow.
     * @param visualizer: The SortingVisualizer object that contains
     *     the values and the bars.
     * @param left: The leftmost index of the sublist.
     * @param middle: The middle index of the sublist.
     * @param right: The rightmost index of the sublist.
     */
    public static void merge(SortingVisualizer visualizer, int left, int middle, int right) {

        ArrayList<Integer> lst = visualizer.getValues();
        ArrayList<ValueBar> bars = visualizer.getBars();

        int leftSize = middle - left + 1;
        int rightSize = right - middle;

        int[] leftArray = new int[leftSize];
        int[] rightArray = new int[rightSize];

        // Copy the values to the temporary arrays.
        for (int i = 0; i < leftSize; i++) {
            leftArray[i] = lst.get(left + i);
        }
        for (int i = 0; i < rightSize; i++) {
            rightArray[i] = lst.get(middle + 1 + i);
        }

        int indexLeft = 0;
        int indexRight = 0;
        int indexMerged = left;

        while (indexLeft < leftSize && indexRight < rightSize) {

            if (leftArray[indexLeft] <= rightArray[indexRight]) {

                lst.set(indexMerged, leftArray[indexLeft]);
                bars.get(indexMerged).setValue(leftArray[indexLeft]);

                indexLeft++;

            } else {

                lst.set(indexMerged, rightArray[indexRight]);
                bars.get(indexMerged).setValue(rightArray[indexRight]);

                indexRight++;
            }

            // Set bar color to yellow.
            bars.get(indexMerged).setColor(Color.YELLOW);
            indexMerged++;
        }

        // Copy any remaining values from the left or right arrays.
        while (indexLeft < leftSize) {
            lst.set(indexMerged, leftArray[indexLeft]);
            bars.get(indexMerged).setValue(leftArray[indexLeft]);
            bars.get(indexMerged).setColor(Color.YELLOW);
            indexLeft++;
            indexMerged++;
        }

        while (indexRight < rightSize) {
            lst.set(indexMerged, rightArray[indexRight]);
            bars.get(indexMerged).setValue(rightArray[indexRight]);
            bars.get(indexMerged).setColor(Color.YELLOW);
            indexRight++;
            indexMerged++;
        }
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

                Thread.sleep(50);

            } catch (InterruptedException e) {

                System.out.println("Thread interrupted!");

                // Reset the color of the bars.
                for (ValueBar bar : visualizer.getBars()) {
                    bar.setColor(Color.WHITE);
                }
                visualizer.updateBars();
                
                return;
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
