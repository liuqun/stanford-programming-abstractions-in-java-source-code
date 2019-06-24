/*
 * File: MinimalArc.java
 * ---------------------
 * This file defines a minimal extension of the GenericArc class that
 * includes nothing beyond the standard definitions.
 */

package edu.stanford.cs.javacs2.ch17;

class MinimalArc extends GenericArc<MinimalNode,MinimalArc> {

   public MinimalArc(MinimalNode n1, MinimalNode n2) {
      super(n1, n2);
   }

   public MinimalArc(MinimalNode n1, MinimalNode n2, double cost) {
      super(n1, n2, cost);
   }

}
