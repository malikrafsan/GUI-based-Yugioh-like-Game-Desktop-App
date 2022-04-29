package com.aetherwars.Test;

import com.aetherwars.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player steve = new Player();

    @Test
    void useMana() {
        assertEquals(steve.getMana(), 1);
        steve.useMana(1);
        assertEquals(steve.getMana(), 0);
    }

    @Test
    void getHealth() {
        assertEquals(steve.getHealth(), 80);
    }

    @Test
    void minusHealth() {
        steve.minusHealth(5);
        assertEquals(steve.getHealth(), 75);
    }
}