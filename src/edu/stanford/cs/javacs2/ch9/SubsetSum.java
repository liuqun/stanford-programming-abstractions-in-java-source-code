/*
 * File: SubsetSum.java
 * --------------------
 * This file tests the subsetSumExists function.
 */

package edu.stanford.cs.javacs2.ch9;

import java.util.TreeSet;

public class SubsetSum {

   public void run() {
      TreeSet<Integer> set = createIntSet(-2, 1, 3, 8);
      System.out.println("set = " + toString(set));
      int min = minSum(set);
      int max = maxSum(set);
      for (int i = min; i <= max; i++) {
         System.out.println("subsetSumExists(set, " + i + ") = " +
                            subsetSumExists(set, i));
      }
   }

   private TreeSet<Integer> createIntSet(int... args) {
      TreeSet<Integer> set = new TreeSet<Integer>();
      for (int n : args) {
         set.add(n);
      }
      return set;
   }

   private boolean subsetSumExists(TreeSet<Integer> set, int target) {
      if (set.isEmpty()) {
         return target == 0;
      } else {
         int element = set.first();
         TreeSet<Integer> rest = new TreeSet<Integer>(set);
         rest.remove(element);
         return subsetSumExists(rest, target)
             || subsetSumExists(rest, target - element);
      }
   }

   private int minSum(TreeSet<Integer> set) {
      int total = 0;
      for (int element : set) {
         if (element < 0) total += element;
      }
      return total;
   }

   private int maxSum(TreeSet<Integer> set) {
      int total = 0;
      for (int element : set) {
         if (element > 0) total += element;
      }
      return total;
   }

/**
 * Returns the String representation of the set using the
 * conventional curly-brace representation.
 */

   private String toString(TreeSet<Integer> set) {
      String str = "{";
      for (int element : set) {
         if (str.length() > 1) str += ",";
         str += " " + element;
      }
      return str + " }";
   }

/* Main program */

   public static void main(String[] args) {
      new SubsetSum().run();
   }

}
