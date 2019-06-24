/*
 * File: CheckoutLine.java
 * -----------------------
 * This program simulates a checkout line, such as one you might encounter
 * in a supermarket.  Customers arrive at the checkout stand and get in
 * line.  Those customers wait until the cashier is free, at which point
 * they occupy the cashier for some period of time.  After the service time
 * is complete, the cashier is free to serve the next customer.
 *
 * In each second, the simulation performs the following operations:
 *
 * 1. Determine whether a new customer has arrived.  New customers arrive
 *    randomly, with a probability given by the constant ARRIVAL_PROBABILITY.
 *
 * 2. If the cashier is busy, subtract one second from the time remaining.
 *    When that count reaches zero, the current customer is finished.
 *
 * 3. If the cashier is free, serve the next customer.  The service time
 *    is uniformly distributed between MIN_SERVICE_TIME and MAX_SERVICE_TIME.
 *
 * At the end of the simulation, the program displays the simulation
 * parameters along with the results of the simulation.
 */

package edu.stanford.cs.javacs2.ch6;

import edu.stanford.cs.patch.Math;
import java.util.ArrayDeque;
import java.util.Queue;

public class CheckoutLine {

   public void run() {
      Queue<Integer> queue = new ArrayDeque<Integer>();
      int timeRemaining = 0;
      int nServed = 0;
      double totalWait = 0;
      double totalLength = 0;
      for (int t = 0; t < SIMULATION_TIME; t++) {
         if (randomBoolean(ARRIVAL_PROBABILITY)) {
            queue.add(t);
         }
         if (timeRemaining > 0) {
            timeRemaining--;
         } else if (!queue.isEmpty()) {
            totalWait += t - queue.remove();
            nServed++;
            timeRemaining = randomInt(MIN_SERVICE_TIME, MAX_SERVICE_TIME);
         }
         totalLength += queue.size();
      }
      printReport(nServed, totalWait / nServed, totalLength / SIMULATION_TIME);
   }

/*
 * Reports the results of the simulation in tabular format.
 */

   private void printReport(int nServed, double avgWait, double avgLength) {
      System.out.printf("Simulation results given the following constants:%n");
      System.out.printf("  SIMULATION_TIME:     %5d min%n",
                        (int) Math.round(SIMULATION_TIME / MINUTES));
      System.out.printf("  MIN_SERVICE_TIME:    %5d sec%n", MIN_SERVICE_TIME);
      System.out.printf("  MAX_SERVICE_TIME:    %5d sec%n", MAX_SERVICE_TIME);
      System.out.printf("  ARRIVAL_PROBABILITY: %5.3f%n", ARRIVAL_PROBABILITY);
      System.out.println();
      System.out.printf("Customers served:      %5d%n", nServed);
      System.out.printf("Average waiting time:  %5.2f min%n",
                        avgWait / MINUTES);
      System.out.printf("Average queue length:  %5.2f%n", avgLength);
   }

/*
 * Returns a random integer between low and high, inclusive.
 */

   private int randomInt(int low, int high) {
      return (int) Math.floor(low + ((double) high - low + 1) * Math.random());
   }

/*
 * Returns true with probability p, which is a floating-point number
 * between 0 (impossible) and 1 (certain).
 */

   private boolean randomBoolean(double p) {
      return Math.random() < p;
   }

/* Constants */

   private static final int SECONDS = 1;
   private static final int MINUTES = 60;
   public static final double ARRIVAL_PROBABILITY = 0.005;
   public static final int MIN_SERVICE_TIME = 30 * SECONDS;
   public static final int MAX_SERVICE_TIME = 5 * MINUTES;
   public static final int SIMULATION_TIME = 500 * MINUTES;

/* Main program */

   public static void main(String[] args) {
      new CheckoutLine().run();
   }

}
