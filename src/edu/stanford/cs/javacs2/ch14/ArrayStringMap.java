/*
 * File: ArrayStringMap.java
 * -------------------------
 * This file implements the StringMap interface using an ArrayList of
 * key-value pairs.
 */

package edu.stanford.cs.javacs2.ch14;

import edu.stanford.cs.javacs2.ch13.ArrayList;

public class ArrayStringMap implements StringMap {

   public ArrayStringMap() {
      bindings = new ArrayList<KeyValuePair>();
   }

   public void put(String key, String value) {
      int index = findCell(key);
      if (index == -1) {
         KeyValuePair kvp = new KeyValuePair();
         kvp.key = key;
         kvp.value = value;
         bindings.add(kvp);
      } else {
         bindings.get(index).value = value;
      }
   }

   public String get(String key) {
      int index = findCell(key);
      return (index == -1) ? null : bindings.get(index).value;
   }

   private int findCell(String key) {
      for (int i = 0; i < bindings.size(); i++) {
         if (bindings.get(i).key.equals(key)) return i;
      }
      return -1;
   }

/* Inner class to represent a key-value pair */

   private static class KeyValuePair {
      String key;
      String value;
   }

/* Private instance variables */

   private ArrayList<KeyValuePair> bindings;

}
