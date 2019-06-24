/*
 * File: ListTest.java
 * -------------------
 * This program implements an interactive test of the List abstraction
 * using a client-supplied List<String>.
 */

package edu.stanford.cs.javacs2.ch13;

import edu.stanford.cs.consoletest.ConsoleTest;
import edu.stanford.cs.consoletest.HelpText;
import edu.stanford.cs.tokenscanner.TokenScanner;

public abstract class ListTest extends ConsoleTest {

   public ListTest() {
      list = createList();
   }

   public abstract List<String> createList();

   @HelpText("add key -- Adds key to the BST")
   public void addCommand(TokenScanner scanner) {
      String t1 = scanner.nextToken();
      String t2 = scanner.nextToken();
      if (t1.isEmpty()) {
         println("Usage: add [k] value");
      } else if (t2.isEmpty()) {
         list.add(t1);
      } else {
         list.add(Integer.parseInt(t1), t2);
      }
   }

   @HelpText("clear -- Removes all nodes from the BST")
   public void clearCommand(TokenScanner scanner) {
      list.clear();
   }

   @HelpText("contains key -- Prints whether the BST contains key")
   public void containsCommand(TokenScanner scanner) {
      println(list.contains(scanner.nextToken()));
   }

   @HelpText("get k -- Gets the element at index k")
   public void getCommand(TokenScanner scanner) {
      println(list.get(nextInt(scanner)));
   }

   @HelpText("indexOf str -- Prints the first index containing str")
   public void indexOfCommand(TokenScanner scanner) {
      println(list.indexOf(scanner.nextToken()));
   }

   @HelpText("isEmpty -- Prints whether the list is empty")
   public void isEmptyCommand(TokenScanner scanner) {
      println(list.isEmpty());
   }

   @HelpText("list -- Lists the elements of the BST in order")
   public void listCommand(TokenScanner scanner) {
      if (list.isEmpty()) {
         println("List is empty");
      } else {
         for (String str : list) {
            println(str);
         }
      }
   }

   @HelpText("remove k -- Removes element at index k")
   public void removeCommand(TokenScanner scanner) {
      String token = scanner.nextToken();
      if (token.isEmpty()) {
         println("Usage: remove k  or  remove str");
      }
      if (Character.isDigit(token.charAt(0))) {
         list.remove(Integer.parseInt(token));
      } else {
         list.remove(token);
      }
   }

   @HelpText("set k value -- Sets the element at index k to value")
   public void setCommand(TokenScanner scanner) {
      int k = nextInt(scanner);
      String token = scanner.nextToken();
      if (token.isEmpty()) {
         println("Missing value in set");
      } else {
         list.set(k, token);
      }
   }

   @HelpText("size -- Prints the size of the list")
   public void sizeCommand(TokenScanner scanner) {
      println(list.size());
   }

/* Private instance variables */

   private List<String> list;

}
