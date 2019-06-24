/*
 * File: HelloWriter.java
 * ----------------------
 * This program writes a text file containing the message "hello, world".
 */

package edu.stanford.cs.javacs2.ch4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloWriter {

   public void run() {
      try {
         PrintWriter wr = new PrintWriter(
                             new BufferedWriter(
                                new FileWriter("Hello.txt")));
         wr.println("hello, world");
         wr.close();
      } catch (IOException ex) {
         throw new RuntimeException(ex.toString());
      }
   }

/* Main program */

   public static void main(String[] args) {
      new HelloWriter().run();
   }

}
