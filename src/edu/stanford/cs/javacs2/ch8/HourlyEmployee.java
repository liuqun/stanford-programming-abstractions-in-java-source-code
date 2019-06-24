/*
 * File: HourlyEmployee.java
 * -------------------------
 * This file defines the concrete class HourlyEmployee, whose pay is
 * computed as the number of hours worked times the hourly rate.
 */

package edu.stanford.cs.javacs2.ch8;

public class HourlyEmployee extends Employee {

/**
 * Constructs a new HourlyEmployee object with the specified name.
 */

   public HourlyEmployee(String name) {
      super(name);
   }

/**
 * Sets the hourly wage for this worker.
 */

   public void setHourlyRate(double wage) {
      hourlyRate = wage;
   }

/**
 * Sets the number of hours worked.
 */

   public void setHoursWorked(double hours) {
      hoursWorked = hours;
   }

/**
 * Computes the pay for an hourly employee.
 */

   @Override
   public double getPay() {
      return hoursWorked * hourlyRate;
   }

/* Private instance variables */

   private double hourlyRate;
   private double hoursWorked;

}
