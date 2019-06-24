/*
 * File: DwarfTree.java
 * --------------------
 * This file contains the code for producing the binary search tree
 * containing Disney's seven dwarves.
 */

package edu.stanford.cs.javacs2.ch15;

public class DwarfTree {

   public void run() {
      Node dwarfTree = null;
      dwarfTree = insertNode(dwarfTree, "Grumpy");
      dwarfTree = insertNode(dwarfTree, "Sleepy");
      dwarfTree = insertNode(dwarfTree, "Doc");
      dwarfTree = insertNode(dwarfTree, "Bashful");
      dwarfTree = insertNode(dwarfTree, "Dopey");
      dwarfTree = insertNode(dwarfTree, "Happy");
      dwarfTree = insertNode(dwarfTree, "Sneezy");
      System.out.println("Preorder walk: ");
      displayPreorder(dwarfTree);
      System.out.println();
      System.out.println("Inorder walk:  ");
      display(dwarfTree);
      System.out.println();
      System.out.println("Postorder walk:");
      displayPostorder(dwarfTree);
   }

/**
 * Inserts the specified key at the appropriate location in the
 * binary search tree rooted at t and returns the updated tree.
 */

   private Node insertNode(Node t, String key) {
      if (t == null) {
         t = new Node();
         t.key = key;
         t.left = t.right = null;
      } else {
         int cmp = key.compareTo(t.key);
         if (cmp < 0) {
            t.left = insertNode(t.left, key);
         } else if (cmp > 0) {
            t.right = insertNode(t.right, key);
         }
      }
      return t;
   }

   private void displayPreorder(Node t) {
      if (t != null) {
         System.out.println(t.key);
         displayPreorder(t.left);
         displayPreorder(t.right);
      }
   }

   private void display(Node t) {
      if (t != null) {
         display(t.left);
         System.out.println(t.key);
         display(t.right);
      }
   }

   private void displayPostorder(Node t) {
      if (t != null) {
         displayPostorder(t.left);
         displayPostorder(t.right);
         System.out.println(t.key);
      }
   }

/*
 * Inner class representing a node in the tree.
 */

   private static class Node {
      String key;
      Node left, right;
   }

/* Main program */

   public static void main(String[] args) {
      new DwarfTree().run();
   }

}
