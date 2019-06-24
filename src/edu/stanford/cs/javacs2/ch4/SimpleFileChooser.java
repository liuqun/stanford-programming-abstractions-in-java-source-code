/*
 * File: SimpleFileChooser.java
 * ----------------------------
 * This file exports a utility class with several static methods to
 * simplify the creation of file dialogs.
 */

package edu.stanford.cs.javacs2.ch4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;

public class SimpleFileChooser {

   public static BufferedReader openInputFile() {
      return openInputFile("Input File");
   }

   public static BufferedReader openInputFile(String title) {
      try {
         File file = openDialog(title, "load");
         if (file == null) return null;
         return new BufferedReader(new FileReader(file));
      } catch (IOException ex) {
         return null;
      }
   }

   private static File openDialog(String title, String mode) {
      File dir = new File(System.getProperty("user.dir"));
      JFileChooser chooser = new JFileChooser(dir);
      chooser.setDialogTitle(title);
      int response = 0;
      if (mode.equals("load")) {
         response = chooser.showOpenDialog(null);
      } else if (mode.equals("save")) {
         response = chooser.showSaveDialog(null);
      } else {
         return null;
      }
      if (response == JFileChooser.APPROVE_OPTION) {
         return chooser.getSelectedFile();
      } else {
         return null;
      }
   }

/* Main program to test the class operation */

   public static void main(String[] args) {
      BufferedReader rd = SimpleFileChooser.openInputFile();
      if (rd == null) return;
      try {
         while (true) {
            String line = rd.readLine();
            if (line == null) break;
            System.out.println(line);
         }
         rd.close();
      } catch (IOException ex) {
         throw new RuntimeException(ex.toString());
      }
   }

}
