package com.github.mietteinen.algorithms;

import com.github.mietteinen.gui.ValueBar;

import java.awt.Color;
import java.util.ArrayList;

public class Algorithms {
    
    private enum Algorithm {
        BUBBLE_SORT
    }

    // Implement bubbleSort in a way that it can be used with the SortingVisualizer.
    public static void bubbleSort(ArrayList<Integer> lst, ArrayList<ValueBar> bars) {

        int size = lst.size();
        int temp = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 1; j < (size - i); j++) {

                // Check if j-1 and j are valid indices for both lst and bars
                if (j - 1 >= 0 && j < lst.size() && j < bars.size()) {
                    // Update the bars.
                    bars.get(j - 1).setColor(Color.RED);
                    bars.get(j).setColor(Color.RED);

                    if (lst.get(j - 1) > lst.get(j)) {
                        temp = lst.get(j - 1);

                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            System.out.println("Thread interrupted!");
                        }

                        lst.set(j - 1, lst.get(j));
                        lst.set(j, temp);
                    }

                // Reset the colors
                bars.get(j - 1).setColor(Color.WHITE);
                bars.get(j).setColor(Color.WHITE);  
                }
            }
            //checkOrder(lst, bars);
        }
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
