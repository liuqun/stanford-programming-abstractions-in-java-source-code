/*
 * File: SolveMaze.java
 * --------------------
 * This program solves a maze by recursive backtracking.
 */

package edu.stanford.cs.javacs2.ch10;

import edu.stanford.cs.javacs2.ch7.Direction;
import edu.stanford.cs.javacs2.ch8.GWindow;
import java.awt.Point;

public class SolveMaze {

   public void run() {
      GWindow gw = new GWindow();
      Maze maze = new Maze("SampleMaze.txt", gw);
      if (!solveMaze(maze, maze.getStartPosition())) {
         System.out.println("No solution exists.");
      }
   }

/*
 * Attempts to generate a solution to the current maze from the specified
 * start point.  The method returns true if the maze has a solution.
 */

   private boolean solveMaze(Maze maze, Point start) {
      if (maze.isOutside(start)) return true;
      if (maze.isMarked(start)) return false;
      maze.markSquare(start);
      for (Direction dir : Direction.values()) {
         if (!maze.wallExists(start, dir)) {
            if (solveMaze(maze, takeOneStep(start, dir))) {
               return true;
            }
         }
      }
      maze.unmarkSquare(start);
      return false;
   }

/*
 * Returns the point that is one step from pt in the specified direction.
 */

   private Point takeOneStep(Point pt, Direction dir) {
      switch (dir) {
       case NORTH: return new Point(pt.x, pt.y - 1);
       case EAST: return new Point(pt.x + 1, pt.y);
       case SOUTH: return new Point(pt.x, pt.y + 1);
       case WEST: return new Point(pt.x - 1, pt.y);
      }
      throw new RuntimeException("Illegal direction");
   }

/* Main program */

   public static void main(String[] args) {
      new SolveMaze().run();
   }

}
