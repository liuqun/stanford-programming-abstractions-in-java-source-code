/*
 * File: Predicate.java
 * --------------------
 * This file defines a parameterized functional interface that takes a
 * single argument of type T and returns a boolean.
 */

package edu.stanford.cs.javacs2.ch19;

public interface Predicate<T> {

   public boolean test(T arg);

}
