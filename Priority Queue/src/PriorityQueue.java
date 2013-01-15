import java.util.*;

/**
 * An implementation of a Priority Queue
 * @author Sarah Hale
 * @param <T> type of elements that the PriorityQueue holds
 */
public class PriorityQueue<T> {
   private ArrayList<QueueNode<T>> list;
   
   public PriorityQueue() {
      list = new ArrayList<QueueNode<T>>();
   }
   
   /**
    * Adds a new element of a specific priority to the end of the queue
    * @param element the specific element added
    * @param priority the priority of the new element
    */
   public void add(T element, int priority) {
      list.add(new QueueNode<T>(element, priority));
   }
   
   /**
    * Adds a new element with no priority to the end of the queue
    * @param element
    */
   public void add(T element) {
      list.add(new QueueNode<T>(element));
   }
   
   /**
    * Determines whether or not the queue is empty
    * @return if the queue is empty or not
    */
   public boolean isEmpty() {
      return list.isEmpty();
   }
   
   /**
    * Peeks at the next element in the queue with the highest priority,
    *  without removing that element from the queue
    * @return the next element
    */
   public T peek() {
      Iterator<QueueNode<T>> itr = list.iterator();
      QueueNode<T> current = (QueueNode<T>) itr.next();
      int p = current.getPriority();
      T e = (T) current.getElement();
      while (itr.hasNext()) {
         current = (QueueNode<T>) itr.next();
         if (current.getPriority() > p) {
            e = (T) current.getElement();
            p = current.getPriority();
         }
      }
      return e;
   }
   
   /**
    * Polls the next element in the queue with the highest priority,
    *  removing that element from the queue
    * @return the next element
    */
   public T poll() {
      Iterator<QueueNode<T>> itr = list.iterator();
      QueueNode<T> current = (QueueNode<T>) itr.next();
      QueueNode<T> toRemove = new QueueNode<T>((T) new Object());
      int p = current.getPriority();
      T e = (T) current.getElement();
      boolean x = false;
      while (itr.hasNext()) {
         current = (QueueNode<T>) itr.next();
         if (current.getPriority() > p) {
            e = (T) current.getElement();
            p = current.getPriority();
            toRemove = new QueueNode<T>(e, p);
            x = true;
         }
      }
      if (x) {
         list.remove(toRemove);
      }
      else {
         e = list.get(0).getElement();
         list.remove(0);
      }
      return e;
   }
   
   /*
    * just some testing to see if this implementation works
    */
   public static void main(String [] args) {
      PriorityQueue<Integer> q = new PriorityQueue<Integer>();
      q.add(5, 0);
      q.add(6, 1);
      q.add(7, 0);
      q.add(8, 1);
      q.add(9, 2);
      while (!q.isEmpty()) {
         System.out.println("peek: " + q.peek());
         System.out.println("poll: " + q.poll());
      }
      // correct output would be: 9, 6, 8, 5, 7
   }
}

/**
 * Each QueueNode acts as a node, containing an element and a priority,
 *  in an ArrayList to form a PriorityQueue
 * @param <T> type of element that the node holds
 */
class QueueNode<T> {
   private T element;
   private int priority;
   
   /**
    * Creates a QueueNode of a specific element and priority
    * @param element
    * @param priority
    */
   public QueueNode(T element, int priority) {
      this.element = element;
      this.priority = priority;
   }
   /**
    * Creates a QueueNode with no priority
    * @param element
    */
   public QueueNode(T element) {
      this.element = element;
      this.priority = 0;
   }
   
   /**
    * Gets the element in this node
    * @return
    */
   public T getElement() {
      return element;
   }
   
   /**
    * Sets the element in this node
    * @param element
    */
   public void setElement(T element) {
      this.element = element;
   }
   
   /**
    * Gets the priority of this node
    * @return
    */
   public int getPriority() {
      return priority;
   }
   
   /**
    * Sets the priority of this node
    * @param priority
    */
   public void setPriority(int priority) {
      this.priority = priority;
   }
   
   public boolean equals(Object o) {
      if (!o.getClass().toString().equals("class QueueNode")) {
         return false;
      }
      else if (((QueueNode<T>)o).getElement().equals(this.element) && ((QueueNode<T>)o).getPriority() == this.priority) {
         return true;
      }
      else {
         return false;
      }
   }
}