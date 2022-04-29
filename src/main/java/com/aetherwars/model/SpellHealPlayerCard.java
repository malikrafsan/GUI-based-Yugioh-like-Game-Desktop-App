package com.aetherwars.model;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class SpellHealPlayerCard extends SpellCard {
    private int heal;

    public SpellHealPlayerCard() {
        super();
        this.heal = 0;
    }

    public SpellHealPlayerCard(int id, String name, String description, String image_path, int mana, int heal) {
        super(id, name, description, image_path, mana, SpellType.HEALPLAYER);
        this.heal = heal;
    }

    @Override
    public String preview() {
        return "HP +" + heal;
    }

    @Override
    public String toString() {
        return  "Spell Swap" +
                "\nName:" + this.name +
                "\nDescription:" + this.description +
                "\nImage:" + this.image_path +
                "\nMana:" + this.mana +
                "\nHealing:" + this.heal;
    }

    @Override
    public List<Pair<String, String>> displayInfo() {
        List<Pair<String, String>> res = new ArrayList<>();
        res.add(new Pair<>("Mana", Integer.toString(this.mana)));
        res.add(new Pair<>("Healing", Integer.toString(this.heal)));
        return res;
    }
}
