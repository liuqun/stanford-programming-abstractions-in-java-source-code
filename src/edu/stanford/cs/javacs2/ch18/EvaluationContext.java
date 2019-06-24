/*
 * File: EvaluationContext.java
 * ----------------------------
 * This file exports the EvaluationContext class, which maintains the
 * information necessary to support expression evaluation.
 */

package edu.stanford.cs.javacs2.ch18;

import edu.stanford.cs.javacs2.ch14.HashMap;

public class EvaluationContext {

/* Creates a new evaluation context with no variable bindings */

   public EvaluationContext() {
      symbolTable = new HashMap<String,Integer>();
   }

/* Sets the value of the variable var */

   public void setValue(String var, int value) {
      symbolTable.put(var, value);
   }

/* Gets the value of the variable var */

   public int getValue(String var) {
      return symbolTable.get(var);
   }

/* Returns true if the variable var is defined */

   public boolean isDefined(String var) {
      return symbolTable.containsKey(var);
   }

/* Private instance variables */

   private HashMap<String,Integer> symbolTable;

}
