/*
 * File: MapTest.java
 * ------------------
 * This program implements an interactive test of the Map abstraction,
 * using a client-supplied map.
 */

package edu.stanford.cs.javacs2.ch14;

import edu.stanford.cs.consoletest.ConsoleTest;
import edu.stanford.cs.consoletest.HelpText;
import edu.stanford.cs.tokenscanner.TokenScanner;

public abstract class MapTest extends ConsoleTest {

   public MapTest() {
      map = createMap();
   }

   public abstract Map<String,String> createMap();

   @HelpText("contains key -- Prints whether map contains key")
   public void containsCommand(TokenScanner scanner) {
      println(map.containsKey(scanner.nextToken()));
   }

   @HelpText("size -- Prints the size of the map")
   public void sizeCommand(TokenScanner scanner) {
      println(map.size());
   }

   @HelpText("isEmpty -- Prints whether the map is empty")
   public void isEmptyCommand(TokenScanner scanner) {
      println(map.isEmpty());
   }

   @HelpText("clear -- Clears the map")
   public void clearCommand(TokenScanner scanner) {
      map.clear();
   }

   @HelpText("remove key -- Removes key from the map")
   public void removeCommand(TokenScanner scanner) {
      map.remove(scanner.nextToken());
   }

   @HelpText("list -- List the elements of the map")
   public void listCommand(TokenScanner scanner) {
      if (map.isEmpty()) {
         println("Map is empty");
      } else {
         for (String key : map.keySet()) {
            println(key + " = " + map.get(key));
         }
      }
   }

   @Override
   @HelpText("help -- List these commands")
   public void helpCommand(TokenScanner scanner) {
      super.helpCommand(scanner);
      String format = "  %-" + getCommandFieldWidth() + "s%s%n";
      printf(format, "key = value", " -- Assign value to key");
      printf(format, "key", " -- Print current value of key");
   }

   @Override
   public void undefinedCommand(String cmd, TokenScanner scanner) {
      String token = scanner.nextToken();
      if (token.equals("=")) {
         String value = "";
         while (scanner.hasMoreTokens()) {
            value += scanner.nextToken();
         }
         map.put(cmd, value);
      } else if (token.equals("")) {
         println(map.get(cmd));
      } else {
         println("Unrecognized command: " + cmd);
      }
   }

/* Private instance variables */

   private Map<String,String> map;

}
