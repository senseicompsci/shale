
/**
 * This class contains methods that calculate the function f both recursively and iteratively 
 * f(n) = f(n-1) + 2*f(n-2) + 3*f(n-3)
 * base cases: f(0) = 0, f(1) = 1, f(2) = 2
 * This meets the requirements of AS exercise 1.11
 * @author Sarah Hale
 */
public class UnrecursiveFunction 
{
   public static void main(String[] args)
   {
      // print f(n) for all n in [0,7]
      int arg = 7;
      for (int i = 0; i <= arg; i++)
      {
         System.out.println("f(" + i + ") = " + recursive(i));
         System.out.println("f(" + i + ") = " + iterative(i) + "\n");
      }
   }
   
   /**
    * Calculates f(n) recursively, just as was defined in the specification of exercise 1.11
    * @param n input for the function f
    * @return output of the function f
    */
   public static int recursive(int n)
   {
      if (n < 3)
      {
         return n;
      }
      else
      {
         return recursive(n-1) + 2*recursive(n-2) + 3*recursive(n-3);
      }
   }
   
   /**
    * Calculates f(n) iteratively, as was required in the specification of exercise 1.11
    * This method is based off of the generalized method taught in class
    * @param n input for the function f
    * @return output of the function f
    */
   public static int iterative(int n)
   {
      if (n < 3)
      {
         return n;
      }
      
      /*
       * fi means "f of i"
       * fim1 means "f of i minus 1"
       * the rest follow this pattern as well
       */
      int fi = 0;
      int fim1 = 2;
      int fim2 = 1;
      int fim3 = 0;
      
      for (int i = 3; i <= n; i++)
      {
         fi = fim1 + 2*fim2 + 3*fim3;
         
         fim3 = fim2;
         fim2 = fim1;
         fim1 = fi;
      }
      
      return fi;
   }
}
