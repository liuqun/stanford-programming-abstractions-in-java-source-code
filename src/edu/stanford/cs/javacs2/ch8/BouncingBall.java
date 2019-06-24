/*
 * File: BouncingBall.java
 * -----------------------
 * This program displays a ball bouncing off the walls of the window.
 */

package edu.stanford.cs.javacs2.ch8;

public class BouncingBall {

   public void run() {
      GWindow gw = new GWindow(WIDTH, HEIGHT);
      double r = BALL_RADIUS;
      double x = WIDTH / 2;
      double y = HEIGHT / 2;
      double vx = 2;
      double vy = -2;
      GOval ball = new GOval(x - r, y - r, 2 * r, 2 * r);
      ball.setFilled(true);
      gw.add(ball);
      while (true) {
         x += vx;
         y += vy;
         ball.setLocation(x - r, y - r);
         if (x < r || x > WIDTH - r) vx = -vx;
         if (y < r || y > HEIGHT - r) vy = -vy;
         pause(PAUSE_TIME);
      }
   }

/* Pauses for the specified number of milliseconds */

   private void pause(int milliseconds) {
      try {
         Thread.sleep(milliseconds);
      } catch (InterruptedException ex) {
         /* Ignore the exception */
      }
   }

/* Constants */

   private static final int WIDTH = 500;
   private static final int HEIGHT = 300;
   private static final int BALL_RADIUS = 9;
   private static final int PAUSE_TIME = 10;

/* Main program */

   public static void main(String[] args) {
      new BouncingBall().run();
   }

}
