/*
 * File: DijkstraTrace.java
 * ------------------------
 * This program performs Dijkstra's algorithm on the airline graph.
 */

package edu.stanford.cs.javacs2.ch17;

import edu.stanford.cs.console.Console;
import edu.stanford.cs.console.SystemConsole;
import edu.stanford.cs.javacs2.ch14.HashMap;
import java.util.PriorityQueue;

public class DijkstraTrace {

   public void run() {
      Console console = new SystemConsole();
      Graph airline = createAirlineGraph();
      Node start = askUserForNode(console, airline, "Enter starting city: ");
      Node finish = askUserForNode(console, airline, "Enter ending city: ");
      Path path = findMinimumPath(start, finish, true);
      if (path == null) {
         System.out.println("No path exists.");
      } else {
         System.out.println(path);
      }
   }

   private Node askUserForNode(Console console, Graph g, String prompt) {
      while (true) {
         Node node = g.getNode(console.nextLine(prompt));
         if (node != null) return node;
         System.out.println("Unknown node name");
      }
   }

/*
 * Finds the minimum-cost path between the nodes start and finish using
 * Dijkstra's algorithm, which keeps track of the shortest paths in
 * a priority queue.  The method returns a Path object, or null if
 * no path exists.  The optional trace argument enables a trace
 * of the operation.
 */

   private Path findMinimumPath(Node start, Node finish, boolean trace) {
      Path path = new Path();
      PriorityQueue<Path> queue = new PriorityQueue<Path>();
      HashMap<String,Double> fixed = new HashMap<String,Double>();
      while (start != finish) {
         if (fixed.containsKey(start.getName())) {
            if (trace) {
               System.out.println("Ignore this path because the " +
                                  "distance to " + start.getName() +
                                  " is fixed.");
            }
         } else {
            if (trace) {
               System.out.print("Fix the distance to " + start.getName());
               System.out.printf(" at %.0f%n", path.getCost());
            }
            fixed.put(start.getName(), path.getCost());
            if (trace) {
               System.out.print("Process the arcs out of " +
                                start.getName() + " (");
               int count = 0;
               for (Arc arc : start.getArcs()) {
                  if (count > 0) System.out.print(", ");
                  System.out.print(arc.getFinish().getName());
                  count++;
               }
               System.out.println(").");
            }
            for (Arc arc : start.getArcs()) {
               if (fixed.containsKey(arc.getFinish().getName())) {
                  if (trace) {
                     System.out.println("  Ignore " +
                                        arc.getFinish().getName() +
                                        " because its distance is fixed.");
                  }
               } else {
                  Path newPath = path.extend(arc);
                  queue.add(newPath);
                  if (trace) {
                     System.out.print("  Enqueue the path: " + newPath);
                     System.out.printf(" (%.0f)%n", newPath.getCost());
                  }
               }
            }
         }
         if (queue.isEmpty()) return null;
         path = queue.remove();
         start = path.getFinish();
         if (trace) {
            System.out.print("Dequeue the shortest path: " + path);
            System.out.printf(" (%.0f)%n", path.getCost());
         }
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
      new DijkstraTrace().run();
   }

}
