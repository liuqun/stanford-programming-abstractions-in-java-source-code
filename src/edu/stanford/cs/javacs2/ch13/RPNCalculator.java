/*
 * File: RPNCalculator.java
 * ------------------------
 * This program simulates an electronic calculator that uses
 * reverse Polish notation, in which the operators come after
 * the operands to which they apply.  Information for users
 * of this application appears in the helpCommand function.
 */

package edu.stanford.cs.javacs2.ch13;

import edu.stanford.cs.console.Console;
import edu.stanford.cs.console.SystemConsole;

public class RPNCalculator {

   public void run() {
      Console console = new SystemConsole();
      System.out.println("RPN Calculator Simulation (type H for help)");
      Stack<Double> operandStack = new LinkedStack<Double>();
      while (true) {
         String line = console.nextLine("> ");
         char ch = Character.toUpperCase(line.charAt(0));
         if (ch == 'Q') {
            break;
         } else if (ch == 'C') {
            operandStack.clear();
         } else if (ch == 'H') {
            helpCommand();
         } else if (Character.isDigit(ch)) {
            operandStack.push(Double.parseDouble(line));
         } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
            applyOperator(ch, operandStack);
         } else {
            System.out.println("Unrecognized command " + ch);
         }
      }
   }

/**
 * Applies the operator to the top two elements on the operand stack.
 * Because the elements on the stack are popped in reverse order,
 * the right operand is popped before the left operand.
 */

   private void applyOperator(char op, Stack<Double> operandStack) {
      double result;
      double rhs = operandStack.pop();
      double lhs = operandStack.pop();
      switch (op) {
       case '+': result = lhs + rhs; break;
       case '-': result = lhs - rhs; break;
       case '*': result = lhs * rhs; break;
       case '/': result = lhs / rhs; break;
       default: throw new RuntimeException("Undefined operator " + op);
      }
      System.out.println(result);
      operandStack.push(result);
   }

/**
 * Generates a help message for the user.
 */

   private void helpCommand() {
      System.out.println("Enter expressions in Reverse Polish Notation,");
      System.out.println("in which operators follow the operands to which");
      System.out.println("they apply.  Each line consists of a number, an");
      System.out.println("operator, or one of the following commands:");
      System.out.println("  Q -- Quit the program");
      System.out.println("  H -- Display this help message");
      System.out.println("  C -- Clear the calculator stack");
   }

/* Main program */

   public static void main(String[] args) {
      new RPNCalculator().run();
   }

}
