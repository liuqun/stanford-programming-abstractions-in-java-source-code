/*
 * File: BasicArc.java
 * -------------------
 * This class represents a minimal extension of GenericArc.
 */

package edu.stanford.cs.javacs2.ch17;

public class BasicArc extends GenericArc<BasicNode,BasicArc> {

   public BasicArc(BasicNode n1, BasicNode n2) {
      super(n1, n2);
   }

   public BasicArc(BasicNode n1, BasicNode n2, double cost) {
      super(n1, n2, cost);
   }

}
