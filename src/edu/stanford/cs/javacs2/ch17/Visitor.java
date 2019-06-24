/*
 * File: Visitor.java
 * ------------------
 * This interface defines the behavior of classes that can serve as
 * visitors for nodes in a graph.
 */

package edu.stanford.cs.javacs2.ch17;

public interface Visitor<T> {

/**
 * Performs an operation on the specified node as part of the visitor
 * pattern.
 *
 * @param obj The object to be visited
 */

   public void visit(T obj);

}
