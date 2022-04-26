package com.aetherwars.model;
import com.aetherwars.interfaces.IActiveCharGetter;
import java.util.Observable;

public class ActiveChar extends Observable implements IActiveCharGetter{
    private CharacterCard card;
    private int attackPlus;
    private int healthPlus;
    private boolean clicked;
    private boolean hovered;
    private int exp;
    private int expUp;
    private int level;

    public ActiveChar() {
        this.card = new CharacterCard();
        this.attackPlus = 0;
        this.healthPlus = 0;
        this.clicked = false;
        this.hovered = false;
        this.exp = 0;
        this.expUp = 1;
        this.level = 1;
    }

    public ActiveChar(CharacterCard card) {
        this.card = card;
        this.attackPlus = 0;
        this.healthPlus = 0;
        this.clicked = false;
        this.hovered = false;
        this.exp = 0;
        this.expUp = 1;
        this.level = 1;
    }

    public void addAttack() {
        this.attackPlus = this.attackPlus + this.card.getAttackUp();
    }

    public void addHealth() {
        this.healthPlus = this.healthPlus + this.card.getHealthUp();
    }

    public void onClick() {
        this.clicked = true;
    }

    public void offClick() {
        this.clicked = false;
    }

    public void onHover() {
        this.hovered = true;
    }

    public void offHover() {
        this.hovered = false;
    }

    public void addExp(int exp) {
        if (this.level < 10) {
            this.exp = this.exp + exp;

            if (this.exp > this.expUp) {
                this.levelUp();
            }
        }
    }

    public void levelUp() {
        this.level = this.level + 1;
        this.exp = this.exp - this.expUp;
        this.expUp = this.expUp + 2;
        this.addAttack();
        this.addHealth();
        
        if (this.level == 10) {
            this.exp = 0;
            this.expUp = 0;
        }
    }

    public int getAttack() {
        return (this.card.getAttack() + this.attackPlus);
    }
    public int getHealth() {
        return (this.card.getHealth() + this.healthPlus);
    }
    public int getExp() {
        return (this.exp);
    }
    public int getExpUp() {
        return (this.expUp);
    }
    public int getLevel() {
        return (this.level);
    }
    public String getImagePath() {
        return (this.card.getImagePath());
    }
}
