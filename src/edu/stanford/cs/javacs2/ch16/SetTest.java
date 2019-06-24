/*
 * File: SetTest.java
 * ------------------
 * This program implements an interactive test of the Set abstraction
 * using a client-supplied Set<String>.
 */

package edu.stanford.cs.javacs2.ch16;

import edu.stanford.cs.consoletest.ConsoleTest;
import edu.stanford.cs.consoletest.HelpText;
import edu.stanford.cs.tokenscanner.TokenScanner;

public abstract class SetTest extends ConsoleTest {

   public SetTest() {
      set = createSet();
   }

   public abstract Set<String> createSet();

   @HelpText("add str -- Adds str to the set")
   public void addCommand(TokenScanner scanner) {
      String token = scanner.nextToken();
      if (token.isEmpty()) {
         println("Missing value to add");
      } else {
         set.add(token);
      }
   }

   @HelpText("clear -- Clears the set")
   public void clearCommand(TokenScanner scanner) {
      set.clear();
   }

   @HelpText("contains str -- Prints whether the set contains str")
   public void containsCommand(TokenScanner scanner) {
      println(set.contains(scanner.nextToken()));
   }

   @HelpText("isEmpty -- Prints whether the set is empty")
   public void isEmptyCommand(TokenScanner scanner) {
      println(set.isEmpty());
   }

   @HelpText("list -- Prints the elements of the set")
   public void listCommand(TokenScanner scanner) {
      if (set.isEmpty()) {
         println("Set is empty");
      } else {
         for (String str : set) {
            println(str);
         }
      }
   }

   @HelpText("remove str -- Removes str from the set")
   public void removeCommand(TokenScanner scanner) {
      String token = scanner.nextToken();
      if (token.isEmpty()) {
         println("Missing value to remove");
      } else {
         set.remove(token);
      }
   }

   @HelpText("size -- Prints the size of the set")
   public void sizeCommand(TokenScanner scanner) {
      println(set.size());
   }

/* Private instance variables */

   private Set<String> set;

}
