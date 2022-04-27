package com.aetherwars.model;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

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
    public String preview() {
        return "MORPH";
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

    @Override
    public List<Pair<String,String>> displayInfo() {
        List<Pair<String, String>> res = new ArrayList<>();
        res.add(new Pair<>("Mana", Integer.toString(this.mana)));
        return res;
    }
}