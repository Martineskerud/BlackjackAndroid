package eskerud.hiof.blackjack;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Martin on 04.03.2015.
 */
public class Deck<T> extends ArrayList {


    public Deck(Resources res) {
        String[] types = {"Clubs", "Spades", "Diamonds", "Hearts"};
        //for each type of card - clubs, spades, diamonds and hearts
        for (int i = 0; i < 4; i++) {
            //create a new card, specify the type of card.

            for (int j = 2; j < 15; j++) {
                Card card = new Card(types[i]);
                if (j < 11) {
                    card.setValue(j);
                    card.setName(Integer.toString(j));
                    int resID = res.getIdentifier(card.getType() + "of" + card.getName() + ".png", "drawable", MainActivity.PACKAGE_NAME);
                    Log.d("this is ResID", resID + "");
                    Bitmap img = BitmapFactory.decodeResource(res,resID);
                    card.setImage(img);
                    //card.setImage()
                } else {
                    card.setValue(10);

                    if (j == 11) {
                        card.setName("jack");
                        int resID = res.getIdentifier(card.getType() + "of" + card.getName() + ".png", "drawable", MainActivity.PACKAGE_NAME);
                        Log.d("this is ResID", resID + "");
                        Bitmap img = BitmapFactory.decodeResource(res,resID);
                        card.setImage(img);
                    } else if (j == 12) {
                        card.setName("queen");
                        int resID = res.getIdentifier(card.getType() + "of" + card.getName() + ".png", "drawable", MainActivity.PACKAGE_NAME);
                        Log.d("this is ResID", resID + "");
                        Bitmap img = BitmapFactory.decodeResource(res,resID);
                        card.setImage(img);
                    } else if (j == 13) {
                        card.setName("king");
                        int resID = res.getIdentifier(card.getType() + "of" + card.getName() + ".png", "drawable", MainActivity.PACKAGE_NAME);
                        Log.d("this is ResID", resID + "");
                        Bitmap img = BitmapFactory.decodeResource(res,resID);
                        card.setImage(img);
                    } else if (j == 14) {
                        card.setName("ace");
                        int resID = res.getIdentifier(card.getType() + "of" + card.getName() + ".png", "drawable", MainActivity.PACKAGE_NAME);
                        Log.d("this is ResID", resID + "");
                        Bitmap img = BitmapFactory.decodeResource(res,resID);
                        card.setImage(img);
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

