/*
 * File: GenericGraph.java
 * -----------------------
 * This file exports a class that represents graphs consisting of nodes
 * and arcs.
 */

package edu.stanford.cs.javacs2.ch17;

import edu.stanford.cs.javacs2.ch13.ArrayList;
import edu.stanford.cs.javacs2.ch13.ArrayQueue;
import edu.stanford.cs.javacs2.ch13.Queue;
import edu.stanford.cs.javacs2.ch14.HashMap;
import edu.stanford.cs.javacs2.ch16.XSet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;

/**
 * This class represents a graph with the specified node and arc types.
 * The <code>N</code> and <code>A</code> type parameters indicate the
 * types used for nodes and arcs, respectively.
 */

public class GenericGraph<N extends GenericNode<N,A>,
                          A extends GenericArc<N,A>> {

/**
 * Creates an empty <code>GenericGraph</code> object.
 */

   public GenericGraph() {
      nodes = new XSet<N>();
      arcs = new XSet<A>();
      nodeMap = new HashMap<String,N>();
   }

/**
 * Creates an GenericGraph initialized to the contents of the specified
 * file.  This method throws a runtime exception if the file cannot be
 * opened or if I/O or formatting errors occur.
 *
 * @param filename The name of the input file.
 */

   public GenericGraph(String filename) {
      this();
      load(filename);
   }

/**
 * Initializes this graph from the file, stopping at a blank line or the
 * end of the file.  The individual lines of the file are in one of the
 * following forms:
 *
 *<pre>
 *     node
 *     n1 - n2
 *     n1 -> n2
 *     n1 - n2 (cost)
 *     n1 -> n2 (cost)
 *</pre>
 *
 * @param filename The name of the input file
 */

   public void load(String filename) {
      try {
         BufferedReader rd = null;
         IOException failure = null;
         try {
            rd = new BufferedReader(new FileReader(filename));
         } catch (IOException ex) {
            failure = ex;
         }
         if (!filename.contains(".")) {
            rd = new BufferedReader(new FileReader(filename + ".txt"));
         }
         if (rd == null) throw new RuntimeException(failure.toString());
         read(rd);
         rd.close();
      } catch (IOException ex) {
         throw new RuntimeException(ex.toString());
      }
   }

/*
 * Initializes this graph from the contents of the reader, stopping at a
 * blank line or the end of the file.
 *
 * @param rd A BufferedReader for a file containing the graph description.
 */

   public void read(BufferedReader rd) {
      try {
         while (true) {
            String line = rd.readLine();
            if (line == null || line.isEmpty()) break;
            parseGraphLine(line);
         }
      } catch (IOException ex) {
         throw new RuntimeException(ex.toString());
      }
   }

/**
 * Parses a line of an input file and updates the graph structure.
 * Subclasses can override this method to read files in a different
 * format, although most specializations can be applied in the
 * <code>createNode</code> method supplied by the subclass.
 *
 * @param line A line from the graph description file
 */

   public void parseGraphLine(String line) {
      parseStandardGraphLine(line);
   }

/**
 * Creates a node from the name and the parameter string, which can be
 * used to set properties of the node.  Subclasses must override this
 * method if they intend to read graphs from files.
 *
 * @param name The name of the node
 * @param parameter The parameter string included on the line
 */

   public N createNode(String name, String parameter) {
      throw new RuntimeException("Subclasses must override createNode");
   }

/**
 * Creates an arc from the start and finish nodes, along with a parameter
 * string, which can be used to set properties of the arc.  Subclasses
 * must override this method if they intend to read graphs from files.
 *
 * @param name The name of the node
 * @param parameter The parameter string included on the line
 */

   public A createArc(N start, N finish, String parameter) {
      throw new RuntimeException("Subclasses must override createArc");
   }

/**
 * Saves the graph to the specified file.  This method throws a runtime
 * exception if the file cannot be opened or if data errors occur.
 *
 * @param filename The name of the file
 */

   public void save(String filename) {
      try {
         PrintWriter wr = new PrintWriter(
                             new BufferedWriter(new FileWriter(filename)));
         write(wr);
         wr.close();
      } catch (IOException ex) {
         throw new RuntimeException(ex.toString());
      }
   }

/**
 * Writes a graph to the writer in a form compatible with read.
 *
 * @param wr A PrintWriter for the file to which the data is written.
 */

   public void write(PrintWriter wr) {
      XSet<N> nodesRemaining = new XSet<N>(getNodeSet());
      for (N node : getNodeSet()) {
         String parameter = node.parameterString();
         if (parameter != null) {
            wr.printf("%s (%s)%n", quoteIfNeeded(node.getName()), parameter);
            nodesRemaining.remove(node);
         }
      }
      for (A arc : getArcSet()) {
         N start = arc.getStart();
         N finish = arc.getFinish();
         double cost = arc.getCost();
         nodesRemaining.remove(start);
         nodesRemaining.remove(finish);
         boolean undirected = containsArc(finish, start, cost);
         String parameter = arc.parameterString();
         if (!undirected || start.compareTo(finish) < 0) {
            wr.print(start.getName());
            wr.print((undirected) ? "-" : "->");
            wr.print(finish.getName());
            if (parameter != null) wr.print(" (" + parameter + ")");
            wr.println();
         }
      }
      for (N node : nodesRemaining) {
         wr.println(node);
      }
   }

/**
 * Returns the number of nodes in the graph.
 *
 * @return The number of nodes in the graph
 */

   public int size() {
      return nodes.size();
   }

/**
 * Returns <code>true</code> if the graph is empty.
 *
 * @return The value <code>true</code> if the graph is empty
 */

   public boolean isEmpty() {
      return nodes.isEmpty();
   }

/**
 * Removes all nodes and arcs from the graph.
 */

   public void clear() {
      nodes.clear();
      arcs.clear();
      nodeMap.clear();
   }

/**
 * Adds a new node to the graph.
 *
 * @param node The new node
 */

   public void addNode(N node) {
      nodes.add(node);
      nodeMap.put(node.getName(), node);
   }

/**
 * Removes a node from the graph.  Removing a node also removes all arcs
 * that contain that node.
 *
 * @param name The name of the node
 */

   public void removeNode(String name) {
      N node = getNode(name);
      if (node != null) removeNode(node);
   }

/**
 * Removes a node from the graph.  Removing a node also removes all arcs
 * that contain that node.
 *
 * @param node The node to remove
 */

   public void removeNode(N node) {
      ArrayList<A> toRemove = new ArrayList<A>();
      for (A arc : arcs) {
         if (arc.getStart() == node || arc.getFinish() == node) {
            toRemove.add(arc);
         }
      }
      for (A arc : toRemove) {
         removeArc(arc);
      }
      nodes.remove(node);
      nodeMap.remove(node.getName());
   }

/**
 * Looks up a name in the table attached to the graph and returns
 * the corresponding node.  If no node with that name exists,
 * <code>getNode</code> returns <code>null</code>.
 *
 * @param name The name of the node
 * @return The corresponding node
 */

   public N getNode(String name) {
      return nodeMap.get(name);
   }

/**
 * Adds a new arc to the graph.
 *
 * @param arc The new arc
 */

   public void addArc(A arc) {
      arc.getStart().getArcs().add(arc);
      arcs.add(arc);
   }

/**
 * Removes an arc from the graph.
 *
 * @param arc The arc to remove
 */

   public void removeArc(A arc) {
      arc.getStart().getArcs().remove(arc);
      arcs.remove(arc);
   }

/**
 * Removes an arc from the graph specified by its endpoints.  If more
 * than one arc connects the specified endpoints, all of them are removed.
 *
 * @param n1 The start node
 * @param n2 The finish node
 */

   public void removeArc(N n1, N n2) {
      ArrayList<A> toRemove = new ArrayList<A>();
      for (A arc : arcs) {
         if (arc.getStart() == n1 && arc.getFinish() == n2) {
            toRemove.add(arc);
         }
      }
      for (A arc : toRemove) {
         removeArc(arc);
      }
   }

/**
 * Removes an arc from the graph specified by the names of its endpoints.
 * If more than one arc connects the specified endpoints, all of them are
 * removed.
 *
 * @param s1 The name of the start node
 * @param s2 The name of the finish node
 */

   public void removeArc(String s1, String s2) {
      removeArc(getExistingNode(s1), getExistingNode(s2));
   }

/**
 * Returns the set of all nodes in the graph.
 *
 * @return The set of all nodes in the graph
 */

   public XSet<N> getNodeSet() {
      return nodes;
   }

/**
 * Returns the set of all arcs in the graph.
 *
 * @return The set of all arcs in the graph
 */

   public XSet<A> getArcSet() {
      return arcs;
   }

/**
 * Returns the set of nodes that are neighbors of the specified node.
 *
 * @param node The node whose neighbors are sought
 * @return A set of the neighboring nodes
 */

   public XSet<N> getNeighbors(N node) {
      XSet<N> neighbors = new XSet<N>();
      for (A arc : node.getArcs()) {
         neighbors.add(arc.getFinish());
      }
      return neighbors;
   }

/**
 * Returns the set of nodes that are neighbors of the named node.
 *
 * @param name The name of the node whose neighbors are sought
 * @return A set of the neighboring nodes
 */

   public XSet<N> getNeighbors(String name) {
      return getNeighbors(getExistingNode(name));
   }

/**
 * Initiates a breadth-first search beginning at the specified node, calling
 * the <code>visit</code> method provided by the <code>Visitor</code>
 * object at each one.
 *
 * @param node The starting node
 * @param visitor An object exporting a <code>visit</code> method
 */

   public void bfs(N node, Visitor<N> visitor) {
      bfs(node, null, visitor);
   }

/**
 * Initiates a breadth-first search beginning at <code>n1</code>, calling
 * the <code>visit</code> method provided by the <code>Visitor</code>
 * object at each one, stopping at <code>n2</code>.
 *
 * @param n1 The starting node
 * @param n2 The destination node
 * @param visitor An object exporting a <code>visit</code> method
 */

   public void bfs(N n1, N n2, Visitor<N> visitor) {
      XSet<N> visited = new XSet<N>();
      bfs(n1, n2, visitor, visited);
   }

/**
 * Initiates a depth-first search beginning at the specified node, calling
 * the <code>visit</code> method provided by the <code>Visitor</code>
 * object at each one.
 *
 * @param node The starting node
 * @param visitor An object exporting a <code>visit</code> method
 */

   public void dfs(N node, Visitor<N> visitor) {
      dfs(node, null, visitor);
   }

/**
 * Initiates a depth-first search beginning at <code>n1</code>, calling
 * the <code>visit</code> method provided by the <code>Visitor</code>
 * object at each one, stopping at <code>n2</code>.
 *
 * @param n1 The starting node
 * @param n2 The destination node
 * @param visitor An object exporting a <code>visit</code> method
 */

   public void dfs(N n1, N n2, Visitor<N> visitor) {
      XSet<N> visited = new XSet<N>();
      dfs(n1, n2, visitor, visited);
   }

/**
 * Finds the minimum-cost path between <code>n1</code> and <code>n2</code>
 * using Dijkstra&rsquo;s algorithm, which keeps track of the shortest paths
 * in a priority queue.  The method returns a <code>GenericPath</code>
 * object or <code>null</code> if no path exists.
 *
 * @param n1 The starting node
 * @param n2 The destination node
 * @return The shortest path or <code>null</code> if none exists
 */

   public GenericPath<N,A> findMinimumPath(N n1, N n2) {
      GenericPath<N,A> path = new GenericPath<N,A>();
      PriorityQueue<GenericPath<N,A>> queue =
         new PriorityQueue<GenericPath<N,A>>();
      HashMap<String,Double> fixed = new HashMap<String,Double>();
      while (n1 != n2) {
         if (!fixed.containsKey(n1.getName())) {
            fixed.put(n1.getName(), path.getCost());
            for (A arc : n1.getArcs()) {
               if (!fixed.containsKey(arc.getFinish().getName())) {
                  queue.add(path.extend(arc));
               }
            }
         }
         if (queue.isEmpty()) return null;
         path = queue.remove();
         n1 = path.getFinish();
      }
      return path;
   }

/*
 * Looks up a node and throws a RuntimeException if the node is not found.
 */

   private N getExistingNode(String name) {
      N node = getNode(name);
      if (node == null) throw new RuntimeException("No node named " + name);
      return node;
   }

/*
 * Executes a breadth-first search beginning at the specified node that
 * avoids revisiting any nodes in the visited set, stopping when it
 * reaches n2 or when no unexplored nodes exist.  The function returns
 * true if n2 has been found.
 */

   private boolean bfs(N n1, N n2, Visitor<N> visitor, XSet<N> visited) {
      Queue<N> queue = new ArrayQueue<N>();
      queue.add(n1);
      while (!queue.isEmpty()) {
         N node = queue.remove();
         if (!visited.contains(node)) {
            if (visitor != null) visitor.visit(node);
            if (node == n2) return true;
            visited.add(node);
            for (A arc : node.getArcs()) {
               queue.add(arc.getFinish());
            }
         }
      }
      return false;
   }

/*
 * Executes a depth-first search beginning at the specified node that
 * avoids revisiting any nodes in the visited set, stopping when it
 * reaches n2 or when no unexplored nodes exist.  The function returns
 * true if n2 has been found.
 */

   private boolean dfs(N n1, N n2, Visitor<N> visitor, XSet<N> visited) {
      if (visited.contains(n1)) return false;
      if (visitor != null) visitor.visit(n1);
      if (n1 == n2) return true;
      visited.add(n1);
      for (A arc : n1.getArcs()) {
         if (dfs(arc.getFinish(), n2, visitor, visited)) return true;
      }
      return false;
   }

/*
 * Implements the standard parsing algorithm for graph files, which is
 * surprisingly intricate.  This code is implemented as a finite-state
 * machine.
 */

   private static final int INITIAL = 0;
   private static final int IN_START = 1;
   private static final int QUOTED_START = 2;
   private static final int AFTER_START = 3;
   private static final int AFTER_HYPHEN = 4;
   private static final int AFTER_CONNECTOR = 5;
   private static final int IN_FINISH = 6;
   private static final int QUOTED_FINISH = 7;
   private static final int AFTER_FINISH = 8;
   private static final int IN_PARAMETER = 9;
   private static final int AFTER_PARAMETER = 10;

   private static final int NO_CONNECTOR = 0;
   private static final int UNDIRECTED_CONNECTOR = 1;
   private static final int DIRECTED_CONNECTOR = 2;

   private void parseStandardGraphLine(String line) {
      int state = INITIAL;
      int connector = NO_CONNECTOR;
      String start = "";
      String finish = "";
      String parameter = "";
      for (int i = 0; i < line.length(); i++) {
         char ch = line.charAt(i);
         switch (state) {
          case INITIAL:
            if (ch == '"') {
               state = QUOTED_START;
            } else if (!Character.isWhitespace(ch)) {
               start += ch;
               state = IN_START;
            }
            break;
          case IN_START:
            if (ch == '-') {
               start = start.trim();
               connector = UNDIRECTED_CONNECTOR;
               state = AFTER_HYPHEN;
            } else {
               start += ch;
            }
            break;
          case QUOTED_START:
            if (ch == '"') {
               state = AFTER_START;
            } else {
               start += ch;
            }
            break;
          case AFTER_START:
            if (ch == '-') {
               connector = UNDIRECTED_CONNECTOR;
               state = AFTER_HYPHEN;
            } else if (!Character.isWhitespace(ch)) {
               throw new RuntimeException("Unexpected character '" + ch + "'");
            }
            break;
          case AFTER_HYPHEN:
            if (ch == '>') {
               connector = DIRECTED_CONNECTOR;
               state = AFTER_CONNECTOR;
            } else if (ch == '"') {
               state = QUOTED_FINISH;
            } else if(!Character.isWhitespace(ch)) {
               finish += ch;
               state = IN_FINISH;
            }
            break;
          case AFTER_CONNECTOR:
            if (ch == '"') {
               state = QUOTED_FINISH;
            } else if(!Character.isWhitespace(ch)) {
               finish += ch;
               state = IN_FINISH;
            }
            break;
          case IN_FINISH:
            if (ch == '(') {
               finish = finish.trim();
               state = IN_PARAMETER;
            } else {
               finish += ch;
            }
            break;
          case QUOTED_FINISH:
            if (ch == '"') {
               state = AFTER_FINISH;
            } else {
               finish += ch;
            }
            break;
          case AFTER_FINISH:
            if (ch == '(') {
               state = IN_PARAMETER;
            } else if (!Character.isWhitespace(ch)) {
               throw new RuntimeException("Unexpected character '" + ch + "'");
            }
            break;
          case IN_PARAMETER:
            if (ch == ')') {
               state = AFTER_PARAMETER;
            } else {
               parameter += ch;
            }
            break;
          case AFTER_PARAMETER:
            if (!Character.isWhitespace(ch)) {
               throw new RuntimeException("Unexpected character '" + ch + "'");
            }
            break;
         }
      }
      switch (state) {
       case INITIAL:
         return;
       case QUOTED_START: case QUOTED_FINISH:
         throw new RuntimeException("Missing close quotation mark");
       case AFTER_HYPHEN: case AFTER_CONNECTOR:
         throw new RuntimeException("Missing finish node");
       case IN_PARAMETER:
         throw new RuntimeException("Missing close parenthesis");
      }
      if (connector == NO_CONNECTOR) {
         addNode(createNode(start, parameter));
      } else {
         N n1 = getNode(start);
         if (n1 == null) addNode(n1 = createNode(start, ""));
         N n2 = getNode(finish);
         if (n2 == null) addNode(n2 = createNode(finish, ""));
         addArc(createArc(n1, n2, parameter));
         if (connector == UNDIRECTED_CONNECTOR) {
            addArc(createArc(n2, n1, parameter));
         }
      }
   }

/* Checks to see whether an arc with the specified fields exists */

   private boolean containsArc(N n1, N n2, double cost) {
      for (A arc : getArcSet()) {
         if (n1 == arc.getStart() && n2 == arc.getFinish()
                                  && cost == arc.getCost()) return true;
      }
      return false;
   }

/*
 * Returns a string surrounded by quotes if the quotes are necessary,
 * which is true if the string contains a hyphen or an open parenthesis
 * or if it has leading or trailing spaces.
 */

   private String quoteIfNeeded(String str) {
      if (str.contains("-") || str.contains("(") || !str.equals(str.trim())) {
         return '"' + str + '"';
      } else {
         return str;
      }
   }

/* Private instance variables */

   private XSet<N> nodes;              /* The set of nodes in the graph */
   private XSet<A> arcs;               /* The set of arcs in the graph  */
   private HashMap<String,N> nodeMap;  /* A map from names and nodes    */

}
