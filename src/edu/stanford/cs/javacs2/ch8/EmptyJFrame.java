/*
 * File: EmptyJFrame.java
 * ----------------------
 * This program creates an empty JFrame and makes it visible on the screen.
 */

package edu.stanford.cs.javacs2.ch8;

import java.awt.Color;
import javax.swing.JFrame;

public class EmptyJFrame {

   public void run() {
      JFrame frame = new JFrame("EmptyJFrame");
      frame.setSize(300, 150);
      frame.setBackground(Color.WHITE);
      frame.setVisible(true);
   }

/* Main program */

   public static void main(String[] args) {
      new EmptyJFrame().run();
   }

}
