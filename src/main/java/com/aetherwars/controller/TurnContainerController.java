package com.aetherwars.controller;

import com.aetherwars.interfaces.IRoundGetter;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.Random;

public class TurnContainerController {
    @FXML private Text turnCounter;

    @FXML private void initialize() {

        this.turnCounter.setText("Turn:\n");

        // TODO: delete later and
        this.updateTurn(this.mockRoundGameState());
        TurnContainerController t = this;
        this.turnCounter.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                t.updateTurn(t.mockRoundGameState());
            }
        });
    }

    private void updateTurn(IRoundGetter irg) {
        this.turnCounter.setText("Turn:\n" + Integer.toString(irg.getRound()));
    }

    private MockRoundGameState mockRoundGameState() {
        return new MockRoundGameState(new Random().nextInt(10));
    }
}

class MockRoundGameState implements IRoundGetter {
    private int round;

    public MockRoundGameState(int round) {
        this.round = round;
    }

    public int getRound() {
        return this.round;
    }
}