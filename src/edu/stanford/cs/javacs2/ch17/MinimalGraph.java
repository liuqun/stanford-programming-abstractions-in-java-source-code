/*
 * File: MinimalGraph.java
 * -----------------------
 * This file defines a minimal extension of the GenericGraph class that
 * includes nothing beyond the standard definitions.
 */

package edu.stanford.cs.javacs2.ch17;

class MinimalGraph extends GenericGraph<MinimalNode,MinimalArc> {

   public MinimalGraph() {
      super();
   }

   public MinimalGraph(String filename) {
      super(filename);
   }

   @Override
   public MinimalNode createNode(String name, String parameter) {
      return new MinimalNode(name);
   }

   @Override
   public MinimalArc createArc(MinimalNode start, MinimalNode finish,
                                                  String parameter) {
      if (parameter.isEmpty()) {
         return new MinimalArc(start, finish);
      } else {
         return new MinimalArc(start, finish, Double.parseDouble(parameter));
      }
   }

}
