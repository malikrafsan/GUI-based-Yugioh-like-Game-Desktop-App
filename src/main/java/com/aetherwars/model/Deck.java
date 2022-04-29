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

    /**
     * @brief Method untuk melakukan kembalikan
     */
    public void pickCard() {
        this.publish("PICKCARD", new PickCardEvent(this.getThreeCard()));
    }

    /**
     * @param topic topic
     * @param event event yang bersesuaian
     */
    public void publish(String topic, IEvent event) {
        GameManager.getInstance().getEventBroker().sendEvent(topic, event);
    }

    /**
     * @return Mengembalikan capacity deck
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * @return Mengembalikan size deck sekarang
     */
    public int getSize() {
        return this.size;
    }

    /**
     * @return Mengembalikan threeCard
     */
    public List<Card> getThreeCard() {
        return this.threeCard;
    }

    /**
     * @brief Memilih satu kartu dari threeCard
     * @param idx index kartu
     * @return Mengembalikan kartu pilihan
     */
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

    /**
     * @param card Menambahkan kartu ke deck
     */
    public void addCard(Card card) {
        if (this.size < this.capacity) {
            this.deck.add(0, card);
            this.size++;
            this.setChanged();
            this.notifyObservers();
        }
    }

    /**
     * @param cardList list of kartu yang akan ditambahkan
     */
    public void addCard(List<Card> cardList) {
        for (Card card : cardList) {
            this.addCard(card);
        }
    }

    /**
     * @brief Method untuk melakukan drawing
     */
    public void draw() {
        this.threeCard = new ArrayList<>();
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

    /**
     * @brief Method untuk sinkronisasi
     */
    public void sync() {
        this.setChanged();
        this.notifyObservers();
    }
}