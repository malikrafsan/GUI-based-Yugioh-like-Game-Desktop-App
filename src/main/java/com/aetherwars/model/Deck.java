package com.aetherwars.model;
import com.aetherwars.controller.GameManager;
import com.aetherwars.event.PickCardEvent;
import com.aetherwars.interfaces.IEvent;
import com.aetherwars.interfaces.IPublisher;

import java.beans.EventHandler;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Observable;

public class Deck extends Observable implements IPublisher {
    private List<Card> deck;
    private int capacity;
    private int size;
    
    public Deck() {
        this.deck = new ArrayList<Card>();
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

    public void sync() {
        this.setChanged();
        this.notifyObservers();
    }
}