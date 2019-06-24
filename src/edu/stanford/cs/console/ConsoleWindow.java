/*
 * File: ConsoleWindow.java
 * ------------------------
 * This file implements the Console interface using a JFrame window.
 */

package edu.stanford.cs.console;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 * This class makes it easier to interact with the user using text-based
 * input and output in the style of a traditional console.  Given a
 * <code>ConsoleWindow</code> object, you can write output to that
 * console using the <a href="#print(Object)"><code>print</code></a>,
 * <a href="#println(Object)"><code>println</code></a>, and
 * <a href="#printf(String,Object...)"><code>printf</code></a> methods,
 * just as you would for the standard output stream.  To request input
 * from the user, you can use the following methods:
 *
 * <ul>
 * <li><a href="#nextInt(String)"><code>nextInt</code></a>
 * <li><a href="#nextDouble(String)"><code>nextDouble</code></a>
 * <li><a href="#nextLine()"><code>nextLine</code></a>
 * </ul>
 */

public class ConsoleWindow extends JFrame implements Console {

/**
 * Creates a new <code>ConsoleWindow</code> with a default size and
 * makes it visible.
 */

   public ConsoleWindow() {
      this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
   }

/**
 * Creates a new <code>ConsoleWindow</code> with a default size and
 * makes it visible.
 */

   public ConsoleWindow(double width, double height) {
      super("Console Window");
      cpane = new ConsolePane();
      setFont(DEFAULT_FONT);
      cpane.setSize((int) Math.round(width), (int) Math.round(height));
      cpane.setPreferredSize(cpane.getSize());
      add(cpane);
      pack();
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
   }

/**
 * Clears the console display.
 */

   public void clear() {
      cpane.clear();
   }

/**
 * Prints the argument value, allowing for the possibility of more output
 * on the same line.
 *
 * @param value The value to be displayed
 */

   @Override
   public void print(Object value) {
      cpane.print(value.toString());
   }

/**
 * Prints the end-of-line sequence to move to the next line.
 */

   @Override
   public void println() {
      cpane.print("\n");
   }

/**
 * Prints the value and then moves to the next line.
 *
 * @param value The value to be displayed
 */

   @Override
   public void println(Object value) {
      print(value);
      println();
   }

/**
 * Formats and prints the argument values as specified by the
 * <code>format</code> string. The <code>printf</code> formats are
 * described in the <code>java.util.Formatter</code> class.
 *
 * @param format The format string
 * @param args The list of arguments to be formatted
 */

   @Override
   public void printf(String format, Object... args) {
      print(String.format(format, args));
   }

/**
 * Formats a set of values on the console.  This call is equivalent to
 * <code>printf</code> and is provided for compatibility.
 *
 * @param str The control string
 * @param args A list of arguments that are inserted into the control string
 */

   @Override
   public void format(String format, Object... args) {
      System.out.printf(format, args);
   }

/**
 * Reads and returns a line of input, without including the end-of-line
 * characters that terminate the input.
 *
 * @return The next line of input as a <code>String</code>
 */

   @Override
   public String nextLine() {
      return cpane.nextLine();
   }

/**
 * Prompts the user to enter a line of text, which is then returned
 * as the value of this method.  The end-of-line characters that
 * terminate the input are not included in the returned string.
 *
 * @param prompt The prompt string to display to the user
 * @return The next line of input as a <code>String</code>
 */

   @Override
   public String nextLine(String prompt) {
      if (prompt != null) print(prompt);
      return nextLine();
   }

/**
 * Reads and returns an integer value from the user.
 *
 * @return The value of the input interpreted as a decimal integer
 */

   @Override
   public int nextInt() {
      return nextInt(null);
   }

/**
 * Prompts the user to enter an integer, which is then returned as the value
 * of this method.
 *
 * @param prompt The prompt string to display to the user
 * @return The value of the input interpreted as a decimal integer
 */

   @Override
   public int nextInt(String prompt) {
      while (true) {
         String line = nextLine(prompt);
         try {
            return Integer.parseInt(line);
         } catch (NumberFormatException ex) {
            println("Illegal integer format");
            if (prompt == null) prompt = "Retry: ";
         }
      }
   }

/**
 * Reads and returns a double-precision value from the user.
 *
 * @return The value of the input interpreted as a <code>double</code>
 */

   @Override
   public double nextDouble() {
      return nextDouble(null);
   }

/**
 * Prompts the user to enter an double-precision number, which is then
 * returned as the value of this method.
 *
 * @param prompt The prompt string to display to the user
 * @return The value of the input interpreted as a <code>double</code>
 */

   @Override
   public double nextDouble(String prompt) {
      while (true) {
         String line = nextLine(prompt);
         try {
            return Double.parseDouble(line);
         } catch (NumberFormatException ex) {
            println("Illegal floating-point format");
            if (prompt == null) prompt = "Retry: ";
         }
      }
   }

/**
 * Sets the font used for the console as specified by the string
 * <code>str</code>, which is interpreted as in <code>Font.decode</code>.
 *
 * @param str A <code>String</code> specifying the new font
 */

   @Override
   public void setFont(Font font) {
      super.setFont(font);
      if (cpane != null) cpane.setFont(font);
   }

/**
 * Sets the font used for the console as specified by the string
 * <code>str</code>, which is interpreted as in <code>Font.decode</code>.
 *
 * @param str A <code>String</code> specifying the new font
 */

   public void setFont(String str) {
      setFont(Font.decode(str));
   }

/**
 * The default font used by a new <code>ConsoleWindow</code>.
 */

   public static final Font DEFAULT_FONT = Font.decode("Monospaced-12");
   public static final int DEFAULT_WIDTH = 500;
   public static final int DEFAULT_HEIGHT = 300;

/* Private instance variables */

   private ConsolePane cpane;

}

/**
 * This class is the JTextPane that implements the console.
 */

class ConsolePane extends JScrollPane implements KeyListener {

/**
 * Creates a new <code>ConsolePane</code> object.
 */

   public ConsolePane() {
      super(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
      textPane = new JTextPane();
      textPane.addKeyListener(this);
      setViewportView(textPane);
      document = textPane.getDocument();
      textAttributes = new SimpleAttributeSet();
      inputAttributes = new SimpleAttributeSet();
      inputAttributes.addAttribute(StyleConstants.Bold, true);
      inputAttributes.addAttribute(StyleConstants.Foreground, Color.BLUE);
      inputQueue = new CharacterQueue();
      base = 0;
   }

/**
 * Reads and returns the next line of text from the console.
 */

// Debug this and add cut/copy/paste back in
// Try with DocumentFilter instead

   public String nextLine() {
      base = document.getLength();
      textPane.setCaretPosition(base);
      while (true) {
         char ch = inputQueue.dequeue();
         if (ch == '\n' || ch == '\r') break;
         if (textPane.getCaretPosition() < base) {
            textPane.setCaretPosition(document.getLength());
         }
         int dot = textPane.getSelectionStart();
         switch (ch) {
           case '\b': case '\177':
            if (dot == textPane.getSelectionEnd()) {
               if (dot > base) {
                  delete(dot - 1, dot);
                  dot--;
               }
            } else {
               dot = deleteSelection();
            }
            break;
           case 'B'-'@':
            dot = Math.max(textPane.getSelectionStart() - 1, base);
            break;
           case 'C'-'@':
//            copy();
            dot = -1;
            break;
           case 'F'-'@':
            dot = Math.min(textPane.getSelectionEnd() + 1,
                           document.getLength());
            break;
           case 'V'-'@':
//            paste();
            dot = -1;
            break;
           case 'X'-'@':
//            cut();
            dot = -1;
            break;
           default:
            if (dot != textPane.getSelectionEnd()) {
               dot = deleteSelection();
            }
            insert(dot, "" + ch, inputAttributes);
            dot++;
         }
         if (dot != -1) {
            textPane.select(dot, dot);
            textPane.setCaretPosition(dot);
         }
      }
      int len = document.getLength() - base;
      try {
         String line = textPane.getText(base, len);
         insert(base + len, "\n", textAttributes);
         base += len + 1;
         return line;
      } catch (BadLocationException ex) {
         throw new RuntimeException(ex.toString());
      }
   }

/**
 * Clears the console pane.
 */

   public void clear() {
      textPane.setText("");
      base = 0;
   }

/**
 * Prints the string to the console in the standard style.
 */

   public void print(String str) {
      insert(document.getLength(), str, textAttributes);
      base = document.getLength();
      textPane.setCaretPosition(base);
   }

/**
 * Forwards the request focus to the text pane.
 */

   @Override
   public void requestFocus() {
      if (textPane != null) textPane.requestFocus();
   }

/**
 * Forwards the setFont to the text pane.
 */

   @Override
   public void setFont(Font font) {
      super.setFont(font);
      if (textPane != null) textPane.setFont(font);
   }

/* Implementation of the KeyListener interface */

/**
 * Responds to a key being typed in the console pane.
 */

   @Override
   public void keyTyped(KeyEvent e) {
      if (!e.isMetaDown() && !e.isControlDown()) {
         inputQueue.enqueue(e.getKeyChar());
         e.consume();
      }
   }

/**
 * Responds to key presses that do not correspond to typed keys.
 */

   @Override
   public void keyPressed(KeyEvent e) {
      switch (e.getKeyCode()) {
        case KeyEvent.VK_LEFT:
         inputQueue.enqueue('\002');
         break;
        case KeyEvent.VK_RIGHT:
         inputQueue.enqueue('\006');
         break;
      }
      e.consume();
   }

/**
 * Responds to the release of a key.
 */

   @Override
   public void keyReleased(KeyEvent e) {
      e.consume();
   }

/* Inserts a string into the document */

   private void insert(int dot, String str, SimpleAttributeSet attributes) {
      try {
         document.insertString(dot, str, attributes);
      } catch (BadLocationException ex) {
         throw new RuntimeException(ex.toString());
      }
   }

/* Deletes a range from the document */

   private void delete(int p1, int p2) {
      try {
         document.remove(p1, p2 - p1);
      } catch (BadLocationException ex) {
         throw new RuntimeException(ex.toString());
      }
   }

/* Deletes the current selection and returns the index of the deletion point */

   private int deleteSelection() {
      int start = Math.max(base, textPane.getSelectionStart());
      int end = textPane.getSelectionEnd();
      if (end <= base) return document.getLength();
      delete(start, end);
      return start;
   }

/* Private instance variables */

   private SimpleAttributeSet textAttributes;
   private SimpleAttributeSet inputAttributes;
   private JTextPane textPane;
   private Document document;
   private int base;
   private CharacterQueue inputQueue;

}

/**
 * This package class defines a simple character queue.
 */

class CharacterQueue {

/* Creates an empty character queue */

   public CharacterQueue() {
      buffer = "";
   }

/* Adds a character to the end of the queue */

   public synchronized void enqueue(char ch) {
      buffer += ch;
      notifyAll();
   }

/* Adds a string to the end of the queue */

   public synchronized void enqueue(String str) {
      buffer += str;
      notifyAll();
   }

/* Waits for data and then returns the first character in the queue */

   public synchronized char dequeue() {
      while (buffer.length() == 0) {
         try {
            isWaiting = true;
            wait();
            isWaiting = false;
         } catch (InterruptedException ex) {
            /* Empty */
         }
      }
      char ch = buffer.charAt(0);
      buffer = buffer.substring(1);
      return ch;
   }

/* Clears the character queue */

   public synchronized void clear() {
      buffer = "";
      notifyAll();
   }

/* Returns true if the buffer is in a waiting state */

   public boolean isWaiting() {
      return isWaiting;
   }

/* Private instance variables */

   private String buffer;
   private boolean isWaiting;

}
