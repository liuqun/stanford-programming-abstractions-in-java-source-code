/*
 * File: GenericGraphTest.java
 * ---------------------------
 * This program implements several algorithms for graphs using the
 * generic form of the graph abstraction.  Subclasses must provide
 * a factory method to create the desired graph type.
 */

package edu.stanford.cs.javacs2.ch17;

import edu.stanford.cs.consoletest.ConsoleTest;
import edu.stanford.cs.consoletest.HelpText;
import edu.stanford.cs.tokenscanner.TokenScanner;
import java.io.PrintWriter;
import java.io.StringWriter;

public abstract class GenericGraphTest<G extends GenericGraph<N,A>,
                                       N extends GenericNode<N,A>,
                                       A extends GenericArc<N,A>>
                      extends ConsoleTest {

   public GenericGraphTest() {
      g = createGraph();
   }

   public abstract G createGraph();

/**
 * Gets the graph used in the test program.
 *
 * @return The graph used in the test program
 */

   public G getGraph() {
      return g;
   }

/**
 * Sets the graph used in the test program.
 *
 * @param The new graph to use in the test program
 */

   public void setGraph(G g) {
      this.g = g;
   }

/* Commands as described in the help text */

   @HelpText("bfs n1 -- Performs a breadth-first search from n1")
   public void bfsCommand(TokenScanner scanner) {
      N n1 = nextNode(scanner);
      if (n1 == null) {
         println("Missing source node name");
      } else {
         N n2 = nextNode(scanner);
         g.bfs(n1, n2, new SimpleVisitor());
      }
   }

   @HelpText("clear -- Removes all nodes and arcs")
   public void clearCommand(TokenScanner scanner) {
      g.clear();
   }

   @HelpText("dfs n1 -- Performs a depth-first search from n1")
   public void dfsCommand(TokenScanner scanner) {
      N n1 = nextNode(scanner);
      if (n1 == null) {
         println("Missing source node name");
      } else {
         N n2 = nextNode(scanner);
         g.dfs(n1, n2, new SimpleVisitor());
      }
   }

   @HelpText("load filename -- Loads the graph from the specified file")
   public void loadCommand(TokenScanner scanner) {
      g.load(scanFilename(scanner));
   }

   @HelpText("list -- Lists the nodes in the graph")
   public void listCommand(TokenScanner scanner) {
      list(getGraph());
   }

   @HelpText("minPath n1 n2 -- Finds minimum-cost path from n1 to n2")
   public void minPathCommand(TokenScanner scanner) {
      N n1 = nextNode(scanner);
      if (n1 == null) {
         println("Missing source node name");
      } else {
         N n2 = nextNode(scanner);
         if (n2 == null) {
            println("Missing destination node name");
         } else {
            GenericPath<N,A> path = g.findMinimumPath(n1, n2);
            if (path == null) {
               System.out.println("No path exists");
            } else {
               System.out.print(path);
               System.out.printf(" (%.0f)%n", path.getCost());
            }
         }
      }
   }

   @HelpText("save filename -- Saves the graph to the specified file")
   public void saveCommand(TokenScanner scanner) {
      String filename = scanFilename(scanner);
      if (!filename.contains(".")) filename += ".txt";
      g.save(filename);
   }

/**
 * Scans the next node from the scanner.  Nodes names may be enclosed
 * in quotation marks.
 *
 * @param scanner The TokenScanner object
 * @return The next node, or <code>null</code> if there are no more tokens
 */

   public N nextNode(TokenScanner scanner) {
      String name = scanner.nextToken();
      if (name.equals("")) return null;
      if (name.startsWith("\"") || name.startsWith("'")) {
         name = scanner.getStringValue(name);
      }
      return g.getNode(name);
   }

   public void list(G g) {
      StringWriter wr = new StringWriter();
      g.write(new PrintWriter(wr));
      print(wr.toString());
   }

/* Inner class that visits nodes by displaying their name */

   private class SimpleVisitor implements Visitor<N> {

      public void visit(N node) {
         println("Visiting " + node.getName());
      }

   }

/* Private instance variables */

   private G g;

}
