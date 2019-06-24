/*
 * File: Path.java
 * ---------------
 * This file exports the Path class, which consists of a sequence of Arc
 * objects.  The Path class is immutable in that paths are never changed
 * once they are constructed.  Clients instead use the extend method to
 * create new paths that contain an additional arc.
 */

package edu.stanford.cs.javacs2.ch17;

import edu.stanford.cs.javacs2.ch13.ArrayList;
import java.util.Iterator;

public class Path implements Comparable<Path>, Iterable<Arc> {

   public Path() {
      arcs = new ArrayList<Arc>();
      totalCost = 0;
   }

/**
 * Creates a new path that has the same arcs as the current one, but
 * includes the new arc at the end.  Note that this method leaves the
 * current path unchanged.
 */

   public Path extend(Arc arc) {
      if (!isEmpty() && getFinish() != arc.getStart()) {
         throw new RuntimeException("Arcs are disconnected");
      }
      Path path = new Path();
      for (Arc a : this.arcs) {
         path.arcs.add(a);
      }
      path.arcs.add(arc);
      path.totalCost = this.totalCost + arc.getCost();
      return path;
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

   public Node getStart() {
      if (arcs.isEmpty()) throw new RuntimeException("Path is empty");
      return arcs.get(0).getStart();
   }

/**
 * Gets the finish node of this path.
 */

   public Node getFinish() {
      if (arcs.isEmpty()) throw new RuntimeException("Path is empty");
      return arcs.get(arcs.size() - 1).getFinish();
   }

/**
 * Converts the path to a string.
 */

   @Override
   public String toString() {
      if (arcs.isEmpty()) return "empty";
      String str = arcs.get(0).getStart().getName();
      for (Arc arc : arcs) {
         str += " -> " + arc.getFinish().getName();
      }
      return str;
   }

/**
 * Compares this path to p2 based on the total cost.
 */

   public int compareTo(Path p2) {
      return (int) Math.signum(this.totalCost - p2.totalCost);
   }

/**
 * Returns an iterator over the arcs in the path.
 */

   public Iterator<Arc> iterator() {
      return arcs.iterator();
   }

/* Private instance variables */

   private ArrayList<Arc> arcs;
   private double totalCost;

}
