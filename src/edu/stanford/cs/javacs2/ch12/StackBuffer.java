/*
 * File: StackBuffer.java
 * ----------------------
 * This file implements the EditorBuffer abstraction using a pair of stacks.
 */

package edu.stanford.cs.javacs2.ch12;

import java.util.Stack;

/*
 * Implementation notes: StackBuffer
 * ---------------------------------
 * This class implements the EditorBuffer abstraction using a pair of stacks.
 * Characters before the cursor are stored in a stack named "before"; those
 * after the cursor are stored in a stack named "after".  In each case, the
 * characters closest to the cursor are closer to the top of the stack.
 */

public class StackBuffer implements EditorBuffer {

   public StackBuffer() {
      before = new Stack<Character>();
      after = new Stack<Character>();
   }

/*
 * Implementation notes: moveCursor methods
 * ----------------------------------------
 * These methods use push and pop to transfer values between the two stacks.
 */

   public void moveCursorForward() {
      if (!after.isEmpty()) {
         before.push(after.pop());
      }
   }

   public void moveCursorBackward() {
      if (!before.isEmpty()) {
         after.push(before.pop());
      }
   }

   public void moveCursorToStart() {
      while (!before.isEmpty()) {
         after.push(before.pop());
      }
   }

   public void moveCursorToEnd() {
      while (!after.isEmpty()) {
         before.push(after.pop());
      }
   }

/*
 * Implementation notes: character insertion and deletion
 * ------------------------------------------------------
 * Each of the functions that inserts or deletes characters can do so
 * with a single push or pop operation.
 */

   public void insertCharacter(char ch) {
      before.push(ch);
   }

   public void deleteCharacter() {
      if (!after.isEmpty()) {
         after.pop();
      }
   }

/*
 * Implementation notes: getText and getCursor
 * -------------------------------------------
 * This implementation of getText uses only the push, pop, size, and isEmpty
 * methods, which are fundamental primitives for the stack abstraction.  The
 * code must restore the contents of the stacks after creating the string.
 */

   public String getText() {
      int nBefore = before.size();
      int nAfter = after.size();
      String str = "";
      while (!before.isEmpty()) {
         str = before.pop() + str;
      }
      while (!after.isEmpty()) {
         str += after.pop();
      }
      for (int i = 0; i < nBefore; i++) {
         before.push(str.charAt(i));
      }
      for (int i = nBefore + nAfter - 1; i >= nBefore; i--) {
         after.push(str.charAt(i));
      }
      return str;
   }

   public int getCursor() {
      return before.size();
   }

/* Private instance variables */

   private Stack<Character> before;     /* Characters before the cursor */
   private Stack<Character> after;      /* Characters after the cursor  */

}
