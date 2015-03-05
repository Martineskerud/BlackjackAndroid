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


public class MainActivity extends Activity {
    public static String PACKAGE_NAME;
    private Deck<Card> myDeck;


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
        private long lastPress;

        public MyView(Context context) {
            super(context);
        }


        private void initBitmapPositions(Resources res) {

            /*
            * TODO: this should be made in the constructor of the bitmapBlock object instead.
            */

        }

        @Override
        public boolean onTouchEvent(MotionEvent e) {
            x=e.getX();
            y=e.getY();
            eventType = e.getAction();
            if(eventType==MotionEvent.ACTION_UP){

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
            canvas.drawText(""+x+","+y,x,y,paint);
            Card temp = (Card) myDeck.get(0);
            //  temp.getImage().setHeight(50);
            // temp.getImage().setWidth(50);

            canvas.drawBitmap(temp.getImage(), 0, 0, paint);
            this.invalidate();


        }

        public void evaluateHit(){
            if(x < 660 && x > 400 && y > 1350 && y < 1500 ){
                hitMe();
            }
            else if(x < 1000 && x > 800 && y > 1350 && y < 1500 ){
                stand();
            }
            else{
                Log.d(PACKAGE_NAME,"we didn't hit shit, captain");
            }
        }

        public void hitMe(){

            Log.d(PACKAGE_NAME, "we hit the 'HIT ME' box");

        }
        public void stand(){
            Log.d(PACKAGE_NAME, "we hit the 'STAND' box");
        }



        //Clamps the value of X so the pieces aren't put off screen.


    }

}