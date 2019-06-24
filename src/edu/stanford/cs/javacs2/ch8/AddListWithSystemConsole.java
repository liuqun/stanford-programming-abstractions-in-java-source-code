/*
 * File: AddListWithSystemConsole.java
 * -----------------------------------
 * This program adds a list of integers.  The end of the input is indicated
 * by entering a sentinel value, which is defined by the constant SENTINEL.
 * This version uses a Console object for input and output.  The console is
 * created using a factory method, which makes it easy for subclasses to
 * substitute a different implementation.
 */

package edu.stanford.cs.javacs2.ch8;

import edu.stanford.cs.console.Console;
import edu.stanford.cs.console.SystemConsole;

public class AddListWithSystemConsole {

   public void run() {
      Console console = createConsole();
      console.println("This program adds a list of integers.");
      console.println("Use " + SENTINEL + " to signal the end.");
      int total = 0;
      while (true) {
         int value = console.nextInt(" ? ");
         if (value == SENTINEL) break;
         total += value;
      }
      console.println("The total is " + total);
   }

/* Factory method to create the console */

   public Console createConsole() {
      return new SystemConsole();
   }

/* Private constants */

   private static final int SENTINEL = 0;

/* Main program */

   public static void main(String[] args) {
      new AddListWithSystemConsole().run();
   }

}
