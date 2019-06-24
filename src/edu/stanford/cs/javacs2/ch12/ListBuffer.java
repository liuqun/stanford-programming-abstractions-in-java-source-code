/*
 * File: ListBuffer.java
 * ---------------------
 * This file implements the EditorBuffer abstraction using a linked
 * list as the underlying storage model.
 */

package edu.stanford.cs.javacs2.ch12;

/*
 * Implementation notes: ListBuffer
 * --------------------------------
 * This class implements the EditorBuffer abstraction using a linked list.
 * In the linked-list model, the characters in the buffer are stored in a
 * list of Cell structures, each of which contains a character and a
 * reference to the next cell in the chain.  To simplify the code used to
 * maintain the cursor, this implementation adds an extra "dummy cell" at
 * the beginning of the list.  The character in this cell is not used, but
 * having it in the data structure simplifies the code.
 *
 * The following diagram shows the structure of the list-based buffer
 * containing "ABC" with the cursor at the beginning:
 *
 *        +-----+      +-----+      +-----+      +-----+      +-----+
 *  start |  o--+---==>|     |   -->|  A  |   -->|  B  |   -->|  C  |
 *        +-----+  /   +-----+  /   +-----+  /   +-----+  /   +-----+
 * cursor |  o--+--    |  o--+--    |  o--+--    |  o--+--    |  /  |
 *        +-----+      +-----+      +-----+      +-----+      +-----+
 */

public class ListBuffer implements EditorBuffer {

/* Cell structure */

   private static class Cell {
      char ch;          /* The character in the cell                 */
      Cell link;        /* A reference to the next cell in the chain */
   }

/* Constructor */

   public ListBuffer() {
      start = cursor = new Cell();
      start.link = null;
   }

/*
 * Implementation notes: moveCursor methods
 * ----------------------------------------
 * The four methods that move the cursor have different time complexities
 * because the structure of a linked list is asymmetrical with respect to
 * moving backward and forward.  The moveCursorForward and moveCursorToStart
 * methods operate in constant time.  By contrast, the moveCursorBackward
 * and moveCursorToEnd methods each require a loop that runs in linear time.
 */

   public void moveCursorForward() {
      if (cursor.link != null) {
         cursor = cursor.link;
      }
   }

   public void moveCursorBackward() {
      if (cursor != start) {
         Cell cp = start;
         while (cp.link != cursor) {
            cp = cp.link;
         }
         cursor = cp;
      }
   }

   public void moveCursorToStart() {
      cursor = start;
   }

   public void moveCursorToEnd() {
      while (cursor.link != null) {
         cursor = cursor.link;
      }
   }

/*
 * Implementation notes: insertCharacter
 * -------------------------------------
 * The steps required to insert a new character are:
 *
 * 1. Create a new cell and put the new character in it.
 * 2. Copy the reference indicating the rest of the list into the link.
 * 3. Update the link in the current cell to point to the new one.
 * 4. Move the cursor forward over the inserted character.
 */

   public void insertCharacter(char ch) {
      Cell cp = new Cell();
      cp.ch = ch;
      cp.link = cursor.link;
      cursor.link = cp;
      cursor = cp;
   }

/*
 * Implementation notes: deleteCharacter
 * -------------------------------------
 * Deletion of the character requires removing the next cell from the
 * chain by changing the link field so that it points to the following
 * cell.
 */

   public void deleteCharacter() {
      if (cursor.link != null) {
         cursor.link = cursor.link.link;
      }
   }

/*
 * Implementation notes: getText and getCursor
 * -------------------------------------------
 * The getText method uses the standard linked-list pattern to loop
 * through the cells in the linked list.  The getCursor method counts
 * the characters in the list until it reaches the cursor.
 */

   public String getText() {
      String str = "";
      for (Cell cp = start.link; cp != null; cp = cp.link) {
         str += cp.ch;
      }
      return str;
   }

   public int getCursor() {
      int nChars = 0;
      for (Cell cp = start; cp != cursor; cp = cp.link) {
         nChars++;
      }
      return nChars;
   }

/* Private instance variables */

   private Cell start;     /* Reference to the dummy cell             */
   private Cell cursor;    /* Reference to the cell before the cursor */

}
