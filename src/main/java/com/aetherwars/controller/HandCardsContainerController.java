package com.aetherwars.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class HandCardsContainerController {

    @FXML
    private HBox cardContainer;
    private HandCardController[] handCardController = new HandCardController[5];
    @FXML
    private Pane[] handCard = new Pane[5];

    @FXML
    private void initialize(){
        try {
            for (int i=0; i<5; i++){
                FXMLLoader loaderHandCard = new FXMLLoader(getClass().getResource("/view/HandCard.fxml"));
                this.handCard[i] = loaderHandCard.load();
                this.handCardController[i] = loaderHandCard.getController();
                this.cardContainer.getChildren().add(this.handCard[i]);
                int finalI = i;
                this.handCard[i].setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        System.out.println("HAND CARD " + finalI + " IS HOVERED");
                    }
                });
                this.handCard[i].setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        System.out.println("HAND CARD " + finalI + " NO LONGER LONGER HOVERED");
                    }
                });
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
