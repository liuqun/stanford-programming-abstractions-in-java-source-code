/*
 * File: ConstantExp.java
 * ----------------------
 * This file exports the ConstantExp subclass.
 */

package edu.stanford.cs.javacs2.ch18;

public class ConstantExp extends Expression {

/**
 * Creates a new ConstantExp with the specified value.
 *
 * @param value The value of the constant
 */

   public ConstantExp(int value) {
      this.value = value;
   }

/* Evaluates a constant expression, which simply returns its value */

   @Override
   public int eval(EvaluationContext context) {
      return value;
   }

/* Converts the expression to a string */

   @Override
   public String toString() {
      return Integer.toString(value);
   }

/* Gets the value in this node */

   @Override
   public int getValue() {
      return value;
   }

/* Private instance variables */

   private int value;

}
