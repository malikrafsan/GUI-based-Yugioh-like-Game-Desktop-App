package com.aetherwars.Test;

import com.aetherwars.model.*;
import com.aetherwars.controller.GameManager;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player steve = new Player();

    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // DICOBA SATU SATU KARENA KELAS Player MENGGUNAKAN static
    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    @Test
    void getMaxMana() {
        assertEquals(steve.getMaxMana(), 1);
    }

    @Test
    void getMana() {
        assertEquals(steve.getMana(), 1);
    }

    @Test
    void useMana() {
        steve.useMana(1);
        assertEquals(steve.getMana(), 0);
    }

    @Test
    void getHealth() {
        assertEquals(steve.getHealth(), 80);
    }

    @Test
    void minusHealth() {
        steve.minusHealth(10);
        assertEquals(steve.getHealth(), 70);
    }
}