/*
 * File: AirlineDFS.java
 * ---------------------
 * This program performs a depth-first search in the airline graph from
 * a user-selected node.
 */

package edu.stanford.cs.javacs2.ch17;

import edu.stanford.cs.console.Console;
import edu.stanford.cs.console.SystemConsole;
import edu.stanford.cs.javacs2.ch16.XSet;

public class AirlineDFS extends AirlineTest implements Visitor<Node> {

   @Override
   public void run() {
      Console console = new SystemConsole();
      Graph airline = createGraph();
      Node start = askUserForNode(console, airline, "Enter starting city: ");
      depthFirstSearch(start, this);
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
 * Initiates a depth-first search beginning at the specified node, calling
 * the visit method provided by the Visitor object at each one.
 */

   private void depthFirstSearch(Node node, Visitor<Node> visitor) {
      XSet<Node> visited = new XSet<Node>();
      dfs(node, visitor, visited);
   }

/*
 * Executes a depth-first search beginning at the specified node that
 * avoids revisiting any nodes in the visited set.  If visitor is
 * non-null, its visit method is applied to each node.
 */

   private void dfs(Node node, Visitor<Node> visitor, XSet<Node> visited) {
      if (visited.contains(node)) return;
      if (visitor != null) visitor.visit(node);
      visited.add(node);
      for (Arc arc : node.getArcs()) {
         dfs(arc.getFinish(), visitor, visited);
      }
   }

/*
 * Reports that the node has been visited.
 */

   public void visit(Node node) {
      System.out.println("Visiting " + node.getName());
   }

/* Main program */

   public static void main(String[] args) {
      new AirlineDFS().run();
   }

}
