/*
 * File: HelloName.java
 * --------------------
 * This program reads in a name from the user and then prints that
 * name back as part of a cheery greeting.
 */

package edu.stanford.cs.javacs2.ch3;

import java.util.Scanner;

public class HelloName {

   public void run() {
      Scanner sysin = new Scanner(System.in);
      System.out.print("Enter your name: ");
      String name = sysin.nextLine();
      System.out.println("Hello, " + name + "!");
   }

/* Main program */

   public static void main(String[] args) {
      new HelloName().run();
   }

}
