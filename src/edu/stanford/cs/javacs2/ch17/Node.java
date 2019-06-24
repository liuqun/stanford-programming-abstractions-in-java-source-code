/*
 * File: Node.java
 * ---------------
 * This file exports the basic Node class, which contains only the minimal
 * information necessary to implement the Graph abstraction.  Clients that
 * want to extend nodes should use the GenericNode class instead.
 */

package edu.stanford.cs.javacs2.ch17;

import edu.stanford.cs.javacs2.ch16.XSet;

public class Node implements Comparable<Node> {

   public Node(String name) {
      this.name = name;
      arcs = new XSet<Arc>();
   }

   public String getName() {
      return name;
   }

   public XSet<Arc> getArcs() {
      return arcs;
   }

   public void addArc(Arc arc) {
      arcs.add(arc);
   }

   public boolean isConnectedTo(Node n2) {
      for (Arc arc : arcs) {
         if (arc.getFinish() == n2) return true;
      }
      return false;
   }

   public int compareTo(Node n2) {
      return name.compareTo(n2.getName());
   }

   @Override
   public String toString() {
      return name;
   }

/* Private instance variables */

   private String name;
   private XSet<Arc> arcs;

}
