package com.aetherwars.Test;

import com.aetherwars.model.ActiveSpellsPotion;
import org.junit.jupiter.api.Test;
import com.aetherwars.model.SpellPotionCard;

import static org.junit.jupiter.api.Assertions.*;

class ActiveSpellsPotionTest {

    SpellPotionCard deathlyMagic = new SpellPotionCard(101, "Deathly Magic", "The magic that is deadly", "card/image/spell/potion/Deathly Magic.png", 1, 2, 2, 2);
    ActiveSpellsPotion test = new ActiveSpellsPotion(deathlyMagic);

    @Test
    void swap() {
        test.swap();
        assertEquals(test.getAttackPotion(), 2);
        assertEquals(test.getHealthPotion(), 1);
    }

    @Test
    void healAll() {
        test.receiveDamage(2);
        test.healAll();
        assertEquals(test.getHealthPotion(), 2);
    }

    @Test
    void getAttackPotion() {
        assertEquals(test.getAttackPotion(), 1);
    }

    @Test
    void getHealthPotion() {
        assertEquals(test.getHealthPotion(), 2);
    }

    @Test
    void getDuration() {
        assertEquals(test.getDuration(), 2);
    }

    @Test
    void receiveDamage() {
        test.receiveDamage(1);
        assertEquals(test.getHealthPotion(), 1);
    }

    @Test
    void newRound() {
        test.newRound();
        assertEquals(test.getDuration(), 1);
    }

    @Test
    void display() {
    }
}