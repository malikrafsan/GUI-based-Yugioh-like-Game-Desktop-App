package com.aetherwars.model;

import javafx.util.Pair;

import java.util.List;

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

    /**
     * @return Mengembalikan tipe spell
     */
    public SpellType getType() {
        return (this.type);
    }

    abstract public String preview();
    abstract public String toString();
    abstract public List<Pair<String,String>> displayInfo();
}