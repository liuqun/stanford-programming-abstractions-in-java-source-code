/*
 * File: Rational.java
 * -------------------
 * This file defines a simple class for representing rational numbers.
 */

package edu.stanford.cs.javacs2.ch7;

/**
 * This class represents a rational number (the quotient of two integers).
 */

public class Rational {

/**
 * Creates a new Rational initialized to zero.
 */

   public Rational() {
      this(0);
   }

/**
 * Creates a new Rational from the integer argument.
 *
 * @param n The initial value
 */

   public Rational(int n) {
      this(n, 1);
   }

/**
 * Creates a new Rational with the value x / y.
 *
 * @param x The numerator of the rational number
 * @param y The denominator of the rational number
 */

   public Rational(int x, int y) {
      if (y == 0) throw new RuntimeException("Division by zero");
      if (x == 0) {
         num = 0;
         den = 1;
      } else {
         int g = gcd(Math.abs(x), Math.abs(y));
         num = x / g;
         den = Math.abs(y) / g;
         if (y < 0) num = -num;
      }
   }

/**
 * Adds the current number (r1) to the rational number r2 and returns the sum.
 *
 * @param r2 The rational number to be added
 * @return The sum of the current number and r2
 */

   public Rational add(Rational r2) {
      return new Rational(this.num * r2.den + r2.num * this.den,
                          this.den * r2.den);
   }

/**
 * Subtracts the rational number r2 from this one (r1).
 *
 * @param r2 The rational number to be subtracted
 * @return The result of subtracting r2 from the current number
 */

   public Rational subtract(Rational r2) {
      return new Rational(this.num * r2.den - r2.num * this.den,
                          this.den * r2.den);
   }

/**
 * Multiplies this number (r1) by the rational number r2.
 *
 * @param r2 The rational number used as a multiplier
 * @return The result of multiplying the current number by r2
 */

   public Rational multiply(Rational r2) {
      return new Rational(this.num * r2.num, this.den * r2.den);
   }

/**
 * Divides this number (r1) by the rational number r2.
 *
 * @param r2 The rational number used as a divisor
 * @return The result of dividing the current number by r2
 */

   public Rational divide(Rational r2) {
      return new Rational(this.num * r2.den, this.den * r2.num);
   }

/**
 * Creates a string representation of this rational number.
 *
 * @return The string representation of this rational number
 */

   @Override
   public String toString() {
      if (den == 1) {
         return "" + num;
      } else {
         return num + "/" + den;
      }
   }

/**
 * Calculates the greatest common divisor using Euclid's algorithm.
 *
 * @param x First integer
 * @param y Second integer
 * @return The greatest common divisor of x and y
 */

   private int gcd(int x, int y) {
      int r = x % y;
      while (r != 0) {
         x = y;
         y = r;
         r = x % y;
      }
      return y;
   }

/* Private instance variables */

   private int num;    /* The numerator of this Rational   */
   private int den;    /* The denominator of this Rational */

}
