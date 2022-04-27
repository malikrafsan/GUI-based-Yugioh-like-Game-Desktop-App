package com.aetherwars.model;
import com.aetherwars.interfaces.Hoverable;
import com.aetherwars.interfaces.IRoundGetter;

import java.util.Observable;

import static com.aetherwars.model.Phase.*;
import static com.aetherwars.model.Turn.*;

public class GameState extends Observable implements IRoundGetter{
    private Player player1;
    private Player player2;
    private int round;
    private Phase phase;
    private Turn turn;
    private Hoverable hoveredObject;
    

    public GameState() {
        this.player1 = new Player();
        this.player2 = new Player();
        this.round = 1;
        this.phase = DRAW;
        this.turn = PLAYER1;
    }

    public int getRound() {
        return (this.round);
    }

    public void nextPhase() {
        switch (this.phase) {
            case DRAW:
                this.phase = PLANNING;
                break;
            case PLANNING:
                this.phase = ATTACK;
                break;
            case ATTACK:
                this.phase = END;
                break;
            case END:
                this.phase = DRAW;
                this.nextTurn();
                break;  
        }
    }

    public void nextTurn() {
        switch (this.turn) {
            case PLAYER1:
                this.turn = PLAYER2;
                this.player2.newRound();
                break;
            case PLAYER2:
                this.turn = PLAYER1;
                this.round = this.round + 1;
                this.player1.newRound();
                break;
        }
    }

    public Player getPlayer1() {
        return this.player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Turn getTurn() {
        return turn;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setHoveredObject(Hoverable hoveredObject) {
        this.hoveredObject = hoveredObject;
    }
}
