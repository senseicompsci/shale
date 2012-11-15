
import java.io.*;

public class CatMethod 
{
   public static void cat(File file) 
   {
      RandomAccessFile input = null;
      String line = null;

      try 
      {
          input = new RandomAccessFile(file, "r");
          while ((line = input.readLine()) != null) 
          {
              System.out.println(line);
          }
          return;
      } 
      catch (FileNotFoundException e)
      {
         System.out.println("File not found");
      }
      catch (IOException e)
      {
         System.out.println("Failed to read file");
      }
      finally 
      {
          if (input != null) 
          {
              try 
              {
                 input.close();
              }
              catch (IOException e)
              {
                 System.out.println("Failed to close file");
              }
          }
      }
  }
}
