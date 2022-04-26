package com.aetherwars.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class SelectCardController {

    @FXML private HBox cardContainer;
    @FXML private Label playerLabel;
    private Pane[] cardToSelect = new Pane[3];
    private HandCardController[] cardController = new HandCardController[3];
    private AppController appController;

    @FXML
    public void initialize(){
        try {
            for (int i=0; i<3; i++){
                FXMLLoader loaderHandCard = new FXMLLoader(getClass().getResource("/view/HandCard.fxml"));
                this.cardToSelect[i] = loaderHandCard.load();
                this.cardController[i] = loaderHandCard.getController();
                this.cardContainer.getChildren().add(this.cardToSelect[i]);
                int finalI = i + 1;
                this.cardToSelect[i].setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        System.out.println("CARD " + finalI + " IS HOVERED");
                    }
                });
                this.cardToSelect[i].setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        System.out.println("CARD " + finalI + " IS NO LONGER LONGER HOVERED");
                    }
                });
                this.cardToSelect[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        System.out.println("CARD " + finalI + " IS CLICKED");
                        appController.nextPhase();
                    }
                });
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void setAppController(AppController appController){
        this.appController = appController;
    }


    public void setPlayer(Integer playerNo){
        this.playerLabel.setText("PLAYER " + playerNo.toString());
    }
}
