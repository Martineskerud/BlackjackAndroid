package eskerud.hiof.blackjack;

import android.graphics.Bitmap;

/**
 * Created by Martin on 04.03.2015.
 */
public class Card {

    private int value;
    private String name;
    private String type;
    private Bitmap image;

    public Card(String type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString(){

        return this.type+ " " + this.name + " worth " + this.value + " points";

    }

}
