/*
 * File: Dijkstra.java
 * -------------------
 * This program performs Dijkstra's algorithm on the airline graph.
 */

package edu.stanford.cs.javacs2.ch17;

import edu.stanford.cs.console.Console;
import edu.stanford.cs.console.SystemConsole;
import edu.stanford.cs.javacs2.ch14.HashMap;
import java.util.PriorityQueue;

public class Dijkstra {

   public void run() {
      Console console = new SystemConsole();
      Graph airline = createAirlineGraph();
      Node start = askUserForNode(console, airline, "Enter starting city: ");
      Node finish = askUserForNode(console, airline, "Enter ending city: ");
      Path path = findMinimumPath(start, finish);
      if (path == null) {
         System.out.println("No path exists.");
      } else {
         System.out.println(path);
      }
   }

/**
 * Asks the user to enter the name of a node in the graph and returns the
 * Node object.  If the user enters a nonexistent node, askUserForNode
 * prints the prompt again and asks the user to reenter the name.
 */

   private Node askUserForNode(Console console, Graph g, String prompt) {
      while (true) {
         System.out.print(prompt);
         Node node = g.getNode(console.nextLine());
         if (node != null) return node;
         System.out.println("Unknown node name");
      }
   }

/*
 * Finds the minimum-cost path between start and finish using Dijkstra's
 * algorithm, which keeps track of the shortest paths in a priority
 * queue.  The method returns a Path object, or null if no path exists.
 */

   private Path findMinimumPath(Node start, Node finish) {
      Path path = new Path();
      PriorityQueue<Path> queue = new PriorityQueue<Path>();
      HashMap<String,Double> fixed = new HashMap<String,Double>();
      while (start != finish) {
         if (!fixed.containsKey(start.getName())) {
            fixed.put(start.getName(), path.getCost());
            for (Arc arc : start.getArcs()) {
               if (!fixed.containsKey(arc.getFinish().getName())) {
                  queue.add(path.extend(arc));
               }
            }
         }
         if (queue.isEmpty()) return null;
         path = queue.remove();
         start = path.getFinish();
      }
      return path;
   }

/*
 * Creates an airline graph containing the flight data from Figure 17-2.
 * Real applications would almost certainly read the data from a file.
 */

   private Graph createAirlineGraph() {
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
      new Dijkstra().run();
   }

}
