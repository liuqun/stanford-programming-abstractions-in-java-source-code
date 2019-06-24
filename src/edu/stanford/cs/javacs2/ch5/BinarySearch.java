/*
 * File: BinarySearch.java
 * -----------------------
 * This file implements the binary search algorithm on a sorted array.
 */

package edu.stanford.cs.javacs2.ch5;

public class BinarySearch {

   public void run() {
      for (String city : CITY_NAMES) {
         int index = binarySearch(city, CITY_NAMES);
         System.out.println(city + " appears at CITY_NAMES[" + index + "]");
      }
   }

/*
 * Searches for the specified key in the string array, which
 * must be sorted in lexicographic (character code) order.  If the
 * key is found, the function returns the index in the array at
 * which that key appears. (If the key appears more than once in
 * the array, any of the matching indices may be returned).  If the
 * key does not exist in the array, the function returns -1.  This
 * implementation is simply a wrapper function; all of the real work
 * is done by the more general binarySearch method, which takes two
 * additional arguments indicating a subrange of the array.
 */

   public int binarySearch(String key, String[] array) {
      return binarySearch(key, array, 0, array.length);
   }

/*
 * Searches for the specified key in the elements of array that appear
 * between the limits p1 and p2.  In keeping with the conventions of
 * Java, the subarray consists of all elements starting at p1 and
 * extending up to but not including p1.  If the key is found, the
 * function returns the index in the array at which that key appears.
 * If the key does not exist in the array, the function returns -1.
 */

   private int binarySearch(String key, String[] array, int p1, int p2) {
      if (p1 >= p2) return -1;
      int mid = (p1 + p2) / 2;
      int cmp = key.compareTo(array[mid]);
      if (cmp == 0) return mid;
      if (cmp < 0) {
         return binarySearch(key, array, p1, mid);
      } else {
         return binarySearch(key, array, mid, p2);
      }
   }

/*
 * Constant array containing the city names from the game Ticket to Ride
 * Europe by Alan Moon.
 */

   private static final String[] CITY_NAMES = {
      "Amsterdam", "Angora", "Athina", "Barcelona", "Berlin", "Brest",
      "Brindisi", "Bruxelles", "Bucuresti", "Budapest", "Cadiz",
      "Constantinople", "Danzig", "Dieppe", "Edinburgh", "Erzurum",
      "Essen", "Frankfurt", "Kharkov", "Kobenhavn", "Kyiv", "Lisboa",
      "London", "Madrid", "Marseille", "Moskva", "Munchen", "Palermo",
      "Pamplona", "Paris", "Petrograd", "Riga", "Roma", "Rostov",
      "Sarajevo", "Sevastopol", "Smolensk", "Smyrna", "Sochi", "Sofia",
      "Stockholm", "Venezia", "Warszawa", "Wien", "Wilno", "Zagrab",
      "Zurich"
   };

/* Main program */

   public static void main(String[] array) {
      new BinarySearch().run();
   }

}
