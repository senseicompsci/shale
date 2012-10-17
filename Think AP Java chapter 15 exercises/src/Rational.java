/**
 * Think AP Java exercise 15.4
 *  the Rational class from exercise 11.3 with all the class methods modified to be object methods.
 * The Rational class models a rational number by a fraction, since one of the definitions of a rational number is that 
 *  it can be showed in the form of a fraction.
 *  This format fulfills the specification of 11.3.1 that it have two instance variables,
 *  one for the numerator and one for the denominator.
 * @author Sarah Hale
 */
public class Rational 
{
   private int n;
   private int d;
   
   /**
    * Default constructor that creates a rational number with the numerator 0 and denominator 1
    * This constructor fulfills the specification of exercise 11.3.2
    */
   public Rational()
   {
      n = 0;
      d = 1;
   }
   
   /**
    * Constructor that creates a rational number with numerator n and denominator d
    * The denominator can not be set to zero, if it is tried, the denominator will be set to 1
    * This constructor fulfills the specification of exercise 11.3.6 by taking two arguments to initialize the variables
    * @param n the numerator
    * @param d the denominator
    */
   public Rational(int n, int d)
   {
      this.n = n;
      if (d != 0)
         this.d = d;
      else
         this.d = 1;
   }
   
   /**
    * Mutator method that resets the numerator
    * @param n the new numerator
    */
   public void setNum(int n)
   {
      this.n = n;
   }
   
   /**
    * Mutator method that resets the denominator
    * The denominator can not be set to zero
    * @param d the new denominator
    */
   public void setDenom(int d)
   {
      if (d != 0)
      {
         this.d = d;
      }
   }
   
   /**
    * Accessor method that returns the numerator 
    * @return the numerator
    */
   public int getNum()
   {
      return n;
   }
   
   /**
    * Accessor method that returns the denominator
    * @return the denominator
    */
   public int getDenom()
   {
      return d;
   }
   
   /**
    * Prints the Rational number in the format "numerator / denominator"
    * This method fulfills the specification of 11.3.3, which simply asks for 
    *  the number to be printed in a reasonable format.
    */
   public void printRational()
   {
      System.out.println(n + " / " + d);
   }
   
   /**
    * Negates the Rational number; multiplies it by -1
    * This method fulfills the specification given in 11.3.7 to be a modifier method that
    *  reverses the sign of the calling object.
    */
   public void negate()
   {
      n = -n;
   }
   
   /**
    * Inverts the Rational number; flips the numerator and the denominator
    * The Rational number can not be inverted if the numerator is zero
    * This method fulfills the specification given in 11.3.8 to invert the Rational number 
    *  by swapping the numerator and denominator.
    */
   public void invert()
   {
      if (n != 0)
      {
         int temp = n;
         n = d;
         d = temp;
      }
   }
   
   /**
    * Gets the decimal equivalent to the fraction
    * This method fulfills the specification given in 11.3.9 to convert a rational number into a
    *  double, while not modifying the calling object.
    * @return the Rational number in decimal form
    */
   public double toDouble()
   {
      return ((double)n / (double)d);
   }
   
   /**
    * Reduces the Rational number's fraction to simplest terms
    * This method fulfills the specification of 11.3.10 to create a modifier method that reduces a rational 
    *  number with the greatest common divisor, done via the method gcd()
    */
   public void reduce()
   {
      int gcd = gcd(n, d);
      n = n / gcd;
      d = d/ gcd;
   }
   
   /**
    * Finds the greatest common divisor between two integers
    * @param a the first integer
    * @param b the second integer
    * @return the greatest common divisor
    */
   public static int gcd(int a, int b)
   {
      int t = 0;
      while (b != 0)
      {
         t = b;
         b = a%b;
         a = t;
      }
      return a;
   }
   
   /**
    * Adds two rational numbers and returns the sum of the two
    * This method fulfills the specification of 11.3.11 to add two Rational numbers
    *  and return a new Rational number in its completely reduced form.
    * @param num number to be added to the calling Rational number
    * @return the sum of the two numbers
    */
   public Rational add(Rational num)
   {
      int d = this.d * num.d;
      int n = (num.d * this.n) + (this.d * num.n);
      Rational rational = new Rational(n, d);
      rational.reduce();
      return rational;
   }
   
   /*
    * A main method, created to fulfill the first part of the specification of 11.3.4
    */
   public static void main(String[] args)
   {
      /* 
       * As specified in 11.3.4, a default Rational variable is created, 
       * has it's values manually modified, and then is printed.
       */
      Rational num1 = new Rational();
      num1.setNum(5);
      num1.setDenom(7);
      num1.printRational();
      
      
      /* 
       * As specified in 11.3.7, the new negate() method is tested by the following lines
       */
      num1.negate();
      num1.printRational();
      
      /* 
       * As specified in 11.3.8, the new invert() method is tested by the following lines
       */
      Rational num2 = new Rational(2, 3);
      num2.printRational();
      num2.invert();
      num2.printRational();
      
      /* 
       * The following tests various methods that were made as by the requirements of the exercise, 
       *  but did not require and testing in the main method
       */
      System.out.println(num1.toDouble());
      
      Rational num3 = new Rational(24, 32);
      num3.printRational();
      num3.reduce();
      num3.printRational();
      
      Rational num4 = new Rational(6, 10);
      Rational num5 = new Rational(1, 15);
      num4.printRational();
      num5.printRational();
      Rational num6 = num4.add(num5);
      num6.printRational();
   }
}