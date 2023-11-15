// Card Deck Shuffling Jake Roman
import java.util.*;

public class CardDeck {
    public static void main(String[] args) {
        // create deck
        int[] deck = new int[52];
        for (int i = 0; i < deck.length; i++) {
            deck[i] = i; // set each card to its index (prevents duplicates)
        }

        // shuffle deck
        Random rng = new Random();
        for (int i = 0; i < deck.length; i++) {
            int index = rng.nextInt(0,deck.length-1);
            int storage = deck[index];
            deck[index] = deck[i];
            deck[i] = storage;
        }

        // display first 4 cards
        String[] cards = {"A","2","3","4","5","6","7","8","9","10","J","K","Q"};
        String[] suits = {"Spades","Hearts","Diamonds","Clubs"};
        for (int i = 0; i < 4; i++) {
            int cardValue = deck[i];
            int suit = cardValue/13;
            int card = cardValue%13;
            System.out.println(cards[card]+" "+suits[suit]);
        }
    }
}
