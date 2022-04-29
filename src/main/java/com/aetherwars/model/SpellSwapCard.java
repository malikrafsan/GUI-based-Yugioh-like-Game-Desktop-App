package com.aetherwars.model;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * @return Mengembalikan durasi dari spell swap
     */
    public int getDuration() {
        return (this.duration);
    }

    /**
     * @return Preview untuk kartu spell swap
     */
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

    /**
     * @brief Method untuk display info yang dibutuhkan
     * @return Mengembalikan list of pair untuk setiap key dan value yang ditampilkan
     */
    @Override
    public List<Pair<String,String>> displayInfo() {
        List<Pair<String, String>> res = new ArrayList<>();
        res.add(new Pair<>("Mana", Integer.toString(this.mana)));
        res.add(new Pair<>("Duration", Integer.toString(this.duration)));
        return res;
    }
}