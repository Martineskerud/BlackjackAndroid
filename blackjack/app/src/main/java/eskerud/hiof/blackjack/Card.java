package eskerud.hiof.blackjack;

/**
 * Created by Martin on 04.03.2015.
 */
public class Card {

    private int value;
    private String name;
    private String type;


    public Card(String type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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
