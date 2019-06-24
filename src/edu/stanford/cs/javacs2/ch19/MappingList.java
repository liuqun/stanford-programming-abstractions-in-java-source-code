/*
 * File: MappingList.java
 * ----------------------
 * This class extends the ArrayList class by adding methods that apply
 * an operation to every element in the list.
 */

package edu.stanford.cs.javacs2.ch19;

import edu.stanford.cs.javacs2.ch13.ArrayList;

public class MappingList<T> extends ArrayList<T> {

   public void map(Consumer<T> proc) {
      for (T value : this) {
         proc.accept(value);
      }
   }

   public MappingList<T> map(Function<T,T> fn) {
      MappingList<T> list = new MappingList<T>();
      for (T value : this) {
         list.add(fn.apply(value));
      }
      return list;
   }

}
