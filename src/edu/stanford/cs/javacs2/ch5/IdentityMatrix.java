/*
 * File: IdentityMatrix.java
 * -------------------------
 * This program tests the declaration of the IDENTITY_MATRIX constant
 * in the text.
 */

package edu.stanford.cs.javacs2.ch5;

public class IdentityMatrix {

   public void run() {
      printMatrix(IDENTITY_MATRIX);
   }

/*
 * Displays a matrix of floating-point values.
 */

   private void printMatrix(double[][] matrix) {
      for (int i = 0; i < matrix.length; i++) {
         for (int j = 0; j < matrix[0].length; j++) {
            if (j > 0) System.out.print("  ");
            System.out.print(matrix[i][j]);
         }
         System.out.println();
      }
   }

/* Constants */

   public static final double[][] IDENTITY_MATRIX = {
      { 1.0, 0.0, 0.0 },
      { 0.0, 1.0, 0.0 },
      { 0.0, 0.0, 1.0 }
   };

/* Main program */

   public static void main(String[] args) {
      new IdentityMatrix().run();
   }

}
