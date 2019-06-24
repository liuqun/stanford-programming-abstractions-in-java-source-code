/*
 * File: RandomGenerator.java
 * --------------------------
 * This file implements the RandomGenerator class.  The external interface
 * is based on its counterpart in the ACM libraries.  The implementation,
 * however, is completely rewritten to support identical operation in Java
 * and JavaScript, which lacks any mechanism for seeding its generator.
 */

package edu.stanford.cs.random;

import java.awt.Color;

/**
 * This class implements a simple random number generator that allows
 * clients to generate pseudorandom integers, doubles, booleans, and
 * colors.  To use it, the first step is to declare an instance variable
 * to hold the random generator as follows:
 *
 *<pre>
 *    private RandomGenerator rgen = RandomGenerator.getInstance();
 *</pre>
 *
 * <p>By default, the <code>RandomGenerator</code> object is initialized
 * to begin at an unpredictable point in a pseudorandom sequence.  During
 * debugging, it is often useful to set the internal seed for the random
 * generator explicitly so that it always returns the same sequence.
 * To do so, you need to invoke the <code>setSeed</code> method.
 *
 * <p>The <code>RandomGenerator</code> object returned by
 * <code>getInstance</code> is shared across all classes in an application.
 * Using this shared instance of the generator is preferable to allocating
 * new instances of <code>RandomGenerator</code>.  If you create several
 * random generators in succession, they will typically generate the same
 * sequence of values.
 */

public class RandomGenerator {

/**
 * Creates a new random generator.  Most clients will not use the
 * constructor directly but will instead call
 * <a href="#getInstance()"><code>getInstance</code></a>
 * to obtain a <code>RandomGenerator</code> object that is shared by all
 * classes in the application.
 */

   public RandomGenerator() {
      setSeed((int) (System.currentTimeMillis() & 0x7FFFFFFF));
   }

/**
 * Returns a random <code>int</code> <i>k</i> in the range
 * 0 &le; <i>k</i> &lt; <code>n</code>.
 *
 * @param n The number of values in the range
 * @return A random <code>double</code> <i>k</i> in the range
 *         0 &le; <i>n</i> &lt; 1
 */

   public int nextInt(int n) {
      return (int) (n * nextDouble());
   }

/**
 * Returns the next random integer in the specified range.  For example, you
 * can generate the roll of a six-sided die by calling
 *
 *<pre>
 *    rgen.nextInt(1, 6);
 *</pre>
 *
 * <p>or a random decimal digit by calling
 *
 *<pre>
 *    rgen.nextInt(0, 9);
 *</pre>
 *
 * @param low The low end of the range
 * @param high The high end of the range
 * @return The next random <code>int</code> between <code>low</code> and
 *         <code>high</code>, inclusive
 */

   public int nextInt(int low, int high) {
      return low + (int) ((high - low + 1) * nextDouble());
   }

/**
 * Returns a random <code>double</code> <i>d</i> in the range
 * 0 &le; <i>d</i> &lt; 1.
 *
 * @return A random <code>double</code> <i>d</i> in the range
 *         0 &le; <i>d</i> &lt; 1
 */

   public double nextDouble() {
      return (double) next() / 0x40000000;
   }

/**
 * Returns the next random real number in the specified range.  The resulting
 * value is always at least <code>low</code> but always strictly less than
 * <code>high</code>.  You can use this method to generate continuous random
 * values.  For example, you can set the variables <code>x</code> and
 * <code>y</code> to specify a random point inside the unit square as follows:
 *
 *<pre>
 *    double x = rgen.nextDouble(0.0, 1.0);
 *    double y = rgen.nextDouble(0.0, 1.0);
 *</pre>
 *
 * @param low The low end of the range
 * @param high The high end of the range
 * @return A random <code>double</code> value <i>d</i> in the range
 *         <code>low</code> &le; <i>d</i> &lt; <code>high</code>
 */

   public double nextDouble(double low, double high) {
      return low + (high - low) * nextDouble();
   }

/**
 * Returns a random <code>boolean</code> value with the specified probability.
 * For example, you can simulate the result of tossing a coin like this:
 *
 *<pre>
 *    String coinFlip = rgen.nextBoolean(0.5) ? "HEADS" : "TAILS";
 *</pre>
 *
 * @param p A value between 0 (impossible) and 1 (certain) indicating the
 *          probability
 * @return The value <code>true</code> with probability <code>p</code>
 */

   public boolean nextBoolean(double p) {
      return nextDouble() < p;
   }

/**
 * Returns a random <code>boolean</code> that is <code>true</code>
 * 50 percent of the time.
 *
 * @return A random <code>boolean</code> that is <code>true</code>
 *         50 percent of the time
 */

   public boolean nextBoolean() {
      return nextBoolean(0.5);
   }

/**
 * Returns a random opaque color whose components are chosen uniformly
 * in the 0-255 range.
 *
 * @return A random opaque <code>Color</code>
 */

   public Color nextColor() {
      return new Color(nextInt(256), nextInt(256), nextInt(256));
   }

/**
 * Sets a new starting point for the random generator sequence.
 *
 * @param seed The seed for the generator
 */

   public void setSeed(int seed) {
      s1 = seed & 0xFFFF;
      s2 = (seed ^ (seed >> 8)) & 0xFFFF;
   }

/**
 * Returns a <code>RandomGenerator</code> instance that can be shared among
 * several classes.
 *
 * @return A shared <code>RandomGenerator</code> object
 */

   public static RandomGenerator getInstance() {
      if (standardInstance == null) standardInstance = new RandomGenerator();
      return standardInstance;
   }

/**
 * Returns a 30-bit unsigned integer.  This function runs two sixteen-bit
 * linear congruential generators and then combines them bitwise.  The
 * reason for this strategy is to ensure accuracy in the JavaScript
 * version.
 *
 * @return The next 30-bit unsigned integer.
 */

   private int next() {
      s1 = (s1 * MULTIPLIER + INCREMENT) & 0xFFFF;
      s2 = (s2 * MULTIPLIER + INCREMENT) & 0xFFFF;
      return ((s2 & 0x3FFF) << 16) | s1;
   }

/* Private constant */

   private static final int MULTIPLIER = 31421;
   private static final int INCREMENT = 6927;

/* Private package variables */

   private static RandomGenerator standardInstance = new RandomGenerator();

/* Private instance variables */

   private int s1;
   private int s2;

}
