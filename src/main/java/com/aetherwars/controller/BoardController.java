package com.aetherwars.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class BoardController {

    @FXML private TilePane turnPane;
    @FXML private Pane player1Pane;
    @FXML private Pane player2Pane;
    @FXML
    private Pane phasePane;
    @FXML
    private Pane handCardsPane;
    @FXML
    private Pane cardInfoContainer;

    private CardInfoController cardInfoController;
    private HandCardsContainerController handCardsContainerController;
    private PhaseController phaseController;

    @FXML private AnchorPane player1Board;
    @FXML private Pane turnContainer;
    @FXML private AnchorPane player2Board;

    @FXML
    private void initialize() throws Exception{
        FXMLLoader loaderCardInfo = new FXMLLoader(getClass().getResource("/view/CardInfo.fxml"));
        Pane cardInfo = loaderCardInfo.load();
        this.cardInfoController = loaderCardInfo.getController();
        this.cardInfoContainer.getChildren().add(cardInfo);

        FXMLLoader loaderCardContainer = new FXMLLoader(getClass().getResource("/view/HandCardsContainer.fxml"));
        Pane handCardsContainer = loaderCardContainer.load();
        this.handCardsContainerController = loaderCardContainer.getController();
        this.handCardsPane.getChildren().add(handCardsContainer);

        FXMLLoader loaderPhaseContainer = new FXMLLoader(getClass().getResource("/view/Phase.fxml"));
        Pane phaseContainer = loaderPhaseContainer.load();
        this.phaseController = loaderPhaseContainer.getController();
        this.phasePane.getChildren().add(phaseContainer);

        FXMLLoader player1BoardLoader = new FXMLLoader(getClass().getResource("/view/Player1Board.fxml"));
        this.player1Board = player1BoardLoader.load();
        this.player1Pane.getChildren().add(this.player1Board);

        FXMLLoader turnContainerLoader = new FXMLLoader(getClass().getResource("/view/TurnContainer.fxml"));
        this.turnContainer = turnContainerLoader.load();
        this.turnPane.getChildren().add(this.turnContainer);

        FXMLLoader player2BoardLoader = new FXMLLoader(getClass().getResource("/view/Player2Board.fxml"));
        this.player2Board = player2BoardLoader.load();
        this.player2Pane.getChildren().add(this.player2Board);
    }
}
