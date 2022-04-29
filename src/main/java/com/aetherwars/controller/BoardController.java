package com.aetherwars.controller;

import com.aetherwars.interfaces.IPhaseGetter;
import com.aetherwars.model.Phase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Lighting;

import java.util.*;

import com.aetherwars.model.*;
import static com.aetherwars.model.Phase.*;
import static com.aetherwars.model.Phase.END;

public class BoardController implements Observer {

    public Label manaValueLabel;
    @FXML private Label deckValueLabel;
    @FXML private TilePane turnPane;
    @FXML private Pane player1Pane;
    @FXML private Pane player2Pane;
    @FXML
    protected Pane phasePane;
    @FXML
    protected Pane handCardsPane;
    @FXML
    protected Pane cardInfoContainer;

    protected CardInfoController cardInfoController;
    protected HandCardsContainerController handCardsContainerController;
    protected PhaseController phaseController;

    @FXML protected AnchorPane player1Board;
    @FXML protected Pane turnContainer;
    @FXML protected AnchorPane player2Board;
    @FXML private Button addExpBtn;
    @FXML private Button deleteBtn;
    @FXML private Pane boardPane;

    @FXML
    private void initialize() throws Exception{
        GameManager.getInstance().addObserver("GAMESTATE", this);
        GameManager.getInstance().addObserver("DECK1", this);
        GameManager.getInstance().addObserver("DECK2", this);
        GameManager.getInstance().addObserver("PLAYER1", this);
        GameManager.getInstance().addObserver("PLAYER2", this);

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

        // this.deckValueLabel.setText("40/40");
        // this.manaValueLabel.setText("2/2");

        this.addExpBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("ADD EXP BUTTON CLICKED");

                GameManager.getInstance().click(-1, "ADDEXP", -1);
            }
        });

        this.deleteBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("DELETE BTN CLICKED");

                GameManager.getInstance().click(-1, "DELETE", -1);
            }
        });
    }

    private void drawPhaseBoard() {
        this.boardPane.setEffect(new GaussianBlur());
        this.boardPane.setDisable(true);
    }

    private void nonDrawPhaseBoard() {
        this.boardPane.setDisable(false);
        this.boardPane.setEffect(null);
    }

    public void update(Observable obs, Object obj) {
        if (obs instanceof IPhaseGetter) {
            IPhaseGetter gs = (IPhaseGetter) obs;
            if (gs.getPhase() == DRAW && !gs.getHasPickCard()) {
                drawPhaseBoard();
            } else {
                nonDrawPhaseBoard();
            }
        } else if (obs instanceof Deck) {
            Deck deck = (Deck) obs;
            this.deckValueLabel.setText(deck.getSize() + "/" + deck.getCapacity());
        } else if (obs instanceof Player) {
            Player player = (Player) obs;

            // TODO: CHANGE LATER MAX MANA
            this.manaValueLabel.setText(player.getMana() + "/" + player.getMana());
        }
    }
}

class MockGameStateBoard extends Observable implements IPhaseGetter {
    private Phase phase;
    private boolean hasPickCard = false;

    public Phase getPhase() { return this.phase; }

    public void setPhase(Phase newPhase) {
        this.phase = newPhase;
        setChanged();
        notifyObservers();
    }

    public boolean getHasPickCard() {
        return this.hasPickCard;
    }
}