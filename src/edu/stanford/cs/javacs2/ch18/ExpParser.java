/*
 * File: ExpParser.java
 * --------------------
 * This file exports a simple recursive-descent parser for expressions.
 */

package edu.stanford.cs.javacs2.ch18;

import edu.stanford.cs.javacs2.ch14.HashMap;
import edu.stanford.cs.javacs2.ch14.Map;
import edu.stanford.cs.tokenscanner.TokenScanner;

public class ExpParser {

/**
 * Creates a new expression parser.
 */

   public ExpParser() {
      scanner = createTokenScanner();
      precedenceTable = createPrecedenceTable();
   }

/**
 * Sets the input for the parser to be the specified string.
 *
 * @param str The input string for parsing.
 */

   public void setInput(String str) {
      scanner.setInput(str);
   }

/**
 * Parses the next expression from the scanner.
 *
 * @return The parsed representation of the input
 */

   public Expression parseExp() {
      Expression exp = readE(0);
      if (scanner.hasMoreTokens()) {
         String token = scanner.nextToken();
         throw new RuntimeException("Unexpected token \"" + token + "\"");
      }
      return exp;
   }

/**
 * Reads an expression starting at the specified precedence level.
 *
 * @param prec The current precedence level
 * @return The parsed expression
 */

   public Expression readE(int prec) {
      Expression exp = readT();
      String token;
      while (true) {
         token = scanner.nextToken();
         int tprec = precedence(token);
         if (tprec <= prec) break;
         Expression rhs = readE(tprec);
         exp = new CompoundExp(token, exp, rhs);
      }
      scanner.saveToken(token);
      return exp;
   }

/*
 * Scans a term, which is either an integer, an identifier, or a
 * parenthesized subexpression.
 *
 * @return The parsed term
 */

   public Expression readT() {
      String token = scanner.nextToken();
      if (token.isEmpty()) throw new RuntimeException("Illegal expression");
      if (Character.isLetter(token.charAt(0))) {
         return new IdentifierExp(token);
      }
      if (Character.isDigit(token.charAt(0))) {
         return new ConstantExp(Integer.parseInt(token));
      }
      if (!token.equals("(")) {
         throw new RuntimeException("Unexpected token \"" + token + "\"");
      }
      Expression exp = readE(0);
      token = scanner.nextToken();
      if (!token.equals(")")) {
         throw new RuntimeException("Unbalanced parentheses");
      }
      return exp;
   }

/**
 * Returns the precedence of the operator.  If the operator is not defined,
 * its precedence is 0.
 *
 * @param token The operator token
 * @return The numeric precedence value or 0 if token is not an operator
 */

   public int precedence(String token) {
      Integer prec = precedenceTable.get(token);
      return (prec == null) ? 0 : prec;
   }

/*
 * Creates the TokenScanner for this parser.  Subclasses can override
 * this method to change the characteristics of the scanner.
 */

   public TokenScanner createTokenScanner() {
      TokenScanner scanner = new TokenScanner();
      scanner.ignoreWhitespace();
      return scanner;
   }

/*
 * Creates the precedence table for this parser.  Subclasses can override
 * this method to add new operators to the parser.
 */

   public Map<String,Integer> createPrecedenceTable() {
      Map<String,Integer> map = new HashMap<String,Integer>();
      map.put("=", 1);
      map.put("+", 2);
      map.put("-", 2);
      map.put("*", 3);
      map.put("/", 3);
      return map;
   }

/* Private instance variables */

   private TokenScanner scanner;
   private Map<String,Integer> precedenceTable;

}
