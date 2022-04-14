package com.aetherwars.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class TurnContainerController {
    @FXML private Text turnCounter;

    @FXML private void initialize() {
        turnCounter.setText("Turn\n1");
    }
}
