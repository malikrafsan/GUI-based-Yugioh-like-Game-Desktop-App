package com.aetherwars.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.Observer;
import java.util.Observable;
import javafx.scene.layout.HBox;


public class AppController {
    @FXML private AnchorPane player1Board;
    @FXML private AnchorPane turnContainer;
    @FXML private AnchorPane player2Board;
    @FXML private HBox container;

    @FXML private void initialize() throws Exception {
        FXMLLoader player1BoardLoader = new FXMLLoader(getClass().getResource("/view/Player1Board.fxml"));
        this.player1Board = player1BoardLoader.load();
        this.container.getChildren().add(this.player1Board);

        FXMLLoader turnContainerLoader = new FXMLLoader(getClass().getResource("/view/TurnContainer.fxml"));
        this.turnContainer = turnContainerLoader.load();
        this.container.getChildren().add(this.turnContainer);

        FXMLLoader player2BoardLoader = new FXMLLoader(getClass().getResource("/view/Player2Board.fxml"));
        this.player2Board = player2BoardLoader.load();
        this.container.getChildren().add(this.player2Board);
    }
}
