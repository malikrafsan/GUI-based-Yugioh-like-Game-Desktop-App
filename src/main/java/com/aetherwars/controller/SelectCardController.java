package com.aetherwars.controller;

import com.aetherwars.interfaces.IPhaseGetter;
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

import java.util.*;

import com.aetherwars.interfaces.*;
import com.aetherwars.event.PickCardEvent;

import static com.aetherwars.model.Phase.*;

public class SelectCardController implements Observer, ISubscriber {
    @FXML private Pane pane;
    @FXML private HBox cardContainer;
    @FXML private Label playerLabel;
    private Pane[] cardsToSelect = new Pane[3];
    private HandCardController[] cardController = new HandCardController[3];
    private Integer currentSelectCardCount;
    private final Background hoverBackground = new Background(new BackgroundFill(Color.LIGHTCORAL, null, null));
    private final Background normalBackground = new Background(new BackgroundFill(null, null, null));

    @FXML
    public void initialize() {
        GameManager.getInstance().getEventBroker().addSubscriber("PICKCARD", this);
        GameManager.getInstance().addObserver("GAMESTATE", this);

        try {
            SelectCardController THIS = this;
            for (int i=0; i<3; i++){
                FXMLLoader loaderHandCard = new FXMLLoader(getClass().getResource("/view/HandCard.fxml"));
                this.cardsToSelect[i] = loaderHandCard.load();
                this.cardController[i] = loaderHandCard.getController();
                this.cardContainer.getChildren().add(this.cardsToSelect[i]);
                int finalI = i + 1;
                this.cardsToSelect[i].setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        cardsToSelect[finalI - 1].setBackground(hoverBackground);
                        System.out.println("CARD " + finalI + " IS HOVERED");
                    }
                });
                this.cardsToSelect[i].setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        cardsToSelect[finalI - 1].setBackground(normalBackground);
                        System.out.println("CARD " + finalI + " IS NO LONGER LONGER HOVERED");
                    }
                });
                this.cardsToSelect[i].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        System.out.println("CARD " + finalI + " IS CLICKED");
                        // TODO : CALL GAME MANAGER FOR PICKING CARD
                        GameManager.getInstance().clickPickCard(finalI);
                    }
                });
            }
            this.currentSelectCardCount = 3;
        } catch (Exception e) {
            System.out.println("SELECT CARD CONTROLLER ERROR");
            System.out.println(e);
        }
        // testUpdateHandCards();
        setPlayer(1);
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


    public void updateSelectCards(List<Card> listCards) {
        for (int i = 0; i < this.currentSelectCardCount; i++) {
            this.cardContainer.getChildren().remove(0);
        }

        this.currentSelectCardCount = Math.min(listCards.size(), 3);
        for (int i = 0; i < this.currentSelectCardCount; i++) {
            this.cardContainer.getChildren().add(this.cardsToSelect[i]);
            this.cardController[i].setLabelMana(listCards.get(i).getMana());
            this.cardController[i].setLabelAttr(listCards.get(i).preview());
            this.cardController[i].setCardImageView(listCards.get(i).getImagePath());
        }
    }

    public void onEvent(IEvent event) {
        if (event instanceof PickCardEvent) {
            PickCardEvent pickCardEvent = (PickCardEvent) event;
            updateSelectCards(pickCardEvent.getData());
        }
    }
    public void update(Observable obs, Object obj) {
        if (obs instanceof IPhaseGetter) {
            IPhaseGetter gs = (IPhaseGetter) obs;
            if (gs.getPhase() == DRAW && !gs.getHasPickCard()) {
                System.out.println("HERE");
                drawPhaseSelectCard();
            } else {
                nonDrawPhaseSelectCard();
            }
        }
    }

    private void drawPhaseSelectCard() {
        this.pane.setVisible(true);
        this.pane.setDisable(false);
        this.pane.setMouseTransparent(false);
        this.pane.getParent().setMouseTransparent(false);
    }

    private void nonDrawPhaseSelectCard() {
        this.pane.setDisable(true);
        this.pane.setVisible(false);
//        this.boardPane.setEffect(null);
        this.pane.setMouseTransparent(true);
        this.pane.getParent().setMouseTransparent(true);
    }
}

class MockGameStateSelectCard extends Observable implements IPhaseGetter {
    private Phase phase;
    private boolean hasPickCard;

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

class MockDeck implements IPublisher {
    List<Card> deck;
//    private EventBroker eb;

    public MockDeck() {
        this.deck = new ArrayList<Card>();
//        this.eb = GameManager.getInstance().getEventBroker();
    }

    public void pickCard() {
        this.publish("PICKCARD", new PickCardEvent(this.getThreeCard()));
    }

    public void publish(String topic, IEvent event) {
        GameManager.getInstance().getEventBroker().sendEvent(topic, event);
    }

    private List<Card> getThreeCard() {
        List<Card> lst = new ArrayList<>();

        Random rand = new Random();
        CharType[] types = new CharType[] {CharType.END, CharType.OVERWORLD, CharType.NETHER};
        String[] imgPaths = new String[]{"/com/aetherwars/card/image/spell/morph/Sugondese.png", "/com/aetherwars/card/image/spell/potion/Sadikin Elixir.png", "/com/aetherwars/card/image/character/Shulker.png", "/com/aetherwars/card/image/character/Zombie.png", "card/data/image/character/Shulker.png"};

        for (int i=0;i<3;i++) {
            if (rand.nextDouble() > 0.5) {
                lst.add(new CharacterCard(rand.nextInt(10), "INI NAMA" + rand.nextInt(10), types[rand.nextInt(types.length)], "...", imgPaths[rand.nextInt(imgPaths.length)], rand.nextInt(10), rand.nextInt(10), rand.nextInt(10), rand.nextInt(10), rand.nextInt(10)));
            } else {
                if (rand.nextDouble() > 0.5) {
                    lst.add(new SpellMorphCard(2, "Sugondese", "...", "/com/aetherwars/card/image/spell/morph/Sugondese.png", 7, 2));
                }else {
                    lst.add(new SpellPotionCard(1, "Sadikin Elixir", "...", "/com/aetherwars/card/image/spell/potion/Sadikin Elixir.png", 3, 5, 1, 5));
                }
            }
        }

        return lst;
    }
}