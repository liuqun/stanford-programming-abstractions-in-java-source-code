/*
 * File: TokenScanner.java
 * -----------------------
 * This file exports a simplified version of the TokenScanner class.
 */

package edu.stanford.cs.javacs2.ch7;

/**
 * This class provides an abstract data type for dividing a string
 * into tokens, which are strings of consecutive characters that form
 * logical units.  In this simplified version of the TokenScanner class,
 * there are just two types of tokens:
 *
 * 1. Word -- A string of consecutive letters and digits
 * 2. Operator -- A single character string
 *
 * To use this class, you must first create a TokenScanner instance using
 * the declaration
 *
 *    TokenScanner scanner = new TokenScanner(str);
 *
 * Once you have initialized the scanner, you can retrieve the next token
 * from the token stream by calling
 *
 *    token = scanner.nextToken();
 *
 * To determine whether any tokens remain to be read, you can either
 * call the predicate method scanner.hasMoreTokens() or check to see
 * whether nextToken returns the empty string.
 *
 * The following code fragment serves as a pattern for processing
 * each token in the string inputString:
 *
 *    TokenScanner scanner < new TokenScanner(inputString);
 *    while (scanner.hasMoreTokens()) {
 *       String token < scanner.nextToken();
 *       . . . code to process the token . . .
 *    }
 *
 * By default, TokenScanner treats whitespace characters as operators.
 * You can ignore these characters, by calling scanner.ignoreWhitespace();
 */

public class TokenScanner {

/**
 * Initializes a new TokenScanner object.
 */

   public TokenScanner() {
      ignoreWhitespaceFlag = false;
      setInput("");
   }

/**
 * Initializes a new TokenScanner object that reads tokens from the
 * specified string.
 */

   public TokenScanner(String str) {
      this();
      setInput(str);
   }

/**
 * Sets this scanner input to the specified string.  Any previous input
 * string is discarded.
 */

   public void setInput(String str) {
      input = str;
      savedToken = null;
      cp = 0;
   }

/**
 * Returns the next token from this scanner.  If it is called when no
 * tokens are available, nextToken returns the empty string.
 */

   public String nextToken() {
      String token = savedToken;
      savedToken = null;
      if (token == null) {
         token = "";
         if (ignoreWhitespaceFlag) skipWhitespace();
         if (cp == input.length()) return "";
         char ch = input.charAt(cp++);
         token += ch;
         if (Character.isLetterOrDigit(ch)) {
            while (cp < input.length() &&
                   Character.isLetterOrDigit(input.charAt(cp))) {
               token += input.charAt(cp++);
            }
         }
      }
      return token;
   }

/**
 * Saves one token to reread later.
 */

   public void saveToken(String token) {
      savedToken = token;
   }

/**
 * Returns true if there are more tokens for this scanner to read.
 */

   public boolean hasMoreTokens() {
      if (ignoreWhitespaceFlag) skipWhitespace();
      return cp < input.length();
   }

/**
 * Causes the scanner to ignore whitespace characters.
 */

   public void ignoreWhitespace() {
      ignoreWhitespaceFlag = true;
   }

/**
 * Skips over any whitespace characters before the next token.
 */

   private void skipWhitespace() {
      while (cp < input.length() && Character.isWhitespace(input.charAt(cp))) {
         cp++;
      }
   }

/* Private instance variables */

   private String input;
   private String savedToken;
   private int cp;
   private boolean ignoreWhitespaceFlag;

}
