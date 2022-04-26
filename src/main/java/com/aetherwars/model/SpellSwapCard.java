package com.aetherwars.model;

public class SpellSwapCard extends SpellCard {
    private int duration;

    public SpellSwapCard() {
        super();
        this.duration = 0;
    }

    public SpellSwapCard(int id, String name, String description, String image_path, int duration, int mana) {
        super(id, name, description, image_path, mana, SpellType.SWAP);
        this.duration = duration;
    }

    @Override
    public String preview() {
        return "ATK <-> HP";
    }

    @Override
    public String toString() {
        return  "Spell Swap" +
                "\nName:" + this.name +
                "\nDescription:" + this.description +
                "\nImage:" + this.image_path +
                "\nMana:" + this.mana +
                "\nDuration:" + this.duration;
    }
}