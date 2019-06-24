/*
 * File: BlueGRect.java
 * --------------------
 * This program uses the object-oriented graphics model to draw a
 * blue rectangle on the screen.
 */

package edu.stanford.cs.javacs2.ch8;

import java.awt.Color;

public class BlueGRect {

   public void run() {
      GWindow gw = new GWindow(400, 200);
      GRect rect = new GRect(100, 50, 200, 100);
      rect.setColor(Color.BLUE);
      rect.setFilled(true);
      gw.add(rect);
   }

/* Main program */

   public static void main(String[] args) {
      new BlueGRect().run();
   }

}
