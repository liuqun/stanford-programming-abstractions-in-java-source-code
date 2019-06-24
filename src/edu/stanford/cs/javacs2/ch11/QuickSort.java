/*
 * File: QuickSort.java
 * --------------------
 * This file implements SortAlgorithm using the Quicksort algorithm.
 */

package edu.stanford.cs.javacs2.ch11;

public class QuickSort extends SortAlgorithm {

/**
 * Sorts an array of integers into ascending order.
 */

   @Override
   public void sort(int[] array) {
      quicksort(array, 0, array.length);
   }

/**
 * Applies the Quicksort algorithm to the elements in the subarray
 * starting at p1 and continuing up to but not including p2.
 */

   private void quicksort(int[] array, int p1, int p2) {
      if (p2 - 1 <= p1) return;
      int boundary = partition(array, p1, p2);
      quicksort(array, p1, boundary);
      quicksort(array, boundary + 1, p2);
   }

/**
 * Rearranges the elements of the subarray delimited by the indices
 * p1 and p2 so that "small" elements are grouped at the left end
 * of the array and "large" elements are grouped at the right end.
 * The distinction between small and large is made by comparing each
 * element to the "pivot" value, which initially appears in array[start].
 * When the partitioning is done, the function returns a boundary index
 * such that array[i] < pivot for all i < boundary, array[i] == pivot
 * for i == boundary, and array[i] >= pivot for all i > boundary.
 */

   private int partition(int[] array, int p1, int p2) {
      int pivot = array[p1];
      int lh = p1 + 1;
      int rh = p2 - 1;
      while (true) {
         while (lh < rh && array[rh] >= pivot) rh--;
         while (lh < rh && array[lh] < pivot) lh++;
         if (lh == rh) break;
         int tmp = array[lh];
         array[lh] = array[rh];
         array[rh] = tmp;
      }
      if (array[lh] >= pivot) return p1;
      array[p1] = array[lh];
      array[lh] = pivot;
      return lh;
   }

/* Main program */

   public static void main(String[] args) {
      new QuickSort().test();
   }

}
