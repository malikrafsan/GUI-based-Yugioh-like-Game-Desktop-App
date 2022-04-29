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
    private double maxHealth;
    private boolean clicked;
    private boolean hovered;
    private boolean isCanAttack;
    private int exp;
    private int expUp;
    private int level;
    private int swap;

    public ActiveChar() {
        this.card = new CharacterCard();
        this.attack = 1;
        this.health = 1;
        this.maxHealth = 1;
        this.clicked = false;
        this.hovered = false;
        this.isCanAttack = true;
        this.exp = 0;
        this.expUp = 1;
        this.level = 1;
        this.spellsPotionList = new ArrayList<>();
        this.swap = 0;
    }

    public ActiveChar(CharacterCard card) {
        this.card = card;
        this.attack = this.card.getAttack();
        this.health = this.card.getHealth();
        this.maxHealth = this.card.getHealth();
        this.clicked = false;
        this.hovered = false;
        this.isCanAttack = true;
        this.exp = 0;
        this.expUp = 1;
        this.level = 1;
        this.spellsPotionList = new ArrayList<>();
        this.swap = 0;
    }

    public void addAttackLvl() {
        this.attack = this.attack + this.card.getAttackUp();
    }

    public void addHealthLvl() {
        this.maxHealth = this.maxHealth + this.card.getHealthUp();
        this.healAll();
    }

    public void minusAttackLvl() {
        this.attack = this.attack - this.card.getAttackUp();
    }

    public void minusHealthLvl() {
        this.maxHealth = this.maxHealth - this.card.getHealthUp();
        if (this.health > this.maxHealth) {
            this.health = this.maxHealth;
        }
    }

    public void addSpellPotion(SpellPotionCard card) {
        this.spellsPotionList.add(new ActiveSpellsPotion(card));
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

            if (this.exp >= this.expUp) {
                this.levelUp();
            }
        }
    }

    public void healAll() {
        int i = 0;
        this.health = this.maxHealth;
        while (i < this.spellsPotionList.size()) {
            this.spellsPotionList.get(i).healAll();
            i++;
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
        else {
            this.exp = this.exp + 19;
        }
    }

    public void levelDown() {
        if (this.level > 1) {
            this.level = this.level - 1;
            this.expUp = this.expUp - 2;
            this.minusAttackLvl();
            this.minusHealthLvl();
        }
    }

    public void levelUpSpell(SpellLevelCard card) {
        if (card.getLevelUp() > 0) {
            for (int i = 0; i < card.getLevelUp(); i++) {
                this.levelUp();
            }
            this.exp = 0;
        }
        else if (card.getLevelUp() < 0) {
            for (int i = 0; i < card.getLevelUp(); i++) {
                this.levelDown();
            }
            this.exp = 0;
        }
    }

    public void swapSpell (SpellSwapCard card) {
        int before = this.swap;
        this.swap = this.swap + card.getDuration();
        if (before == 0 && this.swap > 0) {
            this.swap();
        }
    }

    public void swap() {
        double temp;
        int i = 0;

        temp = this.attack;
        this.attack = this.health;
        this.health = temp;

        while (i < this.spellsPotionList.size()) {
            this.spellsPotionList.get(i).swap();
            i++;
        }
    }

    public void receiveDamage(double damage) {
        double damageLeft = damage;
        int i = this.spellsPotionList.size() - 1;

        while (i > -1 && damageLeft > 0) {
            if (this.spellsPotionList.get(i).getHealthPotion() > 0) {
                if (this.spellsPotionList.get(i).getHealthPotion() >= damageLeft) {
                    this.spellsPotionList.get(i).receiveDamage(damageLeft);
                }
                else {
                    this.spellsPotionList.get(i).receiveDamage(this.spellsPotionList.get(i).getHealthPotion());
                    damageLeft = damageLeft - this.spellsPotionList.get(i).getHealthPotion();
                }
            }

            i--;
        }

        if (damageLeft > 0) {
            if (damageLeft <= this.getBaseHealth()){
                this.health = this.health - damageLeft;
            }
            else {
                this.health = 0;
            }
        }
    }

    public void addSpell(SpellCard card) {
        switch (card.getType()) {
            case POTION:
                addSpellPotion((SpellPotionCard) card);
                break;
            case LEVEL:
                levelUpSpell((SpellLevelCard) card);
                break;
            case SWAP:
                swapSpell((SpellSwapCard) card);
                break;
        }
    }


    public void newRound() {
        int before = this.swap;
        this.isCanAttack = true;
        int i = 0;
        while (i < this.spellsPotionList.size()) {
            this.spellsPotionList.get(i).newRound();
            if (this.spellsPotionList.get(i).getDuration() == 0) {
                this.spellsPotionList.remove(i);
            } else {
                i++;
            }
        }
        this.swap = this.swap - 1;
        if (this.swap == 0 && before > 0) {
            this.swap();
        }
    }

    public void cannotAttack() {
        this.isCanAttack = false;
    }

    public double getHealthPotion() {
        double total = 0.0;
        int i = 0;
        while (i < this.spellsPotionList.size()) {
            total += this.spellsPotionList.get(i).getHealthPotion();
            i++;
        }
        return total;
    }

    public double getAttackPotion() {
        double total = 0.0;
        int i = 0;
        while (i < this.spellsPotionList.size()) {
            total += this.spellsPotionList.get(i).getAttackPotion();
            i++;
        }
        return total;
    }

    public void displayActiveSpells() {
        if (this.spellsPotionList.size() == 0) {
            System.out.println("Kosong");
        }
        for (ActiveSpellsPotion potion: this.spellsPotionList) {
            potion.display();
        }
    }

    public double getBaseHealth() { return (this.card.getHealth());}

    public double getBaseAttack() { return (this.card.getAttack());}

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
                if (Double.compare(this.getAttackPotion(), 0.0) != 0) {
                    res.add(new Pair<>("Attack", p.getValue() + " (+" + this.getAttackPotion() + ")"));
                } else {
                    res.add(p);
                }
            } else if (p.getKey().equals("Health")) {
                if (Double.compare(this.getHealthPotion(), 0.0) != 0) {
                    res.add(new Pair<>("Health", p.getValue() + " (+" + this.getHealthPotion() + ")"));
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
