package com.aetherwars.Test;

import com.aetherwars.model.SpellLevelCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpellLevelCardTest {
    SpellLevelCard levelplus = new SpellLevelCard(201, "Level Plus", "Increases your level by 1", "card/image/spell/level/Level Plus.png", 2, 1);

    @Test
    void getType() {
        assertEquals(levelplus.getType().toString(), "LEVEL");
    }

    @Test
    void getName() {
        assertEquals(levelplus.getName(), "Level Plus");
    }

    @Test
    void getMana() {
        assertEquals(levelplus.getMana(), 2);
    }

    @Test
    void getLevelUp() {
        assertEquals(levelplus.getLevelUp(), 1);
    }

    @Test
    void preview() {
        assertEquals(levelplus.preview(), "LVL +1");
    }
}