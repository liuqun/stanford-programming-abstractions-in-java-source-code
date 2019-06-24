/*
 * File: IdentifierExp.java
 * ------------------------
 * This file exports the IdentifierExp subclass.
 */

package edu.stanford.cs.javacs2.ch18;

public class IdentifierExp extends Expression {

/**
 * Creates a new IdentifierExp with the specified name.
 */

   public IdentifierExp(String name) {
      this.name = name;
   }

/* Evaluates the identifier by looking it up in the evaluation context */

   @Override
   public int eval(EvaluationContext context) {
      if (!context.isDefined(name)) {
         throw new RuntimeException(name + " is undefined");
      }
      return context.getValue(name);
   }

/* Converts the expression to a string */

   @Override
   public String toString() {
      return name;
   }

   @Override
   public String getName() {
      return name;
   }

/* Private instance variables */

   private String name;

}
