/*
 * File: AirlineGraph.java
 * -----------------------
 * This program displays the structure of the airline graph.
 */

package edu.stanford.cs.javacs2.ch17;

public class AirlineGraph {

   public void run() {
      Graph airline = createGraph();
      printAdjacencyLists(airline);
   }

/*
 * Prints the adjacency list for each city in the graph.
 */

   private void printAdjacencyLists(Graph g) {
      for (Node node : g.getNodeSet()) {
         System.out.print(node.getName() + " -> ");
         boolean first = true;
         for (Arc arc : node.getArcs()) {
            if (!first) System.out.print(", ");
            System.out.print(arc.getFinish().getName());
            first = false;
         }
         System.out.println();
      }
   }

/*
 * Creates an airline graph containing the flight data from Figure 17-2.
 * Real applications would almost certainly read the data from a file.
 */

   public Graph createGraph() {
      Graph airline = new Graph();
      addFlight(airline, "Atlanta", "Chicago", 599);
      addFlight(airline, "Atlanta", "Dallas", 725);
      addFlight(airline, "Atlanta", "New York", 756);
      addFlight(airline, "Boston", "New York", 191);
      addFlight(airline, "Boston", "Seattle", 2489);
      addFlight(airline, "Chicago", "Denver", 907);
      addFlight(airline, "Dallas", "Denver", 650);
      addFlight(airline, "Dallas", "Los Angeles", 1240);
      addFlight(airline, "Dallas", "San Francisco", 1468);
      addFlight(airline, "Denver", "San Francisco", 954);
      addFlight(airline, "Portland", "San Francisco", 550);
      addFlight(airline, "Portland", "Seattle", 130);
      return airline;
   }

/*
 * Adds an arc in each direction between the cities c1 and c2.
 */

   private void addFlight(Graph airline, String c1, String c2, int miles) {
      Node n1 = airline.getNode(c1);
      if (n1 == null) airline.addNode(n1 = new Node(c1));
      Node n2 = airline.getNode(c2);
      if (n2 == null) airline.addNode(n2 = new Node(c2));
      Arc arc = new Arc(n1, n2, miles);
      airline.addArc(arc);
      arc = new Arc(n2, n1, miles);
      airline.addArc(arc);
   }

/* Main program */

   public static void main(String[] args) {
      new AirlineGraph().run();
   }

}
