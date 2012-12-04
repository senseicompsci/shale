
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
   
   public Words combine(Words otherword)
   {
      Words w = new Words(word + otherword.get());
      System.out.println(w);
      return w;
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
