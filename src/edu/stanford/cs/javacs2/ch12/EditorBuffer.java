/*
 * File: EditorBuffer.java
 * -----------------------
 * This file defines the interface for the editor buffer abstraction.
 */

package edu.stanford.cs.javacs2.ch12;

public interface EditorBuffer {

/**
 * Moves the cursor forward one character, if it is not at the end.
 */

   public void moveCursorForward();

/**
 * Moves the cursor backward one character, if it is not at the beginning.
 */

   public void moveCursorBackward();

/**
 * Moves the cursor to the start of this buffer.
 */

   public void moveCursorToStart();

/**
 * Moves the cursor to the end of this buffer.
 */

   public void moveCursorToEnd();

/**
 * Inserts the character ch, leaving the cursor after the inserted character.
 */

   public void insertCharacter(char ch);

/**
 * Deletes the character immediately after the cursor, if any.
 */

   public void deleteCharacter();

/**
 * Returns the contents of the buffer as a string.
 */

   public String getText();

/**
 * Returns the index of the cursor.
 */

   public int getCursor();

}
