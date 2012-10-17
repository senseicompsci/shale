import java.util.*;

/**
 * Think AP Java exercise 15.5
 * The PokerHand class creates random poker hands and is able to classify them by type of hand
 * @author Sarah Hale
 */
public class PokerHand extends Deck
{
   private Card[] hand;
   // the below allows for both 5 and 7 card hands, as by 15.5.9
   private static int numberInAHand = 7;
   
   /**
    * Creates a poker hand of random cards from a 52 card deck
    */
   public PokerHand()
   {
      hand = dealCards();
   }
   
   /**
    * Creates a poker hand from an array of Cards
    * @param hand Cards from which the hand is to be made
    */
   public PokerHand(Card[] hand)
   {
      this.hand = hand;
   }
   
   /**
    * Deals random cards, with none being the same, into a array of Cards
    * @return the hand as an array of Cards
    */
   public Card[] dealCards()
   {
      Deck deck = new Deck();
      Card[] hand = new Card[numberInAHand];
      int i = 0;
      while(i < numberInAHand)
      {
         deck.shuffle();
         Card temp = deck.cards[0];
         boolean equal = false;
         for (int j = 0; j < i; j++)
         {
            if (temp.equals(hand[j]))
            {
               equal = true;
               break;
            }
         }
         if (!equal)
         {
            hand[i] = temp;
            i++;
         }
      }
      return hand;
   }
   
   /**
    * Deals a poker hand of random non-repeating cards
    * This method meets the specifications of exercise 15.5.3
    * It uses the method dealCards() to do so
    * @return a PokerHand of randomly dealt non-repeating cards
    */
   public PokerHand deal()
   {
      return new PokerHand(dealCards());
   }
   
   /**
    * Prints all of the cards in a hand
    */
   public void print()
   {
      for (int i = 0; i < hand.length; i++)
      {
         hand[i].print();
      }
   }
   
   /**
    * Finds whether or not a PokerHand has a flush in it
    * This method meets the specifications of exercise 15.5.5
    * @return if it has a flush
    */
   public boolean hasFlush()
   {
      boolean hasFlush = true;
      int suit = hand[0].suit;
      for (int i = 0; i < hand.length; i++)
      {
         if (hand[i].suit != suit)
         {
            hasFlush = false;
            break;
         }
      }
      return hasFlush;
   }
   
   /**
    * Finds whether or not the PokerHand has a three of a kind
    * This method meets the specifications of exercise 15.5.6
    * @return if it has a three of a kind
    */
   public boolean hasThreeKind()
   {
      int[] count = new int[numberInAHand];
      int[] rank = new int[numberInAHand];
      boolean has = false;
      
      for (int i = 0; i < hand.length; i++)
      {
         rank[i] = hand[i].rank;
      }
      for (int i = 0; i < hand.length; i++)
      {
         for (int j = 0; j < hand.length; j++)
         {
            if (i != j && rank[i] == rank[j])
            {
               count[i]++;
               if (count[i] >= 3) has = true;
            }
         }
      }
      
      return has;
   }
   
   /**
    * Finds whether or not a PokerHand has a two pair
    * This method meets the specifications of 15.5.8
    * @return if it has a two pair
    */
   public boolean hasTwoPair()
   {
      int[] count = new int[numberInAHand];
      int[] rank = new int[numberInAHand];
      /* the double array represents if the first or second pair is found, and what the ranks are
         -1 = false; 1 = true; */
      int[][] has = {{-1, -1}, {-1, -1}};
      
      for (int i = 0; i < hand.length; i++)
      {
         rank[i] = hand[i].rank;
      }
      for (int i = 0; i < hand.length; i++)
      {
         for (int j = 0; j < hand.length; j++)
         {
            if (i != j && rank[i] == rank[j])
            {
               count[i]++;
               if (count[i] >= 2)
               {
                  if (has[0][0] != 1)
                  {
                     has[0][0] = 1;
                     has[0][1] = rank[i];
                  }
                  else if (has[0][0] == 1 && has[0][1] != rank[i])
                  {
                     has[1][0] = 1;
                     has[1][1] = rank[i];
                  }
               }
            }
         }
      }
      
      if (has[0][0] == 1 && has[1][0] == 1) return true;
      else return false;
   }
   
   /**
    * Finds whether or not a PokerHand has a four of a kind
    * This method meets the specifications of 15.5.8
    * @return if it has a four of a kind
    */
   public boolean hasFourKind()
   {
      int[] count = new int[numberInAHand];
      int[] rank = new int[numberInAHand];
      boolean has = false;
      
      for (int i = 0; i < hand.length; i++)
      {
         rank[i] = hand[i].rank;
      }
      for (int i = 0; i < hand.length; i++)
      {
         for (int j = 0; j < hand.length; j++)
         {
            if (i != j && rank[i] == rank[j])
            {
               count[i]++;
               if (count[i] >= 4) has = true;
            }
         }
      }
      
      return has;
   }
   
   
   public static void main(String[] args)
   {
      /* 
       * The following creates and prints out 4 poker hands,
       *  as was asked to do in question 4 of 15.5
       * An ArrayList is used so as to not have a clutter of different PokerHand variables, 
       *  despite not being in the requirements
       */
      ArrayList<PokerHand> hands = new ArrayList<PokerHand>();
      for (int i = 0; i < 4; i++)
      {
         hands.add(new PokerHand());
      }
      Iterator<PokerHand> itr = hands.iterator();
      while(itr.hasNext())
      {
         PokerHand temp = itr.next();
         temp.print();
      }
      
      /*
       * The following creates ten thousand different poker hands and tests them, 
       *  as specified in question 7 of 15.5
       * The probability of getting certain hands appear slightly different than on the given webpage
       */
      ArrayList<PokerHand> hands2 = new ArrayList<PokerHand>();
      int numberHands = 10000;
      for (int i = 0; i < numberHands; i++)
      {
         hands2.add(new PokerHand());
      }
      itr = hands2.iterator();
      int countFlush = 0;
      int countThree = 0;
      while(itr.hasNext())
      {
         PokerHand temp = itr.next();
         if (temp.hasFlush()) countFlush++;
         if (temp.hasThreeKind()) countThree++;
      }
      System.out.println("Number of flushes: " + countFlush + 
            "\nNumber of three of a kinds: " + countThree + "\n   in " + numberHands);
   }
}
