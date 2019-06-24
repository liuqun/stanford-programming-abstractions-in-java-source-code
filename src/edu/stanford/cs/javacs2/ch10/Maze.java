/*
 * File: Maze.java
 * ---------------
 * This file implements the Maze class.
 */

package edu.stanford.cs.javacs2.ch10;

import edu.stanford.cs.javacs2.ch7.Direction;
import edu.stanford.cs.javacs2.ch8.GCanvas;
import edu.stanford.cs.javacs2.ch8.GLine;
import edu.stanford.cs.javacs2.ch8.GWindow;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Maze {

/**
 * Initializes a Maze object from the specified file.
 *
 * @param filename The name of the file containing the maze data
 */

   public Maze(String filename) {
      this(filename, null);
   }

/**
 * Initializes a Maze object from the specified file and displays it in
 * the graphics window.
 *
 * @param filename The name of the file containing the maze data
 * @param gw The graphics window
 */

   public Maze(String filename, GWindow gw) {
      this.gw = gw;
      readMazeFile(filename);
      if (gw != null) {
         GCanvas gc = gw.getGCanvas();
         int width = (int) Math.round((cols + 2) * SQUARE_SIZE);
         int height = (int) Math.round((rows + 2) * SQUARE_SIZE);
         gc.setSize(width, height);
         gc.setPreferredSize(gc.getSize());
         Container c = gc.getParent();
         while (!(c instanceof Frame)) {
            c = c.getParent();
         }
         ((Frame) c).pack();
      }
   }

   public Point getStartPosition() {
      return startSquare;
   }

   public boolean isOutside(Point pt) {
      return pt.x < 0 || pt.x >= cols || pt.y < 0 || pt.y >= rows;
   }

   public boolean wallExists(Point pt, Direction dir) {
      if (pt.x == -1 && dir == Direction.EAST) {
         return wallExists(takeOneStep(pt, Direction.EAST), Direction.WEST);
      }
      if (pt.y == -1 && dir == Direction.SOUTH) {
         return wallExists(takeOneStep(pt, Direction.SOUTH), Direction.NORTH);
      }
      if (pt.x == cols && dir == Direction.WEST) {
         return wallExists(takeOneStep(pt, Direction.WEST), Direction.EAST);
      }
      if (pt.y == rows && dir == Direction.NORTH) {
         return wallExists(takeOneStep(pt, Direction.NORTH), Direction.SOUTH);
      }
      if (isOutside(pt)) {
         throw new RuntimeException("Coordinates are out of range");
      }
      return (maze[pt.y][pt.x].walls[dir.ordinal()]);
   }

   public void markSquare(Point pt) {
      if (isOutside(pt)) {
         throw new RuntimeException("Coordinates are out of range");
      }
      if (!isMarked(pt)) {
         if (gw != null) {
            maze[pt.y][pt.x].markH.setColor(Color.RED);
            maze[pt.y][pt.x].markV.setColor(Color.RED);
         }
         maze[pt.y][pt.x].isMarked = true;
      }
      if (gw != null) pause(PAUSE_TIME);
   }

   public void unmarkSquare(Point pt) {
      if (isOutside(pt)) {
         throw new RuntimeException("Coordinates are out of range");
      }
      if (isMarked(pt)) {
         if (gw != null) {
            maze[pt.y][pt.x].markH.setColor(Color.WHITE);
            maze[pt.y][pt.x].markV.setColor(Color.WHITE);
         }
         maze[pt.y][pt.x].isMarked = false;
      }
      if (gw != null) pause(PAUSE_TIME);
   }

   public boolean isMarked(Point pt) {
      if (isOutside(pt)) {
         throw new RuntimeException("Coordinates are out of range");
      }
      return maze[pt.y][pt.x].isMarked;
   }

/* Private methods */

/*
 * Reads the data file and computes the dimensions of the maze.  The file
 * must then be read a second time to read the actual data.  The values
 * indicating the size of the maze are stored in the private variables
 * cols and rows.
 */

   private void readMazeFile(String filename) {
      try {
         BufferedReader rd = new BufferedReader(new FileReader(filename));
         computeMazeSize(rd);
         rd.close();
         maze = new MazeSquare[rows][cols];
         for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
               MazeSquare sq = new MazeSquare();
               if (gw != null) {
                  double x = (j + 1.5) * SQUARE_SIZE;
                  double y = (i + 1.5) * SQUARE_SIZE;
                  sq.markH = new GLine(x - Maze.MARK_SIZE / 2, y,
                                       x + Maze.MARK_SIZE / 2, y);
                  sq.markH.setColor(Color.WHITE);
                  sq.markV = new GLine(x, y - Maze.MARK_SIZE / 2,
                                       x, y + Maze.MARK_SIZE / 2);
                  sq.markV.setColor(Color.WHITE);
                  gw.add(sq.markH);
                  gw.add(sq.markV);
               }
               maze[i][j] = sq;
            }
         }
         startSquare = new Point(-1, -1);
         rd = new BufferedReader(new FileReader(filename));
         processMazeFile(rd);
         rd.close();
         if (startSquare.x == -1) {
            throw new RuntimeException("Maze contains no start square");
         }
      } catch (IOException ex) {
         throw new RuntimeException(ex.getMessage());
      }
   }

   private void computeMazeSize(BufferedReader rd) throws IOException {
      int nLines = 0;
      boolean trailing = false;
      while (true) {
         String line = rd.readLine();
         if (line == null) break;
         int len = line.length();
         if (len == 0) {
            trailing = true;
         } else if (trailing) {
            throw new RuntimeException("Illegal blank lines in data file");
         } else if (nLines == 0) {
            if (len % 2 != 1) throw new RuntimeException("Illegal maze width");
            cols = (len - 1) / 2;
            nLines++;
         } else {
            nLines++;
         }
      }
      if (nLines % 2 != 1) throw new RuntimeException("Illegal maze height");
      rows = (nLines - 1) / 2;
   }

/*
 * Method: processMazeFile
 * Usage: processMazeFile(rd);
 * ---------------------------
 * Reads the actual maze data from the file.  The method computeMazeSize
 * must be called prior to calling processMazeFile.
 */

   private void processMazeFile(BufferedReader rd) throws IOException {
      String line = rd.readLine();
      processDividerLine(line, 0);
      for (int y = 0; y < rows; y++) {
         line = rd.readLine();
         processPassageLine(line, y);
         line = rd.readLine();
         processDividerLine(line, y + 1);
      }
   }

/*
 * Method: processDividerLine
 * Usage: processDividerLine(line, y);
 * -----------------------------------
 * Reads the odd-numbered lines in the data file, which specify the
 * positions of the horizontal walls.  These lines have the form
 *
 *     +-+-+-+-+-+-+-+-+
 *
 * where the - symbols may be replaced by spaces to indicate a
 * corridor square.  The y value gives the index of the squares
 * immediately to the north of this line.
 */

   private void processDividerLine(String line, int y) {
      if (line.length() != 2 * cols + 1) {
         throw new RuntimeException("Divider line has incorrect width");
      }
      for (int x = 0; x < cols; x++) {
         if (line.charAt(2 * x) != '+') {
            throw new RuntimeException("Missing corner symbol");
         }
         char ch = line.charAt(2 * x + 1);
         if (ch == '-') {
            setHorizontalWall(new Point(x, y));
         } else if (ch != ' ') {
            throw new RuntimeException("Illegal character in maze file");
         }
      }
      if (line.charAt(2 * cols) != '+') {
         throw new RuntimeException("Missing corner symbol");
      }
   }

/*
 * Method: processPassageLine
 * Usage: processPassageLine(line, y);
 * -----------------------------------
 * Reads the even-numbered lines in the data file, which specify the
 * passageways and locations of the vertical walls.  These lines have
 * the form
 *
 *     | | | | | | | | |
 *
 * where the | symbols may be replaced by spaces to indicate a corridor
 * square.  One of the open passageway squares in the file may also be
 * marked with an 'S' to indicate the start square.  The y argument
 * gives the index of the squares on this line.
 */

   private void processPassageLine(String line, int y) {
      int len = line.length();
      for (int x = 0; x < (len - 1) / 2; x++) {
         if (line.charAt(2 * x) == '|') {
            setVerticalWall(new Point(x, y));
         }
         char ch = line.charAt(2 * x + 1);
         if (ch == 'S') {
            setStartSquare(new Point(x, y));
         } else if (ch != ' ' && ch != '\0') {
            throw new RuntimeException("Illegal character in maze file");
         }
      }
      if (len % 2 == 1 && line.charAt(len - 1) == '|') {
         setVerticalWall(new Point((len - 1) / 2, y));
      }
   }

/*
 * Method: setHorizontalWall
 * Usage: setHorizontalWall(pt);
 * -----------------------------
 * Sets a horizontal wall to the north of the square at pt.  To maintain
 * consistency in the data structure, it is also necessary to create
 * a wall to the south of the square just north of this one.
 */

   private void setHorizontalWall(Point pt) {
      if (!isOutside(pt)) {
         maze[pt.y][pt.x].walls[Direction.NORTH.ordinal()] = true;
      }
      if (!isOutside(takeOneStep(pt, Direction.NORTH))) {
         maze[pt.y - 1][pt.x].walls[Direction.SOUTH.ordinal()] = true;
      }
      if (gw != null) {
         double x0 = (1.0 + pt.x) * SQUARE_SIZE;
         double y0 = (1.0 + pt.y) * SQUARE_SIZE;
         gw.add(new GLine(x0, y0, x0 + SQUARE_SIZE, y0));
      }
   }

/*
 * Method: setVerticalWall
 * Usage: setVerticalWall(pt);
 * ---------------------------
 * Sets a vertical wall to the west of the square at pt.  To maintain
 * consistency in the data structure, it is also necessary to create
 * a wall to the east of the square just west of this one.
 */

   private void setVerticalWall(Point pt) {
      if (!isOutside(pt)) {
         maze[pt.y][pt.x].walls[Direction.WEST.ordinal()] = true;
      }
      if (!isOutside(takeOneStep(pt, Direction.WEST))) {
         maze[pt.y][pt.x - 1].walls[Direction.EAST.ordinal()] = true;
      }
      if (gw != null) {
         double x0 = (1.0 + pt.x) * SQUARE_SIZE;
         double y0 = (1.0 + pt.y) * SQUARE_SIZE;
         gw.add(new GLine(x0, y0, x0, y0 + SQUARE_SIZE));
      }
   }

/*
 * Method: setStartSquare
 * Usage: setStartSquare(pt);
 * --------------------------
 * Sets the start square to the indicated point and draws a circle on
 * the screen.
 */

   private void setStartSquare(Point pt) {
      if (startSquare.x != -1) {
         throw new RuntimeException("Multiple start squares specified");
      }
      startSquare = pt;
   }

/*
 * Method: pause
 * Usage: pause(milliseconds);
 * ---------------------------
 * This method pauses the calling thread for the specified number of
 * milliseconds.
 */

   private void pause(int milliseconds) {
      try {
         Thread.sleep(milliseconds);
      } catch (InterruptedException ex) {
         /* Empty */
      }
   }

/*
 * Returns the point you reach if you move one step from start in the
 * specified direction.  To conform with standard graphical conventions,
 * moving north decreases the y value.
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

/* Constants */

   public static final double SQUARE_SIZE = 25;
   public static final double MARK_SIZE = 8;
   public static final int PAUSE_TIME = 150;

/* Private instance variables */

   private GWindow gw;
   private MazeSquare[][] maze;
   private int rows;
   private int cols;
   private Point startSquare;

}

/*
 * Class: MazeSquare
 * -----------------
 * This class defines the structure of a square in the maze.
 */

class MazeSquare {
   boolean[] walls;
   boolean isMarked;
   GLine markH, markV;

   public MazeSquare() {
      walls = new boolean[4];
      isMarked = false;
   }
}
