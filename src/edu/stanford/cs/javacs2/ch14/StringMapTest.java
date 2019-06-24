/*
 * File: StringMapTest.java
 * ------------------------
 * This program implements an interactive test of the StringMap interface.
 */

package edu.stanford.cs.javacs2.ch14;

import edu.stanford.cs.consoletest.ConsoleTest;
import edu.stanford.cs.consoletest.HelpText;
import edu.stanford.cs.tokenscanner.TokenScanner;

public abstract class StringMapTest extends ConsoleTest {

   public StringMapTest() {
      map = createMap();
   }

   public abstract StringMap createMap();

   public StringMap getMap() {
      return map;
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

   private StringMap map;

}
