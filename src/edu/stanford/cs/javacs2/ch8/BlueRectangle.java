/*
 * File: BlueRectangle.java
 * ------------------------
 * This program creates a JFrame that defines a JComponent subclass to
 * draw a blue rectangle on the screen.
 */

package edu.stanford.cs.javacs2.ch8;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class BlueRectangle {

   public void run() {
      JFrame frame = new JFrame("BlueRectangle");
      frame.add(new BlueRectangleCanvas());
      frame.setBackground(Color.WHITE);
      frame.setSize(400, 225);
      frame.setVisible(true);
   }

/* Inner class that draws a blue rectangle */

   private static class BlueRectangleCanvas extends JComponent {

      @Override
      public void paintComponent(Graphics g) {
         g.setColor(Color.BLUE);
         g.fillRect(100, 50, 200, 100);
      }

   }

/* Main program */

   public static void main(String[] args) {
      new BlueRectangle().run();
   }

}
