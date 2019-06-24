/*
 * File: Function.java
 * -------------------
 * This file defines a parameterized function type that maps T -> U.
 */

package edu.stanford.cs.javacs2.ch19;

public interface Function<T,U> {

   public U apply(T arg);

}
