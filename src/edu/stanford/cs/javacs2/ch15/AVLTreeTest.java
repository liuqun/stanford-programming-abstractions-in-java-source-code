/*
 * File: AVLTreeTest.java
 * ----------------------
 * This program implements an interactive test of the AVLTree class.
 */

package edu.stanford.cs.javacs2.ch15;

import edu.stanford.cs.consoletest.ConsoleTest;
import edu.stanford.cs.consoletest.HelpText;
import edu.stanford.cs.tokenscanner.TokenScanner;

public class AVLTreeTest extends ConsoleTest {

   public AVLTreeTest() {
      bst = createAVLTree();
   }

   public AVLTree createAVLTree() {
      return new AVLTree();
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

   @HelpText("remove key -- Removes key from the BST")
   public void removeCommand(TokenScanner scanner) {
      String token = scanner.nextToken();
      if (token.isEmpty()) {
         println("Missing value to remove");
      } else {
         bst.remove(token);
      }
   }

   @HelpText("contains key -- Prints whether the BST contains key")
   public void containsCommand(TokenScanner scanner) {
      println(bst.contains(scanner.nextToken()));
   }

   @HelpText("display -- Displays the structure of the BST")
   public void displayCommand(TokenScanner scanner) {
      bst.display();
   }

   @HelpText("list -- Lists the elements of the BST in order")
   public void listCommand(TokenScanner scanner) {
      bst.list();
   }

   @HelpText("trace on/off -- Turns rotation tracing on or off")
   public void traceCommand(TokenScanner scanner) {
      String token = scanner.nextToken();
      if (token.isEmpty()) {
         bst.setTraceFlag(!bst.getTraceFlag());
      } else if (token.equalsIgnoreCase("on")) {
         bst.setTraceFlag(true);
      } else if (token.equalsIgnoreCase("off")) {
         bst.setTraceFlag(false);
      } else {
         println("The trace command requires on or off");
      }
   }

/* Private instance variables */

   private AVLTree bst;

/* Main program */

   public static void main(String[] args) {
      new AVLTreeTest().run();
   }

}
