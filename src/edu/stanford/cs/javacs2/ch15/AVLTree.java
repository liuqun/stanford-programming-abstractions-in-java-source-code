/*
 * File: AVLTree.java
 * ------------------
 * This file exports a self-balancing binary search tree in which the
 * keys are limited to strings.
 */

package edu.stanford.cs.javacs2.ch15;

/**
 * This class exports an implementation of the binary search tree (BST)
 * structure in which the tree is kept in balance as nodes are added
 * and removed.  The algorithm comes from the Russian mathematicians
 * Georgii Adelson Velskii and Evgenii Landis and is known by the
 * initials AVL.  In this version, the keys in the tree are limited
 * to strings.
 */

public class AVLTree {

/**
 * Creates an empty AVL tree.
 */

   public AVLTree() {
      root = null;
   }

/**
 * Adds the specified key to the AVL tree.
 */

   public void add(String key) {
      root = insertNode(root, key);
   }

/**
 * Removes the specified key from the AVL tree.
 */

   public void remove(String key) {
      root = removeNode(root, key);
   }

/**
 * Returns true if the AVL tree contains the specified key.
 */

   public boolean contains(String key) {
      return findNode(root, key) != null;
   }

/**
 * Displays the structure of the AVL tree, including the heights of each
 * node.
 */

   public void display() {
      if (root == null) {
         System.out.println("Tree is empty");
      } else {
         displayTree(root, "");
      }
   }

/*
 * Performs a preorder walk, displaying an indented tree showing the
 * height and balance factor at each node.
 */

   private void displayTree(AVLNode node, String indent) {
      if (node != null) {
         int bf = getHeight(node.right) - getHeight(node.left);
         System.out.printf("%s%s", indent, node.key);
         if (bf == 0) {
            System.out.printf(" (%d:0)%n", node.height);
         } else {
            System.out.printf(" (%d:%+d)%n", node.height, bf);
         }
         displayTree(node.left, indent + "  ");
         displayTree(node.right, indent + "  ");
      }
   }

/**
 * Lists the keys in lexicographic order.
 */

   public void list() {
      if (root == null) {
         System.out.println("Tree is empty");
      } else {
         listTree(root);
      }
   }

/*
 * Performs an inorder walk listing the keys in the AVL tree node.
 */

   private void listTree(AVLNode node) {
      if (node != null) {
         listTree(node.left);
         System.out.println(node.key);
         listTree(node.right);
      }
   }

/**
 * Sets a flag that traces the rotations as they are performed.
 */

   public void setTraceFlag(boolean flag) {
      trace = flag;
   }

/**
 * Returns the value of the trace flag.
 */

   public boolean getTraceFlag() {
      return trace;
   }

/*
 * Finds a node in the AVL tree with the specified key.  If no such
 * node exists, findNode returns null.
 */

   private AVLNode findNode(AVLNode node, String key) {
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

/*
 * Enters the key into the tree and returns the updated tree.  The usual
 * pattern for using this method assigns the result back to the tree, as
 * follows:
 *
 *     node = insertNode(node, key);
 */

   private AVLNode insertNode(AVLNode node, String key) {
      if (node == null) {
         node = new AVLNode();
         node.key = key;
         node.height = 0;
         node.left = node.right = null;
      } else {
         int cmp = key.compareTo(node.key);
         if (cmp < 0) {
            node.left = insertNode(node.left, key);
         } else if (cmp > 0) {
            node.right = insertNode(node.right, key);
         }
         fixHeight(node);
         int bf = getHeight(node.right) - getHeight(node.left);
         if (bf == -2) {
            node = fixLeftImbalance(node);
         } else if (bf == +2) {
            node = fixRightImbalance(node);
         }
      }
      return node;
   }

/*
 * Recomputes the height in the top node of the specified tree, assuming
 * that the heights of all subtrees are stored correctly.
 */

   private void fixHeight(AVLNode node) {
      if (node != null) {
         node.height = Math.max(getHeight(node.left),
                                getHeight(node.right)) + 1;
      }
   }

/*
 * Returns the height of the specified tree.  The special case check is
 * necessary to define the height of the empty tree as -1.
 */

   private int getHeight(AVLNode node) {
      return (node == null) ? -1 : node.height;
   }

/*
 * Restores the balance to a tree that has a longer subtree on the left.
 * Like insertNode, fixLeftImbalance returns the updated tree.
 */

   private AVLNode fixLeftImbalance(AVLNode node) {
      AVLNode child = node.left;
      if (getHeight(child.right) > getHeight(child.left)) {
         node.left = rotateLeft(child);
      }
      return rotateRight(node);
   }

/*
 * Restores the balance to a tree that has a longer subtree on the right.
 * Like insertNode, fixRightImbalance returns the updated tree.
 */

   private AVLNode fixRightImbalance(AVLNode node) {
      AVLNode child = node.right;
      if (getHeight(child.left) > getHeight(child.right)) {
         node.right = rotateRight(child);
      }
      return rotateLeft(node);
   }

/*
 * Performs a single left rotation around the specified node and its right
 * child, returning the updated tree.
 */

   private AVLNode rotateLeft(AVLNode node) {
      AVLNode child = node.right;
      if (trace) {
         System.out.printf("Rotate left around %s-%s axis%n", node.key,
                                                              child.key);
      }
      node.right = child.left;
      child.left = node;
      fixHeight(node);
      fixHeight(child);
      return child;
   }

/*
 * Performs a single right rotation around the specified node and its left
 * child, returning the updated tree.
 */

   private AVLNode rotateRight(AVLNode node) {
      AVLNode child = node.left;
      if (trace) {
         System.out.printf("Rotate right around %s-%s axis%n", node.key,
                                                               child.key);
      }
      node.left = child.right;
      child.right = node;
      fixHeight(node);
      fixHeight(child);
      return child;
   }

/*
 * Removes the key from the tree (if it exists) and returns the updated tree.
 * As with insertNode, removeNode returns the updated tree, so that the usual
 * pattern for using this method looks like this:
 *
 *     node = removeNode(node, key);
 */

   private AVLNode removeNode(AVLNode node, String key) {
      if (node != null) {
         int cmp = key.compareTo(node.key);
         if (cmp < 0) {
            node.left = removeNode(node.left, key);
         } else if (cmp > 0) {
            node.right = removeNode(node.right, key);
         } else {
            node = removeTargetNode(node);
         }
         if (node != null) {
            fixHeight(node);
            int bf = getHeight(node.right) - getHeight(node.left);
            if (bf == -2) {
               node = fixLeftImbalance(node);
            } else if (bf == +2) {
               node = fixRightImbalance(node);
            }
         }
      }
      return node;
   }

/*
 * Implementation notes: removeTargetNode(node)
 * --------------------------------------------
 * Removes the specified node and returns an updated tree.  The easy case
 * occurs when either (or both) of the children is null; all you need to
 * do is replace the node with its non-null child, if any.  If both children
 * are non-null, this code finds the rightmost descendent of the left
 * child; this node may not be a leaf, but will have no right child.
 * Its left child replaces it in the tree, after which the replacement
 * data is moved to the position occupied by the target node.
 */

   private AVLNode removeTargetNode(AVLNode node) {
      if (node.left == null) {
         node = node.right;
      } else if (node.right == null) {
         node = node.left;
      } else {
         AVLNode successor = node.left;
         while (successor.right != null) {
            successor = successor.right;
         }
         node.key = successor.key;
         node.left = removeNode(node.left, successor.key);
      }
      return node;
   }

/* Inner class representing a node in the tree */

   private static class AVLNode {
      String key;
      int height;
      AVLNode left, right;
   }

/* Private instance variables */

   private AVLNode root;
   private boolean trace;

}
