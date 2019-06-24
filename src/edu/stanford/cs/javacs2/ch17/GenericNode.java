/*
 * File: GenericNode.java
 * ----------------------
 * This file exports the basic Node class, which contains only the minimal
 * information necessary to implement the Graph abstraction.
 */

package edu.stanford.cs.javacs2.ch17;

import edu.stanford.cs.javacs2.ch16.XSet;

/**
 * Represents the most basic class of node.  Subclasses may extend this
 * structure to add other fields necessary to represent a particular node
 * type.
 */

public class GenericNode<N extends GenericNode<N,A>,
                         A extends GenericArc<N,A>>
                         implements Comparable<GenericNode<N,A>> {

   public GenericNode(String name) {
      this.name = name;
      arcs = new XSet<A>();
   }

   public String getName() {
      return name;
   }

   public XSet<A> getArcs() {
      return arcs;
   }

   public void addArc(A arc) {
      arcs.add(arc);
   }

   public int compareTo(GenericNode<N,A> n2) {
      return name.compareTo(n2.getName());
   }

/**
 * Returns the parameter string used when writing out a node.  If the
 * return value is <code>null</code>, no parameter string appears.
 */

   public String parameterString() {
      return null;
   }

   @Override
   public String toString() {
      return name;
   }

/* Private instance variables */

   private String name;
   private XSet<A> arcs;

}
