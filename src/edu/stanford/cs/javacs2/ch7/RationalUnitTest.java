/*
 * File: RationalUnitTest.java
 * ---------------------------
 * This program implements a unit test for the Rational class.
 */

package edu.stanford.cs.javacs2.ch7;

import edu.stanford.cs.unittest.UnitTest;

public class RationalUnitTest {

   public void run() {
      testConstructor();
      testAdd();
      testSubtract();
      testMultiply();
      testDivide();
   }

/* Test the three forms of the Rational constructor */

   private void testConstructor() {
      UnitTest.assertEquals(new Rational().toString(), "0");
      UnitTest.assertEquals(new Rational(42).toString(), "42");
      UnitTest.assertEquals(new Rational(-17).toString(), "-17");
      UnitTest.assertEquals(new Rational(3, 1).toString(), "3");
      UnitTest.assertEquals(new Rational(1, 3).toString(), "1/3");
      UnitTest.assertEquals(new Rational(2, 6).toString(), "1/3");
      UnitTest.assertEquals(new Rational(-1, 3).toString(), "-1/3");
      UnitTest.assertEquals(new Rational(1, -3).toString(), "-1/3");
      UnitTest.assertEquals(new Rational(0, 2).toString(), "0");
   }

/* Test the add method */

   private void testAdd() {
      UnitTest.assertEquals(ONE.add(ONE).toString(), "2");
      UnitTest.assertEquals(ONE_HALF.add(ONE_THIRD).toString(), "5/6");
      UnitTest.assertEquals(ONE.add(MINUS_ONE).toString(), "0");
      UnitTest.assertEquals(MINUS_ONE.add(ONE).toString(), "0");
   }

/* Test the subtract method */

   private void testSubtract() {
      UnitTest.assertEquals(ONE.subtract(ONE).toString(), "0");
      UnitTest.assertEquals(ONE_HALF.subtract(ONE_THIRD).toString(), "1/6");
      UnitTest.assertEquals(ONE.subtract(MINUS_ONE).toString(), "2");
      UnitTest.assertEquals(MINUS_ONE.subtract(ONE).toString(), "-2");
   }

/* Test the multiply method */

   private void testMultiply() {
      UnitTest.assertEquals(ZERO.multiply(TWO).toString(), "0");
      UnitTest.assertEquals(ONE_HALF.multiply(ONE_THIRD).toString(), "1/6");
      UnitTest.assertEquals(MINUS_ONE.multiply(ONE_THIRD).toString(), "-1/3");
      UnitTest.assertEquals(MINUS_ONE.multiply(MINUS_ONE).toString(), "1");
   }

/* Test the divide method, including the division-by-zero exception */

   private void testDivide() {
      UnitTest.assertEquals(ZERO.divide(TWO).toString(), "0");
      UnitTest.assertEquals(ONE.divide(TWO).toString(), "1/2");
      UnitTest.assertEquals(TWO_THIRDS.divide(ONE_THIRD).toString(), "2");
      UnitTest.assertEquals(TWO.divide(MINUS_ONE).toString(), "-2");
      try {
         TWO.divide(ZERO);
         System.err.println("Failure: Zero divide");
      } catch (RuntimeException ex) {
         /* OK */
      }
   }

/* Constants */

   private static final Rational ZERO = new Rational(0);
   private static final Rational ONE = new Rational(1);
   private static final Rational TWO = new Rational(2);
   private static final Rational ONE_HALF = new Rational(1, 2);
   private static final Rational ONE_THIRD = new Rational(1, 3);
   private static final Rational TWO_THIRDS = new Rational(2, 3);
   private static final Rational MINUS_ONE = new Rational(-1);

/* Main program */

   public static void main(String[] args) {
      new RationalUnitTest().run();
   }

}
