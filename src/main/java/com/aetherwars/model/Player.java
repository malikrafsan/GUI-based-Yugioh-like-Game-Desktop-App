package com.aetherwars.model;
import java.util.Observable;

public class Player extends Observable{
    private String name;
    private int health;
    private int mana;
    private Deck deck;
    private HandCard handCard;
    private ActiveCharObserver activeChars;
    private boolean clicked;
    private boolean hovered;
    
    static int no = 1; 

    public Player() {
        this.name = "Player " + no;
        this.health = 80;
        this.mana = 0;
        this.deck = new Deck();
        this.handCard = new HandCard();
        this.activeChars = new ActiveChar();
        no++;
    }

    public void newTurn() {
        if (this.mana < 10) {
            this.mana = this.mana + 1;
        }
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
}