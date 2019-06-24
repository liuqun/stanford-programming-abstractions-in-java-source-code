/*
 * File: StackTestUsingACommandTable.java
 * --------------------------------------
 * This program implements an interactive test for the Stack abstraction.
 * This version uses a command dispatch table.
 */

package edu.stanford.cs.javacs2.ch19;

import edu.stanford.cs.console.Console;
import edu.stanford.cs.console.SystemConsole;
import edu.stanford.cs.javacs2.ch13.Stack;
import edu.stanford.cs.javacs2.ch14.HashMap;
import edu.stanford.cs.javacs2.ch7.TokenScanner;

public class StackTestUsingACommandTable {

   public void run() {
      Console console = new SystemConsole();
      stack = new Stack<String>();
      scanner = new TokenScanner();
      scanner.ignoreWhitespace();
      initCommandTable();
      while (true) {
         String line = console.nextLine("-> ");
         scanner.setInput(line);
         if (scanner.hasMoreTokens()) dispatch(scanner.nextToken());
      }
   }

/* Calls the appropriate method based on the command name */

   private void dispatch(String name) {
      Command cmd = commands.get(name);
      if (cmd == null) {
         System.out.println("Unknown command: " + name);
      } else {
         cmd.execute();
      }
   }

/* Inner interface to define the structure of a command */

   interface Command {
      public void execute();
   }

/* Initializes the command table */

   private void initCommandTable() {
      commands = new HashMap<String,Command>();
      commands.put("push",
                   new Command() {
                      public void execute() { pushCommand(); }
                   });
      commands.put("pop",
                   new Command() {
                      public void execute() { popCommand(); }
                   });
      commands.put("peek",
                   new Command() {
                      public void execute() { peekCommand(); }
                   });
      commands.put("size",
                   new Command() {
                      public void execute() { sizeCommand(); }
                   });
      commands.put("isEmpty",
                   new Command() {
                      public void execute() { isEmptyCommand(); }
                   });
      commands.put("clear",
                   new Command() {
                      public void execute() { clearCommand(); }
                   });
      commands.put("list",
                   new Command() {
                      public void execute() { listCommand(); }
                   });
      commands.put("quit",
                   new Command() {
                      public void execute() { quitCommand(); }
                   });
   }

/* Command methods for each of the test commands */

   private void pushCommand() {
      stack.push(scanner.nextToken());
   }

   private void popCommand() {
      System.out.println(stack.pop());
   }

   private void clearCommand() {
      stack.clear();
   }

   private void peekCommand() {
      System.out.println(stack.peek());
   }

   private void sizeCommand() {
      System.out.println(stack.size());
   }

   private void isEmptyCommand() {
      System.out.println(stack.isEmpty());
   }

   private void listCommand() {
      Stack<String> save = new Stack<String>();
      while (!stack.isEmpty()) {
         System.out.println(stack.peek());
         save.push(stack.pop());
      }
      while (!save.isEmpty()) {
         stack.push(save.pop());
      }
   }

   private void quitCommand() {
      System.exit(0);
   }

/* Private instance variables */

   private Stack<String> stack;
   private TokenScanner scanner;
   private HashMap<String,Command> commands;

/* Main program */

   public static void main(String[] args) {
      new StackTestUsingACommandTable().run();
   }

}
