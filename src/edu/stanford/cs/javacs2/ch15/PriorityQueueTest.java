/*
 * File: PriorityQueueTest.java
 * ----------------------------
 * This program implements an interactive test of the PriorityQueue
 * abstraction.
 */

package edu.stanford.cs.javacs2.ch15;

import edu.stanford.cs.consoletest.ConsoleTest;
import edu.stanford.cs.consoletest.HelpText;
import edu.stanford.cs.tokenscanner.TokenScanner;

public class PriorityQueueTest extends ConsoleTest {

   public PriorityQueueTest() {
      pq = createQueue();
   }

   public PriorityQueue<String> createQueue() {
      return new PriorityQueue<String>();
   }

   @HelpText("add str -- Adds str to the queue in priority order")
   public void addCommand(TokenScanner scanner) {
      String token = scanner.nextToken();
      if (token.isEmpty()) {
         println("Missing value to add");
      } else {
         pq.add(token);
      }
   }

   @HelpText("clear -- Clears the queue")
   public void clearCommand(TokenScanner scanner) {
      pq.clear();
   }

   @HelpText("isEmpty -- Prints whether the queue is empty")
   public void isEmptyCommand(TokenScanner scanner) {
      println(pq.isEmpty());
   }

   @HelpText("list -- List the elements of the queue")
   public void listCommand(TokenScanner scanner) {
      if (pq.isEmpty()) {
         println("Queue is empty");
      } else {
         listQueue(pq);
      }
   }

   @HelpText("peek -- Peeks at the first element")
   public void peekCommand(TokenScanner scanner) {
      if (pq.isEmpty()) {
         println("Queue is empty");
      } else {
         println(pq.peek());
      }
   }

   @HelpText("remove -- Removes and displays the first value")
   public void removeCommand(TokenScanner scanner) {
      if (pq.isEmpty()) {
         println("Queue is empty");
      } else {
         println(pq.remove());
      }
   }

   @HelpText("size -- Prints the size of the queue")
   public void sizeCommand(TokenScanner scanner) {
      println(pq.size());
   }

/*
 * Lists the elements of the queue while preserving its contents.
 */

   private void listQueue(PriorityQueue<String> pq) {
      if (!pq.isEmpty()) {
         String value = pq.remove();
         println(value);
         listQueue(pq);
         pq.add(value);
      }
   }

/* Private instance variables */

   private PriorityQueue<String> pq;

/* Main program */

   public static void main(String[] args) {
      new PriorityQueueTest().run();
   }

}
