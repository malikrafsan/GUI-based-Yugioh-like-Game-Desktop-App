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
        this.sync();
    }

    public void delChar(int index) {
        this.chars[index] = null;
        this.sync();
    }

    public ActiveChar getActChar(int idx_board) {
        return this.chars[idx_board];
    }

    public void onClick(int index) {
        this.chars[index].onClick();
        this.sync();
    }

    public void offClick(int index) {
        this.chars[index].offClick();
        this.sync();
    }
    
    public void onHover(int index) {
        this.chars[index].onHover();
        this.sync();
    }

    public void offHover(int index) {
        this.chars[index].offHover();
        this.sync();
    }

    public void addExp(int index, int exp) {
        this.chars[index].addExp(exp);
        this.sync();
    }

    public void update() {
        for(int i=0; i<5; i++) {
            if(this.chars[i] != null && this.chars[i].getHealth() <= 0) {
                delChar(i);
            }
        }
        this.sync();
    }

    public ActiveChar[] getChars() {
        return (this.chars);
    }

    public void newRound() {
        for (int i=0; i<5; i++) {
            if (this.chars[i] != null) {
                this.chars[i].newRound();
            }
        }
        this.sync();
    }

    public void addSpell(int index, SpellCard card) {
        switch (card.getType()) {
            case POTION:
                this.chars[index].addSpellPotion((SpellPotionCard) card);
                break;
            case LEVEL:
                this.chars[index].levelUpSpell((SpellLevelCard) card);
                break;
            case SWAP:
                this.chars[index].swapSpell((SpellSwapCard) card);
                break;
        }
        this.sync();
    }

    public void healAll(int index) {
        this.chars[index].healAll();
        this.sync();
    }

    public void receiveDamage(int index, double damage) {
        this.chars[index].receiveDamage(damage);
        this.sync();
    }

    public void cannotAttack(int index) {
        this.chars[index].cannotAttack();
        this.sync();
    }

    public boolean canAttack(int index) { return (this.chars[index].canAttack()); }

    public void sync() {
        this.setChanged();
        this.notifyObservers();
    }

    public boolean empty() {
        for(int i=0; i<5; i++) {
            if(this.chars[i] != null) {
                return false;
            }
        }
        return true;
    }
}
