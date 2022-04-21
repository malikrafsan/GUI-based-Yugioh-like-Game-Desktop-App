package com.aetherwars.model;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Observable;

class Deck extends Observable {
    List<Card> deck;
    int capacity;
    int size;
    
    public Deck() {
        this.deck = new ArrayList<Card>();
        this.capacity = 60;
        this.size = 0;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getSize() {
        return this.size;
    }

    public void addCard(Card card) {
        if (this.size < this.capacity) {
            this.deck.add(0, card);
            this.size++;
            this.setChanged();
            this.notifyObservers();
        }
    }

    public void addCard(List<Card> cardList) {
        for (Card card : cardList) {
            this.addCard(card);
        }
    }

    public List<Card> getThreeCard() {
        Collections.shuffle(this.deck);
        List<Card> threeCard = new ArrayList<Card>();

        if (this.size < 3) {
            threeCard = this.deck;
            this.size= 0;
            this.setChanged();
            this.notifyObservers();
        } else {
            for (int i = 0; i < 3; i++) {
                threeCard.add(this.deck.remove(0));
                this.size--;
            }
            this.setChanged();
            this.notifyObservers();
        }
        return threeCard;
    }

    // Just for debugging, deprecated soon
    public void getInfo() {
        for (Card card : this.deck) {
            System.out.println(card.getName());
        }
    }
}