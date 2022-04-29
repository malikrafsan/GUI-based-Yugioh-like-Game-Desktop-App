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
    static int firstMana = 1;

    /**
     * Constructor
     */
    public Player() {
        this.name = "Player " + no;
        this.health = 80;
        this.mana = firstMana;
        this.maxMana = firstMana;
        this.deck = new Deck();
        this.handCard = new HandCard();
        this.activeChars = new ActiveCharObserver();
        no++;
        firstMana--;
    }

    /**
     * Method to sync
     */
    public void sync() {
        setChanged();
        notifyObservers();
    }

    /**
     * Method when new round
     */
    public void newRound() {
        if (this.maxMana < 10) {
            this.maxMana = this.maxMana + 1;
        }
        this.activeChars.newRound();
        this.mana = this.maxMana;
        if(this.getHand().getSize() <5) {
            this.deck.draw();
            this.deck.pickCard();
        }
        this.sync();
    }

    /**
     * getter
     * @return int
     */
    public int getMaxMana() {
        return (this.maxMana);
    }

    /**
     * getter
     * @return Deck
     */
    public Deck getDeck() {
        return this.deck;
    }

    /**
     * getter
     * @return HandCard
     */
    public HandCard getHand() {
        return this.handCard;
    }

    /**
     * getter
     * @return ActiveCharObserver
     */
    public ActiveCharObserver getActiveChars() {
        return this.activeChars;
    }

    /**
     * getter
     * @return int
     */
    public int getMana() {
        return this.mana;
    }

    /**
     * Method to use mana
     * @param x
     */
    public void useMana(int x) {
        this.mana = this.mana - x;
        this.sync();
    }

    /**
     * Method to set clicked
     */
    public void onClick() {
        this.clicked = true;
        this.sync();
    }

    /**
     * Method to set clicked
     */
    public void offClick() {
        this.clicked = false;
        this.sync();
    }

    /**
     * Method to set hovered
     */
    public void onHover() {
        this.hovered = true;
        this.sync();
    }

    /**
     * Method to set hovered
     */
    public void offHover() {
        this.hovered = false;
        this.sync();
    }

    /**
     * getter
     * @return double
     */
    public double getHealth() {
        return this.health;
    }


    /**
     * Method to decrease health
     * @param x
     */
    public void minusHealth(double x) {
        this.health = this.health - x;
        this.sync();
    }
}
