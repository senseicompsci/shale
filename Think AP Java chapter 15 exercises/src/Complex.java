
/**
 * Think AP Java exercises 15.2 and 15.3 are contained within this class
 * 15.2: abs()
 * 15.3: equals()
 * (see below)
 * The Complex class models a complex number that is in the format real + imaginary, commonly seen as a + bi format
 * @author Sarah Hale
 */

public class Complex 
{
   private int real;
   private int imag;
   
   /**
    * Creates a default complex number of value 0
    */
   public Complex()
   {
      real = 0;
      imag = 0;
   }
   
   /**
    * Creates a complex number of the format a + b(i)
    * @param real the real number, a
    * @param imag the imaginary number, b
    */
   public Complex(int real, int imag)
   {
      this.real = real;
      this.imag = imag;
   }
   
   /**
    * Returns the real number 
    * @return the real number
    */
   public int getReal()
   {
      return real;
   }
   
   /**
    * Returns the imaginary number
    * @return the imaginary number
    */
   public int getImag()
   {
      return imag;
   }
   
   /**
    * Modifies the real number
    * @param real the number for it to be set as
    */
   public void setReal(int real)
   {
      this.real = real;
   }
   
   /**
    * Modifies the imaginary number
    * @param imag the number for it to be set as
    */
   public void setImag(int imag)
   {
      this.imag = imag;
   }
   
   
   
   /**
    * The given class method that gives the absolute value of a complex number
    * @param c complex number
    * @return absolute value
    */
   public static double abs(Complex c)
   {
      return Math.sqrt(c.real * c.real + c.imag * c.imag);
   }
   
   /* 
    * The following method, abs(), is a modified version of the above method such that it is now
    *  an object method rather than a class method, as by the specification of exercise 15.2
    */
   /**
    * It return the absolute value of the complex number that called it.
    * @return the absolute value
    */
   public double abs()
   {
      return Math.sqrt(real * real + imag * imag);
   }
   
   
   /**
    * The given object method that returns whether or not the complex number b
    * is equal to the complex number that called the method.
    * @param b complex number to be compared to the method caller
    * @return whether or not they are equal
    */
   public boolean equals(Complex b)
   {
      return (real == b.real && imag == b.imag);
   }
   
   /* 
    * The following method, equals(), is a modified version of the above method such that it is now
    *  a class method rather than an object method, as by the specification of exercise 15.3
    */
   /**
    * It returns whether or not the inputed a and b are equal. 
    * @param a complex number to be compared
    * @param b complex number to be compared
    * @return whether or not the two are equal
    */
   public static boolean equals(Complex a, Complex b)
   {
      return (a.real == b.real && a.imag == b.imag);
   }
}
