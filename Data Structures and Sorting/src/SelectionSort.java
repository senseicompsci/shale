
/**
 * This class displays selection sort with an array of 20 random integers
 * @author Sarah Hale
 */

public class SelectionSort 
{
   public static void main(String[] args)
   {
      int[] list = new int[20];
      for (int i = 0; i < list.length; i++)
      {
         list[i] = (int) (Math.random()*100);
      }
      for(int i = 0; i < list.length; i++)
      {
         System.out.print(list[i] + " ");
      }
      System.out.println();
      selectionSort(list);
      for(int i = 0; i < list.length; i++)
      {
         System.out.print(list[i] + " ");
      }
   }
   
   /**
    * Sorts an array in ascending order using selection sort
    * @param array
    */
   public static void selectionSort(int[] array)
   {
      // loops through all elements in array
      for (int i = 0; i < array.length; i++)
      {
         /* the index of the smallest value in the unsorted array
          * starts at i because anything <i has already been sorted
          */
         int minIndex = i;
         
         // loops through the unsorted elements to find the lowest valued one
         for (int j = i + 1; j < array.length; j++)
         {
            if (array[j] < array[minIndex])
            {
               minIndex = j;
            }
         }
         
         /* if the element of the current index is not the smallest, 
          * it is swapped with the smallest
          */
         if (minIndex != i)
         {
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
         }
      }
   }
}
