/*
 * File: CommandTest.java
 * ----------------------
 * This file tests for the sun.java.command property.
 */

package edu.stanford.cs.javacs2.ch1;

public class CommandTest extends GenericTest {
   /* Empty */
}

class GenericTest {

   public static void main(String[] args) {
      System.out.println("sun.java.command = " +
                         System.getProperty("sun.java.command"));
   }

}
