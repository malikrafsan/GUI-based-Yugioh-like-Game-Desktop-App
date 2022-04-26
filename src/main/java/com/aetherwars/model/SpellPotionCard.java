package com.aetherwars.model;

public class SpellPotionCard extends SpellCard {
    private int attack;
    private int hp;
    private int duration;

    public SpellPotionCard() {
        super();
        this.attack = 0;
        this.hp = 0;
        this.duration = 0;
    }

    public SpellPotionCard(int id, String name, String description, String image_path, int attack, int hp, int mana, int duration) {
        super(id, name, description, image_path, mana, SpellType.POTION);
        this.attack = attack;
        this.hp = hp;
        this.duration = duration;
    }

    @Override
    public String preview() {
        return "ATK " + this.attack + "/HP " + this.hp + "(" + this.duration + ")";
    }

    @Override
    public String toString() {
        return  "Spell Potion" +
                "\nName:" + this.name +
                "\nDescription:" + this.description +
                "\nImage:" + this.image_path +
                "\nMana:" + this.mana +
                "\nAttack:" + this.attack +
                "\nHealth:" + this.hp +
                "\nDuration:" + this.duration;
    }
}