/*
 * File: Arc.java
 * --------------
 * This file exports the basic Arc class, which contains only the minimal
 * information necessary to implement the Graph abstraction.  Clients that
 * want to extend arcs should use the GenericArc class instead.
 */

package edu.stanford.cs.javacs2.ch17;

public class Arc implements Comparable<Arc> {

   public Arc(Node start, Node finish) {
      this(start, finish, 0);
   }

   public Arc(Node start, Node finish, double cost) {
      this.start = start;
      this.finish = finish;
      this.cost = cost;
   }

   public Node getStart() {
      return start;
   }

   public Node getFinish() {
      return finish;
   }

   public double getCost() {
      return cost;
   }

   public int compareTo(Arc a2) {
      if (this == a2) return 0;
      int cmp = start.compareTo(a2.getStart());
      if (cmp != 0) return cmp;
      cmp = finish.compareTo(a2.getFinish());
      if (cmp != 0) return cmp;
      if (cost < a2.cost) return -1;
      if (cost > a2.cost) return 1;
      return a2.hashCode() - hashCode();
   }

   @Override
   public String toString() {
      return start + "->" + finish;
   }

/* Private instance variables */

   private Node start;
   private Node finish;
   private double cost;

}
