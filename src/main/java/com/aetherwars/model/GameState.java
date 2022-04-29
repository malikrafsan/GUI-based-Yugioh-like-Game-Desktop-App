package com.aetherwars.model;
import com.aetherwars.interfaces.Hoverable;
import com.aetherwars.interfaces.*;

import java.util.Observable;

import static com.aetherwars.model.Phase.*;
import static com.aetherwars.model.Turn.*;

public class GameState extends Observable implements IRoundGetter, IPhaseGetter {
    private Player player1;
    private Player player2;
    private int round;
    private Phase phase;
    private Turn turn;
    private Hoverable hoveredObject;
    private ClickObject clickObject;
    private boolean hasPickCard;

    public GameState() {
        this.player1 = new Player();
        this.player2 = new Player();
        this.round = 1;
        this.phase = DRAW;
        this.turn = PLAYER1;
        this.clickObject = new ClickObject();
        this.hasPickCard = false;
    }

    public void sync() {
        setChanged();
        notifyObservers();
    }

    public void setHasPickCard(boolean hasPickCard) {
        this.hasPickCard = hasPickCard;
        setChanged();
        notifyObservers();
    }

    public boolean getHasPickCard() { return this.hasPickCard; }

    public void setHoverObject(Hoverable hoverable){
        this.hoveredObject = hoverable;
        setChanged();
        notifyObservers();
    }

    public Hoverable getHoverObject(){
        return hoveredObject;
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
                this.setHasPickCard(false);
                break;  
        }
        setChanged();
        notifyObservers();
    }

    public void nextTurn() {
        System.out.println("NEXT TURN");
        switch (this.turn) {
            case PLAYER1:
                this.turn = PLAYER2;
                this.player2.newRound();
                System.out.println("SYNC Player 2's turn");
                this.player2.getHand().sync();
                break;
            case PLAYER2:
                this.turn = PLAYER1;
                this.round = this.round + 1;
                this.player1.newRound();
                System.out.println("SYNC Player 1's turn");
                this.player1.getHand().sync();
                break;
        }
        this.sync();
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
        setChanged();
        notifyObservers();
    }

    public ClickObject getClickObject() { return clickObject; }

    public void setClickObject(int player, String name, int index) {
        clickObject.setClickObject(player, name, index);
        this.sync();
    }
}
