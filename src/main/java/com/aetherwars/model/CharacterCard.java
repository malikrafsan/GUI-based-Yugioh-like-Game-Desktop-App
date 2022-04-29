package com.aetherwars.model;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * @return Mengembalikan attack
     */
    public int getAttack() { return (this.attack);}

    /**
     * @return Mengembalikan health
     */
    public int getHealth() { return (this.health);}

    /**
     * @return Mengembalikan attackup
     */
    public int getAttackUp() { return (this.attackUp);}

    /**
     * @return Mengembalikan healthup
     */
    public int getHealthUp() { return (this.healthUp);}

    /**
     * @return Mengembalikan path image
     */
    public String getImagePath() { return (this.image_path);}

    /**
     * @return Mengembalikan tipe sebuah kartu karakter
     */
    public CharType getType() {
        return type;
    }

    /**
     * @return Preview untuk kartu spell level
     */
    @Override
    public String preview() {
        return "ATK " + this.attack + "/HP " + this.health;
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

    /**
     * @brief Method untuk display info yang dibutuhkan
     * @return Mengembalikan list of pair untuk setiap key dan value yang ditampilkan
     */
    @Override
    public List<Pair<String,String>> displayInfo() {
        List<Pair<String,String>> res = new ArrayList<>();
        res.add(new Pair<>("Type", this.type.toString()));
        res.add(new Pair<>("Attack", Integer.toString(this.attack)));
        res.add(new Pair<>("Health", Integer.toString(this.health)));
        res.add(new Pair<>("AttackUp", Integer.toString(this.attackUp)));
        res.add(new Pair<>("HealthUp", Integer.toString(this.healthUp)));
        return res;
    }
}