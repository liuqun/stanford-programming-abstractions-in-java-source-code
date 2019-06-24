/*
 * File: BeaconsOfGondor.java
 * --------------------------
 * This program illustrates the concept of a linked list by simulating the
 * Beacons of Gondor story from J. R. R. Tolkien's Return of the King.
 */

package edu.stanford.cs.javacs2.ch7;

public class BeaconsOfGondor {

   public void run() {
      Tower rohan = createTower("Rohan", null);
      Tower halifirien = createTower("Halifirien", rohan);
      Tower calenhad = createTower("Calenhad", halifirien);
      Tower minRimmon = createTower("Min-Rimmon", calenhad);
      Tower erelas = createTower("Erelas", minRimmon);
      Tower nardol = createTower("Nardol", erelas);
      Tower eilenach = createTower("Eilenach", nardol);
      Tower amonDin = createTower("Amon Din", eilenach);
      Tower minasTirith = createTower("Minas Tirith", amonDin);
      signal(minasTirith);
   }

/* Creates a new Tower object from its name and link fields */

   private Tower createTower(String name, Tower link) {
      Tower t = new Tower();
      t.name = name;
      t.link = link;
      return t;
   }

/* Generates a signal starting at start and propagating down the chain */

   private void signal(Tower start) {
      for (Tower cp = start; cp != null; cp = cp.link) {
         System.out.println("Lighting " + cp.name);
      }
   }

/* Defines an inner class named Tower that acts as a cell in a linked list */

   private static class Tower {
      String name;                  /* The name of this tower              */
      Tower link;                   /* Link to the next tower in the chain */
   }

/* Main program */

   public static void main(String[] args) {
      new BeaconsOfGondor().run();
   }

}
