/*
 * File: CopyFileCharByChar.java
 * -----------------------------
 * This program copies the contents of one text file to another.  In this
 * implementation, the file is read character by character.
 */

package edu.stanford.cs.javacs2.ch4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Scanner;

public class CopyFileCharByChar {

   public void run() {
      Scanner sysin = new Scanner(System.in);
      BufferedReader rd = openFileReader(sysin, "Input file: ");
      PrintWriter wr = openFileWriter(sysin, "Output file: ");
      copyFileCharByChar(rd, wr);
      try {
         rd.close();
         wr.close();
      } catch (IOException ex) {
         throw new RuntimeException(ex.toString());
      }
   }

/*
 * Copies the entire contents of the reader to the writer.
 */

   private void copyFileCharByChar(Reader rd, Writer wr) {
      try {
         while (true) {
            int ch = rd.read();
            if (ch == -1) break;
            wr.write(ch);
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

/*
 * Asks the user for the name of a file and then returns a BufferedWriter
 * for that file.  If the file cannot be opened, the method gives the user
 * another chance.  The sysin argument is a Scanner open on the System.in
 * stream.  The prompt gives the user more information about the file.
 */

   private PrintWriter openFileWriter(Scanner sysin, String prompt) {
      PrintWriter wr = null;
      while (wr == null) {
         try {
            System.out.print(prompt);
            String name = sysin.nextLine();
            wr = new PrintWriter(new BufferedWriter(new FileWriter(name)));
         } catch (IOException ex) {
            System.out.println("Can't open that file.");
         }
      }
      return wr;
   }

/* Main program */

   public static void main(String[] args) {
      new CopyFileCharByChar().run();
   }

}
