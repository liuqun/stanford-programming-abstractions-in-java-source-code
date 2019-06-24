/*
 * File: GraphTest.java
 * --------------------
 * This program implements a common platform for interactive graph tests.
 */

package edu.stanford.cs.javacs2.ch17;

import edu.stanford.cs.consoletest.ConsoleTest;
import edu.stanford.cs.consoletest.HelpText;
import edu.stanford.cs.javacs2.ch13.ArrayQueue;
import edu.stanford.cs.javacs2.ch13.Queue;
import edu.stanford.cs.javacs2.ch16.XSet;
import edu.stanford.cs.tokenscanner.TokenScanner;
import java.io.PrintWriter;
import java.io.StringWriter;

public class GraphTest extends ConsoleTest implements Visitor<Node> {

   public GraphTest() {
      g = createGraph();
   }

   public Graph createGraph() {
      return new Graph();
   }

/**
 * Gets the graph used in the test program.
 *
 * @return The graph used in the test program
 */

   public Graph getGraph() {
      return g;
   }

/**
 * Sets the graph used in the test program.
 *
 * @param The new graph to use in the test program
 */

   public void setGraph(Graph g) {
      this.g = g;
   }

/* Commands as described in the help text */

   @HelpText("bfs n1 -- Performs a breadth-first search from n1")
   public void bfsCommand(TokenScanner scanner) {
      Node n1 = nextNode(scanner);
      if (n1 == null) {
         println("Illegal start node");
      } else {
         Node n2 = null;
         if (scanner.hasMoreTokens()) {
            n2 = nextNode(scanner);
         }
         XSet<Node> visited = new XSet<Node>();
         bfs(n1, n2, this, visited);
      }
   }

   @HelpText("clear -- Removes all nodes and arcs from the graph")
   public void clearCommand(TokenScanner scanner) {
      g.clear();
   }

   @HelpText("dfs n1 -- Performs a depth-first search from n1")
   public void dfsCommand(TokenScanner scanner) {
      Node n1 = nextNode(scanner);
      if (n1 == null) {
         println("Illegal start node");
      } else {
         Node n2 = null;
         if (scanner.hasMoreTokens()) {
            n2 = nextNode(scanner);
         }
         XSet<Node> visited = new XSet<Node>();
         dfs(n1, n2, this, visited);
      }
   }

   @HelpText("list -- Lists the nodes in the graph")
   public void listCommand(TokenScanner scanner) {
      list(g);
   }

   @HelpText("load filename -- Loads the graph from the specified file")
   public void loadCommand(TokenScanner scanner) {
      g.load(scanFilename(scanner));
   }

   @HelpText("neighbors node -- Print out the neighbors of node")
   public void neighborsCommand(TokenScanner scanner) {
      String name = scanner.nextToken();
      Node node = g.getNode(name);
      if (node == null) {
         println("No node named " + name);
      } else {
         String neighbors = "";
         for (Arc arc : node.getArcs()) {
            if (!neighbors.isEmpty()) neighbors += ", ";
            neighbors += arc.getFinish().getName();
         }
         println(neighbors);
      }
   }

   @HelpText("size -- Prints the number of nodes in the graph")
   public void sizeCommand(TokenScanner scanner) {
      println(g.size());
   }

/**
 * Scans the next node from the scanner.  Nodes names may be enclosed
 * in quotation marks.
 *
 * @param scanner The TokenScanner object
 * @return The next node, or <code>null</code> if there are no more tokens
 */

   public Node nextNode(TokenScanner scanner) {
      String name = scanner.nextToken();
      if (name.equals("")) return null;
      if (name.startsWith("\"") || name.startsWith("'")) {
         name = scanner.getStringValue(name);
      }
      return g.getNode(name);
   }

/*
 * Executes a breadth-first search beginning at n1 and ending at n2 that
 * avoids revisiting any nodes in the visited set.  The method returns
 * true if n2 is found before all nodes are exhausted.  If visitor is
 * non-null, its visit method is applied to each node.
 */

   public boolean bfs(Node n1, Node n2, Visitor<Node> visitor,
                      XSet<Node> visited) {
      Queue<Node> queue = new ArrayQueue<Node>();
      queue.add(n1);
      while (!queue.isEmpty()) {
         Node node = queue.remove();
         if (!visited.contains(node)) {
            visited.add(node);
            if (visitor != null) visitor.visit(node);
            if (node == n2) return true;
            for (Arc arc : node.getArcs()) {
               queue.add(arc.getFinish());
            }
         }
      }
      return false;
   }

/*
 * Executes a depth-first search beginning at n1 and ending at n2 that
 * avoids revisiting any nodes in the visited set.  The method returns
 * true if n2 is found before all nodes are exhausted.  If visitor is
 * non-null, its visit method is applied to each node.
 */

   public boolean dfs(Node n1, Node n2, Visitor<Node> visitor,
                      XSet<Node> visited) {
      if (visited.contains(n1)) return false;
      if (visitor != null) visitor.visit(n1);
      if (n1 == n2) return true;
      visited.add(n1);
      for (Arc arc : n1.getArcs()) {
         if (dfs(arc.getFinish(), n2, visitor, visited)) return true;
      }
      return false;
   }

   public void list(Graph g) {
      StringWriter wr = new StringWriter();
      g.write(new PrintWriter(wr));
      print(wr.toString());
   }

   public void visit(Node node) {
      System.out.println(node.getName());
   }

/* Private instance variables */

   private Graph g;

/* Main program */

   public static void main(String[] args) {
      new GraphTest().run();
   }

}
