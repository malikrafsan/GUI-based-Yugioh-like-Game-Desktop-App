package com.aetherwars.model;

class SpellLevelCard extends SpellCard {
    private int levelUp;

    public SpellLevelCard() {
        super();
        this.levelUp = 0;
    }

    public SpellLevelCard(int id, String name, String description, String image_path, int mana, int levelUp) {
        super(id, name, description, image_path, mana, SpellType.LEVEL);
        this.levelUp = levelUp;
    }

    @Override
    public String toString() {
        return  "Spell Level" +
                "\nName:" + this.name +
                "\nDescription:" + this.description +
                "\nImage:" + this.image_path +
                "\nMana:" + this.mana +
                "\nLevel:" + this.levelUp;
    }
}