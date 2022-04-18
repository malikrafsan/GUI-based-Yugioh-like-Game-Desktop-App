package com.aetherwars.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javafx.scene.layout.HBox;


public class AppController {
    @FXML private HBox middleContainer;
    @FXML private HBox lowerContainer;
    @FXML private Pane statePane;
    @FXML private AnchorPane player1Board;
    @FXML private Pane turnContainer;
    @FXML private AnchorPane player2Board;
    @FXML private HBox upperContainer;

    @FXML private void initialize() throws Exception {
        FXMLLoader player1BoardLoader = new FXMLLoader(getClass().getResource("/view/Player1Board.fxml"));
        this.player1Board = player1BoardLoader.load();
        this.upperContainer.getChildren().add(this.player1Board);

        FXMLLoader turnContainerLoader = new FXMLLoader(getClass().getResource("/view/TurnContainer.fxml"));
        this.turnContainer = turnContainerLoader.load();
        this.upperContainer.getChildren().add(this.turnContainer);

        FXMLLoader player2BoardLoader = new FXMLLoader(getClass().getResource("/view/Player2Board.fxml"));
        this.player2Board = player2BoardLoader.load();
        this.upperContainer.getChildren().add(this.player2Board);

        FXMLLoader stateLoader = new FXMLLoader(getClass().getResource("/view/Phase.fxml"));
        this.statePane = stateLoader.load();
        this.middleContainer.getChildren().add(this.statePane);
    }
}
