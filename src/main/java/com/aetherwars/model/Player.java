package com.aetherwars.model;
import java.util.Observable;


public class Player extends Observable{
    private String name;
    private double health;
    private int mana;
    private int maxMana;
    private Deck deck;
    private HandCard handCard;
    private ActiveCharObserver activeChars;
    private boolean clicked;
    private boolean hovered;
    
    static int no = 1; 

    public Player() {
        this.name = "Player " + no;
        this.health = 80;
        this.mana = 1;
        this.maxMana = 1;
        this.deck = new Deck();
        this.handCard = new HandCard();
        this.activeChars = new ActiveCharObserver();
        no++;
    }

    public void sync() {
        setChanged();
        notifyObservers();
    }

    public void newRound() {
        if (this.maxMana < 10) {
            this.maxMana = this.maxMana + 1;
            this.activeChars.newRound();
        }
        this.mana = this.maxMana;
        if(this.getHand().getSize() <5) {
            this.deck.draw();
            this.deck.pickCard();
        }
    }

    public int getMaxMana() {
        return (this.maxMana);
    }

    public Deck getDeck() {
        return this.deck;
    }

    public HandCard getHand() {
        return this.handCard;
    }

    public ActiveCharObserver getActiveChars() {
        return this.activeChars;
    }

    public int getMana() {
        return this.mana;
    }

    public void useMana(int x) {
        this.mana = this.mana - x;
    }

    public void receiveDamage(double damage) {
        this.health = this.health - damage;
    }

    public void onClick() {
        this.clicked = true;
        this.notifyObservers();
    }

    public void offClick() {
        this.clicked = false;
        this.notifyObservers();
    }

    public void onHover() {
        this.hovered = true;
        this.notifyObservers();
    }

    public void offHover() {
        this.hovered = false;
        this.notifyObservers();
    }

    public double getHealth() {
        return this.health;
    }

    public void minusHealth(double x) {
        this.health = this.health - x;
    }
}
