package eskerud.hiof.blackjack;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Martin on 04.03.2015.
 */
public class Deck<T> extends ArrayList {


    public Deck(Context con) {
        Resources res = con.getResources();

        String[] types = {"clubs", "spades", "diamonds", "hearts"};

        int[][] cards = new int[][]{{R.drawable.clubsoface, R.drawable.clubsof2, R.drawable.clubsof3, R.drawable.clubsof4, R.drawable.clubsof5, R.drawable.clubsof6, R.drawable.clubsof7, R.drawable.clubsof8, R.drawable.clubsof9,
                R.drawable.clubsof10, R.drawable.clubsofjack, R.drawable.clubsofqueen, R.drawable.clubsofking}, {R.drawable.heartsoface, R.drawable.heartsof2, R.drawable.heartsof3, R.drawable.heartsof4, R.drawable.heartsof5, R.drawable.heartsof6, R.drawable.heartsof7, R.drawable.heartsof8, R.drawable.heartsof9,
                R.drawable.heartsof10, R.drawable.heartsofjack, R.drawable.heartsofqueen, R.drawable.heartsofking}, {R.drawable.diamondsoface,
                R.drawable.diamondsof2, R.drawable.diamondsof3, R.drawable.diamondsof4, R.drawable.diamondsof5, R.drawable.diamondsof6, R.drawable.diamondsof7, R.drawable.diamondsof8, R.drawable.diamondsof9,
                R.drawable.diamondsof10, R.drawable.diamondsofjack, R.drawable.diamondsofqueen, R.drawable.diamondsofking}, {
                R.drawable.spadesoface, R.drawable.spadesof2, R.drawable.spadesof3, R.drawable.spadesof4, R.drawable.spadesof5, R.drawable.spadesof6, R.drawable.spadesof7, R.drawable.spadesof8, R.drawable.spadesof9,
                R.drawable.spadesof10, R.drawable.spadesofjack, R.drawable.spadesofqueen, R.drawable.spadesofking
        }};

        //for each type of card - clubs, spades, diamonds and hearts
        for (int i = 0; i < 4; i++) {
            //create a new card, specify the type of card.

            //only using 9 cards per color because RAM
            for (int j = 0; j < 13; j++) {
                Card card = new Card(types[i]);

                if (j < 10 && j > 0) {

                    card.setValue(j + 1);
                    card.setName(j + 1 + "");
                    Bitmap img = BitmapFactory.decodeResource(res, cards[i][j]);
                    if (!img.isMutable())
                        img = convertToMutable(con, img);
                    card.setImage(img);
                    //card.setImage()
                } else {
                    card.setValue(10);

                    if (j == 10) {
                        card.setName("jack");
                        Bitmap img = BitmapFactory.decodeResource(res, cards[i][j]);
                        if (!img.isMutable())
                            img = convertToMutable(con, img);
                        card.setImage(img);
                    } else if (j == 11) {
                        card.setName("queen");
                        Bitmap img = BitmapFactory.decodeResource(res, cards[i][j]);
                        if (!img.isMutable())
                            img = convertToMutable(con, img);
                        card.setImage(img);
                    } else if (j == 12) {
                        card.setName("king");
                        Bitmap img = BitmapFactory.decodeResource(res, cards[i][j]);
                        if (!img.isMutable())
                            img = convertToMutable(con, img);
                        card.setImage(img);
                    } else if (j == 0) {
                        card.setName("ace");
                        Bitmap img = BitmapFactory.decodeResource(res, cards[i][j]);
                        if (!img.isMutable())
                            img = convertToMutable(con, img);
                        card.setImage(img);
                    }
                }

                this.add(card);
            }
        }
        shuffleDeck();


    }

    public static Bitmap convertToMutable(final Context context, final Bitmap imgIn) {
        final int width = imgIn.getWidth(), height = imgIn.getHeight();
        final Bitmap.Config type = imgIn.getConfig();
        File outputFile = null;
        final File outputDir = context.getCacheDir();
        try {
            outputFile = File.createTempFile(Long.toString(System.currentTimeMillis()), null, outputDir);
            outputFile.deleteOnExit();
            final RandomAccessFile randomAccessFile = new RandomAccessFile(outputFile, "rw");
            final FileChannel channel = randomAccessFile.getChannel();
            final MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, imgIn.getRowBytes() * height);
            imgIn.copyPixelsToBuffer(map);
            imgIn.recycle();
            final Bitmap result = Bitmap.createBitmap(width, height, type);
            map.position(0);
            result.copyPixelsFromBuffer(map);
            channel.close();
            randomAccessFile.close();
            outputFile.delete();
            return result;
        } catch (final Exception e) {
        } finally {
            if (outputFile != null)
                outputFile.delete();
        }
        return null;
    }

    public void shuffleDeck() {
        long seed = System.nanoTime();
        Collections.shuffle(this, new Random(seed));
    }


}

