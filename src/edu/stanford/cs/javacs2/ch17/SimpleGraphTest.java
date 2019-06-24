/*
 * File: SimpleGraphTest.java
 * --------------------------
 * This program tests some basic operations using the simple form of the
 * Graph abstraction.
 */

package edu.stanford.cs.javacs2.ch17;

import edu.stanford.cs.consoletest.ConsoleTest;
import edu.stanford.cs.consoletest.HelpText;
import edu.stanford.cs.javacs2.ch13.ArrayQueue;
import edu.stanford.cs.javacs2.ch13.Queue;
import edu.stanford.cs.javacs2.ch16.XSet;
import edu.stanford.cs.tokenscanner.TokenScanner;

public class SimpleGraphTest extends ConsoleTest {

   public SimpleGraphTest() {
      g = createGraph();
   }

   public Graph createGraph() {
      return new Graph();
   }

   @HelpText("bfs n1 -- Performs a breadth-first search from n1")
   public void bfsCommand(TokenScanner scanner) {
      Node n1 = nextNode(scanner);
      if (n1 == null) {
         println("Missing source node name");
      } else {
         Node n2 = null;
         if (scanner.hasMoreTokens()) {
            n2 = nextNode(scanner);
         }
         XSet<Node> visited = new XSet<Node>();
         bfs(n1, n2, visited);
      }
   }

   @HelpText("clear -- Removes all nodes and arcs from the graph")
   public void clearCommand(TokenScanner scanner) {
      g.clear();
   }

   @HelpText("connect n1-n2 -- Adds an arc from n1 to n2")
   public void connectCommand(TokenScanner scanner) {
      String s1 = scanner.nextToken();
      Node n1 = g.getNode(s1);
      if (n1 == null) {
         n1 = new Node(s1);
         g.addNode(n1);
      }
      boolean bidirectional = true;
      String s2 = scanner.nextToken();
      if (s2.equals("-")) {
         s2 = scanner.nextToken();
         if (s2.equals(">")) {
            s2 = scanner.nextToken();
            bidirectional = false;
         }
      }
      Node n2 = g.getNode(s2);
      if (n2 == null) {
         n2 = new Node(s2);
         g.addNode(n2);
      }
      String cstr = "1";
      if (scanner.hasMoreTokens()) {
         cstr = scanner.nextToken();
         if (cstr.equals("(")) {
            cstr = scanner.nextToken();
            if (!scanner.nextToken().equals(")")) {
               throw new RuntimeException("Unbalanced parentheses");
            }
         }
      }
      double cost = Double.parseDouble(cstr);
      g.addArc(new Arc(n1, n2, cost));
      if (bidirectional) g.addArc(new Arc(n2, n1, cost));
   }

   @HelpText("dfs n1 -- Performs a depth-first search from n1")
   public void dfsCommand(TokenScanner scanner) {
      Node n1 = nextNode(scanner);
      if (n1 == null) {
         println("Missing source node name");
      } else {
         Node n2 = null;
         if (scanner.hasMoreTokens()) {
            n2 = nextNode(scanner);
         }
         XSet<Node> visited = new XSet<Node>();
         dfs(n1, n2, visited);
      }
   }

//   void runDijkstraCommand(TokenScanner & scanner, void clientData) {
//      Graph<Node,Arc> gp = (Graph<Node,Arc> *) clientData;
//      Node n1 = nextNode(scanner, gp);
//      Node n2 = nextNode(scanner, gp);
//      bool trace = scanner.nextToken() == "trace";
//      Path path = findShortestPath(n1, n2, trace);
//      double cost = path.getTotalCost();
//      if (cost < 0) {
//         cout << "No path exists" << endl;
//      } else {
//         cout << path.toString() << " (" << cost << ")" << endl;
//      }
//   }
//
//   void runKruskalCommand(TokenScanner & scanner, void clientData) {
//      Graph<Node,Arc> gp = (Graph<Node,Arc> *) clientData;
//      bool trace = scanner.nextToken() == "trace";
//      Graph<Node,Arc> mst = findMinimumSpanningTree(*gp, trace);
//      foreach (Node node in mst.getNodeSet()) {
//         listNode(node);
//      }
//   }

   @HelpText("list -- Lists the nodes in the graph")
   public void listCommand(TokenScanner scanner) {
      for (Node node : g.getNodeSet()) {
         listNode(node);
      }
   }

   @HelpText("node name -- Add a node with the specified name")
   public void nodeCommand(TokenScanner scanner) {
      String name = scanner.nextToken();
      g.addNode(new Node(name));
   }

//   void runPathExistsCommand(TokenScanner & scanner, void clientData) {
//      Graph<Node,Arc> gp = (Graph<Node,Arc> *) clientData;
//      Node n1 = nextNode(scanner, gp);
//      Node n2 = nextNode(scanner, gp);
//      XSet<Node *> v1, v2;
//      if (pathExists1(n1, n2, v1) != pathExists2(n1, n2, v2)) {
//         error("Two implementations of pathExists are inconsistent");
//      }
//      v1.clear();
//      if (pathExists1(n1, n2, v1)) {
//         cout << "A path exists";
//      } else {
//         cout << "No path exists";
//      }
//      cout << " between " << n1->name << " and " << n2->name << endl;
//   }

   private Node nextNode(TokenScanner scanner) {
      String name = scanner.nextToken();
      if (name.equals("")) return null;
      return g.getNode(name);
   }

   private void listNode(Node node) {
      println(node);
      for (Arc arc : node.getArcs()) {
         println("  " + arc);
      }
   }

   private void bfs(Node start, Node finish, XSet<Node> visited) {
      Queue<Node> queue = new ArrayQueue<Node>();
      queue.add(start);
      while (!queue.isEmpty()) {
         Node node = queue.remove();
         if (node == finish) return;
         if (!visited.contains(node)) {
            visit(node);
            visited.add(node);
            for (Arc arc : node.getArcs()) {
               queue.add(arc.getFinish());
            }
         }
      }
   }

   private void dfs(Node start, Node finish, XSet<Node> visited) {
      if (visited.contains(start)) return;
      visit(start);
      visited.add(start);
      if (start == finish) return;
      for (Arc arc : start.getArcs()) {
         dfs(arc.getFinish(), finish, visited);
      }
   }

   private void visit(Node node) {
      println("Visiting " + node);
   }

/* Private instance variables */

   private Graph g;

/* Main program */

   public static void main(String[] args) {
      new SimpleGraphTest().run();
   }

}
