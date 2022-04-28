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
import java.util.Observable;

public class HandCardsContainerController {

    @FXML
    private HBox cardContainer;
    private HandCardController[] handCardControllers = new HandCardController[5];
    @FXML
    private Pane[] handCards = new Pane[5];
    private Integer currentActiveCardCount;
    private AppController appController;

    private final Background hoverBackground = new Background(new BackgroundFill(Color.LIGHTCORAL, null, null));
    private final Background normalBackground = new Background(new BackgroundFill(null, null, null));

    private final String inactiveCardStyle = "-fx-border-color: black; -fx-border-width: 1px";
    private final String activeCardStyle = "-fx-border-color: red; -fx-border-width: 3px";


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
                    handCards[finalI].setBackground(hoverBackground);
                    System.out.println("HAND CARD " + finalI + " IS HOVERED");

                }
            });
            this.handCards[i].setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    handCards[finalI].setBackground(normalBackground);
                    System.out.println("HAND CARD " + finalI + " NO LONGER LONGER HOVERED");
                }
            });
            this.handCards[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    for (int j = 0; j < 5; j++){
                        if (finalI == j){
                            setCardClickEffect(j);
                        }
                        else {
                            unsetCardClickEffect(j);
                        }
                    }
                }
            });
        }
    }

    private void setCardClickEffect(int idx){
        handCards[idx].styleProperty().setValue(activeCardStyle);
    }

    private void unsetCardClickEffect(int idx){
        handCards[idx].styleProperty().setValue(inactiveCardStyle);
    }

    private void unsetAllCardClickEffect(){
        for (int i = 0; i < 5; i++){
            unsetCardClickEffect(i);
        }
    }


    public void disableMouseHover(){
        for (int i = 0; i < this.currentActiveCardCount; i++){
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

//    public void setAppController(AppController appController){
//        this.appController = appController;
//    }

    public void setClickEffect(int idx){

    }


    public void update(Observable obs, Object obj) {

//        if (obs instanceof ClickObject) {
//            this.unsetAllCardClickEffect();
//            ClickObject c = (ClickObject) obs;
//            if (c.getType().equal("HANDCARD") && c.getPlayerNo() == this.playerNo) {
//                this.setClickEffect(c.getIdx());
//            }
//        }
    }
}
