package com.aetherwars.model;

public abstract class SpellCard extends Card {
    protected SpellType type;

    public SpellCard() {
        super();
        this.type = SpellType.POTION;
    }

    public SpellCard(int id, String name, String description, String image_path, int mana, SpellType type) {
        super(id, name, description, image_path, mana);
        this.type = type;
    }

    abstract public String toString();
}