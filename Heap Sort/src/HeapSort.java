
/**
 * This code shows an implementation of heapsort without having an 
 *  implementation of a heap. Even so, it displays understanding of the
 *  concept of heapsort. 
 * @author Sarah Hale
 */
public class HeapSort {
   /*
    * Heap is not implemented, but if it were, this heapsort would work
    */
   public void heapsort(int[] ints) {
      Heap h = new Heap(ints); // this constructor would create a heap out of the given array
      for (int i = 0; i < ints.length; i++) {
         ints[i] = h.max(); // add max to the next element in the array
         // max() would remove the max element from the top of the heap and then rearrange the heap accordingly
      }
      // sorted! :)
   }
}
