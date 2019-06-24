/*
 * File: MinimalNode.java
 * ----------------------
 * This file defines a minimal extension of the GenericNode class that
 * includes nothing beyond the standard definitions.
 */

package edu.stanford.cs.javacs2.ch17;

class MinimalNode extends GenericNode<MinimalNode,MinimalArc> {

   public MinimalNode(String name) {
      super(name);
   }

}
