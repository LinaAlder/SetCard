package com.company;

public class Card {
    int fill, count, shape, color;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return fill == card.fill &&
                count == card.count &&
                shape == card.shape &&
                color == card.color;
    }

    @Override
    public String toString() {
        return "Card: [ " + fill + ", " + count +
                ", " + shape + ", " + color + " ]";
    }
    public int getParameter(int p1, int p2){
        int i;
        if(p1 == p2) i = p1;
        else i = 6 - p1 - p2;
        return i;
    }
    public Card getThird(Card c){
        Card thirdCard = new Card();
        thirdCard.color = getParameter(this.color, c.color);
        thirdCard.count = getParameter(this.count, c.count);
        thirdCard.fill = getParameter(this.fill, c.fill);
        thirdCard.shape = getParameter(this.shape, c.shape);
        return thirdCard;
    }
}
