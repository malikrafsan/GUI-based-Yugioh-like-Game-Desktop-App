package com.aetherwars.model;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class SpellLevelCard extends SpellCard {
    private int levelUp;

    public SpellLevelCard() {
        super();
        this.levelUp = 0;
    }

    public SpellLevelCard(int id, String name, String description, String image_path, int mana, int levelUp) {
        super(id, name, description, image_path, mana, SpellType.LEVEL);
        this.levelUp = levelUp;
    }

    /**
     * @return Mengembalikan penambahan level dari spell level
     */
    public int getLevelUp() {
        return (this.levelUp);
    }

    /**
     * @return Preview untuk kartu spell level
     */
    @Override
    public String preview() {
        if (this.levelUp >= 0) {
            return "LVL +" + this.levelUp;
        } else {
            return "LVL " + this.levelUp;
        }
    }

    @Override
    public String toString() {
        return "Spell Level" +
                "\nName:" + this.name +
                "\nDescription:" + this.description +
                "\nImage:" + this.image_path +
                "\nMana:" + this.mana +
                "\nLevel:" + this.levelUp;
    }

    /**
     * @brief Method untuk display info yang dibutuhkan
     * @return Mengembalikan list of pair untuk setiap key dan value yang ditampilkan
     */
    @Override
    public List<Pair<String,String>> displayInfo() {
        List<Pair<String,String>> res = new ArrayList<>();
        res.add(new Pair<>("Mana", Integer.toString(this.mana)));
        if (this.levelUp >= 0){
            res.add(new Pair<>("Level Up", "+" + this.levelUp));
        } else {
            res.add(new Pair<>("Level Up", Integer.toString(this.levelUp)));
        }
        return res;
    }
}