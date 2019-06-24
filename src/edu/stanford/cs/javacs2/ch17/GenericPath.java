/*
 * File: GenericPath.java
 * ----------------------
 * This file exports a generic version of the Path class, which is
 * parameterized by the concrete node and arc types.  The GenericPath
 * class is immutable in that paths are never changed once they are
 * constructed.  Clients instead use the extend method to create new
 * paths that contain an additional arc.
 */

package edu.stanford.cs.javacs2.ch17;

import edu.stanford.cs.javacs2.ch13.ArrayList;

public class GenericPath<N extends GenericNode<N,A>,
                         A extends GenericArc<N,A>>
                         implements Comparable<GenericPath<N,A>> {

   public GenericPath() {
      arcs = new ArrayList<A>();
      totalCost = 0;
      count = 0;
   }

/**
 * Creates a new path that has the same arcs as the current one, but
 * includes the new arc at the end.  Note that this method leaves the
 * current path unchanged.
 */

   public GenericPath<N,A> extend(A arc) {
      if (!isEmpty() && getFinish() != arc.getStart()) {
         throw new RuntimeException("Arcs are disconnected");
      }
      GenericPath<N,A> path = new GenericPath<N,A>();
      for (A a : this.arcs) {
         path.arcs.add(a);
      }
      path.arcs.add(arc);
      path.totalCost = this.totalCost + arc.getCost();
      path.count = this.count + 1;
      return path;
   }

/**
 * Gets the size of this path, which is the number of arcs.
 */

   public int size() {
      return count;
   }

/**
 * Returns true if this path is empty.
 */

   public boolean isEmpty() {
      return arcs.isEmpty();
   }

/**
 * Gets the total cost of this path.
 */

   public double getCost() {
      return totalCost;
   }

/**
 * Gets the starting node of this path.
 */

   public N getStart() {
      if (count == 0) throw new RuntimeException("Path is empty");
      return arcs.get(0).getStart();
   }

/**
 * Gets the finish node of this path.
 */

   public N getFinish() {
      if (count == 0) throw new RuntimeException("Path is empty");
      return arcs.get(count - 1).getFinish();
   }

/**
 * Converts the path to a string.
 */

   @Override
   public String toString() {
      if (count == 0) return "empty";
      String str = arcs.get(0).getStart().getName();
      for (A arc : arcs) {
         str += " -> " + arc.getFinish().getName();
      }
      return str;
   }

/**
 * Compares this path to p2 based on the total cost.
 */

   public int compareTo(GenericPath<N,A> p2) {
      if (this.totalCost > p2.totalCost) return +1;
      if (this.totalCost < p2.totalCost) return -1;
      return 0;
   }

/* Private instance variables */

   private ArrayList<A> arcs;
   private double totalCost;
   private int count;

}
