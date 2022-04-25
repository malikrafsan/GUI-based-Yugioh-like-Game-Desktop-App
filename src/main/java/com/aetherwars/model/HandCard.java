package com.aetherwars.model;
import java.util.Observable;

class HandCard extends Observable {
    private Card[] handCard;
    private int capacity;
    private int size;

    public HandCard() {
        this.capacity = 5;
        this.size = 0;
        this.handCard = new Card[this.capacity];
        for (int i=0; i<this.capacity; i++){
            this.handCard[i] = null;
        }
    }

    public void addCard(Card card, int idx) {
        this.handCard[idx] = card;
        this.size++;
        this.setChanged();
        this.notifyObservers();
    }

    public Card getCard(int idx) {
        Card res = this.handCard[idx];
        this.handCard[idx] = null;
        this.size--;
        this.setChanged();
        this.notifyObservers();
        return res;
    }

    // Just for debugging, deprecated soon
    public void getInfo() {
        for (int i=0; i<5; i++){
            if (this.handCard[i] == null){
                System.out.println(i + " Empty");
            } else {
                System.out.println(i + " " + this.handCard[i].getName());
            }
        }
    }
}