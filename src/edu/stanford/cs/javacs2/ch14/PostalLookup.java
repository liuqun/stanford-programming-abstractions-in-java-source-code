/*
 * File: PostalLookup.java
 * -----------------------
 * This program looks up a state abbreviation from a map containing
 * the abbreviations of the 50 U.S. states.
 */

package edu.stanford.cs.javacs2.ch14;

import edu.stanford.cs.console.Console;
import edu.stanford.cs.console.SystemConsole;

public class PostalLookup {

   public void run() {
      Console console = new SystemConsole();
      StringMap stateMap = new LetterPairMap();
      addStateAbbreviations(stateMap);
      while (true) {
         String code = console.nextLine("Enter two-letter state code: ");
         if (code.isEmpty()) break;
         String state = stateMap.get(code);
         if (state == null) state = "???";
         System.out.println(code + " = " + state);
      }
   }

/*
 * Initializes the map so that it contains the two-letter abbreviations
 * for the 50 states.
 */

   private void addStateAbbreviations(StringMap map) {
      map.put("AK", "Alaska");
      map.put("AL", "Alabama");
      map.put("AR", "Arkansas");
      map.put("AZ", "Arizona");
      map.put("CA", "California");
      map.put("CO", "Colorado");
      map.put("CT", "Connecticut");
      map.put("DE", "Delaware");
      map.put("FL", "Florida");
      map.put("GA", "Georgia");
      map.put("HI", "Hawaii");
      map.put("IA", "Iowa");
      map.put("ID", "Idaho");
      map.put("IL", "Illinois");
      map.put("IN", "Indiana");
      map.put("KS", "Kansas");
      map.put("KY", "Kentucky");
      map.put("LA", "Louisiana");
      map.put("MA", "Massachusetts");
      map.put("MD", "Maryland");
      map.put("ME", "Maine");
      map.put("MI", "Michigan");
      map.put("MN", "Minnesota");
      map.put("MO", "Missouri");
      map.put("MS", "Mississippi");
      map.put("MT", "Montana");
      map.put("NC", "North Carolina");
      map.put("ND", "North Dakota");
      map.put("NE", "Nebraska");
      map.put("NH", "New Hampshire");
      map.put("NJ", "New Jersey");
      map.put("NM", "New Mexico");
      map.put("NV", "Nevada");
      map.put("NY", "New York");
      map.put("OH", "Ohio");
      map.put("OK", "Oklahoma");
      map.put("OR", "Oregon");
      map.put("PA", "Pennsylvania");
      map.put("RI", "Rhode Island");
      map.put("SC", "South Carolina");
      map.put("SD", "South Dakota");
      map.put("TN", "Tennessee");
      map.put("TX", "Texas");
      map.put("UT", "Utah");
      map.put("VA", "Virginia");
      map.put("VT", "Vermont");
      map.put("WA", "Washington");
      map.put("WI", "Wisconsin");
      map.put("WV", "West Virginia");
      map.put("WY", "Wyoming");
   }

/* Main program */

   public static void main(String[] args) {
      new PostalLookup().run();
   }

}
