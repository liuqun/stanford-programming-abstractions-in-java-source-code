/*
 * File: ListEditor.java
 * ---------------------
 * This program implements a simple command-driven text editor.  This
 * version uses a linked list to represent the buffer.
 */

package edu.stanford.cs.javacs2.ch12;

public class ListEditor extends SimpleTextEditor {

   @Override
   public EditorBuffer createEditorBuffer() {
      return new ListBuffer();
   }

   public static void main(String[] args) {
      new ListEditor().run();
   }

}
