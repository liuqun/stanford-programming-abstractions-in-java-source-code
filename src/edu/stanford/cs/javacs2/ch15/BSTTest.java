/*
 * File: BSTTest.java
 * ------------------
 * This file runs an interactive test of the BST class.
 */

package edu.stanford.cs.javacs2.ch15;

import edu.stanford.cs.consoletest.ConsoleTest;
import edu.stanford.cs.consoletest.HelpText;
import edu.stanford.cs.tokenscanner.TokenScanner;

public class BSTTest extends ConsoleTest {

   public BSTTest() {
      bst = createBST();
   }

   public BST createBST() {
      return new BST();
   }

   @HelpText("add key -- Adds key to the BST")
   public void addCommand(TokenScanner scanner) {
      String token = scanner.nextToken();
      if (token.isEmpty()) {
         println("Missing value to add");
      } else {
         bst.add(token);
      }
   }

   @HelpText("contains key -- Prints whether the BST contains key")
   public void containsCommand(TokenScanner scanner) {
      println(bst.contains(scanner.nextToken()));
   }

   @HelpText("list -- Lists the elements of the BST in order")
   public void listCommand(TokenScanner scanner) {
      bst.list();
   }

/* Private instance variables */

   private BST bst;

/* Main program */

   public static void main(String[] args) {
      new BSTTest().run();
   }

}
