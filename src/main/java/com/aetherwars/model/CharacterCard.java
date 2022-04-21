package com.aetherwars.model;

public class CharacterCard extends Card {
    protected CharType type;
    protected int attack;
    protected int health;
    protected int attackUp;
    protected int healthUp;

    public CharacterCard() {
        super();
        this.type = CharType.OVERWORLD;
        this.attack = 0;
        this.health = 0;
        this.attackUp = 0;
        this.healthUp = 0;
    }

    public CharacterCard(int id, String name, CharType type, String description, String image_path, int attack, int health, int mana, int attackUp, int healthUp) {
        super(id, name, description, image_path, mana);
        this.type = type;
        this.attack = attack;
        this.health = health;
        this.attackUp = attackUp;
        this.healthUp = healthUp;
    }

    @Override
    public String toString() {
        return "Character Card" +
                "\nName:" + name +
                "\nType:" + type +
                "\nAttack:" + attack +
                "\nHealth:" + health +
                "\nAttackUp:" + attackUp +
                "\nHealthUp:" + healthUp;
    }
}