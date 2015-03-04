package eskerud.hiof.blackjack;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;


import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class MainActivity extends Activity {
    public static String PACKAGE_NAME = "net.eskerud.martin.masterthesis";
    private Deck myDeck;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
        myDeck = new Deck();
    }

    public class MyView extends View {


        public MyView(Context context) {
            super(context);

            initBitmapHashmapKeys();
        }

        private void initBitmapHashmapKeys() {


        }


        private void initBitmapPositions(Resources res) {

            /*
            * TODO: this should be made in the constructor of the bitmapBlock object instead.
            */

        }

        @Override
        public boolean onTouchEvent(MotionEvent e) {
            //TODO: interpolate between current position and finger position


            this.invalidate();
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
            //draw the bitmaps to canvas
            //canvas.drawbitmap
        }

        //Clamps the value of X so the pieces aren't put off screen.


    }

}