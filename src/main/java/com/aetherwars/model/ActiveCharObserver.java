package com.aetherwars.model;
import java.util.Observable;

public class ActiveCharObserver extends Observable {
    private ActiveChar[] chars;

    public ActiveCharObserver() {
        this.chars = new ActiveChar[5];
        for (int i=0; i<5; i++) {
            this.chars[i] = null;
        }
    }

    public void addChar(CharacterCard card, int index) {
        this.chars[index] = new ActiveChar(card);
        this.notifyObservers();
    }

    public void delChar(int index) {
        this.chars[index] = null;
        this.notifyObservers();
    }

    public void onClick(int index) {
        this.chars[index].onClick();
        this.notifyObservers();
    }

    public void offClick(int index) {
        this.chars[index].offClick();
        this.notifyObservers();
    }
    
    public void onHover(int index) {
        this.chars[index].onHover();
        this.notifyObservers();
    }

    public void offHover(int index) {
        this.chars[index].offHover();
        this.notifyObservers();
    }

    public void addExp(int index, int exp) {
        this.chars[index].addExp(exp);
        this.notifyObservers();
    }
}
