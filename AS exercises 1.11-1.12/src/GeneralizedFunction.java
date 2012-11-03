
/**
 * A class that contains both recursive and iterative methods to calculate a generalized function of the format
 * f(n) = a*f(n-1) + b*f(n-2) + c*f(n-3) + d*f(n-4)
 * @author Sarah Hale
 *
 */
public class GeneralizedFunction 
{
	static int a, b, c, d, f0, f1, f2, f3;
	
	public static void main(String[] args)
	{
		a = 1;
		b = 2;
		c = 3;
		d = 4;
		f0 = 0;
		f1 = 1;
		f2 = 2;
		f3 = 3;
		
		int arg = 7;
		for (int i = 0; i <= arg; i++)
		{

			System.out.println("f(" + i + ") = " + recursive(i));
			System.out.println("f(" + i + ") = " + iterative(i) + "\n");
		}
	}
	
	/**
	 * The recursively defined funtion 
	 * f(n) = a*f(n-1) + b*f(n-2) + c*f(n-3) + d*f(n-4)
	 * @param n the argument for f(n)
	 * @return the result
	 */
	public static int recursive(int n)
	{
		if (n == 0)
		{
			return f0;
		}
		else if (n == 1)
		{
			return f1;
		}
		else if (n == 2)
		{
			return f2;
		}
		else if (n == 3)
		{
			return f3;
		}
		else
		{
			return a*recursive(n-1) + b*recursive(n-2) + c*recursive(n-3) + d*recursive(n-4);
		}
	}
	
	/**
	 * The iterative function modified from
	 * f(n) = a*f(n-1) + b*f(n-2) + c*f(n-3) + d*f(n-4)
	 * @param n the argument for f(n)
	 * @return the result
	 */
	public static int iterative(int n)
	{
		if (n == 0)
		{
			return f0;
		}
		else if (n == 1)
		{
			return f1;
		}
		else if (n == 2)
		{
			return f2;
		}
		else if (n == 3)
		{
			return f3;
		}
		else
		{
			int fi = 0;
			int fim1 = f3;
			int fim2 = f2;
			int fim3 = f1;
			int fim4 = f0;
			
			for (int i = 4; i <= n; i++)
			{
				fi = a*fim1 + b*fim2 + c*fim3 + d*fim4;
				
				fim4 = fim3;
				fim3 = fim2;
				fim2 = fim1;
				fim1 = fi;
			}
			
			return fi;
		}
	}
}
