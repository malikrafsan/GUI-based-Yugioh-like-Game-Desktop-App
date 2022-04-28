package com.aetherwars.model;
import com.aetherwars.interfaces.Hoverable;
import com.aetherwars.interfaces.IActiveCharGetter;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class ActiveChar implements IActiveCharGetter, Hoverable {
    private CharacterCard card;
    private List<ActiveSpellsPotion> spellsPotionList;
    private double attack;
    private double health;
    private boolean clicked;
    private boolean hovered;
    private boolean isCanAttack;
    private int exp;
    private int expUp;
    private int level;

    public ActiveChar() {
        this.card = new CharacterCard();
        this.attack = 1;
        this.health = 1;
        this.clicked = false;
        this.hovered = false;
        this.isCanAttack = true;
        this.exp = 0;
        this.expUp = 1;
        this.level = 1;
    }

    public ActiveChar(CharacterCard card) {
        this.card = card;
        this.attack = this.card.getAttack();
        this.health = this.card.getHealth();
        this.clicked = false;
        this.hovered = false;
        this.isCanAttack = true;
        this.exp = 0;
        this.expUp = 1;
        this.level = 1;
    }

    public void addAttackLvl() {
        this.attack = this.attack + this.card.getAttackUp();
    }

    public void addHealthLvl() {
        this.health = this.health + this.card.getHealthUp();
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

    public void newRound() {
        this.isCanAttack = true;
    }

    public void cannotAttack() {
        this.isCanAttack = false;
    }

    public int getHealthPotion() {
        int total = 0;
        for (int i = 0; i < this.spellsPotionList.size(); i++) {
            total += this.spellsPotionList.get(i).getHealthPotion();
        }
        return total;
    }

    public int getAttackPotion() {
        int total = 0;
        for (int i = 0; i < this.spellsPotionList.size(); i++) {
            total += this.spellsPotionList.get(i).getAttackPotion();
        }
        return total;
    }

    public boolean canAttack() { return (this.isCanAttack); }

    public double getAttack() {
        return (this.attack + this.getAttackPotion());
    }

    public double getHealth() {
        return (this.health + this.getHealthPotion());
    }

    public int getExp() {
        return (this.exp);
    }

    public int getExpUp() {
        return (this.expUp);
    }

    public int getLevel() { return (this.level); }

    public String getImagePath() {
        return (this.card.getImagePath());
    }

    public CharType getType() {
        return (this.card.getType());
    }

    public String getName() {
        return (this.card).getName();
    }

    public String getDesc() {
        return (this.card).getDesc();
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
