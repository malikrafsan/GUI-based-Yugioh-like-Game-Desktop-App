package com.aetherwars.controller;

import com.aetherwars.model.Card;
import com.aetherwars.model.Phase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;


public class AppController {
    @FXML private Pane gameOverContainer;
    @FXML private Pane selectCardContainer;
    @FXML private Pane boardContainer;

    private Pane boardPane;
    private Pane selectCardPane;
    private Pane gameOverPane;
    private BoardController boardController;
    private SelectCardController selectCardController;
    private GameOverController gameOverController;

    /**
     * Melakukan inisiasi GUI
     */
    @FXML private void initialize() throws Exception {
        FXMLLoader boardLoader = new FXMLLoader(getClass().getResource("/view/Board.fxml"));
        this.boardPane = boardLoader.load();
        this.boardController = boardLoader.getController();
        this.boardContainer.getChildren().add(this.boardPane);

        FXMLLoader selectCardLoader = new FXMLLoader(getClass().getResource("/view/SelectCard.fxml"));
        this.selectCardPane = selectCardLoader.load();
        this.selectCardController = selectCardLoader.getController();
        this.selectCardContainer.getChildren().add(this.selectCardPane);

        FXMLLoader gameOverLoader = new FXMLLoader(getClass().getResource("/view/EndGame.fxml"));
        this.gameOverPane = gameOverLoader.load();
        this.gameOverController = gameOverLoader.getController();
        this.gameOverContainer.getChildren().add(this.gameOverPane);
        this.gameOverContainer.setMouseTransparent(true);

//        this.selectCardController.setAppController(this);
//        this.boardController.phaseController.setAppController(this);
//        this.boardController.handCardsContainerController.setAppController(this);
//        drawPhase();
    }
}
