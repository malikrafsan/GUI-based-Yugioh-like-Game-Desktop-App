package com.aetherwars.model;
import com.aetherwars.interfaces.IActiveCharGetter;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class ActiveChar extends Observable implements IActiveCharGetter, Hoverable{
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

    public String getName() {
        return (this.card).getName();
    }

    public String getDesc() {
        return (this.card).getDesc();
    }

    public void addAttack(int attack) {
        this.attackPlus = this.attackPlus + attack;
    }

    public void addHealth(int health) {
        this.healthPlus = this.healthPlus + health;
    }

    public void addAttackLvl() {
        this.attackPlus = this.attackPlus + this.card.getAttackUp();
    }

    public void addHealthLvl() {
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
        if (this.level < 10) {
            this.level = this.level + 1;
            this.exp = this.exp - this.expUp;
            this.expUp = this.expUp + 2;
            this.addAttackLvl();
            this.addHealthLvl();
            
            if (this.level == 10) {
                this.exp = 0;
                this.expUp = 0;
            }
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

    public CharType getType() {
        return (this.card.getType());
    }

    @Override
    public List<Pair<String,String>> displayInfo() {
        List<Pair<String,String>> res = new ArrayList<>();
        List<Pair<String,String>> temp = this.card.displayInfo();
        for (Pair<String,String> p: temp) {
            if (p.getKey().equals("Attack")) {
                if (this.attackPlus != 0) {
                    res.add(new Pair<>("Attack", p.getValue() + " (+" + this.attackPlus + ")"));
                } else {
                    res.add(p);
                }
            } else if (p.getKey().equals("Health")) {
                if (this.attackPlus != 0) {
                    res.add(new Pair<>("Health", p.getValue() + " (+" + this.healthPlus + ")"));
                } else {
                    res.add(p);
                }
            } else {
                res.add(p);
            }
        }
        return res;
    }
}
