/*
 * File: ConsoleWindowTest.java
 * ----------------------------
 * This program reads and prints the three types of values supported by
 * the console interface: integers, floating-point numbers, and lines.
 */

package edu.stanford.cs.javacs2.ch8;

import edu.stanford.cs.console.Console;
import edu.stanford.cs.console.ConsoleWindow;

public class ConsoleWindowTest {

   public void run() {
      Console console = new ConsoleWindow();
      int n = console.nextInt("Enter an integer: ");
      console.println("That integer was " + n);
      double d = console.nextDouble("Enter a floating-point number: ");
      console.println("That number was " + d);
      String line = console.nextLine("Enter a line: ");
      console.printf("That line was \"%s\"%n", line);
   }

/* Main program */

   public static void main(String[] args) {
      new ConsoleWindowTest().run();
   }

}
