package com.aetherwars.controller;

import com.aetherwars.model.Card;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.Pane;


public class AppController {
    @FXML private Pane selectCardContainer;
    @FXML private Pane boardContainer;
    private Pane boardPane;
    private Pane selectCardPane;
    private BoardController boardController;
    private SelectCardController selectCardController;

    @FXML private void initialize() throws Exception {
        FXMLLoader boardLoader = new FXMLLoader(getClass().getResource("/view/Board.fxml"));
        this.boardPane = boardLoader.load();
        this.boardController = boardLoader.getController();
        this.boardContainer.getChildren().add(this.boardPane);

        FXMLLoader selectCardLoader = new FXMLLoader(getClass().getResource("/view/SelectCard.fxml"));
        this.selectCardPane = selectCardLoader.load();
        this.selectCardController = selectCardLoader.getController();
        this.selectCardContainer.getChildren().add(this.selectCardPane);

        this.selectCardController.setAppController(this);
        this.boardController.phaseController.setAppController(this);
        this.boardController.handCardsContainerController.setAppController(this);
        drawPhase();
    }

    public void drawPhase(){
        this.boardPane.setEffect(new GaussianBlur());
        this.boardPane.setDisable(true);
        this.selectCardPane.setVisible(true);
        this.selectCardPane.setDisable(false);
        this.selectCardPane.setMouseTransparent(false);
        this.selectCardContainer.setMouseTransparent(false);
    }

    public void nonDrawPhase(){
        this.boardPane.setDisable(false);
        this.selectCardPane.setDisable(true);
        this.selectCardPane.setVisible(false);
        this.boardPane.setEffect(null);
        this.selectCardPane.setMouseTransparent(true);
        this.selectCardContainer.setMouseTransparent(true);
    }

    public void nextPhase(){
        this.nonDrawPhase();
        this.boardController.phaseController.setNextPhase();
    }

    public void setCardInfo(Card card){
        this.boardController.cardInfoController.setCardInfo(card);
    }

    public void unsetCardInfo(){
        this.boardController.cardInfoController.unsetCardInfo();
    }
}
