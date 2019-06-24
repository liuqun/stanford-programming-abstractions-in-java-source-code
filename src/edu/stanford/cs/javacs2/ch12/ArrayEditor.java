/*
 * File: ArrayEditor.java
 * ----------------------
 * This program implements a simple command-driven text editor.  This
 * version uses an array-based representation for the buffer.
 */

package edu.stanford.cs.javacs2.ch12;

public class ArrayEditor extends SimpleTextEditor {

   @Override
   public EditorBuffer createEditorBuffer() {
      return new ArrayBuffer();
   }

   public static void main(String[] args) {
      new ArrayEditor().run();
   }

}
