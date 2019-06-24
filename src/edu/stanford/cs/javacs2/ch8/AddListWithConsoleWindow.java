/*
 * File: AddListWithConsoleWindow.java
 * -----------------------------------
 * This program adds a list of integers.  This version of the program uses
 * a console window instead of the system console.
 */

package edu.stanford.cs.javacs2.ch8;

import edu.stanford.cs.console.Console;
import edu.stanford.cs.console.ConsoleWindow;

public class AddListWithConsoleWindow extends AddListWithSystemConsole {

/* Factory method to create the console */

   public Console createConsole() {
      return new ConsoleWindow();
   }

/* Main program */

   public static void main(String[] args) {
      new AddListWithConsoleWindow().run();
   }

}
