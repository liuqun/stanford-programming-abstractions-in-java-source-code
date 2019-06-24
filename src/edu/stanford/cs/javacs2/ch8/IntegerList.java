/*
 * File: IntegerList.java
 * ----------------------
 * This file exports the IntegerList class, which extends the parameterized
 * class ArrayList<Integer>.  It inherits all the methods from ArrayList
 * but also defines a new constructor that takes its values from the
 * parameter list and a new toString method that overrides its superclass.
 */

package edu.stanford.cs.javacs2.ch8;

import java.util.ArrayList;

public class IntegerList extends ArrayList<Integer> {

/**
 * Constructs a new IntegerList from the parameters.
 *
 * @param args A list of the integers used to initialize the IntegerList
 */

   public IntegerList(int... args) {
      for (int k : args) {
         add(k);
      }
   }

/**
 * Converts this IntegerList to a string consisting of the elements
 * enclosed in square brackets and separated by commas.
 *
 * @return The string representation of this list
 */

   @Override
   public String toString() {
      String str = "";
      for (int k : this) {
         if (!str.isEmpty()) str += ", ";
         str += k;
      }
      return "[" + str + "]";
   }

}
