import java.util.Random;
import java.util.ArrayList;

/**
 * This class demonstrates a method to obtain the median of an array of 
 * integers through a modified version of quicksort, that takes O(n) time
 * rather than the O(nlogn) time of traditional quicksort
 * @author Max Bernstein, Sarah Hale, Chris Hinstorff
 */
public class MedianfromQuicksort {
  public static void main(String[] args) {
    ArrayList<Integer> randl = new ArrayList<Integer>();
    Random rand = new Random();

    for (int i = 0; i < 15; i++) randl.add(rand.nextInt(100));

    System.out.println(quicksort(randl, randl.size()/2));
  }

  /**
   * Determines the median of the values in an ArrayList using a modified version
   * of quicksort such that this determination takes approximately O(n) time
   * @param a ArrayList of Integers from which to find the median
   * @param med median/middle of the ArrayList
   * @return the median
   */
  public static int quicksort(ArrayList<Integer> a, int med) {
    if (a.size() == 1) return a.get(0);

    Random rand = new Random();
    int r = rand.nextInt(a.size());

    int p = a.get(r);
    a.remove(r);

    ArrayList<Integer> less = new ArrayList<Integer>(), greater = new ArrayList<Integer>();

    for (int i = 0; i < a.size(); i++) {
      int el = a.get(i);
      if (el <= p) less.add(el);
      else greater.add(el);
    }

    if (med <= less.size()) return quicksort(less, med);
    else if (med > less.size()-greater.size()) return quicksort(greater, med-(a.size()-greater.size()));
    else return p;
  }
}