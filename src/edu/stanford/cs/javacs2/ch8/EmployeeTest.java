/*
 * File: EmployeeTest.java
 * -----------------------
 * This program creates one hourly employee and tests the getPay calculation.
 */

package edu.stanford.cs.javacs2.ch8;

public class EmployeeTest {

   public void run() {
      HourlyEmployee clerk = new HourlyEmployee("Bob Cratchit");
      clerk.setHourlyRate(7.25);
      clerk.setHoursWorked(60);
      System.out.printf("%-20s %.2f%n", clerk.getName(), clerk.getPay());
   }

/* Main program */

   public static void main(String[] args) {
      new EmployeeTest().run();
   }

}
