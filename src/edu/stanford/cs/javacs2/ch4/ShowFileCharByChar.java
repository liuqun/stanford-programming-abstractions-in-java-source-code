/*
 * File: ShowFileCharByChar.java
 * -----------------------------
 * This program displays the contents of a text file.  In this
 * implementation, the file is read character by character.
 */

package edu.stanford.cs.javacs2.ch4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ShowFileCharByChar {

/*
 * This program displays the contents of a text file.
 */

   public void run() {
      Scanner sysin = new Scanner(System.in);
      BufferedReader rd = openFileReader(sysin, "Input file: ");
      showFileCharByChar(rd);
      try {
         rd.close();
      } catch (IOException ex) {
         throw new RuntimeException(ex.toString());
      }
   }

/*
 * Displays the entire contents of the reader on the console.
 */

   private void showFileCharByChar(BufferedReader rd) {
      try {
         while (true) {
            int ch = rd.read();
            if (ch == -1) break;
            System.out.print((char) ch);
         }
      } catch (IOException ex) {
         throw new RuntimeException(ex.toString());
      }
   }

/*
 * Asks the user for the name of a file and then returns a BufferedReader
 * for that file.  If the file cannot be opened, the method gives the user
 * another chance.  The sysin argument is a Scanner open on the System.in
 * stream.  The prompt gives the user more information about the file.
 */

   private BufferedReader openFileReader(Scanner sysin, String prompt) {
      BufferedReader rd = null;
      while (rd == null) {
         try {
            System.out.print(prompt);
            String name = sysin.nextLine();
            rd = new BufferedReader(new FileReader(name));
         } catch (IOException ex) {
            System.out.println("Can't open that file.");
         }
      }
      return rd;
   }

/* Main program */

   public static void main(String[] args) {
      new ShowFileCharByChar().run();
   }

}
