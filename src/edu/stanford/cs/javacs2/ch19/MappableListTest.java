/*
 * File: MappableListTest.java
 * ---------------------------
 * This program tests a few examples of the use of MappableList using an
 * anonymous inner class rather than a lambda function.
 */

package edu.stanford.cs.javacs2.ch19;

public class MappableListTest {

   public void run() {
      MappableList<String> names = new MappableList<String>();
      names.add("Susan B. Anthony");
      names.add("Matilda Gage");
      names.add("Angelina Grimke");
      names.add("Sarah Grimke");
      names.add("Lucretia Mott");
      names.add("Elizabeth Cady Stanton");
      names.add("Lucy Stone");
      names.add("Sojourner Truth");
      names.map(new Consumer<String>() {
                   public void accept(String s) { System.out.println(s); }
                });
      MappableList<Integer> digits = new MappableList<Integer>();
      for (int i = 0; i < 10; i++) {
         digits.add(i);
      }
      MappableList<Integer> squares =
         digits.mapList(new Function<Integer,Integer>() {
                           public Integer apply(Integer n) { return n * n; }
                        });
      System.out.println(squares);
      sum = 0;
      digits.mapReduce(new Function<Integer,Integer>() {
                          public Integer apply(Integer n) { return n * n; }
                       },
                       new Consumer<Integer>() {
                          public void accept(Integer n) { sum += n; }
                       });
      System.out.println("sum = " + sum);
   }

/* Private instance variables */

   private int sum;

/* Main program */

   public static void main(String[] args) {
      new MappableListTest().run();
   }

}
