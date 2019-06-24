/*
 * File: SimpleTextEditor.java
 * ---------------------------
 * This file implements a simple command-based text editor.
 */

package edu.stanford.cs.javacs2.ch12;

import edu.stanford.cs.console.Console;
import edu.stanford.cs.console.SystemConsole;

public abstract class SimpleTextEditor {

/* Defines the method that clients must implement to create the buffer */

   public abstract EditorBuffer createEditorBuffer();

/* Runs the text editor */

   public void run() {
      Console console = new SystemConsole();
      EditorBuffer buffer = createEditorBuffer();
      while (true) {
         String cmd = console.nextLine("*");
         if (!cmd.equals("")) executeCommand(buffer, cmd);
      }
   }

/* Executes the command on the editor buffer */

   private void executeCommand(EditorBuffer buffer, String cmd) {
      switch (Character.toUpperCase(cmd.charAt(0))) {
       case 'I': for (int i = 1; i < cmd.length(); i++) {
                    buffer.insertCharacter(cmd.charAt(i));
                 }
                 displayBuffer(buffer);
                 break;
       case 'D': buffer.deleteCharacter(); displayBuffer(buffer); break;
       case 'F': buffer.moveCursorForward(); displayBuffer(buffer); break;
       case 'B': buffer.moveCursorBackward(); displayBuffer(buffer); break;
       case 'J': buffer.moveCursorToStart(); displayBuffer(buffer); break;
       case 'E': buffer.moveCursorToEnd(); displayBuffer(buffer); break;
       case 'H': printHelpText(); break;
       case 'Q': System.exit(0);
       default:  System.out.println("Illegal command"); break;
      }
   }

/* Displays the state of the buffer including the position of the cursor */

   private void displayBuffer(EditorBuffer buffer) {
      String str = buffer.getText();
      for (int i = 0; i < str.length(); i++) {
         System.out.print(" " + str.charAt(i));
      }
      System.out.println();
      int cursor = buffer.getCursor();
      for (int i = 0; i < cursor; i++) {
         System.out.print("  ");
      }
      System.out.println("^");
   }

/* Displays a message showing the legal commands */

   private void printHelpText() {
      System.out.println("Editor commands:");
      System.out.println("  Iabc  Inserts abc at the cursor position");
      System.out.println("  F     Moves the cursor forward one character");
      System.out.println("  B     Moves the cursor backward one character");
      System.out.println("  D     Deletes the character after the cursor");
      System.out.println("  J     Jumps to the beginning of the buffer");
      System.out.println("  E     Jumps to the end of the buffer");
      System.out.println("  H     Prints this message");
      System.out.println("  Q     Exits from the editor program");
   }

}
