/*
 * File: MappingListTest.java
 * --------------------------
 * This program tests a few examples of the use of MappingList using an
 * anonymous inner class rather than a lambda function.
 */

package edu.stanford.cs.javacs2.ch19;

public class MappingListTest {

   public void run() {
      MappingList<Integer> list = new MappingList<Integer>();
      for (int i = 1; i <= 10; i++) {
         list.add(i);
      }
      list.map(new Consumer<Integer>() {
                  public void accept(Integer n) { System.out.println(n); }
               });
      MappingList<Integer> squares =
         list.map(new Function<Integer,Integer>() {
                     public Integer apply(Integer n) {
                        return n * n;
                     }
                  });
      System.out.println(squares);
      sum = 0;
      list.map(new Consumer<Integer>() {
                  public void accept(Integer n) { sum += n; }
               });
      System.out.println("sum = " + sum);
   }

/* Private instance variables */

   private int sum;

/* Main program */

   public static void main(String[] args) {
      new MappingListTest().run();
   }

}
