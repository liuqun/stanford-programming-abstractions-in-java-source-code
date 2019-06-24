/*
 * File: UnitTest.java
 * -------------------
 * The UnitTest class exports several static methods that are compatible
 * with the JUnit framework but do not depend on it.
 */

package edu.stanford.cs.unittest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * The UnitTest class supplies several static methods that are useful
 * in writing unit tests.  Clients may also extend the UnitTest class
 * to avoid having to write the UnitTest prefix with every test method.
 */

public class UnitTest {

/**
 * Checks that the boolean condition is true.
 *
 * @param msg The message to display if the assertion fails
 * @param exp The expression being tested
 */

   public static void assertTrue(String msg, boolean exp) {
      if (!exp) {
         errorCount++;
         if (showErrorMessage == null) {
            System.err.println(msg);
         } else {
            try {
               Object[] args = { msg };
               showErrorMessage.invoke(args);
            } catch (InvocationTargetException ex) {
               throw new RuntimeException(ex.toString());
            } catch (IllegalAccessException ex) {
               throw new RuntimeException(ex.toString());
            }
         }
      }
   }

/**
 * Checks that the boolean condition is true.
 *
 * @param exp The expression being tested
 */

   public static void assertTrue(boolean exp) {
      assertTrue("Failure: " + exp + " != true", exp);
   }

/**
 * Checks that the boolean condition is false.
 *
 * @param msg The message to display if the assertion fails
 * @param exp The expression being tested
 */

   public static void assertFalse(String msg, boolean exp) {
      assertTrue(msg, !exp);
   }

/**
 * Checks that the boolean condition is false.
 *
 * @param exp The expression being tested
 */

   public static void assertFalse(boolean exp) {
      UnitTest.assertFalse("Failure: " + exp + " != false", exp);
   }

/**
 * Tests that two values are equal.
 *
 * @param msg The message to display if the assertion fails
 * @param exp1 The expression being tested
 * @param exp2 The expected value
 */

   public static void assertEquals(String msg, Object exp1, Object exp2) {
      if (exp1 == null || exp2 == null) {
         assertTrue(msg, exp1 == exp2);
      } else if ((exp1 instanceof Double && exp2 instanceof Number) ||
                 (exp1 instanceof Number && exp2 instanceof Double)) {
         double d1 = ((Number) exp1).doubleValue();
         double d2 = ((Number) exp2).doubleValue();
         double r = Math.max(Math.abs(d1), Math.abs(d2)) * RADIUS;
         assertTrue(msg, Math.abs(d1 - d2) <= r);
      } else {
         assertTrue(msg, (exp1 == null) ? exp2 == null : exp1.equals(exp2));
      }
   }

/**
 * Tests that two values are equal.
 *
 * @param exp1 The expression being tested
 * @param exp2 The expected value
 */

   public static void assertEquals(Object exp1, Object exp2) {
      assertEquals("Failure: " + exp1 + " !== " + exp2, exp1, exp2);
   }

/**
 * Tests that two values are not equal.
 *
 * @param msg The message to display if the assertion fails
 * @param exp1 The expression being tested
 * @param exp2 The value that should be excluded
 */

   public static void assertNotEquals(String msg, Object exp1, Object exp2) {
      if (exp1 == null || exp2 == null) {
         assertFalse(msg, exp1 == exp2);
      } else if ((exp1 instanceof Double && exp2 instanceof Number) ||
                 (exp1 instanceof Number && exp2 instanceof Double)) {
         double d1 = ((Number) exp1).doubleValue();
         double d2 = ((Number) exp2).doubleValue();
         double r = Math.max(Math.abs(d1), Math.abs(d2)) * RADIUS;
         assertFalse(msg, Math.abs(d1 - d2) <= r);
      } else {
         assertFalse(msg, (exp1 == null) ? exp2 == null : exp1.equals(exp2));
      }
   }

/**
 * Tests that two values are not equal.
 *
 * @param exp1 The expression being tested
 * @param exp2 The value that should be excluded
 */

   public static void assertNotEquals(Object exp1, Object exp2) {
      assertNotEquals("Failure: " + exp1 + " !== " + exp2, exp1, exp2);
   }

/**
 * Checks that the expression is null.
 *
 * @param msg The message to display if the assertion fails
 * @param exp The expression being tested
 */

   public static void assertNull(String msg, Object exp) {
      assertEquals(msg, exp, null);
   }

/**
 * Checks that the expression is null.
 *
 * @param exp The expression being tested
 */

   public static void assertNull(Object exp) {
      assertNull("Failure: " + exp + " != null", exp);
   }

/**
 * Checks that the expression is not null.
 *
 * @param msg The message to display if the assertion fails
 * @param exp The expression being tested
 */

   public static void assertNotNull(String msg, Object exp) {
      assertNotEquals(msg, exp, null);
   }

/**
 * Checks that the expression is not null.
 *
 * @param exp The expression being tested
 */

   public static void assertNotNull(Object exp) {
      assertNotNull("Failure: " + exp + " == null", exp);
   }

/**
 * Checks that the two expressions are the same.
 *
 * @param msg The message to display if the assertion fails
 * @param exp1 The expression being tested
 * @param exp2 The expected value
 */

   public static void assertSame(String msg, Object exp1, Object exp2) {
      assertTrue(msg, exp1 == exp2);
   }

/**
 * Checks that the two expressions are the same.
 *
 * @param exp1 The expression being tested
 * @param exp2 The expected value
 */

   public static void assertSame(Object exp1, Object exp2) {
      assertSame("Failure: " + exp1 + " != " + exp2, exp1, exp2);
   }

/**
 * Checks that the two expressions are different.
 *
 * @param msg The message to display if the assertion fails
 * @param exp1 The expression being tested
 * @param exp2 The value that should be excluded
 */

   public static void assertNotSame(String msg, Object exp1, Object exp2) {
      assertTrue(msg, exp1 != exp2);
   }

/**
 * Checks that the two expressions are different.
 *
 * @param exp1 The expression being tested
 * @param exp2 The value that should be excluded
 */

   public static void assertNotSame(Object exp1, Object exp2) {
      assertNotSame("Failure: " + exp1 + " == " + exp2, exp1, exp2);
   }

/**
 * Resets the current error count to zero.
 */

   public static void resetErrorCount() {
      errorCount = 0;
   }

/**
 * Returns the current error count.  This method is typically used to keep
 * track of the number of errors in a connected sequence of tests.
 *
 * @return The current error count
 */

   public static int getErrorCount() {
      return errorCount;
   }

/**
 * Redirects output to the specified console.
 *
 * @param console The new error-reporting console
 */

   public static void setConsole(Object console) {
      if (console == null) {
         showErrorMessage = null;
         return;
      }
      for (String m : METHODS) {
         try {
            Class<?>[] types = { String.class };
            showErrorMessage = console.getClass().getMethod(m, types);
            return;
         } catch (NoSuchMethodException ex) {
            throw new RuntimeException("No showErrorMessage method");
         }
      }
   }

/* Constants */

   private static String[] METHODS = { "showErrorMessage", "println" };
   private static double RADIUS = 1.0E-15;

/* Static variables */

   private static int errorCount = 0;
   private static Method showErrorMessage = null;

}
