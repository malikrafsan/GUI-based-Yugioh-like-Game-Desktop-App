package com.aetherwars.Test;

import com.aetherwars.model.SpellPotionCard;
import com.aetherwars.model.SpellType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpellPotionCardTest {
    SpellPotionCard deathlyMagic = new SpellPotionCard(101, "Deathly Magic", "The magic that is deadly", "card/image/spell/potion/Deathly Magic.png", 2, 2, 2, 2);

    @Test
    void getNameTest() {
        assertEquals(deathlyMagic.getName(), "Deathly Magic");
    }

    @Test
    void getTypeTest() {
        assertEquals(deathlyMagic.getType().toString(), "POTION");
    }

    @Test
    void getManaTest() {
        assertEquals(deathlyMagic.getMana(), 2);
    }

    @Test
    void getAttackTest() {
        assertEquals(deathlyMagic.getAttack(), 2);
    }

    @Test
    void getHpTest() {
        assertEquals(deathlyMagic.getHp(), 2);
    }

    @Test
    void getDurationTest() {
        assertEquals(deathlyMagic.getDuration(), 2);
    }

    @Test
    void previewTest() {
        String text = "ATK +2/HP +2 (2)";
        assertEquals(deathlyMagic.preview(), text);
    }
}