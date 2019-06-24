/*
 * File: TreeMapTest.java
 * ----------------------
 * This program implements an interactive test of the TreeMap abstraction.
 */

package edu.stanford.cs.javacs2.ch15;

import edu.stanford.cs.javacs2.ch14.Map;
import edu.stanford.cs.javacs2.ch14.MapTest;

public class TreeMapTest extends MapTest {

   @Override
   public Map<String,String> createMap() {
      return new TreeMap<String,String>();
   }

   public static void main(String[] args) {
      new TreeMapTest().run();
   }

}
