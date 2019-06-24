/*
 * File: AirlineDisplay.java
 * -------------------------
 * This program draws the airline graph on the graphics window.
 */

package edu.stanford.cs.javacs2.ch17;

import edu.stanford.cs.javacs2.ch8.GLine;
import edu.stanford.cs.javacs2.ch8.GOval;
import edu.stanford.cs.javacs2.ch8.GWindow;
import java.awt.Color;

public class AirlineDisplay {

   public void run() {
      Graph airline = createGraph();
      GWindow gw = new GWindow(WIDTH, HEIGHT);
      drawGraph(gw, airline);
   }

/*
 * Draws the airline graph on the graphics window.
 */

   private void drawGraph(GWindow gw, Graph airline) {
      for (Arc a : airline.getArcSet()) {
         AirlineArc arc = (AirlineArc) a;
         AirlineNode n1 = (AirlineNode) arc.getStart();
         AirlineNode n2 = (AirlineNode) arc.getFinish();
         GLine line = new GLine(n1.x, n1.y, n2.x, n2.y);
         line.setColor(arc.color);
         gw.add(line);
      }
      for (Node n : airline.getNodeSet()) {
         AirlineNode node = (AirlineNode) n;
         GOval dot = new GOval(node.x - NODE_RADIUS, node.y - NODE_RADIUS,
                               2 * NODE_RADIUS, 2 * NODE_RADIUS);
         dot.setFilled(true);
         gw.add(dot);
      }
   }

/*
 * Creates an airline graph containing the flight data from Figure 17-2.
 * Real applications would almost certainly read the data from a file.
 */

   private Graph createGraph() {
      Graph airline = new Graph();
      airline.addNode(new AirlineNode("Atlanta", 588, 435));
      airline.addNode(new AirlineNode("Boston", 730, 118));
      airline.addNode(new AirlineNode("Chicago", 508, 221));
      airline.addNode(new AirlineNode("Dallas", 400, 493));
      airline.addNode(new AirlineNode("Denver", 279, 307));
      airline.addNode(new AirlineNode("Los Angeles", 62, 414));
      airline.addNode(new AirlineNode("New York", 706, 189));
      airline.addNode(new AirlineNode("Portland", 62, 68));
      airline.addNode(new AirlineNode("San Francisco", 26, 303));
      airline.addNode(new AirlineNode("Seattle", 86, 14));
      addFlight(airline, "Atlanta", "Chicago", Color.BLACK);
      addFlight(airline, "Atlanta", "Dallas", Color.RED);
      addFlight(airline, "Atlanta", "New York", Color.RED);
      addFlight(airline, "Boston", "New York", Color.RED);
      addFlight(airline, "Boston", "Seattle", Color.BLACK);
      addFlight(airline, "Chicago", "Denver", Color.BLACK);
      addFlight(airline, "Dallas", "Denver", Color.BLACK);
      addFlight(airline, "Dallas", "Los Angeles", Color.BLACK);
      addFlight(airline, "Dallas", "San Francisco", Color.RED);
      addFlight(airline, "Denver", "San Francisco", Color.BLACK);
      addFlight(airline, "Portland", "San Francisco", Color.BLACK);
      addFlight(airline, "Portland", "Seattle", Color.BLACK);
      return airline;
   }

/*
 * Adds an arc in each direction between the cities c1 and c2.
 */

   private void addFlight(Graph airline, String c1, String c2, Color color) {
      AirlineNode n1 = (AirlineNode) airline.getNode(c1);
      AirlineNode n2 = (AirlineNode) airline.getNode(c2);
      AirlineArc arc = new AirlineArc(n1, n2, color);
      airline.addArc(arc);
      arc = new AirlineArc(n2, n1, color);
      airline.addArc(arc);
   }

/* Inner class for a node containing the screen location */

   private static class AirlineNode extends Node {

      public AirlineNode(String name, double x, double y) {
         super(name);
         this.x = x;
         this.y = y;
      }

      private double x;
      private double y;

   }

/* Inner class for an arc containing color information */

   private static class AirlineArc extends Arc {

      public AirlineArc(AirlineNode start, AirlineNode finish, Color color) {
         super(start, finish);
         this.color = color;
      }

      private Color color;

   }

/* Constants */

   private static final int WIDTH = 760;
   private static final int HEIGHT = 520;
   private static final int NODE_RADIUS = 2;

/* Main program */

   public static void main(String[] args) {
      new AirlineDisplay().run();
   }

}
