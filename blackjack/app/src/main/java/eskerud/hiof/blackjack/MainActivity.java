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

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
        Deck myDeck = new Deck();
        Log.d("12124",myDeck.get(0)+"");
        Log.d("12",myDeck.get(1)+"");
        Log.d("124",myDeck.get(2)+"");
        Log.d("124",myDeck.get(3)+"");
        Log.d("25",myDeck.get(4)+"");
        Log.d("31",myDeck.get(5)+"");
        Log.d("124",myDeck.get(6)+"");
        Log.d("asdasdasd",myDeck.get(7)+"");
        Log.d("53",myDeck.get(8)+"");
        Log.d("asdasdasd",myDeck.get(9)+"");
        Log.d("46",myDeck.get(10)+"");
        Log.d("46",myDeck.get(11)+"");
        Log.d("46",myDeck.size()+"");
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

            //draw the bitmaps to canvas
            //canvas.drawbitmap
        }

        //Clamps the value of X so the pieces aren't put off screen.


    }

}