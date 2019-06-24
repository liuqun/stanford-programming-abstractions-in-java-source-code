/*
 * File: SelectionSort.java
 * ------------------------
 * This file implements SortAlgorithm using selection sort.
 */

package edu.stanford.cs.javacs2.ch11;

public class SelectionSort extends SortAlgorithm {

/**
 * Sorts an array of integers into ascending order.
 */

   @Override
   public void sort(int[] array) {
      for (int lh = 0; lh < array.length - 1; lh++) {
         int rh = findSmallest(array, lh);
         swapArrayElements(array, lh, rh);
      }
   }

/**
 * Returns the index of the smallest element in the array between the
 * specified start position and the end of the array.
 */

   private int findSmallest(int[] array, int start) {
      int rh = start;
      for (int i = start + 1; i < array.length; i++) {
         if (array[i] < array[rh]) {
            rh = i;
         }
      }
      return rh;
   }

/**
 * Exchanges the array elements at index positions p1 and p2.
 */

   private void swapArrayElements(int[] array, int p1, int p2) {
      int tmp = array[p1];
      array[p1] = array[p2];
      array[p2] = tmp;
   }

/* Main program */

   public static void main(String[] args) {
      new SelectionSort().test();
   }

}
