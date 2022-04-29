package com.aetherwars.Test;

import com.aetherwars.model.SpellHealPlayerCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpellHealPlayerCardTest {
    SpellHealPlayerCard card = new SpellHealPlayerCard(502, "Ngewibu", "Kamu bisa mendapatkan healing sebesar 20 poin dengan ngewibu sepuasnyaa", "card/image/spell/potion/Mother's Prayer.png", 5, 20);

    @Test
    void getType() {
        assertEquals(card.getType().toString(), "HEALPLAYER");
    }

    @Test
    void getName() {
        assertEquals(card.getName(), "Ngewibu");
    }

    @Test
    void getMana() {
        assertEquals(card.getMana(), 5);
    }

    @Test
    void preview() {
        assertEquals(card.preview(), "HP +20");
    }
}