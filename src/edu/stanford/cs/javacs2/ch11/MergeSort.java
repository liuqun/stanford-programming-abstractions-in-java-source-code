/*
 * File: MergeSort.java
 * --------------------
 * This file implements SortAlgorithm using merge sort.
 */

package edu.stanford.cs.javacs2.ch11;

public class MergeSort extends SortAlgorithm {

/**
 * Sorts an array of integers into ascending order.
 */

   @Override
   public void sort(int[] array) {
      if (array.length > 1) {
         int half = array.length / 2;
         int[] a1 = subarray(array, 0, half);
         int[] a2 = subarray(array, half, array.length);
         sort(a1);
         sort(a2);
         merge(array, a1, a2);
      }
   }

/**
 * Merges the two sorted arrays a1 and a2 into the array storage passed
 * as the first parameter.
 */

   private void merge(int[] array, int[] a1, int[] a2) {
      int n1 = a1.length;
      int n2 = a2.length;
      int p1 = 0;
      int p2 = 0;
      for (int i = 0; i < array.length; i++) {
         if (p2 == n2 || (p1 < n1 && a1[p1] < a2[p2])) {
            array[i] = a1[p1++];
         } else {
            array[i] = a2[p2++];
         }
      }
   }

/**
 * Creates a new array that contains the elements from array starting
 * at p1 and continuing up to but not including p2.
 */

   private int[] subarray(int[] array, int p1, int p2) {
      int[] result = new int[p2 - p1];
      for (int i = p1; i < p2; i++) {
         result[i - p1] = array[i];
      }
      return result;
   }

/* Main program */

   public static void main(String[] args) {
      new MergeSort().test();
   }

}
