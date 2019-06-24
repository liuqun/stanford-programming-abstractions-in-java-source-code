/*
 * File: Interpreter.java
 * ----------------------
 * This program simulates the top level of an expression interpreter.  The
 * program reads an expression, evaluates it, and then displays the result.
 */

package edu.stanford.cs.javacs2.ch18;

import edu.stanford.cs.console.Console;
import edu.stanford.cs.console.SystemConsole;

public class Interpreter {

   public void run() {
      EvaluationContext context = new EvaluationContext();
      ExpParser parser = new ExpParser();
      Console console = new SystemConsole();
      while (true) {
         try {
            String line = console.nextLine("=> ");
            if (line.equals("quit")) break;
            parser.setInput(line);
            Expression exp = parser.parseExp();
            int value = exp.eval(context);
            console.println(value);
         } catch (RuntimeException ex) {
            console.println("Error: " + ex.getMessage());
         }
      }
   }

/* Main program */

   public static void main(String[] args) {
      new Interpreter().run();
   }

}
