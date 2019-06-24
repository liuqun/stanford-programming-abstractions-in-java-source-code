/*
 * File: CountLines.java
 * ---------------------
 * This program counts the lines in a file chosen using a file dialog.
 */

package edu.stanford.cs.javacs2.ch4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;

public class CountLines {

   public void run() {
      try {
         BufferedReader rd = openFileReaderUsingDialog();
         if (rd != null) {
            int nLines = 0;
            while (rd.readLine() != null) {
               nLines++;
            }
            rd.close();
            System.out.println("That file contains " + nLines + " lines.");
         }
      } catch (IOException ex) {
         throw new RuntimeException(ex.toString());
      }
   }

/*
 * Opens a file reader using the JFileChooser dialog.
 */

   private BufferedReader openFileReaderUsingDialog() throws IOException {
      File dir = new File(System.getProperty("user.dir"));
      JFileChooser chooser = new JFileChooser(dir);
      int result = chooser.showOpenDialog(null);
      if (result == JFileChooser.APPROVE_OPTION) {
         File file = chooser.getSelectedFile();
         return new BufferedReader(new FileReader(file));
      }
      return null;
   }

/* Main program */

   public static void main(String[] args) {
      new CountLines().run();
   }

}
