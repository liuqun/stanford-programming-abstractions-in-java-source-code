/*
 * File: Graph.java
 * ----------------
 * This file exports a class that represents graphs consisting of nodes
 * and arcs.  Clients that want to extend the node and arc types should
 * use the GenericGraph class instead.
 */

package edu.stanford.cs.javacs2.ch17;

import edu.stanford.cs.javacs2.ch14.HashMap;
import edu.stanford.cs.javacs2.ch16.XSet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Graph {

/**
 * Creates an empty Graph object.
 */

   public Graph() {
      nodes = new XSet<Node>();
      arcs = new XSet<Arc>();
      nodeMap = new HashMap<String,Node>();
   }

/**
 * Creates a Graph initialized to the contents of the specified file.
 * This method throws a runtime exception if the file cannot be opened or
 * if I/O errors occur.
 *
 * @param filename The name of the input file.
 */

   public Graph(String filename) {
      load(filename);
   }

/**
 * Returns the number of nodes in the graph.
 */

   public int size() {
      return nodes.size();
   }

/**
 * Returns true if the graph is empty.
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
 */

   public void addNode(Node node) {
      nodes.add(node);
      nodeMap.put(node.getName(), node);
   }

/**
 * Looks up a node in the name table attached to the graph and returns it.
 * If no node with the specified name exists, getNode returns null.
 */

   public Node getNode(String name) {
      return nodeMap.get(name);
   }

/*
 * Adds an arc to the graph.
 */

   public void addArc(Arc arc) {
      arc.getStart().getArcs().add(arc);
      arcs.add(arc);
   }

/**
 * Returns the set of all nodes in the graph.
 */

   public XSet<Node> getNodeSet() {
      return nodes;
   }

/**
 * Returns the set of all arcs in the graph.
 */

   public XSet<Arc> getArcSet() {
      return arcs;
   }

/**
 * Loads data for this graph from a file.  The individual lines of the
 * file are in one of the following forms:
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
         load(rd);
         rd.close();
      } catch (IOException ex) {
         throw new RuntimeException(ex.toString());
      }
   }

/*
 * Loads the contents of this graph from the reader, stopping at a
 * blank line or the end of the file.
 *
 * @param rd A BufferedReader for a file containing the graph description.
 */

   public void load(BufferedReader rd) {
      try {
         while (true) {
            String line = rd.readLine();
            if (line == null || line.isEmpty()) break;
            int arrow = line.indexOf("-");
            if (arrow == -1) {
               createNode(line);
            } else {
               double cost = 0;
               boolean directed = line.substring(arrow + 1).startsWith(">");
               int start = (directed) ? arrow + 2 : arrow + 1;
               int finish = line.length();
               int lp = line.indexOf('(', arrow);
               if (lp != -1) {
                  finish = lp;
                  int rp = line.indexOf(')', lp);
                  if (rp == -1) {
                     throw new RuntimeException("Missing close parenthesis");
                  }
                  cost = Double.parseDouble(line.substring(lp + 1, rp));
               }
               Node n1 = createNode(line.substring(0, arrow));
               Node n2 = createNode(line.substring(start, finish));
               createArc(n1, n2, cost);
               if (!directed) createArc(n2, n1, cost);
            }
         }
      } catch (IOException ex) {
         throw new RuntimeException(ex.toString());
      }
   }

/* Creates a node in the graph if it does not already exist */

   private Node createNode(String name) {
      name = name.trim();
      if (name.startsWith("\"")) {
         if (name.length() < 2 || !name.endsWith("\"")) {
            throw new RuntimeException("Unterminated string");
         }
         name = name.substring(1, name.length() - 1);
      }
      Node node = getNode(name);
      if (node == null) {
         node = new Node(name);
         addNode(node);
      }
      return node;
   }

/* Creates an arc in the graph */

   private Arc createArc(Node n1, Node n2, double cost) {
      Arc arc = new Arc(n1, n2, cost);
      addArc(arc);
      return arc;
   }

/**
 * Saves the graph to the specified file.  This method throws a runtime
 * exception if the file cannot be opened or if data errors occur.
 *
 * @param filename The name of the file
 */

   public void save(String filename) {
      try {
         PrintWriter wr =
            new PrintWriter(new BufferedWriter(new FileWriter(filename)));
         write(wr);
         wr.close();
      } catch (IOException ex) {
         throw new RuntimeException(ex.toString());
      }
   }

/**
 * Writes a representation of this graph to the writer in a form
 * compatible with the <code>load</code> method.
 *
 * @param wr A <code>PrintWriter</code> to which the data is written
 */

   public void write(PrintWriter wr) {
      XSet<Node> nodesRemaining = new XSet<Node>(getNodeSet());
      for (Arc arc : getArcSet()) {
         Node start = arc.getStart();
         Node finish = arc.getFinish();
         double cost = arc.getCost();
         nodesRemaining.remove(start);
         nodesRemaining.remove(finish);
         boolean undirected = containsArc(finish, start, cost);
         if (!undirected || start.compareTo(finish) < 0) {
            wr.print(start.getName());
            wr.print((undirected) ? "-" : "->");
            wr.print(finish.getName());
            if (cost != 0) {
               if (cost == Math.floor(cost)) {
                  wr.printf(" (%.0f)", cost);
               } else {
                  wr.printf(" (%g)", cost);
               }
            }
            wr.println();
         }
      }
      for (Node node : nodesRemaining) {
         wr.println(node);
      }
   }

/* Checks to see whether an arc with the specified fields exists */

   private boolean containsArc(Node n1, Node n2, double cost) {
      for (Arc arc : getArcSet()) {
         if (n1 == arc.getStart() && n2 == arc.getFinish()
                                  && cost == arc.getCost()) return true;
      }
      return false;
   }

/* Private instance variables */

   private XSet<Node> nodes;              /* The set of nodes in the graph */
   private XSet<Arc> arcs;                /* The set of arcs in the graph  */
   private HashMap<String,Node> nodeMap;  /* Map from names to nodes       */

};
