/*
 * File: GenericArray.java
 * -----------------------
 * This class exists only to get around the limitations on arrays in
 * Java.  The GenericArray class is a parameterized type that acts like
 * an array in terms of its primitive operations, which are limited to
 * size, get, and put.  The size of a GenericArray object is fixed at the
 * time it is created.  All operations on a GenericArray can therefore be
 * guaranteed to operate in constant time.
 */

package edu.stanford.cs.javacs2.ch13;

public class GenericArray<T> {

/**
 * Allocates a GenericArray object with n elements.
 */

   public GenericArray(int n) {
      array = new Object[n];
   }

/**
 * Returns the length of the underlying array.
 */

   public int size() {
      return array.length;
   }

/**
 * Gets the element at index k.
 */

   @SuppressWarnings("unchecked")
   public T get(int k) {
      return (T) array[k];
   }

/**
 * Sets the element at index k to value.
 */

   public void set(int k, T value) {
      array[k] = value;
   }

/* Private instance variables */

   private Object[] array;

}
