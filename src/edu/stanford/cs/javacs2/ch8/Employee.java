/*
 * File: Employee.java
 * -------------------
 * This file defines the abstract class Employee, which forms the root of
 * the Employee hierarchy.
 */

package edu.stanford.cs.javacs2.ch8;

public abstract class Employee {

/**
 * Constructs a new Employee object with the specified name.
 */

   protected Employee(String name) {
      this.name = name;
   }

/**
 * Returns the name of this employee.
 */

   public String getName() {
      return name;
   }

/**
 * Specifies the prototype of the abstract getPay method.
 */

   public abstract double getPay();

/* Private instance variables */

   private String name;

}
