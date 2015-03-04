package eskerud.hiof.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Martin on 04.03.2015.
 */
public class Deck extends ArrayList {


    public Deck() {
        String[] types = {"Clubs", "Spades", "Diamonds", "Hearts"};
        //for each type of card - clubs, spades, diamonds and hearts
        for (int i = 0; i < 4; i++) {
            //create a new card, specify the type of card.

            for (int j = 2; j < 15; j++) {
                Card card = new Card(types[i]);
                if (j < 11) {
                    card.setValue(j);
                    card.setName(Integer.toString(j));
                } else {
                    card.setValue(10);

                    if (j == 11) {
                        card.setName("Jack");
                    } else if (j == 12) {
                        card.setName("Queen");
                    } else if (j == 13) {
                        card.setName("King");
                    } else if (j == 14) {
                        card.setName("Ace");
                    }
                }
                this.add(card);
            }
        }
        shuffleDeck();


    }

    private void shuffleDeck() {
        long seed = System.nanoTime();
        Collections.shuffle(this, new Random(seed));
    }


}

