package com.aetherwars.Test;

import com.aetherwars.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {
    GameState gs = new GameState();

    @Test
    void getRound() {
        assertEquals(gs.getRound(), 1);
    }

    @Test
    void getTurn() {
        assertEquals(gs.getTurn(), Turn.PLAYER1);
    }

    @Test
    void getPhase() {
        assertEquals(gs.getPhase(), Phase.DRAW);
    }
}