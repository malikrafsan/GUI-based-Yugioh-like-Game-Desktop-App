package com.aetherwars.model;
import java.util.Observable;

public class ActiveChar extends Observable{
    protected CharacterCard card;
    protected int attackPlus;
    protected int healthPlus;

    public ActiveChar() {
        this.card = new CharacterCard();
        this.attackPlus = 0;
        this.healthPlus = 0;
    }

    public ActiveChar(CharacterCard card) {
        this.card = card;
        this.attackPlus = 0;
        this.healthPlus = 0;
    }

    public void addAttack(int plus) {
        this.attackPlus = this.attackPlus + plus;
    }

    public void addHealth(int plus) {
        this.healthPlus = this.healthPlus + plus;
    }
}
