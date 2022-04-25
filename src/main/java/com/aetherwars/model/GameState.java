package com.aetherwars.model;
import java.util.Observable;

public class GameState extends Observable{
    private Player player1;
    private Player player2;
    private int round;
    private Phase phase;
    private Turn turn;
    

    public GameState() {
        this.player1 = new Player();
        this.player2 = new Player();
        this.round = 1;
        this.Phase = DRAW;
        this.turn = PLAYER1
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
                this.player2.newTurn();
                break;
            case PLAYER2:
                this.turn = PLAYER1;
                this.round = this.round + 1;
                this.player1.newTurn();
                break;
        }
    }

}