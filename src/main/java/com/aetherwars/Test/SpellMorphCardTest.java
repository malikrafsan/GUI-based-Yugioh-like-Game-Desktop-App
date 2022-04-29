package com.aetherwars.Test;

import com.aetherwars.model.SpellMorphCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpellMorphCardTest {
    SpellMorphCard sheepify = new SpellMorphCard(301, "Sheepify","Turns any being into a measly sheep, even you.", "card/image/sheepify.png", 2, 4);

    @Test
    void getType() {
        assertEquals(sheepify.getType().toString(), "SpellType.MORPH");
    }

    @Test
    void getName() {
        assertEquals(sheepify.getName(), "Sheepify");
    }

    @Test
    void getMana() {
        assertEquals(sheepify.getMana(), 4);
    }

    @Test
    void getTargetId() {
        assertEquals(sheepify.getTargetId(), 2);
    }

    @Test
    void preview() {
        assertEquals(sheepify.preview(), "MORPH");
    }

    @Test
    void displayInfo() {
    }
}