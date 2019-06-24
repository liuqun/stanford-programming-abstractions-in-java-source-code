/*
 * File: BST.java
 * --------------
 * This file exports a simple implementation of binary search trees in
 * which the keys are always strings.
 */

package edu.stanford.cs.javacs2.ch15;

public class BST {

/**
 * Creates an empty BST.
 */

   public BST() {
      root = null;
   }

/**
 * Adds a key to the BST in the correct position.
 *
 * @param key The key to be added
 */

   public void add(String key) {
      root = insertNode(root, key);
   }

/*
 * Implementation notes: insertNode
 * --------------------------------
 * This method does the work for the public add method for the BST class.
 * The insertNode method returns the updated tree, which must be assigned
 * back into the variable that holds it.  A typical call therefore looks
 * like this:
 *
 *    root = insertNode(root, key);
 */

   private BSTNode insertNode(BSTNode node, String key) {
      if (node == null) {
         node = new BSTNode();
         node.key = key;
         node.left = node.right = null;
      } else {
         int cmp = key.compareTo(node.key);
         if (cmp < 0) {
            node.left = insertNode(node.left, key);
         } else if (cmp > 0) {
            node.right = insertNode(node.right, key);
         }
      }
      return node;
   }

/**
 * Returns true if the key is contained in the BST.
 *
 * @param key The key being checked
 */

   public boolean contains(String key) {
      return findNode(root, key) != null;
   }

   private BSTNode findNode(BSTNode node, String key) {
      if (node == null) {
         return null;
      } else {
         int cmp = key.compareTo(node.key);
         if (cmp < 0) {
            return findNode(node.left, key);
         } else if (cmp > 0) {
            return findNode(node.right, key);
         } else {
            return node;
         }
      }
   }

/**
 * Lists the keys in the BST in ascending order.
 */

   public void list() {
      listInOrder(root);
   }

   private void listInOrder(BSTNode node) {
      if (node != null) {
         listInOrder(node.left);
         System.out.println(node.key);
         listInOrder(node.right);
      }
   }

/* Inner class defining a node in the binary search tree */

   private static class BSTNode {
      String key;
      BSTNode left, right;
   }

/* Private instance variables */

   private BSTNode root;

}
