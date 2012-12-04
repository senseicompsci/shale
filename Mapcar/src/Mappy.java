import java.util.*;
import java.lang.reflect.*;

/**
 * Class that contains and tests mapcar(), a method that invokes another method 
 * onto a variable number of lists
 * @author Sarah Hale
 */

public class Mappy 
{
   public static void main(String[] args)
   {
      ArrayList<Words> one = new ArrayList<Words>();
      one.add(new Words("abc"));
      one.add(new Words("def"));
      one.add(new Words("ghi"));
      ArrayList<Words> two = new ArrayList<Words>();
      two.add(new Words("jkl"));
      two.add(new Words("mno"));
      Method[] methods = Words.class.getMethods();
      
      Scanner input = new Scanner(System.in);
      boolean picked = false;
      int j = 0;
      while (!picked)
      { 
         //choose the print method!
         System.out.println("Invoke " + methods[j].getName() + "? Yes(1) / No(0)");
         if (input.nextInt() == 0) picked = false;
         else picked = true;
         j++;
      }
      
      System.out.println("Invoking this method: " + methods[j-1].getName());
      try {
         mapcar(methods[j-1], one, two);
      } catch (SecurityException e) {
         e.printStackTrace();
      }
   }
   /**
    * Invokes a method on all of the elements of all of the lists
    * This method cannot invoke a method that requires an argument to be passed to it,
    * and also cannot invoke methods taking two different lists as parameters, despite the 
    * specification for the mapcar assignment. This is because it is difficult to determine 
    * when each of these cases is happening, so this version of mapcar only invokes
    * methods taking no arguments onto each element in each list. 
    * @param method the method that is invoked on each element
    * @param lists variable number of lists that is wished to have the method invoked on
    */
   public static <T> void mapcar(Method method, List<T>... lists)
   {
      for (List<T> list: lists)
      {
         for (T element: list)
         {
            try {
               method.invoke(element);
            } catch (IllegalArgumentException e) {
               e.printStackTrace();
            } catch (IllegalAccessException e) {
               e.printStackTrace();
            } catch (InvocationTargetException e) {
               e.printStackTrace();
            }
         }
      }
   }
   
}
