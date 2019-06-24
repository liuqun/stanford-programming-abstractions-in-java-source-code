/*
 * File: CompoundExp.java
 * ----------------------
 * This file exports the CompoundExp subclass, which is used to represent
 * expressions consisting of an operator joining two operands.
 */

package edu.stanford.cs.javacs2.ch18;

public class CompoundExp extends Expression {

/**
 * Creates a new CompoundExp from an operator and the expressions for the
 * left and right operands.
 *
 * @param op The operator
 * @param lhs The expression to the left of the operator
 * @param rhs The expression to the right of the operator
 */

   public CompoundExp(String op, Expression lhs, Expression rhs) {
      this.op = op;
      this.lhs = lhs;
      this.rhs = rhs;
   }

/* Evaluates a compound expression recursively */

   @Override
   public int eval(EvaluationContext context) {
      int right = rhs.eval(context);
      if (op.equals("=")) {
         context.setValue(lhs.getName(), right);
         return right;
      }
      int left = lhs.eval(context);
      if (op.equals("+")) return left + right;
      if (op.equals("-")) return left - right;
      if (op.equals("*")) return left * right;
      if (op.equals("/")) return left / right;
      throw new RuntimeException("Illegal operator");
   }

/* Converts the expression to a string */

   @Override
   public String toString() {
      return '(' + lhs.toString() + ' ' + op + ' ' + rhs.toString() + ')';
   }

/* Gets the operator field */

   @Override
   public String getOperator() {
      return op;
   }

/* Gets the left subexpression */

   @Override
   public Expression getLHS() {
      return lhs;
   }

/* Gets the right subexpression */

   @Override
   public Expression getRHS() {
      return rhs;
   }

/* Private instance variables */

   private String op;
   private Expression lhs;
   private Expression rhs;

}
