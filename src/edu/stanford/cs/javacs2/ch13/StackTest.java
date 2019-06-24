/*
 * File: StackTest.java
 * --------------------
 * This program implements an interactive test of the Stack abstraction.
 * Clients can override createStack to substitute other implementations of
 * the Stack class.
 */

package edu.stanford.cs.javacs2.ch13;

import edu.stanford.cs.consoletest.ConsoleTest;
import edu.stanford.cs.consoletest.HelpText;
import edu.stanford.cs.tokenscanner.TokenScanner;

public class StackTest extends ConsoleTest {

   public StackTest() {
      stack = createStack();
   }

   public Stack<String> createStack() {
      return new Stack<String>();
   }

   @HelpText("clear -- Clears the stack")
   public void clearCommand(TokenScanner scanner) {
      stack.clear();
   }

   @HelpText("isEmpty -- Prints whether the stack is empty")
   public void isEmptyCommand(TokenScanner scanner) {
      println(stack.isEmpty());
   }

   @HelpText("list -- List the elements of the stack")
   public void listCommand(TokenScanner scanner) {
      if (stack.isEmpty()) {
         println("Stack is empty");
      } else {
         listStack(stack);
      }
   }

   @HelpText("peek -- Peeks at the top element")
   public void peekCommand(TokenScanner scanner) {
      if (stack.isEmpty()) {
         println("Stack is empty");
      } else {
         println(stack.peek());
      }
   }

   @HelpText("pop -- Pops the stack and displays the value")
   public void popCommand(TokenScanner scanner) {
      if (stack.isEmpty()) {
         println("Stack is empty");
      } else {
         println(stack.pop());
      }
   }

   @HelpText("push str -- Pushes the string onto the stack")
   public void pushCommand(TokenScanner scanner) {
      String token = scanner.nextToken();
      if (token.isEmpty()) {
         println("Missing value to push");
      } else {
         stack.push(token);
      }
   }

   @HelpText("size -- Prints the size of the stack")
   public void sizeCommand(TokenScanner scanner) {
      println(stack.size());
   }

/* Lists the elements of the stack while preserving its contents */

   private void listStack(Stack<String> stack) {
      if (!stack.isEmpty()) {
         String value = stack.pop();
         println(value);
         listStack(stack);
         stack.push(value);
      }
   }

/* Private instance variables */

   private Stack<String> stack;

/* Main program */

   public static void main(String[] args) {
      new StackTest().run();
   }

}
