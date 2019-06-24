/*
 * File: LetterPairMap.java
 * ------------------------
 * This file defines the class LetterPairMap, which implements the StringMap
 * interface, but only for keys composed of two uppercase letters.  Given
 * these restrictions, the put and get methods run in constant time.
 */

package edu.stanford.cs.javacs2.ch14;

public class LetterPairMap implements StringMap {

   public LetterPairMap() {
      lookupTable = new String[26][26];
   }

   public void put(String key, String value) {
      checkKey(key);
      int row = key.charAt(0) - 'A';
      int col = key.charAt(1) - 'A';
      lookupTable[row][col] = value;
   }

   public String get(String key) {
      checkKey(key);
      int row = key.charAt(0) - 'A';
      int col = key.charAt(1) - 'A';
      return lookupTable[row][col];
   }

   private void checkKey(String key) {
      if (key.length() != 2 || !Character.isUpperCase(key.charAt(0)) ||
                               !Character.isUpperCase(key.charAt(1))) {
         throw new IllegalArgumentException("Only two-letter keys allowed");
      }
   }

/* Private instance variables */

   private String[][] lookupTable;

}
