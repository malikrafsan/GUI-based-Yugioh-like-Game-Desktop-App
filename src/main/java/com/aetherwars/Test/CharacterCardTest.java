package com.aetherwars.Test;

import com.aetherwars.model.CharType;
import com.aetherwars.model.CharacterCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterCardTest {
    CharacterCard card = new CharacterCard(8, "Magma Cube", CharType.NETHER, "A magma cube is a hostile mob found in the Nether. A magma cube behaves similarly to a slime, but has higher jumps and more powerful hits.", "card/image/character/Magma Cube.png", 3, 3, 3, 5, 5);

    @Test
    void getName() {
        assertEquals(card.getName(), "Magma Cube");
    }

    @Test
    void getMana() {
        assertEquals(card.getMana(), 3);
    }

    @Test
    void getAttack() {
        assertEquals(card.getAttack(), 3);
    }

    @Test
    void getHealth() {
        assertEquals(card.getHealth(), 3);
    }

    @Test
    void getAttackUp() {
        assertEquals(card.getAttackUp(), 5);
    }

    @Test
    void getHealthUp() {
        assertEquals(card.getHealthUp(), 5);
    }

    @Test
    void getType() {
        assertEquals(card.getType().toString(), "NETHER");
    }

    @Test
    void preview() {
        assertEquals(card.preview(), "ATK 3/HP 3");
    }

    @Test
    void displayInfo() {
    }
}