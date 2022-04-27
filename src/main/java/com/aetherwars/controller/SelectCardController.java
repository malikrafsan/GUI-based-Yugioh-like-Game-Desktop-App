package com.aetherwars.controller;

import com.aetherwars.model.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class SelectCardController {

    @FXML private HBox cardContainer;
    @FXML private Label playerLabel;
    private Pane[] cardsToSelect = new Pane[3];
    private HandCardController[] cardController = new HandCardController[3];
    private AppController appController;
    private Integer currentSelectCardCount;

    @FXML
    public void initialize(){
        try {
            for (int i=0; i<3; i++){
                FXMLLoader loaderHandCard = new FXMLLoader(getClass().getResource("/view/HandCard.fxml"));
                this.cardsToSelect[i] = loaderHandCard.load();
                this.cardController[i] = loaderHandCard.getController();
                this.cardContainer.getChildren().add(this.cardsToSelect[i]);
                int finalI = i + 1;
                this.cardsToSelect[i].setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Background bg = new Background(new BackgroundFill(Color.MEDIUMVIOLETRED, null, null));
                        cardsToSelect[finalI - 1].setBackground(bg);
                        System.out.println("CARD " + finalI + " IS HOVERED");
                    }
                });
                this.cardsToSelect[i].setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Background bg = new Background(new BackgroundFill(null, null, null));
                        cardsToSelect[finalI - 1].setBackground(bg);
                        System.out.println("CARD " + finalI + " IS NO LONGER LONGER HOVERED");
                    }
                });
                this.cardsToSelect[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        System.out.println("CARD " + finalI + " IS CLICKED");
                        appController.nextPhase();
                    }
                });
            }
            this.currentSelectCardCount = 3;
        } catch (Exception e) {
            System.out.println(e);
        }
        testUpdateHandCards();
        setPlayer(1);
    }

    public void setAppController(AppController appController){
        this.appController = appController;
    }


    public void setPlayer(Integer playerNo){
        this.playerLabel.setText("PLAYER " + playerNo.toString());
    }

    private void testUpdateHandCards(){
        ArrayList<Card> cards = new ArrayList<>();
        //cards.add(new CharacterCard(1, "Shulker", CharType.END, "...", "/com/aetherwars/card/image/character/Shulker.png", 10, 5, 3, 4, 3));
        //cards.add(new CharacterCard(2, "Zombie", CharType.OVERWORLD, "...", "/com/aetherwars/card/image/character/Zombie.png", 8, 4, 1, 4, 3));
        cards.add(new SpellMorphCard(2, "Sugondese", "...", "/com/aetherwars/card/image/spell/morph/Sugondese.png", 7, 2));
        cards.add(new SpellPotionCard(1, "Sadikin Elixir", "...", "/com/aetherwars/card/image/spell/potion/Sadikin Elixir.png", 3, 5, 1, 5));
        //cards.add(new CharacterCard(1, "Shulker", CharType.END, "...", "card/data/image/character/Shulker.png", 10, 5, 2, 4, 3));
        updateSelectCards(cards);
    }


    public void updateSelectCards(List<Card> listCards){
        for (int i = 0; i < this.currentSelectCardCount; i++){
            this.cardContainer.getChildren().remove(0);
        }

        this.currentSelectCardCount = Math.min(listCards.size(), 3);
        for (int i = 0; i < this.currentSelectCardCount; i++){
            this.cardContainer.getChildren().add(this.cardsToSelect[i]);
            this.cardController[i].setLabelMana(listCards.get(i).getMana());
            this.cardController[i].setLabelAttr(listCards.get(i).preview());
            this.cardController[i].setCardImageView(listCards.get(i).getImagePath());
        }
    }

}
