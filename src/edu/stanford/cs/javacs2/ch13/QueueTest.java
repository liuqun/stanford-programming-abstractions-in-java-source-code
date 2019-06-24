/*
 * File: QueueTest.java
 * --------------------
 * This program implements an interactive test of the Queue abstraction
 * using a client-supplied Queue<String>.
 */

package edu.stanford.cs.javacs2.ch13;

import edu.stanford.cs.consoletest.ConsoleTest;
import edu.stanford.cs.consoletest.HelpText;
import edu.stanford.cs.tokenscanner.TokenScanner;

public abstract class QueueTest extends ConsoleTest {

   public QueueTest() {
      queue = createQueue();
   }

   public abstract Queue<String> createQueue();

   @HelpText("add str -- Adds str to the end of the queue")
   public void addCommand(TokenScanner scanner) {
      String token = scanner.nextToken();
      if (token.isEmpty()) {
         println("Missing value to add");
      } else {
         queue.add(token);
      }
   }

   @HelpText("clear -- Clears the queue")
   public void clearCommand(TokenScanner scanner) {
      queue.clear();
   }

   @HelpText("isEmpty -- Prints whether the queue is empty")
   public void isEmptyCommand(TokenScanner scanner) {
      println(queue.isEmpty());
   }

   @HelpText("list -- List the elements of the queue")
   public void listCommand(TokenScanner scanner) {
      if (queue.isEmpty()) {
         println("Queue is empty");
      } else {
         listQueue(queue);
      }
   }

   @HelpText("peek -- Peeks at the first element")
   public void peekCommand(TokenScanner scanner) {
      if (queue.isEmpty()) {
         println("Queue is empty");
      } else {
         println(queue.peek());
      }
   }

   @HelpText("remove -- Removes and displays the first value")
   public void removeCommand(TokenScanner scanner) {
      if (queue.isEmpty()) {
         println("Queue is empty");
      } else {
         println(queue.remove());
      }
   }

   @HelpText("size -- Prints the size of the queue")
   public void sizeCommand(TokenScanner scanner) {
      println(queue.size());
   }

/*
 * Lists the elements of the queue while preserving its contents.
 */

   private void listQueue(Queue<String> queue) {
      if (!queue.isEmpty()) {
         int size = queue.size();
         for (int i = 0; i < size; i++) {
            String value = queue.remove();
            println(value);
            queue.add(value);
         }
      }
   }

/* Private instance variables */

   private Queue<String> queue;

}
