
/**
 * This class displays bubble sort with an array of 20 random integers
 * @author Sarah Hale
 */

public class BubbleSort 
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
      bubbleSort(list);
      for(int i = 0; i < list.length; i++)
      {
         System.out.print(list[i] + " ");
      }
   }
   
   /**
    * Sorts an array in ascending order using bubble sort
    * @param array
    */
   public static void bubbleSort(int[] array)
   {
      for(int j = array.length-1; j >= 0; j--)
      {
         for (int i = 0; i < array.length-1; i++)
         {
            /* each element is compared to its neighbor, and if it is greater,
             *  the two elements are swapped
             */
            if (array[i] > array[i+1])
            {
               int temp = array[i];
               array[i] = array[i+1];
               array[i+1] = temp;
            }
         }
      }
   }
}
