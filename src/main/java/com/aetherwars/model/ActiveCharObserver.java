package com.aetherwars.model;
import com.aetherwars.interfaces.IActiveCharObserverGetter;
import java.util.Observable;

public class ActiveCharObserver extends Observable implements IActiveCharObserverGetter{
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

    public ActiveChar getActChar(int idx_board) {
        return this.chars[idx_board];
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

    public void update() {
        for(int i=0; i<5; i++) {
            if(this.chars[i] != null && this.chars[i].getHealth() <= 0) {
                delChar(i);
            }
        }
    }

    public ActiveChar[] getChars() {
        return (this.chars);
    }

    public void sync() {
        this.setChanged();
        this.notifyObservers();
    }
}
