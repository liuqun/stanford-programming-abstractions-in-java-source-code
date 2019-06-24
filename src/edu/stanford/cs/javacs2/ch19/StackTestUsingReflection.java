/*
 * File: StackTestUsingReflection.java
 * -----------------------------------
 * This program implements an interactive test for the Stack abstraction.
 * This version uses reflection to find the commands in the Java code.
 */

package edu.stanford.cs.javacs2.ch19;

import edu.stanford.cs.console.Console;
import edu.stanford.cs.console.SystemConsole;
import edu.stanford.cs.javacs2.ch13.Stack;
import edu.stanford.cs.javacs2.ch7.TokenScanner;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class StackTestUsingReflection {

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
      try {
         Method fn = getClass().getMethod(name + "Command", new Class[0]);
         fn.invoke(this, new Object[0]);
      } catch (NoSuchMethodException ex) {
         System.out.println("Undefined command: " + name);
      } catch (IllegalAccessException ex) {
         System.out.println(ex.toString());
      } catch (InvocationTargetException ex) {
         System.out.println(ex.getTargetException().toString());
      }
   }

/* Command methods for each of the test commands */

   public void pushCommand() {
      stack.push(scanner.nextToken());
   }

   public void popCommand() {
      System.out.println(stack.pop());
   }

   public void clearCommand() {
      stack.clear();
   }

   public void peekCommand() {
      System.out.println(stack.peek());
   }

   public void sizeCommand() {
      System.out.println(stack.size());
   }

   public void isEmptyCommand() {
      System.out.println(stack.isEmpty());
   }

   public void listCommand() {
      Stack<String> save = new Stack<String>();
      while (!stack.isEmpty()) {
         System.out.println(stack.peek());
         save.push(stack.pop());
      }
      while (!save.isEmpty()) {
         stack.push(save.pop());
      }
   }

   public void quitCommand() {
      System.exit(0);
   }

/* Private instance variables */

   private Stack<String> stack;
   private TokenScanner scanner;

/* Main program */

   public static void main(String[] args) {
      new StackTestUsingReflection().run();
   }

}
