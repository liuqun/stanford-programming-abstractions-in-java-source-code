/*
 * File: Hanoi.java
 * ----------------
 * This program solves the Towers of Hanoi puzzle.
 */

package edu.stanford.cs.javacs2.ch9;

import edu.stanford.cs.console.Console;
import edu.stanford.cs.console.SystemConsole;

public class Hanoi {

   public void run() {
      Console console = new SystemConsole();
      int n = console.nextInt("Enter number of disks: ");
      moveTower(n, 'A', 'B', 'C');
   }

/**
 * Moves a tower of size n from the start spire to the finish
 * spire using the tmp spire as the temporary repository.
 */

   private void moveTower(int n, char start, char finish, char tmp) {
      if (n == 1) {
         moveSingleDisk(start, finish);
      } else {
         moveTower(n - 1, start, tmp, finish);
         moveSingleDisk(start, finish);
         moveTower(n - 1, tmp, finish, start);
      }
   }

/**
 * Executes the transfer of a single disk from the start spire to the
 * finish spire.  In this implementation, the move is simply displayed
 * on the console; in a graphical implementation, the code would update
 * the graphics window to show the new arrangement.
 */

   private void moveSingleDisk(char start, char finish) {
      System.out.println(start + " -> " + finish);
   }

/* Main program */

   public static void main(String[] args) {
      new Hanoi().run();
   }

}
