/*
 * File: StackEditor.java
 * ----------------------
 * This program implements a simple command-driven text editor.  This
 * version uses two character stacks to represent the buffer.
 */

package edu.stanford.cs.javacs2.ch12;

public class StackEditor extends SimpleTextEditor {

   @Override
   public EditorBuffer createEditorBuffer() {
      return new StackBuffer();
   }

   public static void main(String[] args) {
      new StackEditor().run();
   }

}
