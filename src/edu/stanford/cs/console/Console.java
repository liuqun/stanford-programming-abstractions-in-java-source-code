/*
 * File: Console.java
 * ------------------
 * This interface defines the behavior of a console that can communicate
 * with the user.  Two concrete implementations are provided: SystemConsole,
 * which uses the System.in and System.out streams, and ConsoleWindow, which
 * creates a new console window.
 */

package edu.stanford.cs.console;

/**
 * The <code>Console</code> interface defines the input and output methods
 * supported by an interactive console.
 */

public interface Console {

/**
 * Prints the argument value, allowing for the possibility of more output
 * on the same line.
 *
 * @param value The value to be displayed
 */

   public void print(Object value);

/**
 * Prints the end-of-line sequence to move to the next line.
 */

   public void println();

/**
 * Prints the value and then moves to the next line.
 *
 * @param value The value to be displayed
 */

   public void println(Object value);

/**
 * Formats and prints the argument values as specified by the
 * <code>format</code> string. The <code>printf</code> formats are
 * described in the <code>java.util.Formatter</code> class.
 *
 * @param format The format string
 * @param args The list of arguments to be formatted
 */

   public void printf(String format, Object... args);

/**
 * Formats a set of values on the console.  This call is equivalent to
 * <code>printf</code> and is provided for compatibility.
 *
 * @param str The control string
 * @param args A list of arguments that are inserted into the control string
 */

   public void format(String str, Object... args);

/**
 * Reads and returns a line of input, without including the end-of-line
 * characters that terminate the input.
 *
 * @return The next line of input as a <code>String</code>
 */

   public String nextLine();

/**
 * Prompts the user to enter a line of text, which is then returned
 * as the value of this method.
 *
 * @param prompt The prompt string to display to the user
 * @return The next line of input as a <code>String</code>
 */

   public String nextLine(String prompt);

/**
 * Reads and returns an integer value from the user.
 *
 * @return The value of the input interpreted as a decimal integer
 */

   public int nextInt();

/**
 * Prompts the user to enter an integer.
 *
 * @param prompt The prompt string to display to the user
 * @return The value of the input interpreted as a decimal integer
 */

   public int nextInt(String prompt);

/**
 * Reads and returns a double-precision value from the user.
 *
 * @return The value of the input interpreted as a <code>double</code>
 */

   public double nextDouble();

/**
 * Prompts the user to enter an double-precision number.
 *
 * @param prompt The prompt string to display to the user
 * @return The value of the input interpreted as a <code>double</code>
 */

   public double nextDouble(String prompt);

}
