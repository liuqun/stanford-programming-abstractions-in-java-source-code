/*
 * File: MinimalGenericGraphTest.java
 * ----------------------------------
 * This file implements an interactive test of the GenericGraph abstraction
 * using a minimal extension of the GenericGraph, GenericNode, and
 * GenericArc classes that adds no functionality beyond what is
 * provided in the simple Graph abstraction.
 */

package edu.stanford.cs.javacs2.ch17;

public class MinimalGenericGraphTest
       extends GenericGraphTest<MinimalGraph,MinimalNode,MinimalArc> {

   @Override
   public MinimalGraph createGraph() {
      return new MinimalGraph();
   }

/* Main program */

   public static void main(String[] args) {
      new MinimalGenericGraphTest().run();
   }

}
