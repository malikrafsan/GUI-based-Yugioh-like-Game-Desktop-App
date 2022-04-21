package com.aetherwars.model;

public class SpellMorphCard extends SpellCard {
    private int targetid;

    public SpellMorphCard() {
        super();
        this.targetid = 0;
    }

    public SpellMorphCard(int id, String name, String description, String image_path, int targetid, int mana) {
        super(id, name, description, image_path, mana, SpellType.MORPH);
        this.targetid = targetid;
    }

    @Override
    public String toString() {
        return  "Spell Morph" +
                "\nName:" + this.name +
                "\nDescription:" + this.description +
                "\nImage:" + this.image_path +
                "\nMana:" + this.mana +
                "\nTarget:" + this.targetid;
    }
}