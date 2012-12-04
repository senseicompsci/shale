
public class Words
{
   private String word;
   
   public Words()
   {
      set("");
   }
   public Words(String word)
   {
      set(word);
   }
   
   public void print()
   {
      System.out.println(word);
   }
   
   public String get()
   {
      return word;
   }
   
   public void set(String word)
   {
      this.word = word;
   }
}
