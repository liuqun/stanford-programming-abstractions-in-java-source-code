/*
 * File: GenericArc.java
 * ---------------------
 * This file exports the basic Arc class, which contains only the minimal
 * information necessary to implement the Graph abstraction.
 */

package edu.stanford.cs.javacs2.ch17;

/**
 * Represents the most basic class of arc.  Subclasses may extend this
 * structure to add other fields necessary to represent a particular node
 * type.
 */

public class GenericArc<N extends GenericNode<N,A>,
                        A extends GenericArc<N,A>>
                        implements Comparable<GenericArc<N,A>> {

   public GenericArc(N start, N finish) {
      this(start, finish, 0);
   }

   public GenericArc(N start, N finish, double cost) {
      this.start = start;
      this.finish = finish;
      this.cost = cost;
   }

   public N getStart() {
      return start;
   }

   public N getFinish() {
      return finish;
   }

   public double getCost() {
      return cost;
   }

   public int compareTo(GenericArc<N,A> a2) {
      if (this == a2) return 0;
      int cmp = start.compareTo(a2.getStart());
      if (cmp != 0) return cmp;
      cmp = finish.compareTo(a2.getFinish());
      if (cmp != 0) return cmp;
      if (cost < a2.cost) return -1;
      if (cost > a2.cost) return 1;
      return a2.hashCode() - hashCode();
   }

/**
 * Returns the parameter string used when writing out an arc.  If the
 * return value is <code>null</code>, no parameter string appears.
 */

   public String parameterString() {
      if (cost == 0) return null;
      if (cost == Math.floor(cost)) {
         return Integer.toString((int) cost);
      } else {
         return Double.toString(cost);
      }
   }

   @Override
   public String toString() {
      return start + "->" + finish;
   }

/* Private instance variables */

   private N start;
   private N finish;
   private double cost;

}
