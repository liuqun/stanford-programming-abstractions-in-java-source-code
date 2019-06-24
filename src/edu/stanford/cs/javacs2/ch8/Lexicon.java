/*
 * File: Lexicon.java
 * ------------------
 * This file implements the Lexicon class by embedding a TreeSet object
 * in the private data and forwarding the necessary operations to it.
 */

package edu.stanford.cs.javacs2.ch8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeSet;

public class Lexicon implements Iterable<String> {

   public Lexicon(String filename) {
      set = new TreeSet<String>();
      try {
         BufferedReader rd = new BufferedReader(new FileReader(filename));
         while (true) {
            String line = rd.readLine();
            if (line == null) break;
            add(line);
         }
      } catch (IOException ex) {
         throw new RuntimeException(ex.toString());
      }
   }

/**
 * Returns the number of words in the lexicon.
 *
 * @return The number of words in the lexicon
 */

   public int size() {
      return set.size();
   }

/**
 * Returns true if the lexicon is empty.
 *
 * @return The constant true if the lexicon is empty
 */

   public boolean isEmpty() {
      return set.isEmpty();
   }

/**
 * Removes all words from the lexicon.
 */

   public void clear() {
      set.clear();
   }

/**
 * Adds a word to the lexicon.
 *
 * @param word The word being added
 */

   public void add(String word) {
      set.add(word.toLowerCase());
   }

/**
 * Returns true if the specified string is a valid word in the lexicon.
 *
 * @param word The word being tested
 * @return The value true if the string exists in the lexicon
 */

   public boolean contains(String word) {
      return set.contains(word.toLowerCase());
   }

/**
 * Returns true if the specified string is a valid prefix of some word
 * in the lexicon.
 *
 * @param prefix The prefix string being tested
 * @return The value true if the string is a valid prefix
 */

   public boolean containsPrefix(String prefix) {
      prefix = prefix.toLowerCase();
      String next = set.ceiling(prefix);
      return next != null && next.startsWith(prefix);
   }

/**
 * Returns an iterator for the lexicon.
 */

   @Override
   public Iterator<String> iterator() {
      return set.iterator();
   }

/* Private instance variables */

   private TreeSet<String> set;

}
