package com.aetherwars.model;
import java.util.Observable;

public class ActiveCharObserver extends Observable {
    protected ActiveChar[] chars;

    public ActiveCharObserver() {
        this.chars = new ActiveChar[5];
        for (int i=0; i<5; i++) {
            this.chars[i] = null;
        }
    }

    public void addChar(CharacterCard card, int index) {
        this.chars[index] = new ActiveChar(card);
    }

    public void delChar(int index) {
        this.chars[index] = null;
    }
    
}
