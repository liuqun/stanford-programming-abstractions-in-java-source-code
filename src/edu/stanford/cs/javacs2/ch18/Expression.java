/*
 * File: Expression.java
 * ---------------------
 * This file exports the Expression class, which is the root of the
 * expression hierarchy.
 */

package edu.stanford.cs.javacs2.ch18;

/**
 * This abstract class represents the highest level in the expression
 * hierarchy.  Every Expression object is an instance of one of the
 * concrete subclasses, which are ConstantExp, IdentifierExp, and
 * CompoundExp.
 */

public abstract class Expression {

/**
 * Evaluates this expression in the specified evaluation context.
 *
 * @param context The evaluation context
 * @return The result of evaluating the expression
 */

   public abstract int eval(EvaluationContext context);

/**
 * Converts the expression to a string.
 *
 * @return The string form of the expression
 */

   @Override
   public String toString() {
      throw new RuntimeException("No override for the toString method");
   }

/*
 * Implementation notes: getter methods
 * ------------------------------------
 * The remaining methods are implemented at lower levels of the expression
 * hierarchy but exported from the Expression class as a convenience.  These
 * methods throw exceptions if they are called on the wrong expression type.
 */

/**
 * Returns the value of a ConstantExp.
 *
 * @return The value of the constant
 */

   public int getValue() {
      throw new RuntimeException("getValue: Illegal expression type");
   }

/**
 * Returns the name of an IdentifierExp.
 *
 * @return The name of the identifier
 */

   public String getName() {
      throw new RuntimeException("getName: Illegal expression type");
   }

/**
 * Returns the operator field of a CompoundExp.
 *
 * @return The operator field of a compound
 */

   public String getOperator() {
      throw new RuntimeException("getOperator: Illegal expression type");
   }

/**
 * Returns the left hand side of a CompoundExp.
 *
 * @return The left hand side of a compound
 */

   public Expression getLHS() {
      throw new RuntimeException("getLHS: Illegal expression type");
   }

/**
 * Returns the right hand side of a CompoundExp.
 *
 * @return The right hand side of a compound
 */

   public Expression getRHS() {
      throw new RuntimeException("getRHS: Illegal expression type");
   }

}
