package com.aetherwars.model;
import java.util.Observable;

public class Player extends Observable{
    protected String name;
    protected int health;
    protected int mana;
    protected Deck deck;
    protected HandCard handCard;
    protected ActiveCharObserver activeChars;
    
    static int no = 1;
}
