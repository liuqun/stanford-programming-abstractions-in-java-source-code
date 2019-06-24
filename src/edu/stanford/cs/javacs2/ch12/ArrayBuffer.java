/*
 * File: ArrayBuffer.java
 * ----------------------
 * This file implements the EditorBuffer abstraction using an array of
 * characters as the underlying storage model.
 */

package edu.stanford.cs.javacs2.ch12;

/*
 * Implementation notes: ArrayBuffer
 * ---------------------------------
 * This class implements the EditorBuffer abstraction using an array of
 * characters.  In addition to the array, the structure keeps track of
 * the actual number of characters in the buffer (which is typically less
 * than the length of the array) and the current position of the cursor.
 */

public class ArrayBuffer implements EditorBuffer {

   public ArrayBuffer() {
      array = new char[INITIAL_CAPACITY];
      count = 0;
      cursor = 0;
   }

/*
 * Implementation notes: moveCursor methods
 * ----------------------------------------
 * The four moveCursor methods simply adjust the value of cursor.
 */

   public void moveCursorForward() {
      if (cursor < count) cursor++;
   }

   public void moveCursorBackward() {
      if (cursor > 0) cursor--;
   }

   public void moveCursorToStart() {
      cursor = 0;
   }

   public void moveCursorToEnd() {
      cursor = count;
   }

/*
 * Implementation notes: character insertion and deletion
 * ------------------------------------------------------
 * Each of the functions that inserts or deletes characters must shift
 * all subsequent characters in the array, either to make room for new
 * insertions or to close up space left by deletions.
 */

   public void insertCharacter(char ch) {
      if (count == array.length) expandCapacity();
      for (int i = count; i > cursor; i--) {
         array[i] = array[i - 1];
      }
      array[cursor] = ch;
      count++;
      cursor++;
   }

   public void deleteCharacter() {
      if (cursor < count) {
         for (int i = cursor+1; i < count; i++) {
            array[i - 1] = array[i];
         }
         count--;
      }
   }

/* Simple getter methods: getText, getCursor */

   public String getText() {
      return new String(array, 0, count);
   }

   public int getCursor() {
      return cursor;
   }

/*
 * Implementation notes: expandCapacity
 * ------------------------------------
 * This private method doubles the size of the array whenever the old one
 * runs out of space.  To do so, expandCapacity allocates a new array,
 * copies the old characters to the new array, and then replaces the old
 * array with the new one.
 */

   private void expandCapacity() {
      char[] newArray = new char[2 * array.length];
      for (int i = 0; i < count; i++) {
         newArray[i] = array[i];
      }
      array = newArray;
   }

/* Constants */

   private static final int INITIAL_CAPACITY = 10;

/* Private instance variables */

   private char[] array;        /* Allocated array of characters     */
   private int count;           /* Actual number of character in use */
   private int cursor;          /* Index of character after cursor   */

}
