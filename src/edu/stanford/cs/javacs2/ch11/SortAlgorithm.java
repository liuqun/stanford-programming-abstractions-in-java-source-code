/*
 * File: SortAlgorithm.java
 * ------------------------
 * This file defines an abstract class that serves as the superclass for
 * the individual sorting algorithms.
 */

package edu.stanford.cs.javacs2.ch11;

public abstract class SortAlgorithm {

/**
 * Creates a SortAlgorithm whose test program sorts integers in a default
 * range.
 */

   public SortAlgorithm() {
      this(DEFAULT_MAX_ELEMENT);
   }

/**
 * Creates a SortAlgorithm whose test program sorts integers in the inclusive
 * range 0 - max.
 */

   public SortAlgorithm(int max) {
      this.max = max;
   }

/**
 * Sorts an array of integers into ascending order.
 *
 * @param array The array of integers
 */

    public abstract void sort(int[] array);

/**
 * Gets the maximum element allowed for this algorithm.
 *
 * @return The maximum element allowed for this algorithm
 */

   public int getMaxElement() {
      return max;
   }

/**
 * Returns the name of this sorting algorithm.
 *
 * @return The name of this sorting algorithm
 */

   public String getName() {
      String name = getClass().getName();
      int dot = name.lastIndexOf('.');
      return name.substring(dot + 1);
   }

/**
 * Tests whether an integer array is sorted.
 *
 * @param array The array of integers
 * @return The value true if the array is sorted, and false otherwise.
 */

   public boolean isSorted(int[] array) {
      for (int i = 1; i < array.length; i++) {
         if (array[i - 1] > array[i]) return false;
      }
      return true;
   }

/**
 * Tests whether the sorting algorithm works by sorting an array of
 * random integers and checking whether it is sorted.
 */

   public void test() {
      int[] array = new int[max + 1];
      for (int i = 0; i <= max; i++) {
         array[i] = (int) ((max + 1) * Math.random());
      }
      sort(array);
      if (isSorted(array)) {
         System.out.println("Sort test succeeded");
      } else {
         System.out.println("Sort test failed");
      }
   }

/* Constants */

   private static final int DEFAULT_MAX_ELEMENT = 9999;

/* Private instance variables */

   private int max;

}
