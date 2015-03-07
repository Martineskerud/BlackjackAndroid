package eskerud.hiof.blackjack;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Iterator;


public class MainActivity extends Activity {
    public static String PACKAGE_NAME;
    private Deck<Card> myDeck;
    private int currCard = 51;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        String PACKAGE_NAME = getPackageName();
        super.onCreate(savedInstanceState);
        myDeck = new Deck<Card>(getApplicationContext());
        setContentView(new MyView(this));

    }

    public class MyView extends View {
        private float y;
        private float x;
        private int eventType;
        private boolean firstRound = true;
        private boolean dealerStopped = false;

        public MyView(Context context) {
            super(context);
        }

        private ArrayList<Card> cardsToRender = new ArrayList<Card>();
        private ArrayList<Card> dealersCardsToRender = new ArrayList<Card>();

        private void initBitmapPositions(Resources res) {

            /*
            * TODO: this should be made in the constructor of the bitmapBlock object instead.
            */

        }

        @Override
        public boolean onTouchEvent(MotionEvent e) {
            x = e.getX();
            y = e.getY();
            eventType = e.getAction();
            if (eventType == MotionEvent.ACTION_UP) {

                evaluateHit();
            }
            return true;
        }


        @Override
        protected void onDraw(Canvas canvas) {

            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.WHITE);
            canvas.drawPaint(paint);
            // Use Color.parseColor to define HTML colors
            paint.setColor(Color.parseColor("#CD5C5C"));

            final Paint transparentPaint = new Paint();
            transparentPaint.setARGB(128, 255, 255, 255);
            paint.setTextSize(90);
            paint.setColor(Color.BLACK);
            canvas.drawText("Dealer:", 50, 200, paint);
            canvas.drawText("Your hand:", 50, 800, paint);
            canvas.drawText("Hit me!     Stand", 400, 1450, paint);
            canvas.drawText("" + x + "," + y, x, y, paint);
            Card temp = (Card) myDeck.get(0);
            //  temp.getImage().setHeight(50);
            // temp.getImage().setWidth(50);

            //draw the cards which we draw.
            int dealerIndex = 1;
            int dealerScore = 0;
            //draw the cards for the dealer.
            for (Card card : dealersCardsToRender) {

                canvas.drawBitmap(card.getImage(), 100 + (dealerIndex * 20), 300, paint);
                dealerIndex++;
                Log.d("calculation", + dealerScore + " + " +  card.getValue() + "From card:" + card.toString());
                dealerScore += card.getValue();

            }
            if (dealerScore > 17) {
                dealerStopped = true;
                canvas.drawText("Dealer: "+ dealerScore, 50, 670, paint);
            }
            else{
                canvas.drawText("Dealer: "+ dealerScore, 50, 670, paint);
            }

            int index = 0;
            int score = 0;
            for (Card card : cardsToRender) {

                canvas.drawBitmap(card.getImage(), 100 + (index * 20), 970, paint);
                index++;
                score += card.getValue();
            }

            if (score > 21) {
                canvas.drawText("Score: BUST!", 50, 1300, paint);
                firstRound = true;
            } else if (score == 21) {
                canvas.drawText("Score: BLACKJACK!", 50, 1300, paint);
                firstRound = true;
            } else {
                canvas.drawText("Score: " + score, 50, 1300, paint);
            }
            this.invalidate();


        }

        public void evaluateHit() {
            if (x < 660 && x > 400 && y > 1350 && y < 1500) {
                hitMe();
            } else if (x < 1000 && x > 800 && y > 1350 && y < 1500) {
                stand();
            } else {
                Log.d(PACKAGE_NAME, "we didn't hit shit, captain");
            }
        }

        public void hitMe() {
            //2 cards are dealt in the first round
            if (firstRound) {
                //this looks dumb but dunno how else to do it
                dealerStopped = false;
                removeAll(cardsToRender);
                removeAll(dealersCardsToRender);
                firstRound();
                firstRound();
                firstRound = false;
            } else {
                Log.d(PACKAGE_NAME, "we hit the 'HIT ME' box");
                if (currCard == 0) {
                    myDeck.shuffleDeck();
                    currCard = 51;
                }
                //Unfortunately we need to cast, for some reason...
                Card currCardTemp = (Card) myDeck.get(currCard);
                //cards to render will be populated by a new card, which we draw here from the deck
                cardsToRender.add(currCardTemp);
                currCard--;
            }
        }

        public void firstRound() {

            if (currCard == 0) {
                myDeck.shuffleDeck();
                currCard = 51;
            }
            //Unfortunately we need to cast, for some reason...
            Card currCardTemp = (Card) myDeck.get(currCard);
            //cards to render will be populated by a new card, which we draw here from the deck
            cardsToRender.add(currCardTemp);
            currCard--;
            //this will be called twice, as this method is called twice. Dealer receives two cards as well :)
            dealerDealsToSelf();

        }

        public void dealerDealsToSelf() {
            if (!dealerStopped) {

                if (currCard == 0) {
                    myDeck.shuffleDeck();
                    currCard = 51;
                }
                //Unfortunately we need to cast, for some reason...
                Card currCardTemp = (Card) myDeck.get(currCard);
                //cards to render will be populated by a new card, which we draw here from the deck
                dealersCardsToRender.add(currCardTemp);
                currCard--;

            }
        }

        public void stand() {

            removeAll(cardsToRender);
            removeAll(dealersCardsToRender);
            Log.d(PACKAGE_NAME, "we hit the 'STAND' box");
            firstRound = true;
        }


        //Clamps the value of X so the pieces aren't put off screen.
        public boolean removeAll(ArrayList<Card> c) {
            boolean modified = false;
            Iterator<Card> e = c.iterator();
            while (e.hasNext()) {
                if (c.contains(e.next())) {
                    e.remove();
                    modified = true;
                }
            }
            return modified;
        }

    }

}