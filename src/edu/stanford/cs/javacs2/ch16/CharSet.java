/*
 * File: CharSet.java
 * ------------------
 * This file offers an efficient implementation of sets whose elements
 * are ASCII characters.
 */

package edu.stanford.cs.javacs2.ch16;

public class CharSet {

/**
 * Creates an empty CharSet.
 */

   public CharSet() {
      /* Empty */
   }

/**
 * Creates a CharSet containing the characters contained in the string.
 */

   public CharSet(String str) {
      for (int i = 0; i < str.length(); i++) {
         add(str.charAt(i));
      }
   }

/**
 * Returns the number of values in this set.
 */

   public int size() {
      int n = 0;
      for (int i = 0; i < RANGE_SIZE; i++) {
         if (testBit(i)) n++;
      }
      return n;
   }

/**
 * Returns true if this set contains no elements.
 */

   public boolean isEmpty() {
      for (int i = 0; i < CVEC_WORDS; i++) {
         if (cv[i] != 0) return false;
      }
      return true;
   }

/**
 * Removes all elements from this set.
 */

   public void clear() {
      for (int i = 0; i < CVEC_WORDS; i++) {
         cv[i] = 0;
      }
   }

/**
 * Adds the specified character to the set if it is not already present.
 */

   public void add(char ch) {
      setBit(ch);
   }

/**
 * Removes the specified character from the set, if necessary.
 */

   public void remove(char ch) {
      clearBit(ch);
   }

/**
 * Returns true if the set contains the character ch.
 */

   public boolean contains(char ch) {
      return testBit(ch);
   }

/**
 * Creates a new set that is the union of this set and the set s.
 */

   public CharSet union(CharSet s) {
      CharSet result = new CharSet();
      for (int i = 0; i < CVEC_WORDS; i++) {
         result.cv[i] = this.cv[i] | s.cv[i];
      }
      return result;
   }

/**
 * Creates a new set that is the intersection of this set and the set s.
 */

   public CharSet intersect(CharSet s) {
      CharSet result = new CharSet();
      for (int i = 0; i < CVEC_WORDS; i++) {
         result.cv[i] = this.cv[i] & s.cv[i];
      }
      return result;
   }

/**
 * Creates a new set that is the set difference of this set and the set s.
 */

   public CharSet subtract(CharSet s) {
      CharSet result = new CharSet();
      for (int i = 0; i < CVEC_WORDS; i++) {
         result.cv[i] = this.cv[i] & ~s.cv[i];
      }
      return result;
   }

/*
 * Overrides the toString method to support printing of CharSet values.
 */

   @Override
   public String toString() {
      String str = "";
      for (int i = 0; i < RANGE_SIZE; i++) {
         if (testBit(i)) {
            if (!str.isEmpty()) str += ", ";
            str += (char) i;
         }
      }
      return "{" + str + "}";
   }

/*
 * Tests whether the specified bit is set in the characteristic vector.
 */

   private boolean testBit(int k) {
      if (k < 0 || k >= RANGE_SIZE) {
         throw new RuntimeException("Index out of range");
      }
      return (cv[k / BITS_PER_WORD] & createMask(k)) != 0;
   }

/*
 * Sets the specified bit in the characteristic vector.
 */

   private void setBit(int k) {
      if (k < 0 || k >= RANGE_SIZE) {
         throw new RuntimeException("Index out of range");
      }
      cv[k / BITS_PER_WORD] |= createMask(k);
   }

/*
 * Clears the specified bit in the characteristic vector.
 */

   private void clearBit(int k) {
      if (k < 0 || k >= RANGE_SIZE) {
         throw new RuntimeException("Index out of range");
      }
      cv[k / BITS_PER_WORD] &= ~createMask(k);
   }

/*
 * Creates the mask for bit k.
 */

   private int createMask(int k) {
      return 1 << k % BITS_PER_WORD;
   }

/* Constants */

   private static final int RANGE_SIZE = 256;
   private static final int BITS_PER_WORD = 32;
   private static final int CVEC_WORDS = RANGE_SIZE / BITS_PER_WORD;

/* Private instance variables */

   private int[] cv = new int[CVEC_WORDS];

}
