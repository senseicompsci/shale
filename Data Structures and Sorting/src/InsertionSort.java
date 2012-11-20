
/**
 * This class displays insertion sort with an array of 20 random integers
 * @author Sarah Hale
 */

public class InsertionSort 
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
      insertionSort(list);
      for(int i = 0; i < list.length; i++)
      {
         System.out.print(list[i] + " ");
      }
   }
   
   /**
    * Sorts an array in ascending order using insertion sort
    * @param array
    */
   public static void insertionSort(int[] array)
   {
      int j;
      int value; 
      
      for (int i = 0; i < array.length; i++)
      {
         value = array[i];
         j = i;// index of the element to be inserted into its proper place
         
         /* the element moves down the array until it either sees another element smaller than
          * itself, or it reaches the lowest index of the array and becomes the smallest sorted element in the array
          */
         while (j > 0 && array[j-1] > value)
         {
            array[j] = array[j-1];
            j--;
         }
         array[j] = value;
      }
   }
}
