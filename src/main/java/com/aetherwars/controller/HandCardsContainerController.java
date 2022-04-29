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

import java.util.*;

public class HandCardsContainerController implements Observer {

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
    private void initialize() {
        GameManager.getInstance().addObserver("HANDCARD1", this);
        GameManager.getInstance().addObserver("HANDCARD2", this);
        GameManager.getInstance().addObserver("CLICKOBJECT", this);

        try {
            for (int i=0; i<5; i++){
                FXMLLoader loaderHandCard = new FXMLLoader(getClass().getResource("/view/HandCard.fxml"));
                this.handCards[i] = loaderHandCard.load();
                this.handCardControllers[i] = loaderHandCard.getController();
//                this.cardContainer.getChildren().add(this.handCards[i]);
            }
            this.currentActiveCardCount = 0;
        } catch (Exception e) {
            System.out.println("HAND CARD CONTAINER CONTROLLER ERROR");
            System.out.println(e);
        }
    }

    /**
     * @param idx index kartu yang diset
     * Mengaktifkan mouse event pada kartu ke-idx
     */
    public void enableMouseEvent(int idx) {
        this.handCards[idx].setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handCards[idx].setBackground(hoverBackground);

                GameManager.getInstance().hoverHand(idx);
            }
        });
        this.handCards[idx].setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handCards[idx].setBackground(normalBackground);

                GameManager.getInstance().unhover();
            }
        });
        this.handCards[idx].setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                GameManager.getInstance().click(-1, "HANDCARD", idx);
            }
        });
    }
    /**
     * @param idx index kartu yang diset
     * Menonaktifkan mouse event pada kartu ke-idx
     */
    public void disableMouseEvent(int idx) {
        this.handCards[idx].setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {}
        });
        this.handCards[idx].setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {}
        });
        this.handCards[idx].setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {}
        });
    }

    /**
     * @param idx index kartu yang diset
     * Mengaktifkan efek klik pada kartu ke-idx
     */
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

    private void testUpdateHandCards() {
        Card[] cards = new Card[5];

        cards[0] = new CharacterCard(1, "Shulker", CharType.END, "Shulkers are box-shaped hostile mobs found in end cities.", "card/image/character/Shulker.png", 10, 5, 3, 4, 3);
        cards[1] = new CharacterCard(2, "Zombie", CharType.OVERWORLD, "Zombies are common undead hostile mobs that deal melee damage and attack in groups.", "card/image/character/Zombie.png", 8, 4, 1, 4, 3);
        cards[2] = new SpellMorphCard(2, "Sugondese", "Nuts", "card/image/spell/morph/Sugondese.png", 7, 2);
        cards[3] = new SpellPotionCard(1, "Sadikin Elixir", "The best elixir in the world",
                "card/image/spell/potion/Sadikin Elixir.png", 3, 5, 1, 5);
        cards[4] = null;
        //cards.add(new CharacterCard(1, "Shulker", CharType.END, "...", "card/data/image/character/Shulker.png", 10, 5, 2, 4, 3));
        updateHandCards(cards);
    }

    /**
     * @param listCards kartu yang dipakai
     * Memperbarui tampilan handCard sesuai listCards
     */
    public void updateHandCards(Card[] listCards){
        for (int i = 0; i < this.currentActiveCardCount; i++){
            this.cardContainer.getChildren().remove(0);
        }

        for (int i = 0; i < 5; i++){
            this.handCardControllers[i].fadeOut();
        }

        this.currentActiveCardCount = Math.min(listCards.length, 5);
        for (int i = 0; i < this.currentActiveCardCount; i++){
            this.cardContainer.getChildren().add(this.handCards[i]);

            if (listCards[i] != null) {
                this.handCardControllers[i].setLabelMana(listCards[i].getMana());
                this.handCardControllers[i].setLabelAttr(listCards[i].preview());
                this.handCardControllers[i].setCardImageView(listCards[i].getImagePath());

                System.out.println("NOT NULL");
                this.enableMouseEvent(i);
            } else {
                System.out.println("NULL");
                this.handCardControllers[i].unsetLabelMana();
                this.handCardControllers[i].unsetLabelAttr();
                this.handCardControllers[i].unsetCardImageView();

                this.disableMouseEvent(i);
            }
            this.handCardControllers[i].fadeIn();
        }
    }

    public void update(Observable obs, Object obj) {
        if (obs instanceof ClickObject) {
            ClickObject co = (ClickObject) obs;
            
            unsetAllCardClickEffect();
            if (co.getName().equals("HANDCARD")) {
                setCardClickEffect(co.getIndex());
            }
        } else if (obs instanceof HandCard) {
            HandCard hc = (HandCard) obs;

            System.out.println("\n\nHAND CARD IS UPDATED");
            for (int i = 0; i < hc.getCards().length; i++) {
                System.out.println(hc.getCards()[i]);
                System.out.println("--------------------");
            }
            System.out.println("======================\n\n");
            updateHandCards(hc.getCards());
        }
    }
}
