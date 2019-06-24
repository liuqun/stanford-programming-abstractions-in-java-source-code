/*
 * File: MappableList.java
 * -----------------------
 * This file exports a list that supports mapping operations.
 */

package edu.stanford.cs.javacs2.ch19;

import java.util.ArrayList;

/**
 * This class extends the ArrayList class by adding mapping functions.
 */

public class MappableList<T> extends ArrayList<T> {

/**
 * Applies the function fn to every element in the list.
 *
 * @param fn A lambda expression matching Consumer<T>
 */

   public void map(Consumer<T> fn) {
      for (T value : this) {
         fn.accept(value);
      }
   }

/**
 * Creates a new MappableList<T> by applying fn to every element of this one.
 *
 * @param fn A lambda expression matching Function<T,T>
 */

   public MappableList<T> mapList(Function<T,T> fn) {
      MappableList<T> list = new MappableList<T>();
      for (T value : this) {
         list.add(fn.apply(value));
      }
      return list;
   }

/**
 * Applies reduce to the result of applying map to each element.
 *
 * @param map A lambda expression matching Function<T,T>
 * @param reduce A lambda expression matching Consumer<T>
 */

   public void mapReduce(Function<T,T> map, Consumer<T> reduce) {
      for (T value : this) {
         reduce.accept(map.apply(value));
      }
   }

}
