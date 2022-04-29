package com.aetherwars.model;
import com.aetherwars.controller.GameManager;
import com.aetherwars.event.PickCardEvent;
import com.aetherwars.interfaces.IEvent;
import com.aetherwars.interfaces.IPublisher;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Observable;

public class Deck extends Observable implements IPublisher {
    private List<Card> deck;
    private List<Card> threeCard;
    private int capacity;
    private int size;
    
    public Deck() {
        this.deck = new ArrayList<>();
        this.threeCard = new ArrayList<>();
        this.capacity = 60;
        this.size = 0;
    }

    public void pickCard() {
        this.publish("PICKCARD", new PickCardEvent(this.getThreeCard()));
    }

    public void publish(String topic, IEvent event) {
        GameManager.getInstance().getEventBroker().sendEvent(topic, event);
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getSize() {
        return this.size;
    }

    public List<Card> getThreeCard() {
        return this.threeCard;
    }

    public Card select(int idx) {
        // idx pasti 0, 1, atau 2
        Card temp = this.threeCard.remove(idx);
        for (Card card: this.threeCard) {
            addCard(card);
        }
        this.threeCard = new ArrayList<>();
        sync();
        return temp;
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

    public void draw() {
        Collections.shuffle(this.deck);
        if (this.size < 3) {
            this.threeCard = this.deck;
            this.size= 0;
        } else {
            for (int i = 0; i < 3; i++) {
                this.threeCard.add(this.deck.remove(0));
                this.size--;
            }
        }
        sync();
    }

    public void sync() {
        this.setChanged();
        this.notifyObservers();
    }
}