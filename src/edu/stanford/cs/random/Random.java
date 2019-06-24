/*
 * File: Random.java
 * -----------------
 * This file implements the Random class, which exports static versions
 * of the RandomGenerator methods.
 */

package edu.stanford.cs.random;

import java.awt.Color;

/**
 * This class implements a simple random number generator that allows
 * clients to generate pseudorandom integers, doubles, booleans, and
 * colors.  All methods in this class are static and automatically use
 * the generator provided by the <code>RandomGenerator</code> class.
 *
 * By default, the random generator is initialized to begin at an
 * unpredictable point in a pseudorandom sequence.  During debugging,
 * it is often useful to set the internal seed for the random generator
 * explicitly so that it always returns the same sequence.  To do so,
 * you need to invoke the <code>setSeed</code> method.
 */

public class Random {

   private Random() {
      throw new RuntimeException("Illegal call to Random constructor");
   }

/**
 * Returns a random <code>int</code> <i>k</i> in the range
 * 0 &le; <i>k</i> &lt; <code>n</code>.
 *
 * @param n The number of values in the range
 * @return A random <code>double</code> <i>k</i> in the range
 *         0 &le; <i>n</i> &lt; 1
 */

   public static int nextInt(int n) {
      return RandomGenerator.getInstance().nextInt(n);
   }

/**
 * Returns the next random integer in the specified range.  For example, you
 * can generate the roll of a six-sided die by calling
 *
 *<pre>
 *    Random.nextInt(1, 6);
 *</pre>
 *
 * <p>or a random decimal digit by calling
 *
 *<pre>
 *    Random.nextInt(0, 9);
 *</pre>
 *
 * @param low The low end of the range
 * @param high The high end of the range
 * @return The next random <code>int</code> between <code>low</code> and
 *         <code>high</code>, inclusive
 */

   public static int nextInt(int low, int high) {
      return RandomGenerator.getInstance().nextInt(low, high);
   }

/**
 * Returns a random <code>double</code> <i>d</i> in the range
 * 0 &le; <i>d</i> &lt; 1.
 *
 * @return A random <code>double</code> <i>d</i> in the range
 *         0 &le; <i>d</i> &lt; 1
 */

   public static double nextDouble() {
      return RandomGenerator.getInstance().nextDouble();
   }

/**
 * Returns the next random real number in the specified range.  The resulting
 * value is always at least <code>low</code> but always strictly less than
 * <code>high</code>.  You can use this method to generate continuous random
 * values.  For example, you can set the variables <code>x</code> and
 * <code>y</code> to specify a random point inside the unit square as follows:
 *
 *<pre>
 *    double x = Random.nextDouble(0.0, 1.0);
 *    double y = Random.nextDouble(0.0, 1.0);
 *</pre>
 *
 * @param low The low end of the range
 * @param high The high end of the range
 * @return A random <code>double</code> value <i>d</i> in the range
 *         <code>low</code> &le; <i>d</i> &lt; <code>high</code>
 */

   public static double nextDouble(double low, double high) {
      return RandomGenerator.getInstance().nextDouble(low, high);
   }

/**
 * Returns a random <code>boolean</code> value with the specified probability.
 * For example, you can simulate the result of tossing a coin like this:
 *
 *<pre>
 *    String coinFlip = Random.nextBoolean(0.5) ? "HEADS" : "TAILS";
 *</pre>
 *
 * @param p A value between 0 (impossible) and 1 (certain) indicating the
 *          probability
 * @return The value <code>true</code> with probability <code>p</code>
 */

   public static boolean nextBoolean(double p) {
      return RandomGenerator.getInstance().nextBoolean(p);
   }

/**
 * Returns a random <code>boolean</code> that is <code>true</code>
 * 50 percent of the time.
 *
 * @return A random <code>boolean</code> that is <code>true</code>
 *         50 percent of the time
 */

   public static boolean nextBoolean() {
      return RandomGenerator.getInstance().nextBoolean();
   }

/**
 * Returns a random opaque color whose components are chosen uniformly
 * in the 0-255 range.
 *
 * @return A random opaque <code>Color</code>
 */

   public static Color nextColor() {
      return RandomGenerator.getInstance().nextColor();
   }

/**
 * Sets a new starting point for the random generator sequence.
 *
 * @param seed The seed for the generator
 */

   public static void setSeed(int seed) {
      RandomGenerator.getInstance().setSeed(seed);
   }

}
