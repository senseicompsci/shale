
/**
 * This class calculates elements of Pascal's Triangle recursively and prints them to the console
 * It meets the requirements of AS exercise 1.12 
 * @author Sarah Hale
 */

public class Pascal 
{
   public static void main(String[] args)
   {
      // prints row 0 through 7 of pascal's triangle
      int rows = 7;
      for (int i = 0; i < rows + 1; i++)
      {
         for (int j = 0; j < i + 1; j++)
         {
            System.out.print(calculate(i, j) + " ");
         }
         System.out.println("");
      }
   }
   
   /**
    * Calculates the value of an element in Pascal's Triangle recursively,
    *  as specified in exercise 1.12
    * @param row the row of the element of the triangle
    * @param col the column of the element of the triangle
    * @return the value of that element
    */
   public static int calculate(int row, int col)
   {
      if (row == 0 || col == 0)
      {
         return 1;
      }
      
      else if (row == col)
      {
         return 1;
      }
      
      else
      {
         return calculate(row - 1, col -1) + calculate(row - 1, col);
      }
   }
}
