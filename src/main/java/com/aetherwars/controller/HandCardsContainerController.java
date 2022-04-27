package com.aetherwars.controller;

import com.aetherwars.model.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class HandCardsContainerController {

    @FXML
    private HBox cardContainer;
    private HandCardController[] handCardControllers = new HandCardController[5];
    @FXML
    private Pane[] handCards = new Pane[5];
    private Integer currentActiveCardCount;
    private AppController appController;


    @FXML
    private void initialize(){
        try {
            for (int i=0; i<5; i++){
                FXMLLoader loaderHandCard = new FXMLLoader(getClass().getResource("/view/HandCard.fxml"));
                this.handCards[i] = loaderHandCard.load();
                this.handCardControllers[i] = loaderHandCard.getController();
                this.cardContainer.getChildren().add(this.handCards[i]);
            }
            this.currentActiveCardCount = 5;
        } catch (Exception e) {
            System.out.println(e);
        }
        testUpdateHandCards();
        enableMouseHover();
//        disableMouseHover();
    }

    public void enableMouseHover(){
        for (int i = 0; i < this.currentActiveCardCount; i++){
            int finalI = i;
            this.handCards[i].setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Background bg = new Background(new BackgroundFill(Color.MEDIUMVIOLETRED, null, null));
                    handCards[finalI].setBackground(bg);
                    System.out.println("HAND CARD " + finalI + " IS HOVERED");
                    handleHoverCard(finalI);

                }
            });
            this.handCards[i].setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Background bg = new Background(new BackgroundFill(null, null, null));
                    handCards[finalI].setBackground(bg);
                    System.out.println("HAND CARD " + finalI + " NO LONGER LONGER HOVERED");
                    appController.unsetCardInfo();
                }
            });
        }
    }

    public void handleHoverCard(int idx){
        appController.setCardInfo(this.cards.get(idx));
    }


    public void disableMouseHover(){
        for (int i = 0; i < this.currentActiveCardCount; i++){
            int finalI = i;
            this.handCards[i].setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {}
            });
            this.handCards[i].setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {}
            });
        }
    }

    private ArrayList<Card> cards = new ArrayList<>();
    private void testUpdateHandCards(){
        cards.add(new CharacterCard(1, "Shulker", CharType.END, "Shulkers are box-shaped hostile mobs found in end cities.", "/com/aetherwars/card/image/character/Shulker.png", 10, 5, 3, 4, 3));
        cards.add(new CharacterCard(2, "Zombie", CharType.OVERWORLD, "Zombies are common undead hostile mobs that deal melee damage and attack in groups.", "/com/aetherwars/card/image/character/Zombie.png", 8, 4, 1, 4, 3));
        cards.add(new SpellMorphCard(2, "Sugondese", "Nuts", "/com/aetherwars/card/image/spell/morph/Sugondese.png", 7, 2));
        cards.add(new SpellPotionCard(1, "Sadikin Elixir", "The best elixir in the world", "/com/aetherwars/card/image/spell/potion/Sadikin Elixir.png", 3, 5, 1, 5));
        //cards.add(new CharacterCard(1, "Shulker", CharType.END, "...", "card/data/image/character/Shulker.png", 10, 5, 2, 4, 3));
        updateHandCards(cards);
    }


    public void updateHandCards(List<Card> listCards){
        for (int i = 0; i < this.currentActiveCardCount; i++){
            this.cardContainer.getChildren().remove(0);
        }

        this.currentActiveCardCount = Math.min(listCards.size(), 5);
        for (int i = 0; i < this.currentActiveCardCount; i++){
            this.cardContainer.getChildren().add(this.handCards[i]);
            this.handCardControllers[i].setLabelMana(listCards.get(i).getMana());
            this.handCardControllers[i].setLabelAttr(listCards.get(i).preview());
            this.handCardControllers[i].setCardImageView(listCards.get(i).getImagePath());
        }
    }

    public void setAppController(AppController appController){
        this.appController = appController;
    }
}
