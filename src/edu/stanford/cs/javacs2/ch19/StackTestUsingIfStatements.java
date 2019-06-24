/*
 * File: StackTestUsingIfStatements.java
 * -------------------------------------
 * This program implements an interactive test for the Stack abstraction.
 */

package edu.stanford.cs.javacs2.ch19;

import edu.stanford.cs.console.Console;
import edu.stanford.cs.console.SystemConsole;
import edu.stanford.cs.javacs2.ch13.Stack;
import edu.stanford.cs.javacs2.ch7.TokenScanner;

public class StackTestUsingIfStatements {

   public void run() {
      Console console = new SystemConsole();
      stack = new Stack<String>();
      scanner = new TokenScanner();
      scanner.ignoreWhitespace();
      while (true) {
         String line = console.nextLine("-> ");
         scanner.setInput(line);
         if (scanner.hasMoreTokens()) dispatch(scanner.nextToken());
      }
   }

/* Calls the appropriate method based on the command name */

   private void dispatch(String name) {
      if (name.equals("push")) {
         pushCommand();
      } else if (name.equals("pop")) {
         popCommand();
      } else if (name.equals("clear")) {
         clearCommand();
      } else if (name.equals("peek")) {
         peekCommand();
      } else if (name.equals("size")) {
         sizeCommand();
      } else if (name.equals("isEmpty")) {
         isEmptyCommand();
      } else if (name.equals("list")) {
         listCommand();
      } else if (name.equals("quit")) {
         quitCommand();
      } else {
         System.out.println("Unknown command: " + name);
      }
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

/* Main program */

   public static void main(String[] args) {
      new StackTestUsingIfStatements().run();
   }

}
