/*
 * File: TreeMap.java
 * ------------------
 * This file implements the Map interface using a binary search tree.
 */

package edu.stanford.cs.javacs2.ch15;

import edu.stanford.cs.javacs2.ch14.Map;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class TreeMap<K extends Comparable<? super K>,V> implements Map<K,V> {

   public TreeMap() {
      clear();
   }

   public int size() {
      return count;
   }

   public boolean isEmpty() {
      return count == 0;
   }

   public void clear() {
      root = null;
      count = 0;
   }

/*
 * Implementation notes: get, put, containsKey, remove
 * ---------------------------------------------------
 * These methods all depend on the implementations of findNode, insertNode,
 * and removeNode, which are described earlier in the chapter.
 */

   public V get(K key) {
      TreeMapNode np = findNode(root, key);
      return (np == null) ? null : np.value;
   }

   public void put(K key, V value) {
      root = insertNode(root, key, value);
   }

   public boolean containsKey(K key) {
      return findNode(root, key) != null;
   }

   public void remove(K key) {
      root = removeNode(root, key);
   }

/*
 * Implementation notes: keySet and keyIterator
 * --------------------------------------------
 * These methods call addKeysInOrder to perform an inorder walk.  Lists and
 * sets implement the Collection interface in Java, so the code is shared.
 */

   public Set<K> keySet() {
      Set<K> keys = new TreeSet<K>();
      addKeysInOrder(root, keys);
      return keys;
   }

   public Iterator<K> keyIterator() {
      ArrayList<K> keys = new ArrayList<K>();
      addKeysInOrder(root, keys);
      return keys.iterator();
   }

   private void addKeysInOrder(TreeMapNode t, Collection<K> keys) {
      if (t != null) {
         addKeysInOrder(t.left, keys);
         keys.add(t.key);
         addKeysInOrder(t.right, keys);
      }
   }

/*
 * Finds a node in the AVL tree with the specified key.  If no such
 * node exists, findNode returns null.
 */

   private TreeMapNode findNode(TreeMapNode t, K key) {
      if (t == null) {
         return null;
      } else {
         int cmp = key.compareTo(t.key);
         if (cmp < 0) {
            return findNode(t.left, key);
         } else if (cmp > 0) {
            return findNode(t.right, key);
         } else {
            return t;
         }
      }
   }

/*
 * Enters the key into the tree and returns the updated tree.  The usual
 * pattern for using this method assigns the result back to the tree, as
 * follows:
 *
 *     t = insertNode(t, key, value);
 */

   private TreeMapNode insertNode(TreeMapNode t, K key, V value) {
      if (t == null) {
         t = new TreeMapNode();
         t.key = key;
         t.value = value;
         t.height = 0;
         t.left = t.right = null;
         count++;
      } else {
         int cmp = key.compareTo(t.key);
         if (cmp < 0) {
            t.left = insertNode(t.left, key, value);
         } else if (cmp > 0) {
            t.right = insertNode(t.right, key, value);
         } else {
            t.value = value;
         }
         fixHeight(t);
         int bf = getHeight(t.right) - getHeight(t.left);
         if (bf == -2) {
            t = fixLeftImbalance(t);
         } else if (bf == +2) {
            t = fixRightImbalance(t);
         }
      }
      return t;
   }

/*
 * Recomputes the height in the top node of the specified tree, assuming
 * that the heights of all subtrees are stored correctly.
 */

   private void fixHeight(TreeMapNode t) {
      if (t != null) {
         t.height = Math.max(getHeight(t.left), getHeight(t.right)) + 1;
      }
   }

/*
 * Returns the height of the specified tree.  The special case check is
 * necessary to define the height of the empty tree as -1.
 */

   private int getHeight(TreeMapNode t) {
      return (t == null) ? -1 : t.height;
   }

/*
 * Restores the balance to a tree that has a longer subtree on the left.
 * Like insertNode, fixLeftImbalance returns the updated tree.
 */

   private TreeMapNode fixLeftImbalance(TreeMapNode t) {
      TreeMapNode child = t.left;
      if (getHeight(child.right) > getHeight(child.left)) {
         t.left = rotateLeft(child);
      }
      return rotateRight(t);
   }

/*
 * Restores the balance to a tree that has a longer subtree on the right.
 * Like insertNode, fixRightImbalance returns the updated tree.
 */

   private TreeMapNode fixRightImbalance(TreeMapNode t) {
      TreeMapNode child = t.right;
      if (getHeight(child.left) > getHeight(child.right)) {
         t.right = rotateRight(child);
      }
      return rotateLeft(t);
   }

/*
 * Performs a single left rotation around the specified node and its right
 * child, returning the updated tree.
 */

   private TreeMapNode rotateLeft(TreeMapNode t) {
      TreeMapNode child = t.right;
      t.right = child.left;
      child.left = t;
      fixHeight(t);
      fixHeight(child);
      return child;
   }

/*
 * Performs a single right rotation around the specified node and its left
 * child, returning the updated tree.
 */

   private TreeMapNode rotateRight(TreeMapNode t) {
      TreeMapNode child = t.left;
      t.left = child.right;
      child.right = t;
      fixHeight(t);
      fixHeight(child);
      return child;
   }

/*
 * Removes the key from the tree (if it exists) and returns the updated tree.
 * As with insertNode, removeNode returns the updated tree, so that the usual
 * pattern for using this method looks like this:
 *
 *     t = removeNode(t, key);
 */

   private TreeMapNode removeNode(TreeMapNode t, K key) {
      if (t != null) {
         int cmp = key.compareTo(t.key);
         if (cmp < 0) {
            t.left = removeNode(t.left, key);
         } else if (cmp > 0) {
            t.right = removeNode(t.right, key);
         } else {
            t = removeTargetNode(t);
            count--;
         }
         if (t != null) {
            fixHeight(t);
            int bf = getHeight(t.right) - getHeight(t.left);
            if (bf == -2) {
               t = fixLeftImbalance(t);
            } else if (bf == +2) {
               t = fixRightImbalance(t);
            }
         }
      }
      return t;
   }

/*
 * Implementation notes: removeTargetNode(t)
 * -----------------------------------------
 * Removes the node t and returns an updated tree.  The easy case occurs
 * when either (or both) of the children is null; all you need to do is
 * replace the node with its non-null child, if any.  If both children
 * are non-null, this code finds the rightmost descendent of the left
 * child; this node may not be a leaf, but will have no right child.
 * Its left child replaces it in the tree, after which the replacement
 * data is moved to the position occupied by the target node.
 */

   private TreeMapNode removeTargetNode(TreeMapNode t) {
      if (t.left == null) {
         t = t.right;
      } else if (t.right == null) {
         t = t.left;
      } else {
         TreeMapNode successor = t.left;
         while (successor.right != null) {
            successor = successor.right;
         }
         t.key = successor.key;
         t.left = removeNode(t.left, successor.key);
      }
      return t;
   }

/* Inner class defining a node in the tree */

   private class TreeMapNode {
      K key;
      V value;
      int height;
      TreeMapNode left, right;
   }

/* Private instance variables */

   private TreeMapNode root;
   private int count;

}
