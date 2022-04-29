package com.aetherwars.Test;

import com.aetherwars.model.SpellSwapCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpellSwapCardTest {
    SpellSwapCard catFood = new SpellSwapCard(201, "Cat Food", "Disclaimer: includes catnip", "card/image/catfood.png", 3, 3);

    @Test
    void getType() {
        assertEquals(catFood.getType().toString(), "SWAP");
    }

    @Test
    void getName() {
        assertEquals(catFood.getName(), "Cat Food");
    }

    @Test
    void getMana() {
        assertEquals(catFood.getMana(), 3);
    }

    @Test
    void getDuration() {
        assertEquals(catFood.getDuration(), 3);
    }

    @Test
    void preview() {
        assertEquals(catFood.preview(), "ATK <-> HP");
    }

    @Test
    void displayInfo() {
    }
}