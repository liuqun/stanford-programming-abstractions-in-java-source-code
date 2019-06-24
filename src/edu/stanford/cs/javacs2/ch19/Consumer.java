/*
 * File: Consumer.java
 * -------------------
 * This file defines a parameterized functional interface that takes a
 * single argument of type T and returns no result.
 */

package edu.stanford.cs.javacs2.ch19;

public interface Consumer<T> {

   public void accept(T arg);

}
