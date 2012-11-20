
import java.util.*;

public class TreeVsList 
{
	public static void main(String[] args)
	{
		TreeSet<Integer> tree = new TreeSet<Integer>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		int redundancy = 13;
		
		long start = System.nanoTime();
		for (int i = 0; i < 5000000; i++)
		{
			list.add(i%redundancy);
		}
		long elapsed = System.nanoTime() - start;
		System.out.println(elapsed);
		
		start = System.nanoTime();
		for (int i = 0; i < 5000000; i++)
		{
			tree.add(i%redundancy);
		}
		elapsed = System.nanoTime() - start;
		System.out.println(elapsed);
	}
}
